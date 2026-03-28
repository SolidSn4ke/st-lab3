package itmo.st.lab3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import itmo.st.lab3.env.EnvironmentManager;
import itmo.st.lab3.env.RunEnvironment;
import itmo.st.lab3.pages.HomePage;

public class HomePageTest {

    @BeforeAll
    static void init() {
        EnvironmentManager.initWebDriver("http://stackoverflow.com/");
    }

    @TestFactory
    public Stream<DynamicTest> checkTitle() {
        return RunEnvironment.getWebDriver().stream().map(d -> dynamicTest(d.getClass().getName(), () -> {
            HomePage homePage = new HomePage(d);
            assertEquals("Newest Questions - Stack Overflow", homePage.getTitle());
        }));
    }

    @AfterAll
    static void destroy() {
        EnvironmentManager.shutDownDriver();
    }
}
