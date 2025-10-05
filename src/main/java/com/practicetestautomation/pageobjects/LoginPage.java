package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;//class level variable
    private WebDriverWait wait;
    private By usernameInputLocator = By.id("username");//usernameInputLocator is a locator of type By.Is now a class variable.
    private By passwordInputLocator = By.id("password");
    private By submitButtonLocator = By.id("submit");
    private By errorMessageLocator = By.id("error");

    public LoginPage(WebDriver driver) {
        //constructor with driver parameter
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUsername(String u) {
        driver.findElement(usernameInputLocator).sendKeys(u);
    }

    public void enterPassword(String p) {
        driver.findElement(passwordInputLocator).sendKeys(p);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
    }

    public SuccessfulLoginPage executeLogin(String u, String p) {
        enterPassword(u);
        enterPassword(p);
        clickSubmitButton();
        return new SuccessfulLoginPage(driver);

    }

    public String returnErrorMessage() {
        WebElement errorMessage= wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorMessage.getText();
    }
}
