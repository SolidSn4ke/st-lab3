package itmo.st.lab3.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import itmo.st.lab3.env.RunEnvironment;
import itmo.st.lab3.pages.AuthPage;
import itmo.st.lab3.pages.HomePage;
import itmo.st.lab3.pages.LoggedHomePage;
import itmo.st.lab3.pages.LoggedNavigator;
import itmo.st.lab3.pages.QuestionPage;
import itmo.st.lab3.pages.SavedQuestionsPage;

public class SavedQuestionsPageTest extends PageTest {
    @TestFactory
    public Stream<DynamicTest> saveQuestionTest() {
        return RunEnvironment.getWebDrivers().stream()
                .map(d -> dynamicTest(d.getClass().getName().replace(d.getClass().getPackageName(), ""), () -> {
                    String questionName;
                    HomePage homePage = new HomePage(d);
                    AuthPage authPage = homePage.goToAuthPage();
                    LoggedHomePage loggedHomePage = authPage.logIn(EMAIL, PASSWORD);
                    QuestionPage questionPage = loggedHomePage.goToQuestionPage(1);
                    questionName = questionPage.getQuestionName();
                    questionPage.save();
                    SavedQuestionsPage savedQuestionsPage = ((LoggedNavigator) questionPage.getNavigator())
                            .goToSavedQuestionsPage();
                    Boolean isPresent = savedQuestionsPage.checkIfQuestionIsPresent(questionName);
                    assertTrue(isPresent);
                }));
    }
}
