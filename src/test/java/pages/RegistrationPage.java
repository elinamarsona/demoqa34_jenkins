package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultComponent;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderSelect = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesSelect = $("#hobbiesWrapper"),
            pictureSelect = $("#uploadPicture"),
            userAddressInput = $("#currentAddress"),
            stateAndCitySelect = $("#stateCity-wrapper"),
            submitButton = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultComponent resultComponent = new ResultComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".text-center").shouldHave(text("Practice Form"));
        return this;
    }

    public RegistrationPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }


    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderSelect.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setBirthDay(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(String value) {
        hobbiesSelect.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setPicture(String value) {
        pictureSelect.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage setAddress(String value) {
        userAddressInput.scrollTo().setValue(value);
        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        $("#state").click();
        stateAndCitySelect.$(byText(state)).click();
        $("#city").click();
        stateAndCitySelect.$(byText(city)).click();
        return this;
    }

    public void submitForm() {
        submitButton.click();
    }

     public RegistrationPage checkResult(String key, String value){
        resultComponent.checkResult(key, value);
        return this;
    }

    public void checkInvalidInput() {
        userNumberInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

    }
}
