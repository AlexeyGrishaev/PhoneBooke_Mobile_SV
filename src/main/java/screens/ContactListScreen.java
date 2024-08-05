package screens;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;

import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@class='android.widget.TextView']")
    AndroidElement activityTextView;
    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement menuOption;
    @FindBy(xpath = "//*[@text='Logout']")
    AndroidElement logoutBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    AndroidElement plusBtn;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<AndroidElement> contactNameList;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<AndroidElement> contactList;

    @FindBy(id = "android:id/button1")
    AndroidElement yesBtn;

    @FindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    AndroidElement noContactsHereTextView;

    int countBefore=0;
    int countAfter=0;

    public ContactListScreen deleteFirstContact(){
        isActivityTitleDisplayed("Contact list");

        AndroidElement first = contactList.get(0);
        countBefore = contactList.size();



        Rectangle rectangle = first.getRect();
        int xFrom = rectangle.getX() + rectangle.getWidth() / 8;
        int y = rectangle.getY() + rectangle.getHeight() / 2;
        int xTo = rectangle.getX() + (rectangle.getWidth() / 8) * 7;;
        //int xTo2=rectangle.getWidth()-xFrom;


        TouchAction<?> touch = new TouchAction<>(driver);
        touch.longPress(PointOption.point(xFrom,y)).moveTo(PointOption.point(xTo,y)).release().perform();


        should(yesBtn,5);
        yesBtn.click();
        
        countAfter =contactList.size();;
        return this;

    }

    public ContactListScreen isListSizeLessOnOne() {
        Assert.assertEquals(countBefore-countAfter,1);
        return  this;
    }

    public boolean isActivityTitleDisplayed(String text){
       // return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView,text,10);
    }

    public AuthenticationScreen logout(){
        if(activityTextView.getText().equals("Contact list")) {
            menuOption.click();
            logoutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }

    public ContactListScreen isAccountOpened() {

        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));

        return this;
    }

    public AddNewContactScreen openContactForm(){
        pause(1000);
        if(activityTextView.getText().equals("Contact list")) {
            plusBtn.click();
        }
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isContactAddedByName(String name, String lastName) {

        isShouldHave(activityTextView,"Contact list",10);
        System.out.println("contact size"+contactNameList.size());
        boolean isPresent = false;
        for (AndroidElement element:contactNameList){
            if(element.getText().equals(name+" "+lastName)){
                isPresent = true;
                break;
            }
        }

        Assert.assertTrue(isPresent);
        return this;
    }

    public ContactListScreen removeAllContacts() {
        pause(1000);
        while(contactList.size()>0){
            deleteFirstContact();
        }

        return this;
    }

    public ContactListScreen isNotContactsHere() {

        isShouldHave(noContactsHereTextView,"No Contacts. Add One more!",10);
        return this;
    }
}
