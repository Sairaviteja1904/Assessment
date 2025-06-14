package tests;

import config.ConfigManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    
    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void setUp(String browser, String headless) {
        try {
            boolean isHeadless = Boolean.parseBoolean(headless);
            DriverManager.setDriver(browser, isHeadless);
            logger.info("Test setup completed for browser: {}", browser);
        } catch (Exception e) {
            logger.error("Failed to setup test", e);
            throw new RuntimeException("Test setup failed", e);
        }
    }
    
    @AfterMethod
    public void tearDown() {
        try {
            DriverManager.quitDriver();
            logger.info("Test teardown completed");
        } catch (Exception e) {
            logger.error("Failed to teardown test", e);
        }
    }
    
    protected void logTestStep(String step) {
        logger.info("Test Step: {}", step);
    }
}