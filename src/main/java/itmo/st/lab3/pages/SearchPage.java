package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.Getter;

@Getter
public class SearchPage extends Page {

    enum SearchPageType {
        DEFAULT, TAGGED
    }

    static final String searchResultsXpath = "//*[@id=\"mainbar\"]/div[3]/div[1]";
    WebElement searchResults;

    static final String filterButtonXpath = "//*[@id=\"mainbar\"]/div[4]/div/div[2]/div/div[3]/button";

    static final String searchTipsXpath = "//*[@id=\"mainbar\"]/div[1]/div/div[1]/a";

    static final String usedTaggsXpath = "//*[@id=\"mainbar\"]/div[3]";
    WebElement usedTaggs;

    public SearchPage(WebDriver driver, SearchPageType type) {
        super.driver = driver;
        switch (type) {
            case TAGGED -> {
                this.usedTaggs = driver.findElement(By.xpath(usedTaggsXpath));
            }
            default -> {
                this.searchResults = driver.findElement(By.xpath(searchResultsXpath));
            }
        }

    }

}
