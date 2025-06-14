package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestUtils;


public class HomePage extends BasePage {
    
    @FindBy(css = "summary[aria-label*='View profile']")
    private WebElement profileDropdown;
    
    @FindBy(css = "a[href='/new']")
    private WebElement newRepositoryButton;
    
    @FindBy(css = "input[placeholder='Search or jump to...']")
    private WebElement searchBox;
    
    @FindBy(css = "a[href='/notifications']")
    private WebElement notificationsLink;
    
    @FindBy(css = "a[data-ga-click*='create repository']")
    private WebElement createRepositoryLink;
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public boolean isUserLoggedIn() {
        return TestUtils.isElementPresent(driver, By.cssSelector("summary[aria-label*='View profile']"));
    }
    
    public String getLoggedInUsername() {
        if (isUserLoggedIn()) {
            WebElement profileElement = driver.findElement(By.cssSelector("summary[aria-label*='View profile']"));
            return profileElement.getAttribute("aria-label");
        }
        return "";
    }
    
    public void clickProfileDropdown() {
        TestUtils.clickElement(driver, By.cssSelector("summary[aria-label*='View profile']"));
    }
    
    public CreateRepositoryPage clickNewRepository() {
        if (TestUtils.isElementPresent(driver, By.cssSelector("a[href='/new']"))) {
            TestUtils.clickElement(driver, By.cssSelector("a[href='/new']"));
        } else if (TestUtils.isElementPresent(driver, By.cssSelector("a[data-ga-click*='create repository']"))) {
            TestUtils.clickElement(driver, By.cssSelector("a[data-ga-click*='create repository']"));
        } else {
            TestUtils.clickElement(driver, By.partialLinkText("New"));
        }
        
        return new CreateRepositoryPage(driver);
    }
    
    public void searchFor(String searchTerm) {
        TestUtils.sendKeys(driver, By.cssSelector("input[placeholder*='Search']"), searchTerm);
        driver.findElement(By.cssSelector("input[placeholder*='Search']")).submit();
    }
    
    public void navigateToNotifications() {
        TestUtils.clickElement(driver, By.cssSelector("a[href='/notifications']"));
    }
    
    public boolean isSearchBoxVisible() {
        return TestUtils.isElementPresent(driver, By.cssSelector("input[placeholder*='Search']"));
    }
    
    public boolean isNewRepositoryButtonVisible() {
        return TestUtils.isElementPresent(driver, By.cssSelector("a[href='/new']")) ||
               TestUtils.isElementPresent(driver, By.cssSelector("a[data-ga-click*='create repository']"));
    }
    
    public void signOut() {
        clickProfileDropdown();
        TestUtils.sleep(1000);
        TestUtils.clickElement(driver, By.cssSelector("button[type='submit']:has(span:contains('Sign out'))"));
    }
}