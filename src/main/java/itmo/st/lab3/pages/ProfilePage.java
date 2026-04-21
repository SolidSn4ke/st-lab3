package itmo.st.lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.Getter;

@Getter
public class ProfilePage extends Page {
    static final String editProfileButtonXpath = "//a[text()=\" Edit profile\"]";
    WebElement editProfileButton;

    static final String profileLinkXpath = "//a/span[text()=\"Profile\"]";
    WebElement profileLink;

    static final String aboutSectionXpath = "//div/p[contains(text(), 'test')]";
    WebElement aboutSection;

    static final String profileNameXpath = "//div[contains(text(), 'Einherjar')]";
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
