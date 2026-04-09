package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.Getter;

@Getter
public class ProfilePage extends Page {
    static final String editProfileButtonXpath = "/html/body/div[3]/div[2]/div/div[1]/div[2]/a";
    WebElement editProfileButton;

    static final String profileLinkXpath = "/html/body/div[3]/div[2]/div/div[2]/div[1]/a[1]";
    WebElement profileLink;

    static final String aboutSectionXpath = "/html/body/div[3]/div[2]/div/main/div/div[2]/div/div[2]/div[1]/div";
    WebElement aboutSection;

    static final String profileNameXpath = "//*[@id=\"mainbar-full\"]/div[1]/div[1]/div/div/div[1]";
    WebElement profileName;

    public ProfilePage(WebDriver driver) {
        super.driver = driver;
        super.navigator = new LoggedNavigator(driver);
        this.editProfileButton = driver.findElement(By.xpath(editProfileButtonXpath));
        this.profileLink = driver.findElement(By.xpath(profileLinkXpath));
        this.profileName = driver.findElement(By.xpath(profileNameXpath));
    }

    public EditProfilePage goToEditProfilePage() {
        editProfileButton.click();
        waitForElement(EditProfilePage.aboutTextAreaXpath, 10);
        return new EditProfilePage(driver);
    }

    public ProfilePage goToProfileDisplay() {
        profileLink.click();
        waitForElement(aboutSectionXpath, 10);
        this.aboutSection = driver.findElement(By.xpath(aboutSectionXpath));
        return this;
    }
}
