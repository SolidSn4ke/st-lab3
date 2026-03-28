package itmo.st.lab3.env;

import org.openqa.selenium.safari.SafariDriver;

import lombok.NonNull;

public class EnvironmentManager {
    public static void initWebDriver() {
        RunEnvironment.setWebDriver(new SafariDriver());
    }

    public static void initWebDriver(@NonNull String url) {
        RunEnvironment.setWebDriver(new SafariDriver());
        RunEnvironment.getWebDriver().get(url);
    }

    public static void shutDownDriver() {
        RunEnvironment.getWebDriver().quit();
    }
}
