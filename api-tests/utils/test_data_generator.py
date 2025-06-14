import random
import string
from datetime import datetime
from faker import Faker

fake = Faker()

class TestDataGenerator:
    """Generate test data for API tests"""
    
    @staticmethod
    def generate_random_string(length: int = 8) -> str:
        """Generate random string of specified length"""
        return ''.join(random.choices(string.ascii_lowercase + string.digits, k=length))
    
    @staticmethod
    def generate_repository_name() -> str:
        """Generate random repository name"""
        timestamp = datetime.now().strftime("%Y%m%d%H%M%S")
        random_suffix = TestDataGenerator.generate_random_string(4)
        return f"test-repo-{timestamp}-{random_suffix}"
    
    @staticmethod
    def generate_repository_data(private: bool = False) -> dict:
        """Generate repository creation data"""
        return {
            "name": TestDataGenerator.generate_repository_name(),
            "description": f"Test repository created at {datetime.now()}",
            "private": private,
            "auto_init": True,
            "has_issues": True,
            "has_projects": True,
            "has_wiki": True
        }
    
    @staticmethod
    def generate_issue_data() -> dict:
        """Generate issue creation data"""
        return {
            "title": f"Test Issue - {fake.sentence(nb_words=4)}",
            "body": fake.text(max_nb_chars=200),
            "labels": ["bug", "enhancement"],
            "assignees": []
        }
    
    @staticmethod
    def generate_pull_request_data(head_branch: str = "test-branch", 
                                  base_branch: str = "main") -> dict:
        """Generate pull request creation data"""
        return {
            "title": f"Test PR - {fake.sentence(nb_words=4)}",
            "body": fake.text(max_nb_chars=200),
            "head": head_branch,
            "base": base_branch
        }
    
    @staticmethod
    def generate_user_data() -> dict:
        """Generate user data"""
        return {
            "name": fake.name(),
            "email": fake.email(),
            "bio": fake.text(max_nb_chars=100),
            "location": fake.city(),
            "company": fake.company()
        }
    
    @staticmethod
    def generate_invalid_repository_data() -> dict:
        """Generate invalid repository data for negative testing"""
        return {
            "name": "",  # Invalid: empty name
            "description": "A" * 1000,  # Invalid: too long description
            "private": "invalid_boolean"  # Invalid: non-boolean value
        }
    
    @staticmethod
    def generate_long_string(length: int = 1000) -> str:
        """Generate long string for testing limits"""
        return fake.text(max_nb_chars=length)