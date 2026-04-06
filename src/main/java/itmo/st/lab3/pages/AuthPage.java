package itmo.st.lab3.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Getter;

@Getter
public class AuthPage extends Page {

    WebElement emailField;

    WebElement passwordField;

    WebElement logInButton;

    public AuthPage(WebDriver driver) {
        super.driver = driver;
        this.emailField = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        this.passwordField = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        this.logInButton = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
    }

    public LoggedHomePage logIn(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        logInButton.click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlMatches("https?:\\/\\/stackoverflow\\.com"));
        LoggedHomePage homePage = new LoggedHomePage(driver);
        return homePage;
    }
}
