package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import lombok.Getter;

@Getter
public class AuthPage extends Page {

    static final String emailFieldXpath = "//*[@id=\"email\"]";
    WebElement emailField;

    static final String passwordFieldXpath = "//*[@id=\"password\"]";
    WebElement passwordField;

    static final String logInButtonXpath = "//*[@id=\"submit-button\"]";
    WebElement logInButton;

    public AuthPage(WebDriver driver) {
        super.driver = driver;
        this.emailField = driver.findElement(By.xpath(emailFieldXpath));
        this.passwordField = driver.findElement(By.xpath(passwordFieldXpath));
        this.logInButton = driver.findElement(By.xpath(logInButtonXpath));
    }

    public LoggedHomePage logIn(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        logInButton.click();
        waitForElement(LoggedHomePage.reputationXpath, 10);
        LoggedHomePage homePage = new LoggedHomePage(driver);
        return homePage;
    }
}
