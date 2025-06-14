package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestUtils;


public abstract class BasePage {
    protected WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public void navigateTo(String url) {
        driver.get(url);
        logger.info("Navigated to: {}", url);
    }
    
    public void refreshPage() {
        driver.navigate().refresh();
        logger.info("Page refreshed");
    }
    
    public void goBack() {
        driver.navigate().back();
        logger.info("Navigated back");
    }
    
    protected void waitForPageLoad() {
        TestUtils.sleep(2000); // Basic wait for page elements to load
    }
}