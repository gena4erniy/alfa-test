package helpers;

import configs.ConfigReader;
import driver.EmulatorDriver;

public class RunHelper {

    private RunHelper() {
    }

    //Статичный консртуктор для вызова методов из класса без создания экземпляра
    public static RunHelper runHelper() {
        return new RunHelper();
    }

    //Реализуем DriverManager через кастомные классы для каждого варианта девайса
    public Class<?> getDriverClass() {
        String deviceHost = ConfigReader.testConfig.deviceHost();

        switch (deviceHost) {
            case "emulator":
                return EmulatorDriver.class; //Класс для инициализации сессии для эмулятора
            case "real":
                //    return RealMobileDriver.class; можно создать этот класс и реализовать логику
                //    для реальных девайсов
            default:
                throw new RuntimeException("В файле конфигурации нет параметра deviceHost: " +
                        "browserstack/selenoid/emulator/real");
        }
    }
}