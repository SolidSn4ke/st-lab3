package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.Getter;

@Getter
public class SavedQuestionsPage extends Page {

    static final String numOfSavedQuestionsXpath = "//h2[contains(text(), 'saved item')]";
    WebElement numOfSavedQuestions;

    public SavedQuestionsPage(WebDriver driver) {
        super.driver = driver;
        super.navigator = new LoggedNavigator(driver);
        this.numOfSavedQuestions = driver.findElement(By.xpath(numOfSavedQuestionsXpath));
    }
}
