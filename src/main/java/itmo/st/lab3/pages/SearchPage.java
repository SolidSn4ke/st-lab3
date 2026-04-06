package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.Getter;

@Getter
public class SearchPage extends Page {

    static final String searchResultsXpath = "//*[@id=\"mainbar\"]/div[3]/div[1]";
    WebElement searchResults;

    static final String searchTipsXpath = "//*[@id=\"mainbar\"]/div[1]/div/div[1]/a";

    public SearchPage(WebDriver driver) {
        super.driver = driver;
        this.searchResults = driver.findElement(By.xpath(searchResultsXpath));
    }

}
