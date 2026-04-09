package itmo.st.lab3.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Getter;

public abstract class Page {
    WebDriver driver;
    @Getter
    Navigator navigator;

    void waitForElement(String elementXpath, Integer durationInSeconds) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
