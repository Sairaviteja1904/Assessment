import os
from dotenv import load_dotenv

# Load environment variables
load_dotenv()

class Config:
    """Configuration class for API tests"""
    
    # GitHub API Configuration
    BASE_URL = "https://api.github.com"
    GITHUB_TOKEN = os.getenv("GITHUB_TOKEN")
    
    # Test User Configuration
    TEST_USERNAME = os.getenv("TEST_USERNAME", "testuser")
    TEST_EMAIL = os.getenv("TEST_EMAIL", "test@example.com")
    
    # Request Configuration
    REQUEST_TIMEOUT = 30
    MAX_RETRIES = 3
    
    # Test Data Configuration
    TEST_REPO_PREFIX = "test-repo-"
    TEST_ISSUE_TITLE = "Test Issue"
    TEST_ISSUE_BODY = "This is a test issue created by automation"
    TEST_PR_TITLE = "Test Pull Request"
    TEST_PR_BODY = "This is a test pull request created by automation"
    
    @classmethod
    def get_headers(cls):
        """Get standard headers for API requests"""
        return {
            "Authorization": f"token {cls.GITHUB_TOKEN}",
            "Accept": "application/vnd.github.v3+json",
            "Content-Type": "application/json"
        }
    
    @classmethod
    def validate_config(cls):
        """Validate required configuration"""
        if not cls.GITHUB_TOKEN:
            raise ValueError("GITHUB_TOKEN environment variable is required")
        
        if not cls.TEST_USERNAME:
            raise ValueError("TEST_USERNAME environment variable is required")