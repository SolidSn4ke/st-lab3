package itmo.st.lab3.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import itmo.st.lab3.env.RunEnvironment;
import itmo.st.lab3.pages.AIAssistPage;
import itmo.st.lab3.pages.AuthPage;
import itmo.st.lab3.pages.HomePage;
import itmo.st.lab3.pages.LoggedHomePage;

public class AIAssistPageTest extends PageTest {
    @TestFactory
    public Stream<DynamicTest> askAIAssistantTest() {
        return RunEnvironment.getWebDrivers().stream()
                .map(d -> dynamicTest(d.getClass().getName().replace(d.getClass().getPackageName(), ""), () -> {
                    String question = "Haskell quick sort implementation";
                    HomePage homePage = new HomePage(d);
                    AuthPage authPage = homePage.goToAuthPage();
                    LoggedHomePage loggedHomePage = authPage.logIn(EMAIL, PASSWORD);
                    AIAssistPage aiAssistPage = loggedHomePage.goToAiAssistPage();
                    aiAssistPage.askAIAssistant(question);
                    assertEquals("haskell", aiAssistPage.getCodeBlock().getAttribute("code-language"));
                }));
    }
}
