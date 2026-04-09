package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoggedHomePage extends Page {

    static final String searchBarXpath = "//*[@id=\"search\"]/div/input";
    WebElement searchBar;

    static final String reputationXpath = "//*[@id=\"user-profile-button\"]/div[2]/ul/li[1]/span";

    protected static final String firstQuestionXpath = "/html/body/div[3]/div[2]/div[1]/div[3]/div/div[1]/div/div[2]/h3/a";
    WebElement firstQuestion;

    public LoggedHomePage(WebDriver driver) {
        super.driver = driver;
        super.navigator = new LoggedNavigator(driver);
        this.searchBar = driver.findElement(By.xpath(searchBarXpath));
        this.firstQuestion = driver.findElement(By.xpath(firstQuestionXpath));
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

    public QuestionPage goToQuestionPage() {
        firstQuestion.click();
        waitForElement(QuestionPage.upvoteButtonXpath, 10);
        return new QuestionPage(driver);
    }
}
