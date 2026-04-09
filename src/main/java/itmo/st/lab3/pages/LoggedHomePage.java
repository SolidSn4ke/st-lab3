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

    static final String aiAssistButtonXpath = "//*[@id=\"nav-askstack\"]";
    WebElement aiAssistButton;

    public LoggedHomePage(WebDriver driver) {
        super.driver = driver;
        this.searchBar = driver.findElement(By.xpath(searchBarXpath));
        this.aiAssistButton = driver.findElement(By.xpath(aiAssistButtonXpath));
    }

    public SearchPage search(String query, SearchPage.SearchPageType type) {
        searchBar.sendKeys(query);
        searchBar.submit();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        switch (type) {
            case TAGGED -> {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SearchPage.filterButtonXpath)));
            }
            default -> {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SearchPage.searchTipsXpath)));
            }
        }

        return new SearchPage(driver, type);
    }

    public SearchPage search(String query) {
        return search(query, SearchPage.SearchPageType.DEFAULT);
    }

    public SearchPage taggedSearch(String[] tags) {
        StringBuilder sb = new StringBuilder();
        for (String tag : tags) {
            sb.append(String.format("[%s]", tag));
        }
        return search(sb.toString(), SearchPage.SearchPageType.TAGGED);
    }

    public AIAssistPage goToAiAssistPage() {
        aiAssistButton.click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(AIAssistPage.aiAssistantButtonXpath)));
        return new AIAssistPage(driver);
    }
}
