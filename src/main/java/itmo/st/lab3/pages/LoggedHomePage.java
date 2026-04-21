package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoggedHomePage extends Page {

    static final String searchBarXpath = "//input[@placeholder='Search…']";
    WebElement searchBar;

    static final String reputationXpath = "//span[contains(text(), 'reputation')]";

    static final String questionsDivXpath = "//div[@itemprop='mainEntity']";
    WebElement questionsDiv;

    public LoggedHomePage(WebDriver driver) {
        super.driver = driver;
        super.navigator = new LoggedNavigator(driver);
        this.searchBar = driver.findElement(By.xpath(searchBarXpath));
        this.questionsDiv = driver.findElement(By.xpath(questionsDivXpath));
    }

    public SearchPage search(String query, SearchPage.SearchPageType type) {
        searchBar.sendKeys(query);
        searchBar.submit();
        switch (type) {
            case TAGGED -> {
                waitForElement(SearchPage.filterButtonXpath, 10);
            }
            default -> {
                waitForElement(SearchPage.searchTipsXpath, 10);
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

    public QuestionPage goToQuestionPage(Integer questionNumber) {
        questionsDiv.findElement(By.xpath(String.format("//div[%d]//h3/a", questionNumber))).click();
        waitForElement(QuestionPage.upvoteButtonXpath, 10);
        return new QuestionPage(driver);
    }
}
