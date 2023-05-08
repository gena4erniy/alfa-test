package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class HappyPage extends BasePage {

    private final SelenideElement textOnHappyPage = $(MobileBy.xpath(
            "//*[contains(@text, 'Вход в Alfa-Test выполнен')]"));

    @Step("Проверем отображение надписи приветствия на главном экране")
    public Boolean happyPageIsDisplayed() {
        return textOnHappyPage.isDisplayed();
    }
}