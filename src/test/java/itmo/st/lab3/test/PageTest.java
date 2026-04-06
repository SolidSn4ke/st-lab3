package itmo.st.lab3.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import itmo.st.lab3.env.EnvironmentManager;

public abstract class PageTest {

    @BeforeAll
    static void init() {
        EnvironmentManager.initWebDriver("http://stackoverflow.com/");
    }

    @AfterAll
    static void destroy() {
        EnvironmentManager.shutDownDriver();
    }
}
