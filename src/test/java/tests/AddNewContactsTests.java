package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactsTests extends AppiumConfig {
    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver).fillLOginRegistrationForm(Auth.builder()
                .email("locker@gmail.com")
                .password("Qwerty1234!")
                .build())
                .submitLogin();
    }
    @Test(invocationCount = 5)
    public void createNewContactSuccess() {
        int i = new Random().nextInt(1000)+1000;

        Contact contact =  Contact.builder()
                .name("Simon"+i)
                .lastName("Wow"+i)
                .phone("987654321"+i)
                .email("Simon@"+i+"gmail.com")
                .address("NY")
                .description("Friend")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastName());

    }

    @Test
    public void createNewContactEmptyName() {
        int i = new Random().nextInt(1000)+1000;

        Contact contact =  Contact.builder()
                .lastName("Wow"+i)
                .phone("987654321"+i)
                .email("Simon@"+i+"gmail.com")
                .address("NY")
                .description("Friend")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{name=must not be blank}");

    }

    @Test
    public void createNewContactSuccessReq() {

    }

    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver)
                .logout();

    }
}
