package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestUtils;


public class CreateRepositoryPage extends BasePage {
    
    @FindBy(id = "repository_name")
    private WebElement repositoryNameField;
    
    @FindBy(id = "repository_description")
    private WebElement descriptionField;
    
    @FindBy(id = "repository_public")
    private WebElement publicRadioButton;
    
    @FindBy(id = "repository_private")
    private WebElement privateRadioButton;
    
    @FindBy(id = "repository_auto_init")
    private WebElement initializeReadmeCheckbox;
    
    @FindBy(css = "button[type='submit']:has(span:contains('Create repository'))")
    private WebElement createRepositoryButton;
    
    @FindBy(css = ".flash-error")
    private WebElement errorMessage;
    
    public CreateRepositoryPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterRepositoryName(String name) {
        TestUtils.sendKeys(driver, By.id("repository_name"), name);
    }
    
    public void enterDescription(String description) {
        TestUtils.sendKeys(driver, By.id("repository_description"), description);
    }
    
    public void selectPublicRepository() {
        TestUtils.clickElement(driver, By.id("repository_public"));
    }
    
    public void selectPrivateRepository() {
        TestUtils.clickElement(driver, By.id("repository_private"));
    }
    
    public void checkInitializeReadme() {
        if (!initializeReadmeCheckbox.isSelected()) {
            TestUtils.clickElement(driver, By.id("repository_auto_init"));
        }
    }
    
    public void uncheckInitializeReadme() {
        if (initializeReadmeCheckbox.isSelected()) {
            TestUtils.clickElement(driver, By.id("repository_auto_init"));
        }
    }
    
    public RepositoryPage clickCreateRepository() {
        TestUtils.clickElement(driver, By.cssSelector("button[type='submit']"));
        TestUtils.sleep(3000); 
        return new RepositoryPage(driver);
    }
    
    public RepositoryPage createRepository(String name, String description, boolean isPublic, boolean initializeReadme) {
        enterRepositoryName(name);
        
        if (description != null && !description.isEmpty()) {
            enterDescription(description);
        }
        
        if (isPublic) {
            selectPublicRepository();
        } else {
            selectPrivateRepository();
        }
        
        if (initializeReadme) {
            checkInitializeReadme();
        } else {
            uncheckInitializeReadme();
        }
        
        return clickCreateRepository();
    }
    
    public boolean isRepositoryNameFieldEmpty() {
        return repositoryNameField.getAttribute("value").isEmpty();
    }
    
    public boolean isCreateButtonEnabled() {
        return createRepositoryButton.isEnabled();
    }
    
    public String getErrorMessage() {
        if (TestUtils.isElementPresent(driver, By.cssSelector(".flash-error"))) {
            return TestUtils.getText(driver, By.cssSelector(".flash-error"));
        }
        return "";
    }
    
    public boolean isErrorMessageDisplayed() {
        return TestUtils.isElementPresent(driver, By.cssSelector(".flash-error"));
    }
    
    public void attemptCreateWithEmptyName() {
        repositoryNameField.clear();
        clickCreateRepository();
    }
    
    public boolean isPublicSelected() {
        return publicRadioButton.isSelected();
    }
    
    public boolean isPrivateSelected() {
        return privateRadioButton.isSelected();
    }
    
    public boolean isInitializeReadmeChecked() {
        return initializeReadmeCheckbox.isSelected();
    }
}