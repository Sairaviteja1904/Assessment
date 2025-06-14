import pytest
import requests
from config.config import Config
from utils.api_client import GitHubAPIClient
from utils.test_data_generator import TestDataGenerator

@pytest.fixture(scope="session", autouse=True)
def validate_config():
    """Validate configuration before running tests"""
    Config.validate_config()

@pytest.fixture(scope="function")
def api_client():
    """Create API client instance for each test"""
    return GitHubAPIClient()

@pytest.fixture(scope="function")
def test_repository(api_client):
    """Create a test repository and clean it up after test"""
    repo_data = TestDataGenerator.generate_repository_data()
    
    # Create repository
    response = api_client.post("/user/repos", repo_data)
    assert response.status_code == 201, f"Failed to create test repository: {response.text}"
    
    repo_info = response.json()
    
    yield repo_info
    
    # Cleanup: Delete repository
    delete_response = api_client.delete(f"/repos/{Config.TEST_USERNAME}/{repo_info['name']}")
    if delete_response.status_code not in [204, 404]:
        print(f"Warning: Failed to delete test repository {repo_info['name']}")

@pytest.fixture(scope="function")
def test_issue(api_client, test_repository):
    """Create a test issue and clean it up after test"""
    issue_data = TestDataGenerator.generate_issue_data()
    
    # Create issue
    endpoint = f"/repos/{Config.TEST_USERNAME}/{test_repository['name']}/issues"
    response = api_client.post(endpoint, issue_data)
    assert response.status_code == 201, f"Failed to create test issue: {response.text}"
    
    issue_info = response.json()
    
    yield issue_info
    
    # Note: GitHub API doesn't allow deleting issues, they can only be closed

@pytest.fixture(scope="session")
def authenticated_user_info(api_client):
    """Get authenticated user information"""
    response = api_client.get("/user")
    assert response.status_code == 200, "Failed to get user information"
    return response.json()

@pytest.fixture(scope="function") 
def cleanup_repositories():
    """Cleanup created repositories after tests"""
    created_repos = []
    
    def add_repo(repo_name):
        created_repos.append(repo_name)
    
    yield add_repo
    
    # Cleanup
    client = GitHubAPIClient()
    for repo_name in created_repos:
        delete_response = client.delete(f"/repos/{Config.TEST_USERNAME}/{repo_name}")
        if delete_response.status_code not in [204, 404]:
            print(f"Warning: Failed to delete repository {repo_name}")

@pytest.fixture(autouse=True)
def handle_rate_limiting(api_client):
    """Handle rate limiting for all tests"""
    def check_rate_limit():
        response = api_client.get("/rate_limit")
        if response.status_code == 200:
            rate_data = response.json()
            remaining = rate_data.get('rate', {}).get('remaining', 0)
            if remaining < 10:
                api_client.wait_for_rate_limit_reset(response)
    
    check_rate_limit()
    yield
    check_rate_limit()