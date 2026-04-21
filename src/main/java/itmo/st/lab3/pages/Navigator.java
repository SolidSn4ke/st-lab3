package itmo.st.lab3.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Getter;

@Getter
public class Navigator {
    WebDriver driver;

    static final String homePageButtonXpath = "//*[@id=\"nav-questions\"]";
    WebElement homePageButton;

    static final String aiAssistantPageButtonXpath = "//*[@id=\"nav-askstack\"]";
    WebElement aiAssistantPageButton;

    public Navigator(WebDriver driver) {
        this.driver = driver;
        this.homePageButton = driver.findElement(By.xpath(homePageButtonXpath));
        this.aiAssistantPageButton = driver.findElement(By.xpath(aiAssistantPageButtonXpath));
    }

    void waitForElement(String elementXpath, Integer durationInSeconds) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
    }

    public LoggedHomePage goToHomePage() {
        homePageButton.click();
        waitForElement(LoggedHomePage.reputationXpath, 10);
        return new LoggedHomePage(driver);
    }

    public AIAssistPage goToAiAssistPage() {
        aiAssistantPageButton.click();
        waitForElement(AIAssistPage.aiAssistantButtonXpath, 10);
        return new AIAssistPage(driver);
    }
}
