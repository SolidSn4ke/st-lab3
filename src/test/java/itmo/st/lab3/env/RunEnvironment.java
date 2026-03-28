package itmo.st.lab3.env;

import org.openqa.selenium.WebDriver;

import lombok.Getter;
import lombok.Setter;

public class RunEnvironment {
    @Getter
    @Setter
    static WebDriver webDriver;
}
