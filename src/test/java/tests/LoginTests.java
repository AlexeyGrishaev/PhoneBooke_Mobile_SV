package tests;

import config.AppiumConfig;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    @Test
    public void loginSuccess(){
        new SplashScreen(driver)
                .checkCurrentVersion("1.0.0")
                .fillEmail("locker@gmail.com")
                .fillPassword("Qwerty1234!")
                .submitLogin()
                .isContactListDisplayed("Contact list");

    }
}
