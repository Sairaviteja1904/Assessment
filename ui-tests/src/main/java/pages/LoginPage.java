package pages;

import config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestUtils;


public class LoginPage extends BasePage {
    
    @FindBy(id = "login_field")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(css = "input[type='submit'][value='Sign in']")
    private WebElement signInButton;
    
    @FindBy(css = ".flash-error")
    private WebElement errorMessage;
    
    @FindBy(css = "a[href='/join']")
    private WebElement signUpLink;
    
    @FindBy(css = "a[href='/password_reset']")
    private WebElement forgotPasswordLink;
    
    private final By loadingIndicator = By.cssSelector(".loading");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateToLoginPage() {
        navigateTo(ConfigManager.getBaseUrl() + "/login");
        waitForPageLoad();
    }
    
    public void enterUsername(String username) {
        TestUtils.sendKeys(driver, By.id("login_field"), username);
    }
    
    public void enterPassword(String password) {
        TestUtils.sendKeys(driver, By.id("password"), password);
    }
    
    public void clickSignIn() {
        TestUtils.clickElement(driver, By.cssSelector("input[type='submit'][value='Sign in']"));
    }
    
    public HomePage loginWithValidCredentials() {
        return loginWithCredentials(ConfigManager.getTestUsername(), ConfigManager.getTestPassword());
    }
    
    public HomePage loginWithCredentials(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
        
        TestUtils.sleep(2000);
        
        if (isLoginSuccessful()) {
            return new HomePage(driver);
        } else {
            throw new RuntimeException("Login failed with credentials: " + username);
        }
    }
    
    public boolean isLoginSuccessful() {
        return !TestUtils.isElementPresent(driver, By.id("login_field"));
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
    
    public void clickSignUpLink() {
        TestUtils.clickElement(driver, By.cssSelector("a[href='/join']"));
    }
    
    public void clickForgotPasswordLink() {
        TestUtils.clickElement(driver, By.cssSelector("a[href='/password_reset']"));
    }
    
    public boolean isUsernameFieldEmpty() {
        return usernameField.getAttribute("value").isEmpty();
    }
    
    public boolean isPasswordFieldEmpty() {
        return passwordField.getAttribute("value").isEmpty();
    }
    
    public boolean isSignInButtonEnabled() {
        return signInButton.isEnabled();
    }
    
    public void clearUsernameField() {
        usernameField.clear();
    }
    
    public void clearPasswordField() {
        passwordField.clear();
    }
    
    public void attemptLoginWithEmptyCredentials() {
        clearUsernameField();
        clearPasswordField();
        clickSignIn();
    }
    
    public void attemptLoginWithOnlyUsername(String username) {
        enterUsername(username);
        clearPasswordField();
        clickSignIn();
    }
    
    public void attemptLoginWithOnlyPassword(String password) {
        clearUsernameField();
        enterPassword(password);
        clickSignIn();
    }
}