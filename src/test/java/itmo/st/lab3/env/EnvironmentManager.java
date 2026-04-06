package itmo.st.lab3.env;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import lombok.NonNull;

public class EnvironmentManager {
    public static void initWebDriver() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("./src/resources/application.properties"));
            if (Boolean.parseBoolean(properties.getProperty("testing.browser.chrome"))) {
                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/Brave Browser.app/Contents/MacOS/Brave Browser");
                ChromeDriver chromeDriver = new ChromeDriver(options);
                chromeDriver.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
                RunEnvironment.addWebDriver(chromeDriver);
            }

            if (Boolean.parseBoolean(properties.getProperty("testing.browser.firefox")))
                RunEnvironment.addWebDriver(new FirefoxDriver());

            if (Boolean.parseBoolean(properties.getProperty("testing.browser.safari")))
                RunEnvironment.addWebDriver(new SafariDriver());
        } catch (IOException e) {
            RunEnvironment.addWebDriver(new SafariDriver());
        }
    }

    public static void initWebDriver(@NonNull String url) {
        initWebDriver();
        RunEnvironment.getWebDrivers().forEach(d -> d.get(url));
    }

    public static void shutDownDriver() {
        RunEnvironment.getWebDrivers().forEach(WebDriver::quit);
        RunEnvironment.getWebDrivers().clear();
    }
}
