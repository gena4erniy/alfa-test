package tests;

import static constants.Constants.SCREENSHOT_TO_SAVE_FOLDER;
import static helpers.DeviceHelper.executeBash;
import static helpers.RunHelper.runHelper;
import static io.qameta.allure.Allure.step;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import listeners.AllureListener;

//Базовый тестовый класс
@ExtendWith(AllureListener.class)
public abstract class BaseTest {

    @Step("Инициализация и конфигурация драйвера")
    @BeforeAll
    public static void setup() {
        //добавляем логирование действий для аллюр отчета в виде степов
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        //папка для сохранения скриншотов selenide
        Configuration.reportsFolder = SCREENSHOT_TO_SAVE_FOLDER;
        //инициализируем андройд драйвер
        Configuration.browser = runHelper().getDriverClass().getName();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
        disableAnimationOnEmulator();
    }

    //Отключение анимаций на эмуляторе
    private static void disableAnimationOnEmulator() {
        executeBash("adb -s shell settings put global transition_animation_scale 0.0");
        executeBash("adb -s shell settings put global window_animation_scale 0.0");
        executeBash("adb -s shell settings put global animator_duration_scale 0.0");
    }

    //Перед каждым тестом открываем приложение
    @BeforeEach
    public void startDriver() {
        step("Открыть приложение", (Allure.ThrowableRunnableVoid) Selenide::open);
    }

    //После каждого теста закрываем AndroidDriver чтобы тест атомарным был
    @AfterEach
    public void afterEach() {
        step("Закрыть приложение", Selenide::closeWebDriver);
    }
}
