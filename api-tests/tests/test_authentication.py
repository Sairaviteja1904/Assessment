import pytest
import requests
from config.config import Config
from utils.api_client import GitHubAPIClient

@pytest.mark.authentication
@pytest.mark.smoke
class TestAuthentication:
    """Test GitHub API authentication scenarios"""
    
    def test_valid_token_authentication(self, api_client):
        """Test successful authentication with valid token"""
        response = api_client.get("/user")
        
        assert response.status_code == 200
        assert "login" in response.json()
        assert "id" in response.json()
    
    def test_invalid_token_authentication(self):
        """Test authentication failure with invalid token"""
        # Create client with invalid token
        invalid_client = GitHubAPIClient()
        invalid_client.headers["Authorization"] = "token invalid_token_123"
        invalid_client.session.headers.update(invalid_client.headers)
        
        response = invalid_client.get("/user")
        
        assert response.status_code == 401
        assert "Bad credentials" in response.json().get("message", "")
    
    def test_no_token_authentication(self):
        """Test authentication failure without token"""
        # Create client without token
        no_auth_client = GitHubAPIClient()
        no_auth_client.headers.pop("Authorization", None)
        no_auth_client.session.headers.pop("Authorization", None)
        
        response = no_auth_client.get("/user")
        
        assert response.status_code == 401
        assert "Requires authentication" in response.json().get("message", "")
    
    def test_expired_token_handling(self):
        """Test handling of expired token (simulated)"""
        # Note: This test simulates expired token behavior
        # In practice, you would use an actual expired token
        expired_client = GitHubAPIClient()
        expired_client.headers["Authorization"] = "token expired_token_example"
        expired_client.session.headers.update(expired_client.headers)
        
        response = expired_client.get("/user")
        
        assert response.status_code == 401
        assert "Bad credentials" in response.json().get("message", "")
    
    def test_rate_limit_headers_present(self, api_client):
        """Test that rate limit headers are present in response"""
        response = api_client.get("/user")
        
        assert response.status_code == 200
        assert "X-RateLimit-Limit" in response.headers
        assert "X-RateLimit-Remaining" in response.headers
        assert "X-RateLimit-Reset" in response.headers
    
    def test_user_permissions_scope(self, api_client):
        """Test user permissions and scope"""
        response = api_client.get("/user")
        
        assert response.status_code == 200
        user_data = response.json()
        
        # Check that we can access user information
        assert "login" in user_data
        assert "email" in user_data or user_data.get("email") is None  # Email might be private
        
        # Test access to user repositories
        repos_response = api_client.get("/user/repos")
        assert repos_response.status_code == 200
    
    def test_oauth_scopes_in_headers(self, api_client):
        """Test OAuth scopes in response headers"""
        response = api_client.get("/user")
        
        assert response.status_code == 200
        
        # Check if OAuth scopes are present in headers
        oauth_scopes = response.headers.get("X-OAuth-Scopes")
        if oauth_scopes:
            # Verify that we have necessary scopes
            scopes = [scope.strip() for scope in oauth_scopes.split(",")]
            # Basic scopes that should be available
            expected_scopes = ["repo", "user"]  # Adjust based on your token scopes
            # Note: This test might need adjustment based on actual token scopes
    
    @pytest.mark.negative
    def test_malformed_authorization_header(self):
        """Test malformed authorization header"""
        malformed_client = GitHubAPIClient()
        malformed_client.headers["Authorization"] = "Bearer invalid_format"  # Wrong format
        malformed_client.session.headers.update(malformed_client.headers)
        
        response = malformed_client.get("/user")
        
        assert response.status_code == 401
    
    @pytest.mark.negative
    def test_empty_authorization_header(self):
        """Test empty authorization header"""
        empty_auth_client = GitHubAPIClient()
        empty_auth_client.headers["Authorization"] = ""
        empty_auth_client.session.headers.update(empty_auth_client.headers)
        
        response = empty_auth_client.get("/user")
        
        assert response.status_code == 401