package itmo.st.lab3.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import itmo.st.lab3.env.RunEnvironment;
import itmo.st.lab3.pages.AuthPage;
import itmo.st.lab3.pages.HomePage;
import itmo.st.lab3.pages.LoggedHomePage;
import itmo.st.lab3.pages.QuestionPage;

public class QuestionPageTest extends PageTest {
    @TestFactory
    public Stream<DynamicTest> upvoteTest() {
        return RunEnvironment.getWebDrivers().stream()
                .map(d -> dynamicTest(d.getClass().getName().replace(d.getClass().getPackageName(), ""), () -> {
                    HomePage homePage = new HomePage(d);
                    AuthPage authPage = homePage.goToAuthPage();
                    LoggedHomePage loggedHomePage = authPage.logIn(EMAIL, PASSWORD);
                    QuestionPage questionPage = loggedHomePage.goToQuestionPage(1);
                    Integer scoreBefore = Integer.valueOf(questionPage.getQuestionScore().getAttribute("innerText"));
                    questionPage.upvote();
                    Integer scoreAfter = Integer.valueOf(questionPage.getQuestionScore().getAttribute("innerText"));
                    assertEquals(scoreBefore + 1, scoreAfter);
                }));
    }
}
