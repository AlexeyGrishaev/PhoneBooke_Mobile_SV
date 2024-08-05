package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class DeleteContactTests extends AppiumConfig {
    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver).fillLOginRegistrationForm(Auth.builder()
                        .email("locker@gmail.com")
                        .password("Qwerty1234!")
                        .build())
                .submitLogin();

//        int i = new Random().nextInt(1000)+1000;
//
//        Contact contact =  Contact.builder()
//                .name("Simon"+i)
//                .lastName("Wow"+i)
//                .phone("987654321"+i)
//                .email("Simon@"+i+"gmail.com")
//                .address("NY")
//                .description("Friend")
//                .build();
//
//        new ContactListScreen(driver)
//                .openContactForm()
//                .fillContactForm(contact)
//                .submitContactForm()
//                .isContactAddedByName(contact.getName(),contact.getLastName());
    }

    @Test
    public void removeOneContactSuccess(){
        new ContactListScreen(driver).deleteFirstContact().isListSizeLessOnOne();
    }

    @Test
    public void removeALlContactSuccess(){
        new ContactListScreen(driver).removeAllContacts().isNotContactsHere();
    }

}
