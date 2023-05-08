package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class AuthorizationPage extends BasePage {

    private final SelenideElement loginInputField = $(MobileBy.xpath(
            "//*[@resource-id='com.alfabank.qapp:id/etUsername']"));

    private final SelenideElement logInButton = $(MobileBy.id(
            "com.alfabank.qapp:id/btnConfirm"));

    private final SelenideElement passInputField = $(MobileBy.cssSelector(
            "[text^='Пароль']"));

    private final SelenideElement errorText = $(MobileBy.id(
            "com.alfabank.qapp:id/tvError"));

    @Step("Вводим логин")
    public void enterLogin(String login) {
        loginInputField.should(Condition.visible);
        loginInputField.sendKeys(login);
    }

    @Step("Вводим пароль")
    public void enterPass(String pass) {
        passInputField.sendKeys(pass);
    }

    @Step("Нажимаем на кнопку 'Вход'")
    public void tapOnLogInButton() {
        logInButton.click();
    }

    @Step("Проверяем отображение сообщения об ошибке")
    public Boolean errorTextIsDisplayed() {
        return errorText.isDisplayed();
    }
}