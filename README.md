#Project Structure

```
/
├── ui-tests/                    # Java Selenium UI Tests
│   ├── src/
│   │   ├── main/java/
│   │   │   ├── pages/          # Page Object Model classes
│   │   │   ├── utils/          # Utility classes
│   │   │   └── config/         # Configuration classes
│   │   └── test/java/
│   │       ├── tests/          # Test classes
│   │       └── resources/      # Test data and config
│   ├── pom.xml                 # Maven dependencies
│   └── testng.xml             # TestNG configuration
├── api-tests/                  # Python API Tests
│   ├── tests/                  # API test modules
│   ├── utils/                  # Utility functions
│   ├── config/                 # Configuration files
│   ├── requirements.txt        # Python dependencies
│   └── pytest.ini            # Pytest configuration
├── manual-tests/               # Manual test cases
├── reports/                    # Test reports and logs
└── README.md                   # This file
```

### Manual Tests
Refer to `manual-tests/` directory for detailed manual test cases.

