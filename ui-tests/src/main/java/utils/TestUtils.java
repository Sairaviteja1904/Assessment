package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Random;


public class TestUtils {
    private static final Logger logger = LoggerFactory.getLogger(TestUtils.class);
    private static final Random random = new Random();
    
    public static void waitForElementToBeClickable(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public static void waitForElementToBeVisible(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public static void waitForElementToBePresent(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    public static void waitForTextToBePresentInElement(WebDriver driver, By locator, String text, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static void clickElement(WebDriver driver, By locator) {
        waitForElementToBeClickable(driver, locator, 10);
        WebElement element = driver.findElement(locator);
        element.click();
        logger.info("Clicked element: {}", locator);
    }
    
    public static void sendKeys(WebDriver driver, By locator, String text) {
        waitForElementToBeVisible(driver, locator, 10);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
        logger.info("Entered text '{}' in element: {}", text, locator);
    }
    
    public static String getText(WebDriver driver, By locator) {
        waitForElementToBeVisible(driver, locator, 10);
        WebElement element = driver.findElement(locator);
        return element.getText();
    }
    
    public static String generateRandomString(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt(random.nextInt(chars.length())));
        }
        return result.toString();
    }
    
    public static String generateTestRepositoryName() {
        return "test-repo-" + generateRandomString(8);
    }
    
    public static String generateTestEmail() {
        return "test" + generateRandomString(8) + "@example.com";
    }
    
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Sleep interrupted", e);
        }
    }
}