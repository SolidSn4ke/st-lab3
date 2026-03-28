package itmo.st.lab3.pages;

import org.openqa.selenium.WebDriver;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class HomePage extends Page {

    public HomePage(@NonNull WebDriver driver) {
        super.driver = driver;
    }

}
