import requests
import json
import time
from typing import Dict, Any, Optional
from config.config import Config

class GitHubAPIClient:
    """GitHub API client for making HTTP requests"""
    
    def __init__(self):
        self.base_url = Config.BASE_URL
        self.headers = Config.get_headers()
        self.timeout = Config.REQUEST_TIMEOUT
        self.session = requests.Session()
        self.session.headers.update(self.headers)
    
    def get(self, endpoint: str, params: Optional[Dict] = None) -> requests.Response:
        """Make GET request to GitHub API"""
        url = f"{self.base_url}{endpoint}"
        response = self.session.get(url, params=params, timeout=self.timeout)
        self._log_request("GET", url, params, response)
        return response
    
    def post(self, endpoint: str, data: Optional[Dict] = None) -> requests.Response:
        """Make POST request to GitHub API"""
        url = f"{self.base_url}{endpoint}"
        json_data = json.dumps(data) if data else None
        response = self.session.post(url, data=json_data, timeout=self.timeout)
        self._log_request("POST", url, data, response)
        return response
    
    def put(self, endpoint: str, data: Optional[Dict] = None) -> requests.Response:
        """Make PUT request to GitHub API"""
        url = f"{self.base_url}{endpoint}"
        json_data = json.dumps(data) if data else None
        response = self.session.put(url, data=json_data, timeout=self.timeout)
        self._log_request("PUT", url, data, response)
        return response
    
    def patch(self, endpoint: str, data: Optional[Dict] = None) -> requests.Response:
        """Make PATCH request to GitHub API"""
        url = f"{self.base_url}{endpoint}"
        json_data = json.dumps(data) if data else None
        response = self.session.patch(url, data=json_data, timeout=self.timeout)
        self._log_request("PATCH", url, data, response)
        return response
    
    def delete(self, endpoint: str) -> requests.Response:
        """Make DELETE request to GitHub API"""
        url = f"{self.base_url}{endpoint}"
        response = self.session.delete(url, timeout=self.timeout)
        self._log_request("DELETE", url, None, response)
        return response
    
    def _log_request(self, method: str, url: str, data: Any, response: requests.Response):
        """Log API requests and responses"""
        print(f"\n{method} {url}")
        if data:
            print(f"Request Data: {json.dumps(data, indent=2)}")
        print(f"Response Status: {response.status_code}")
        
        try:
            response_json = response.json()
            print(f"Response Body: {json.dumps(response_json, indent=2)[:500]}...")
        except json.JSONDecodeError:
            print(f"Response Body: {response.text[:500]}...")
    
    def wait_for_rate_limit_reset(self, response: requests.Response):
        """Wait for rate limit reset if needed"""
        if response.status_code == 403:
            rate_limit_remaining = int(response.headers.get('X-RateLimit-Remaining', 0))
            if rate_limit_remaining == 0:
                reset_time = int(response.headers.get('X-RateLimit-Reset', 0))
                current_time = int(time.time())
                sleep_time = max(0, reset_time - current_time + 1)
                print(f"Rate limit exceeded. Waiting {sleep_time} seconds...")
                time.sleep(sleep_time)