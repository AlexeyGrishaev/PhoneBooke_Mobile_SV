package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {


    @Test
    public void loginSuccess() {
//      boolean result =   new SplashScreen(driver)
//                .checkCurrentVersion("1.0.0")
        boolean result = new AuthenticationScreen(driver)
                .fillEmail("locker@gmail.com")
                .fillPassword("Qwerty1234!")
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");

        Assert.assertTrue(result);

    }

    @Test
    public void loginSuccessModels() {


        boolean result = new AuthenticationScreen(driver)
                .fillLOginRegistrationForm(Auth.builder()
                        .email("locker@gmail.com")
                        .password("Qwerty1234!")
                        .build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");

        Assert.assertTrue(result);

    }

    @Test
    public void loginSuccessModels2() {
        Assert.assertTrue(new AuthenticationScreen(driver)
                .fillLOginRegistrationForm(Auth.builder()
                        .email("locker@gmail.com")
                        .password("Qwerty1234!")
                        .build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list"));
    }


    @Test
    public void loginWrongEmail() {
        new AuthenticationScreen(driver)
                .fillLOginRegistrationForm(Auth.builder()
                        .email("lockergmail.com")
                        .password("Qwerty1234!")
                        .build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");


    }
    @Test
    public void loginWrongPassword() {
        new AuthenticationScreen(driver)
                .fillLOginRegistrationForm(Auth.builder()
                        .email("lockergmail.com")
                        .password("Qerty12")
                        .build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");


    }

    @AfterMethod
    public void preCondition() {

        new ContactListScreen(driver).logout();
    }

}
