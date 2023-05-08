package listeners;

import com.codeborne.selenide.Selenide;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;

import java.lang.reflect.Method;

import io.qameta.allure.Attachment;

//Кастомный листенер для переопределения логики завершения теста
public class AllureListener implements AfterTestExecutionCallback {

    //Метод добавления скриншота в аллюр отчет через аннотацию
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    //Переопределение логики завершения тестов у JUni5
    @Override
    public void afterTestExecution(ExtensionContext context) {
        Method testMethod = context.getRequiredTestMethod(); //Получаем тестовый метод
        String testName = testMethod.getName(); //Получаем название тестового метода
        boolean testFailed = context.getExecutionException().isPresent(); //Проверяем упал ли тест
        if (testFailed) { //Если тест упал
            if (!testName.contains("Screenshot")) { //Если название метода не содержит Screenshot
                saveScreenshot(Selenide.screenshot(OutputType.BYTES)); //Добавляем скриншот к упавшему тесту
            }
        }
    }
}