package tests.authorization;

import static org.assertj.core.api.Assertions.assertThat;
import static constants.Constants.ValidCredo.VALID_LOGIN;
import static constants.Constants.ValidCredo.VALID_PASS;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import pages.AuthorizationPage;
import pages.HappyPage;
import tests.BaseTest;

@Epic("Authorization")
@Feature("Login page")
@DisplayName("Авторизация")
public class AuthorizationTests extends BaseTest {
    private static AuthorizationPage authorizationPage;
    private static HappyPage happyPage;

    @Step("Инициализация классов экранов")
    @BeforeAll
    public static void init() {
        authorizationPage = new AuthorizationPage();
        happyPage = new HappyPage();
    }

    private static Stream<Arguments> methodSource() {
        return Stream.of(
                Arguments.of("Пробел и null", " ", ""),
                Arguments.of("Логин без пароля", "Login", ""),
                Arguments.of("Пробел в логине без пароля", " ", ""),
                Arguments.of("Пароль без логина", "", "Password"),
                Arguments.of("Пробел в поле пароля без логина", "", " "),
                Arguments.of("Пробелы везде", " ", " "),
                Arguments.of("Некорректные данные", "41343", "324243"),
                Arguments.of("Спецсимволы", "$$$%?$", "    ")
        );
    }

    @Test
    @Owner("Kurchenko V.")
    @DisplayName(value = "Тест на проверку успешной авторизации в приложение")
    public void positiveTest() {
        authorizationPage.enterLogin(VALID_LOGIN);
        authorizationPage.enterPass(VALID_PASS);
        authorizationPage.tapOnLogInButton();
        assertThat(happyPage.happyPageIsDisplayed())
                .as("После нажатия на 'Вход' не произошел переход на главный экран")
                .isTrue();
    }

    @ParameterizedTest(name = "{0}")
    @Owner("Kurchenko V.")
    @DisplayName(value = "Негативные кейсы - ")
    @MethodSource("methodSource")
    public void negativeCases(String name, String login, String password) {
        authorizationPage.enterLogin(login);
        authorizationPage.enterPass(password);
        authorizationPage.tapOnLogInButton();
        assertThat(authorizationPage.errorTextIsDisplayed())
                .as("Сообщение об ошибке авторизации не отобразилось")
                .isTrue();
    }
}