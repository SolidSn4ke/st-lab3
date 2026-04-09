package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditProfilePage extends Page {

    static final String aboutTextAreaXpath = "//*[@id=\"wmd-input\"]";
    WebElement aboutTextArea;

    static final String saveButtonXpath = "//*[@id=\"form-submit\"]/div/button";
    WebElement saveButton;

    public EditProfilePage(WebDriver driver) {
        super.driver = driver;
        this.aboutTextArea = driver.findElement(By.xpath(aboutTextAreaXpath));
        this.saveButton = driver.findElement(By.xpath(saveButtonXpath));
    }

    public ProfilePage editAboutAndSave(String about) {
        aboutTextArea.clear();
        aboutTextArea.sendKeys(about);
        saveButton.click();
        waitForElement(ProfilePage.editProfileButtonXpath, 10);
        return new ProfilePage(driver);
    }
}
