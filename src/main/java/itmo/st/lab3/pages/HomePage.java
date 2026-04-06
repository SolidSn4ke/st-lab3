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

    static final String logInButtonXpath = "/html/body/header/div/nav/ol/li[3]/a";
    WebElement logInButton;

    static final String searchBarXpath = "//*[@id=\"search\"]/div/input";
    WebElement searchBar;

    public HomePage(@NonNull WebDriver driver) {
        super.driver = driver;
        this.logInButton = driver.findElement(By.xpath(logInButtonXpath));
        this.searchBar = driver.findElement(By.xpath(searchBarXpath));
    }

    public AuthPage goToAuthPage() {
        logInButton.click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(AuthPage.emailFieldXpath)));
        return new AuthPage(driver);
    }
}
