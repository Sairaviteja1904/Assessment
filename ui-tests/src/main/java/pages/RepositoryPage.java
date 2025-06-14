package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestUtils;


public class RepositoryPage extends BasePage {
    
    @FindBy(css = "h1[itemprop='name'] a")
    private WebElement repositoryNameHeader;
    
    @FindBy(css = "a[data-tab-item='issues']")
    private WebElement issuesTab;
    
    @FindBy(css = "a[data-tab-item='pull-requests']")
    private WebElement pullRequestsTab;
    
    @FindBy(css = "a[data-tab-item='actions']")
    private WebElement actionsTab;
    
    @FindBy(css = "a[data-tab-item='projects']")
    private WebElement projectsTab;
    
    @FindBy(css = "a[data-tab-item='settings']")
    private WebElement settingsTab;
    
    @FindBy(css = "button:has(span:contains('Code'))")
    private WebElement codeButton;
    
    @FindBy(css = "a[href*='/issues/new']")
    private WebElement newIssueButton;
    
    @FindBy(css = ".repository-content")
    private WebElement repositoryContent;
    
    public RepositoryPage(WebDriver driver) {
        super(driver);
    }
    
    public String getRepositoryName() {
        TestUtils.waitForElementToBeVisible(driver, By.cssSelector("h1[itemprop='name'] a"), 10);
        return TestUtils.getText(driver, By.cssSelector("h1[itemprop='name'] a"));
    }
    
    public boolean isRepositoryPageLoaded() {
        return TestUtils.isElementPresent(driver, By.cssSelector("h1[itemprop='name']"));
    }
    
    public IssuesPage clickIssuesTab() {
        TestUtils.clickElement(driver, By.cssSelector("a[data-tab-item='issues']"));
        return new IssuesPage(driver);
    }
    
    public PullRequestsPage clickPullRequestsTab() {
        TestUtils.clickElement(driver, By.cssSelector("a[data-tab-item='pull-requests']"));
        return new PullRequestsPage(driver);
    }
    
    public void clickActionsTab() {
        TestUtils.clickElement(driver, By.cssSelector("a[data-tab-item='actions']"));
    }
    
    public void clickProjectsTab() {
        TestUtils.clickElement(driver, By.cssSelector("a[data-tab-item='projects']"));
    }
    
    public void clickSettingsTab() {
        TestUtils.clickElement(driver, By.cssSelector("a[data-tab-item='settings']"));
    }
    
    public void clickCodeTab() {
        TestUtils.clickElement(driver, By.cssSelector("a[data-tab-item='code']"));
    }
    
    public CreateIssuePage clickNewIssue() {
        TestUtils.clickElement(driver, By.cssSelector("a[href*='/issues/new']"));
        return new CreateIssuePage(driver);
    }
    
    public boolean isIssuesTabVisible() {
        return TestUtils.isElementPresent(driver, By.cssSelector("a[data-tab-item='issues']"));
    }
    
    public boolean isPullRequestsTabVisible() {
        return TestUtils.isElementPresent(driver, By.cssSelector("a[data-tab-item='pull-requests']"));
    }
    
    public boolean isActionsTabVisible() {
        return TestUtils.isElementPresent(driver, By.cssSelector("a[data-tab-item='actions']"));
    }
    
    public boolean isSettingsTabVisible() {
        return TestUtils.isElementPresent(driver, By.cssSelector("a[data-tab-item='settings']"));
    }
    
    public boolean isNewIssueButtonVisible() {
        return TestUtils.isElementPresent(driver, By.cssSelector("a[href*='/issues/new']"));
    }
    
    public String getCurrentTab() {
        if (TestUtils.isElementPresent(driver, By.cssSelector("a[data-tab-item='issues'][aria-current='page']"))) {
            return "issues";
        } else if (TestUtils.isElementPresent(driver, By.cssSelector("a[data-tab-item='pull-requests'][aria-current='page']"))) {
            return "pull-requests";
        } else if (TestUtils.isElementPresent(driver, By.cssSelector("a[data-tab-item='actions'][aria-current='page']"))) {
            return "actions";
        } else {
            return "code";
        }
    }
    
    public void navigateToRepository(String owner, String repoName) {
        String url = String.format("https://github.com/%s/%s", owner, repoName);
        navigateTo(url);
    }
}