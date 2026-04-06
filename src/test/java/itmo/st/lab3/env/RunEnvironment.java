package itmo.st.lab3.env;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import lombok.Getter;

public class RunEnvironment {
    @Getter
    static List<WebDriver> webDrivers = new ArrayList<>();

    public static <D extends WebDriver> void addWebDriver(D driver) {
        webDrivers.add(driver);
    }
}
