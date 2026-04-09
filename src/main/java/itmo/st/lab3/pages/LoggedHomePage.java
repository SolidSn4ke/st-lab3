package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoggedHomePage extends Page {

    static final String searchBarXpath = "//*[@id=\"search\"]/div/input";
    WebElement searchBar;

    static final String reputationXpath = "//*[@id=\"user-profile-button\"]/div[2]/ul/li[1]/span";

    static final String aiAssistButtonXpath = "//*[@id=\"nav-askstack\"]";
    WebElement aiAssistButton;

    static final String profileButtonXpath = "//*[@id=\"user-profile-button\"]";
    WebElement profileButton;

    public LoggedHomePage(WebDriver driver) {
        super.driver = driver;
        this.searchBar = driver.findElement(By.xpath(searchBarXpath));
        this.aiAssistButton = driver.findElement(By.xpath(aiAssistButtonXpath));
        this.profileButton = driver.findElement(By.xpath(profileButtonXpath));
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

    public AIAssistPage goToAiAssistPage() {
        aiAssistButton.click();
        waitForElement(AIAssistPage.aiAssistantButtonXpath, 10);
        return new AIAssistPage(driver);
    }

    public ProfilePage goToProfilePage() {
        profileButton.click();
        waitForElement(ProfilePage.editProfileButtonXpath, 10);
        return new ProfilePage(driver);
    }
}
