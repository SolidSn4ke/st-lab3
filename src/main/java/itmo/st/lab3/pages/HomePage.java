package itmo.st.lab3.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class HomePage extends Page {

    WebElement logInButton;

    public HomePage(@NonNull WebDriver driver) {
        super.driver = driver;
        this.logInButton = driver.findElement(By.xpath("/html/body/header/div/nav/ol/li[3]/a"));
    }

    public AuthPage goToAuthPage() {
        logInButton.click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/users/login"));
        return new AuthPage(driver);
    }
}
