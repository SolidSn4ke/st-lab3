package itmo.st.lab3.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Getter;

public abstract class Page {
    WebDriver driver;
    @Getter
    Navigator navigator;

    static final String cookieButtonXpath = "//*[@id=\"onetrust-accept-btn-handler\"]";
    WebElement cookieButton;

    static final String googleIframeXpath = "//iframe[@title='Диалоговое окно \"Войти с аккаунтом Google\"']";
    WebElement googleIframe;

    void waitForElement(String elementXpath, Integer durationInSeconds) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void closePopUps() throws InterruptedException {
        waitForElement(googleIframeXpath, 5);
        cookieButton = driver.findElement(By.xpath(cookieButtonXpath));
        cookieButton.click();
        googleIframe = driver.findElement(By.xpath(googleIframeXpath));
        driver.switchTo().frame(googleIframe);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("document.getElementById('close').click()");
        driver.switchTo().defaultContent();
        Thread.sleep(Duration.ofSeconds(2));
    }
}
