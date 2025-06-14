package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverManager;
import utils.TestUtils;

@Epic("Authentication")
@Feature("Login")
public class LoginTests extends BaseTest {
    
    @Test(description = "Verify successful login with valid credentials")
    @Story("Valid Login")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidLogin() {
        logTestStep("Navigate to login page");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToLoginPage();
        
        logTestStep("Login with valid credentials");
        HomePage homePage = loginPage.loginWithValidCredentials();
        
        logTestStep("Verify user is logged in");
        Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in");
        
        logTestStep("Verify page title contains GitHub");
        Assert.assertTrue(homePage.getPageTitle().contains("GitHub"), "Page title should contain GitHub");
    }
    
    @Test(description = "Verify login fails with invalid credentials")
    @Story("Invalid Login")
    @Severity(SeverityLevel.HIGH)
    public void testInvalidLogin() {
        logTestStep("Navigate to login page");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToLoginPage();
        
        logTestStep("Attempt login with invalid credentials");
        loginPage.enterUsername("invalid_user_" + TestUtils.generateRandomString(5));
        loginPage.enterPassword("invalid_password");
        loginPage.clickSignIn();
        
        logTestStep("Verify login fails");
        Assert.assertFalse(loginPage.isLoginSuccessful(), "Login should fail with invalid credentials");
        
        logTestStep("Verify error message is displayed");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
    }
    
    @Test(description = "Verify login form validation with empty fields")
    @Story("Form Validation")
    @Severity(SeverityLevel.MEDIUM)
    public void testEmptyFieldsValidation() {
        logTestStep("Navigate to login page");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToLoginPage();
        
        logTestStep("Attempt login with empty credentials");
        loginPage.attemptLoginWithEmptyCredentials();
        
        logTestStep("Verify login form is still present");
        Assert.assertTrue(loginPage.isUsernameFieldEmpty() || loginPage.isPasswordFieldEmpty(), 
                         "Form should require field validation");
    }
    
    @Test(description = "Verify login with only username provided")
    @Story("Form Validation")
    @Severity(SeverityLevel.MEDIUM)
    public void testLoginWithOnlyUsername() {
        logTestStep("Navigate to login page");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToLoginPage();
        
        logTestStep("Attempt login with only username");
        loginPage.attemptLoginWithOnlyUsername("testuser");
        
        logTestStep("Verify login fails");
        Assert.assertFalse(loginPage.isLoginSuccessful(), "Login should fail without password");
    }
    
    @Test(description = "Verify login with only password provided")
    @Story("Form Validation")
    @Severity(SeverityLevel.MEDIUM)
    public void testLoginWithOnlyPassword() {
        logTestStep("Navigate to login page");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToLoginPage();
        
        logTestStep("Attempt login with only password");
        loginPage.attemptLoginWithOnlyPassword("testpass");
        
        logTestStep("Verify login fails");
        Assert.assertFalse(loginPage.isLoginSuccessful(), "Login should fail without username");
    }
    
    @Test(description = "Verify sign up link is present and clickable")
    @Story("Navigation")
    @Severity(SeverityLevel.LOW)
    public void testSignUpLinkPresent() {
        logTestStep("Navigate to login page");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToLoginPage();
        
        logTestStep("Click sign up link");
        loginPage.clickSignUpLink();
        
        logTestStep("Verify navigation to sign up page");
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("join"), 
                         "Should navigate to sign up page");
    }
    
    @Test(description = "Verify forgot password link is present and clickable")
    @Story("Navigation")
    @Severity(SeverityLevel.LOW)
    public void testForgotPasswordLinkPresent() {
        logTestStep("Navigate to login page");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToLoginPage();
        
        logTestStep("Click forgot password link");
        loginPage.clickForgotPasswordLink();
        
        logTestStep("Verify navigation to password reset page");
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("password_reset"), 
                         "Should navigate to password reset page");
    }
}