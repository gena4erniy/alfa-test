package driver;

//Класс помощник для Page страниц
public class EmulatorHelper extends EmulatorDriver {

    //Нажимаем кнопку назад
    public static void goBack() {
        driver.navigate().back();
    }

    //Листаем к элементу по его тексту
    public static void androidScrollToAnElementByText(String text) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)" +
                        ".instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))")
                .click();
    }

    //Закрываем клавиатуру если она есть
    public static void closeKeyBoard() {
        if (driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }
    }
}