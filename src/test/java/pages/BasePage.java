package pages;

import static com.codeborne.selenide.Selenide.$;

import java.io.File;

import io.appium.java_client.MobileBy;

public abstract class BasePage {

    //Делает скриншот экрана
    public File fullPageScreenshot() {
        return $(MobileBy.id("composerRootCl")).screenshot();
    }
}
