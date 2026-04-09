package itmo.st.lab3.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import itmo.st.lab3.env.EnvironmentManager;

public abstract class PageTest {

    String EMAIL = System.getenv("ST_LAB3_EMAIL");
    String PASSWORD = System.getenv("ST_LAB3_PASSWORD");

    @BeforeAll
    static void init() {
        EnvironmentManager.initWebDriver("http://stackoverflow.com/");
    }

    @AfterAll
    static void destroy() {
        EnvironmentManager.shutDownDriver();
    }
}
