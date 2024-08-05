package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

public class LoginSecondTests extends AppiumConfig {
    @Test
    public void loginSuccess(){
        new AuthenticationScreen(driver)
                .fillEmail("locker@gmail.com")
                .fillPassword("Qwerty1234!")
                .submitLogin()
                .isAccountOpened()
                .logout();

    }

    @Test
    public void loginSuccessModel(){
        new AuthenticationScreen(driver)
                .fillLOginRegistrationForm(Auth.builder()
                        .email("locker@gmail.com")
                        .password("Qwerty1234!")
                        .build())
                .submitLogin()
                .isAccountOpened()
                .logout();

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

}
