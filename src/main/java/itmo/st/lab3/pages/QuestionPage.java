package itmo.st.lab3.pages;

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

    static final String savesButtonXpath = "//*[@id=\"saves-btn-11227809\"]";
    WebElement savesButton;

    public QuestionPage(WebDriver driver) {
        super.driver = driver;
        this.questionScore = driver.findElement(By.xpath(questionScoreXpath));
        this.upvoteButton = driver.findElement(By.xpath(upvoteButtonXpath));
        this.savesButton = driver.findElement(By.xpath(savesButtonXpath));
    }

    public QuestionPage upvote() {
        upvoteButton.click();
        waitForElement(questionScoreXpath, 10);
        return this;
    }
}
