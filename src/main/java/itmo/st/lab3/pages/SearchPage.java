package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage extends Page {

    enum SearchPageType {
        DEFAULT, TAGGED
    }

    static final String searchResultsXpath = "//*[@id=\"mainbar\"]/div[3]/div[1]";
    WebElement searchResults;

    static final String filterButtonXpath = "//*[@id=\"mainbar\"]/div[4]/div/div[2]/div/div[3]/button";

    static final String searchTipsXpath = "//*[@id=\"mainbar\"]/div[1]/div/div[1]/a";

    static final String usedTagsXpath = "//*[@id=\"mainbar\"]/div[3]";
    WebElement usedTags;

    static final String divWithResultsXpath = "//*[@id=\"mainbar\"]/div[4]/div";
    WebElement divWithResults;

    public SearchPage(WebDriver driver, SearchPageType type) {
        super.driver = driver;
        super.navigator = new LoggedNavigator(driver);
        switch (type) {
            case TAGGED -> {
                this.usedTags = driver.findElement(By.xpath(usedTagsXpath));
            }
            default -> {
                this.searchResults = driver.findElement(By.xpath(searchResultsXpath));
                this.divWithResults = driver.findElement(By.xpath(divWithResultsXpath));
            }
        }
    }

    public String getSearchResults() {
        return this.searchResults.getAttribute("innerText");
    }

    public Object[] getUsedTags() {
        return this.usedTags.findElements(By.xpath(".//a")).stream()
                .map(elem -> elem.getAttribute("innerText"))
                .toArray();
    }

    public boolean checkIfAllResultsIsFromOneUser(String username) {
        return this.divWithResults.findElements(By.xpath(".//a/span")).stream()
                .map(span -> span.getAttribute("innerText"))
                .allMatch(s -> s.equals(username));
    }

    public boolean checkIfResultsScoreHigher(Integer score) {
        return this.divWithResults
                .findElements(By.xpath(
                        ".//div[@class='s-post-summary--stats-item s-post-summary--stats-item__emphasized']/span[1]"))
                .stream()
                .map(span -> Long.valueOf(span.getAttribute("innerText")))
                .allMatch(l -> l > score);
    }
}
