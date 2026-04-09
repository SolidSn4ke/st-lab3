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
public class AIAssistPage extends Page {
    static final String rateButtonXpath = "//*[@id=\"conversation\"]/div/div[3]/button[1]";

    static final String codeBlockXpath = "//*[@id=\"conversation\"]/div/div[2]/div[5]/blockquote[1]/pre";
    WebElement codeBlock;

    static final String aiAssistantTextAreaXpath = "/html/body/div[4]/div/div[1]/div[2]/div[1]/div[2]/div[2]/div/div/textarea";
    WebElement aiAssistantTextArea;

    static final String aiAssistantButtonXpath = "/html/body/div[4]/div/div[1]/div[2]/div[1]/div[2]/div[2]/div/div/div/button";
    WebElement aiAssistantButton;

    public AIAssistPage(WebDriver driver) {
        super.driver = driver;
        this.aiAssistantTextArea = driver.findElement(By.xpath(aiAssistantTextAreaXpath));
        this.aiAssistantButton = driver.findElement(By.xpath(aiAssistantButtonXpath));
    }

    public AIAssistPage askAIAssistant(String question) {
        aiAssistantTextArea.sendKeys(question);
        aiAssistantButton.click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(rateButtonXpath)));
        this.codeBlock = driver.findElement(By.xpath(codeBlockXpath));
        return this;
    }
}