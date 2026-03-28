package itmo.st.lab3.pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {
    WebDriver driver;

    public String getTitle() {
        return driver.getTitle();
    }
}
