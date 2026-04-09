package itmo.st.lab3.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.Getter;

@Getter
public class QuestionPage extends Page {

    static final String questionScoreXpath = "/html/body/div[3]/div[2]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div/div[2]";
    WebElement questionScore;

    static final String upvoteButtonXpath = "//*[@id=\"upvote-btn-11227809\"]";
    WebElement upvoteButton;

    static final String saveButtonXpath = "//*[@id=\"saves-btn-11227809\"]";
    WebElement saveButton;

    static final String savesButtonXpath = "/html/body/div[3]/div[1]/div[1]/nav/ol/li[1]/ol/li[5]/a";
    WebElement savesButton;

    public QuestionPage(WebDriver driver) {
        super.driver = driver;
        super.navigator = new LoggedNavigator(driver);
        this.questionScore = driver.findElement(By.xpath(questionScoreXpath));
        this.upvoteButton = driver.findElement(By.xpath(upvoteButtonXpath));
        this.saveButton = driver.findElement(By.xpath(saveButtonXpath));
        this.savesButton = driver.findElement(By.xpath(savesButtonXpath));
    }

    public QuestionPage upvote() {
        upvoteButton.click();
        waitForElement(questionScoreXpath, 10);
        return this;
    }

    public QuestionPage save() {
        saveButton.click();
        try {
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return this;
    }
}
