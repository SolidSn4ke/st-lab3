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
import itmo.st.lab3.pages.LoggedNavigator;
import itmo.st.lab3.pages.QuestionPage;
import itmo.st.lab3.pages.SavedQuestionsPage;

public class SavedQuestionsPageTest extends PageTest {
    @TestFactory
    public Stream<DynamicTest> saveQuestionTest() {
        return RunEnvironment.getWebDrivers().stream()
                .map(d -> dynamicTest(d.getClass().getName().replace(d.getClass().getPackageName(), ""), () -> {
                    HomePage homePage = new HomePage(d);
                    AuthPage authPage = homePage.goToAuthPage();
                    LoggedHomePage loggedHomePage = authPage.logIn(EMAIL, PASSWORD);
                    SavedQuestionsPage savedQuestionsPage = ((LoggedNavigator) loggedHomePage.getNavigator())
                            .goToSavedQuestionsPage();
                    Integer savedQuestionsCountBefore = Integer.valueOf(
                            savedQuestionsPage.getNumOfSavedQuestions().getAttribute("innerText").split(" ")[0]);
                    loggedHomePage = savedQuestionsPage.getNavigator().goToHomePage();
                    QuestionPage questionPage = loggedHomePage.goToQuestionPage();
                    questionPage.save();
                    savedQuestionsPage = ((LoggedNavigator) questionPage.getNavigator()).goToSavedQuestionsPage();
                    Integer savedQuestionsCountAfter = Integer.valueOf(
                            savedQuestionsPage.getNumOfSavedQuestions().getAttribute("innerText").split(" ")[0]);
                    assertEquals(savedQuestionsCountBefore + 1, savedQuestionsCountAfter);
                }));
    }
}
