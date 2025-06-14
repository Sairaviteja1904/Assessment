import pytest
from config.config import Config
from utils.test_data_generator import TestDataGenerator

@pytest.mark.repositories
@pytest.mark.regression
class TestRepositories:
    """Test GitHub API repository operations"""
    
    @pytest.mark.smoke
    def test_create_public_repository(self, api_client, cleanup_repositories):
        """Test creating a public repository"""
        repo_data = TestDataGenerator.generate_repository_data(private=False)
        cleanup_repositories(repo_data["name"])
        
        response = api_client.post("/user/repos", repo_data)
        
        assert response.status_code == 201
        created_repo = response.json()
        
        assert created_repo["name"] == repo_data["name"]
        assert created_repo["description"] == repo_data["description"]
        assert created_repo["private"] == repo_data["private"]
        assert created_repo["owner"]["login"] == Config.TEST_USERNAME
    
    def test_create_private_repository(self, api_client, cleanup_repositories):
        """Test creating a private repository"""
        repo_data = TestDataGenerator.generate_repository_data(private=True)
        cleanup_repositories(repo_data["name"])
        
        response = api_client.post("/user/repos", repo_data)
        
        assert response.status_code == 201
        created_repo = response.json()
        
        assert created_repo["name"] == repo_data["name"]
        assert created_repo["private"] == repo_data["private"]
    
    def test_get_repository_details(self, api_client, test_repository):
        """Test fetching repository details"""
        repo_name = test_repository["name"]
        
        response = api_client.get(f"/repos/{Config.TEST_USERNAME}/{repo_name}")
        
        assert response.status_code == 200
        repo_details = response.json()
        
        assert repo_details["name"] == repo_name
        assert repo_details["owner"]["login"] == Config.TEST_USERNAME
        assert "created_at" in repo_details
        assert "updated_at" in repo_details
    
    def test_list_user_repositories(self, api_client):
        """Test listing user repositories"""
        response = api_client.get("/user/repos")
        
        assert response.status_code == 200
        repositories = response.json()
        
        assert isinstance(repositories, list)
        if repositories:  
            for repo in repositories[:5]:  
                assert "name" in repo
                assert "owner" in repo
                assert "private" in repo
    
    def test_update_repository(self, api_client, test_repository):
        """Test updating repository details"""
        repo_name = test_repository["name"]
        update_data = {
            "description": "Updated description for testing",
            "has_wiki": False,
            "has_projects": False
        }
        
        response = api_client.patch(f"/repos/{Config.TEST_USERNAME}/{repo_name}", update_data)
        
        assert response.status_code == 200
        updated_repo = response.json()
        
        assert updated_repo["description"] == update_data["description"]
        assert updated_repo["has_wiki"] == update_data["has_wiki"]
        assert updated_repo["has_projects"] == update_data["has_projects"]
    
    def test_delete_repository(self, api_client):
        """Test deleting a repository"""
        repo_data = TestDataGenerator.generate_repository_data()
        create_response = api_client.post("/user/repos", repo_data)
        assert create_response.status_code == 201
        
        repo_name = repo_data["name"]
        
        delete_response = api_client.delete(f"/repos/{Config.TEST_USERNAME}/{repo_name}")
        
        assert delete_response.status_code == 204
        
        get_response = api_client.get(f"/repos/{Config.TEST_USERNAME}/{repo_name}")
        assert get_response.status_code == 404
    
    @pytest.mark.negative
    def test_create_repository_with_invalid_name(self, api_client):
        """Test creating repository with invalid name"""
        invalid_repo_data = {
            "name": "", 
            "description": "Test repository with invalid name"
        }
        
        response = api_client.post("/user/repos", invalid_repo_data)
        
        assert response.status_code == 422
        assert "errors" in response.json()
    
    @pytest.mark.negative
    def test_create_duplicate_repository(self, api_client, test_repository):
        """Test creating repository with duplicate name"""
        duplicate_data = {
            "name": test_repository["name"],
            "description": "Duplicate repository"
        }
        
        response = api_client.post("/user/repos", duplicate_data)
        
        assert response.status_code == 422
        error_response = response.json()
        assert "errors" in error_response
    
    @pytest.mark.negative
    def test_get_nonexistent_repository(self, api_client):
        """Test fetching details of non-existent repository"""
        nonexistent_repo = "nonexistent-repo-12345"
        
        response = api_client.get(f"/repos/{Config.TEST_USERNAME}/{nonexistent_repo}")
        
        assert response.status_code == 404
        assert "Not Found" in response.json().get("message", "")
    
    @pytest.mark.negative
    def test_access_private_repository_unauthorized(self):
        """Test accessing private repository without proper authorization"""

        from utils.api_client import GitHubAPIClient
        unauth_client = GitHubAPIClient()
        unauth_client.headers.pop("Authorization", None)
        unauth_client.session.headers.pop("Authorization", None)
        
        response = unauth_client.get("/repos/octocat/Hello-World-Template")
        
        assert response.status_code in [401, 404]
    
    def test_repository_pagination(self, api_client):
        """Test repository listing with pagination"""
        response = api_client.get("/user/repos", params={"per_page": 5, "page": 1})
        
        assert response.status_code == 200
        repositories = response.json()
        
        assert isinstance(repositories, list)
        assert len(repositories) <= 5
        
        if "Link" in response.headers:
            link_header = response.headers["Link"]
            assert "rel=" in link_header
    
    def test_repository_sorting(self, api_client):
        """Test repository listing with sorting"""
        params = {
            "sort": "updated",
            "direction": "desc",
            "per_page": 10
        }
        
        response = api_client.get("/user/repos", params=params)
        
        assert response.status_code == 200
        repositories = response.json()
        
        assert isinstance(repositories, list)
        
        if len(repositories) > 1:
            for i in range(len(repositories) - 1):
                current_updated = repositories[i]["updated_at"]
                next_updated = repositories[i + 1]["updated_at"]
                assert current_updated >= next_updated