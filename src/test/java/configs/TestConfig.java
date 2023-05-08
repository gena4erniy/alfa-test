package configs;

import org.aeonbits.owner.Config;

//Чтение ключей из test.properties
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties", //Читаем env
        "file:src/test/resources/configs/test.properties", //Читаем из файла test
})
public interface TestConfig extends Config {
    @Key("updateScreenshots")
    @DefaultValue("false")
    boolean isScreenshotsNeedToUpdate();

    @Key("deviceHost")
    String deviceHost();
}