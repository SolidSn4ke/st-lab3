package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.Getter;

@Getter
public class LoggedNavigator extends Navigator {

    static final String savesPageButtonXpath = "/html/body/div[3]/div[1]/div[1]/nav/ol/li[1]/ol/li[5]/a";
    WebElement savesPageButton;

    static final String profilePageButtonXpath = "//*[@id=\"user-profile-button\"]";
    WebElement profilePageButton;

    public LoggedNavigator(WebDriver driver) {
        super(driver);
        this.savesPageButton = driver.findElement(By.xpath(savesPageButtonXpath));
        this.profilePageButton = driver.findElement(By.xpath(profilePageButtonXpath));
    }

    public ProfilePage goToProfilePage() {
        profilePageButton.click();
        waitForElement(ProfilePage.editProfileButtonXpath, 10);
        return new ProfilePage(driver);
    }

    public SavedQuestionsPage goToSavedQuestionsPage() {
        savesPageButton.click();
        waitForElement(SavedQuestionsPage.numOfSavedQuestionsXpath, 10);
        return new SavedQuestionsPage(driver);
    }
}
