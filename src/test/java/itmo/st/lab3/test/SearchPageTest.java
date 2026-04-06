package itmo.st.lab3.test;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import itmo.st.lab3.env.RunEnvironment;
import itmo.st.lab3.pages.AuthPage;
import itmo.st.lab3.pages.HomePage;
import itmo.st.lab3.pages.LoggedHomePage;
import itmo.st.lab3.pages.SearchPage;

public class SearchPageTest extends PageTest {

    String EMAIL = System.getenv("ST_LAB3_EMAIL");
    String PASSWORD = System.getenv("ST_LAB3_PASSWORD");

    @TestFactory
    public Stream<DynamicTest> searchTest() {
        return RunEnvironment.getWebDrivers().stream()
                .map(d -> dynamicTest(d.getClass().getName().replace(d.getClass().getPackageName(), ""), () -> {
                    HomePage homePage = new HomePage(d);
                    AuthPage authPage = homePage.goToAuthPage();
                    LoggedHomePage loggedHomePage = authPage.logIn(EMAIL, PASSWORD);
                    SearchPage searchPage = loggedHomePage.search("haskell quick sort");
                    assertLinesMatch(List.of("\\d+ results"),
                            List.of(searchPage.getSearchResults().getAttribute("innerText")));
                }));
    }
}
