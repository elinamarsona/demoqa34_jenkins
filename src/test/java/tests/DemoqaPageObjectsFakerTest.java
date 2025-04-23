package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomUtils;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tag("registration")
@DisplayName("Проверка регистрации на странице demoqa")
public class DemoqaPageObjectsFakerTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    RandomUtils randomUtils = new RandomUtils();

    @DisplayName("Успешная регистрация при заполнении всех полей")
    @Test
    void successRegistrationTest() {
        step("Открыть страницу и заполнить все поля", () -> {
        registrationPage.openPage()
                .setFirstName(randomUtils.firstName)
                .setLastName(randomUtils.lastName)
                .setUserEmail(randomUtils.userEmail)
                .setGender(randomUtils.gender)
                .setUserNumber(randomUtils.userNumber)
                .setBirthDay(randomUtils.day, randomUtils.month, randomUtils.year)
                .setSubjects(randomUtils.subject)
                .setHobbies(randomUtils.hobbies)
                .setPicture(randomUtils.uploadImage)
                .setAddress(randomUtils.currentAddress)
                .setStateAndCity(randomUtils.state, randomUtils.city);
        });
        step("Нажать кнопку Submit", () -> {
            registrationPage.submitForm();
        });
        step("Проверить результат регистрации", () -> {
        registrationPage.checkResult("Student Name", randomUtils.firstName + " " + randomUtils.lastName)
                .checkResult("Student Email", randomUtils.userEmail)
                .checkResult("Gender", randomUtils.gender)
                .checkResult("Mobile", randomUtils.userNumber)
                .checkResult("Date of Birth", randomUtils.day + " " + randomUtils.month + "," + randomUtils.year)
                .checkResult("Subjects", randomUtils.subject)
                .checkResult("Hobbies", randomUtils.hobbies)
                .checkResult("Picture", "2.jpg")
                .checkResult("Address", randomUtils.currentAddress)
                .checkResult("State and City", randomUtils.state + " " + randomUtils.city);
        });
    }

    @DisplayName("Успешная регистрация при заполнении только обязательных полей")
    @Test
    void successRegistrationMinimalInputTest() {
        step("Открыть страницу и заполнить обязательные поля", () -> {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(randomUtils.firstName)
                .setLastName(randomUtils.lastName)
                .setGender(randomUtils.gender)
                .setUserNumber(randomUtils.userNumber);
        });
        step("Нажать кнопку Submit", () -> {
            registrationPage.submitForm();
        });
        step("Проверить результат регистрации", () -> {
        registrationPage.checkResult("Student Name", randomUtils.firstName + " " + randomUtils.lastName)
                 .checkResult("Gender", randomUtils.gender)
                .checkResult("Mobile", randomUtils.userNumber);
        });
    }

    @DisplayName("Несостоявшаяся регистрация при нажатии только кнопки Submit")
    @Test
    void negativeRegistrationTest1() {
        step("Открыть страницу", () -> {
        registrationPage.openPage()
                .removeBanner();
        });
        step("Нажать кнопку Submit", () -> {
            registrationPage.submitForm();
        });
        step("Проверить результат регистрации", () -> {
        registrationPage.checkInvalidInput();
        });
    }

    @DisplayName("Несостоявшаяся регистрация при заполнении одного обязательного поля и нажатии кнопки Submit")
    @Test
    void negativeRegistrationTest2() {
        step("Открыть страницу и заполнить одно обязательное поле", () -> {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(randomUtils.firstName);
        });
        step("Нажать кнопку Submit", () -> {
            registrationPage.submitForm();
        });
        step("Проверить результат регистрации", () -> {
            registrationPage.checkInvalidInput();
        });
    }

}
