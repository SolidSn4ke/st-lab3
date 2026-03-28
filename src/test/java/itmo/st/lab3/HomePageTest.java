package itmo.st.lab3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import itmo.st.lab3.env.EnvironmentManager;
import itmo.st.lab3.env.RunEnvironment;
import itmo.st.lab3.pages.HomePage;

public class HomePageTest {

    @BeforeAll
    static void init() {
        EnvironmentManager.initWebDriver("http://stackoverflow.com/");
    }

    @Test
    public void shouldAnswerWithTrue() {
        HomePage homePage = new HomePage(RunEnvironment.getWebDriver());
        assertEquals("Newest Questions - Stack Overflow", homePage.getTitle());
    }

    @AfterAll
    static void destroy() {
        EnvironmentManager.shutDownDriver();
    }
}
