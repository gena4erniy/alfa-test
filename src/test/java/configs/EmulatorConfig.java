package configs;

import org.aeonbits.owner.Config;

//Чтение ключей из emulator.properties
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties", //Чтение env
        "file:src/test/Resources/configs/emulator.properties", //Чтение из файла
})
public interface EmulatorConfig extends Config {

    //Достаем значения по ключу
    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    String platformName();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

    @Key("app")
    String app();

    @Key("remoteURL")
    String remoteURL();
}