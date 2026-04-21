package itmo.st.lab3.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import itmo.st.lab3.env.RunEnvironment;
import itmo.st.lab3.pages.AuthPage;
import itmo.st.lab3.pages.EditProfilePage;
import itmo.st.lab3.pages.HomePage;
import itmo.st.lab3.pages.LoggedHomePage;
import itmo.st.lab3.pages.LoggedNavigator;
import itmo.st.lab3.pages.ProfilePage;

public class ProfilePageTest extends PageTest {
    @TestFactory
    public Stream<DynamicTest> editAboutTest() {
        return RunEnvironment.getWebDrivers().stream()
                .map(d -> dynamicTest(d.getClass().getName().replace(d.getClass().getPackageName(), ""), () -> {
                    StringBuilder sb = new StringBuilder(String.format("test%d", Math.round(Math.random() * 1000)));
                    HomePage homePage = new HomePage(d);
                    AuthPage authPage = homePage.goToAuthPage();
                    LoggedHomePage loggedHomePage = authPage.logIn(EMAIL, PASSWORD);
                    ProfilePage profilePage = ((LoggedNavigator) loggedHomePage.getNavigator()).goToProfilePage();
                    EditProfilePage editProfilePage = profilePage.goToEditProfilePage();
                    profilePage = editProfilePage.editAboutAndSave(sb.toString());
                    assertEquals(sb.toString(),
                            profilePage.goToProfileDisplay().getAboutSection().getText());
                }));
    }

    @TestFactory
    public Stream<DynamicTest> checkProfileNameTest() {
        return RunEnvironment.getWebDrivers().stream()
                .map(d -> dynamicTest(d.getClass().getName().replace(d.getClass().getPackageName(), ""), () -> {
                    HomePage homePage = new HomePage(d);
                    AuthPage authPage = homePage.goToAuthPage();
                    LoggedHomePage loggedHomePage = authPage.logIn(EMAIL, PASSWORD);
                    ProfilePage profilePage = ((LoggedNavigator) loggedHomePage.getNavigator()).goToProfilePage();
                    assertEquals("Einherjar",
                            profilePage.getProfileName().getAttribute("innerText"));
                }));
    }
}
