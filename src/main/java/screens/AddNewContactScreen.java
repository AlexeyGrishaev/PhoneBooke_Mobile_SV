package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;

public class AddNewContactScreen extends BaseScreen {
    public AddNewContactScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputName']")
    AndroidElement nameEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputLastName']")
    AndroidElement lastNameEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPhone']")
    AndroidElement phoneEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    AndroidElement emailEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputAddress']")
    AndroidElement addressEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputDesc']")
    AndroidElement descriptionEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/createBtn']")
    AndroidElement createBtn;


    public AddNewContactScreen fillContactForm(Contact contact) {
        should(nameEditText, 20);
        type(nameEditText,contact.getName());
        type(lastNameEditText,contact.getLastName());
        type(phoneEditText,contact.getPhone());
        type(emailEditText,contact.getEmail());
        type(addressEditText,contact.getAddress());
        type(descriptionEditText,contact.getDescription());
        return this;
    }

    public ContactListScreen submitContactForm(){
        createBtn.click();

        return new ContactListScreen(driver);
    }

    public AddNewContactScreen submitContactFormNegative() {
        createBtn.click();

        return this;
    }

    public AddNewContactScreen isErrorContainsText(String text) {
        checkAlertText(text);
        return this;

    }
}
