package itmo.st.lab3.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuestionPage extends Page {

    static final String questionScoreXpath = "//*[@id=\"question\"]/div[2]/div[1]/div/div[2]";
    WebElement questionScore;

    static final String upvoteButtonXpath = "//*[@id=\"upvote-btn-11227809\"]";
    WebElement upvoteButton;

    static final String saveButtonXpath = "//*[@id=\"saves-btn-11227809\"]";
    WebElement saveButton;

    static final String questionNameXpath = "//h1[@itemprop='name']/a";
    WebElement questionName;

    public QuestionPage(WebDriver driver) {
        super.driver = driver;
        super.navigator = new LoggedNavigator(driver);
        this.questionScore = driver.findElements(By.xpath(questionScoreXpath)).get(0);
        this.upvoteButton = driver.findElement(By.xpath(upvoteButtonXpath));
        this.saveButton = driver.findElement(By.xpath(saveButtonXpath));
        this.questionName = driver.findElement(By.xpath(questionNameXpath));
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

    public Integer getQuestionScore() {
        return Integer.valueOf(this.questionScore.getAttribute("innerText"));
    }

    public String getQuestionName() {
        return this.questionName.getText();
    }
}
