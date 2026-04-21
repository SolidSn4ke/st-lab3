package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AIAssistPage extends Page {
    static final String rateButtonXpath = "//button[contains(text(),'Yes')]";

    static final String codeBlockXpath = "//pre[@code-language=\"haskell\"]";
    WebElement codeBlock;

    static final String aiAssistantTextAreaXpath = "//textarea[@placeholder='Ask me anything']";
    WebElement aiAssistantTextArea;

    static final String aiAssistantButtonXpath = "//*[@id=\"conversation-bottom\"]/div/div/div/button";
    WebElement aiAssistantButton;

    public AIAssistPage(WebDriver driver) {
        super.driver = driver;
        this.aiAssistantTextArea = driver.findElement(By.xpath(aiAssistantTextAreaXpath));
        this.aiAssistantButton = driver.findElement(By.xpath(aiAssistantButtonXpath));
    }

    public AIAssistPage askAIAssistant(String question) {
        aiAssistantTextArea.sendKeys(question);
        aiAssistantButton.click();
        waitForElement(rateButtonXpath, 60);
        this.codeBlock = driver.findElement(By.xpath(codeBlockXpath));
        return this;
    }

    public String getCodeBlockLanguage() {
        return this.codeBlock.getAttribute("code-language");
    }
}