package itmo.st.lab3.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoggedHomePage extends Page {

    static final String searchBarXpath = "//*[@id=\"search\"]/div/input";
    WebElement searchBar;

    static final String reputationXpath = "//*[@id=\"user-profile-button\"]/div[2]/ul/li[1]/span";

    public LoggedHomePage(WebDriver driver) {
        super.driver = driver;
        this.searchBar = driver.findElement(By.xpath(searchBarXpath));

    }

    public SearchPage search(String query) {
        searchBar.sendKeys(query);
        searchBar.submit();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SearchPage.searchTipsXpath)));
        return new SearchPage(driver);
    }
}
