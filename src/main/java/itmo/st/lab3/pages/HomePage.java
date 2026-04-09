package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        super.navigator = new Navigator(driver);
        this.logInButton = driver.findElement(By.xpath(logInButtonXpath));
        this.searchBar = driver.findElement(By.xpath(searchBarXpath));
    }

    public AuthPage goToAuthPage() {
        logInButton.click();
        waitForElement(AuthPage.emailFieldXpath, 10);
        return new AuthPage(driver);
    }
}
