package pl.bugdemons.utils.config;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@org.springframework.context.annotation.Configuration
@UtilityClass
public class Configuration {

    /**
     * Loads Selenide properties and sets them as System properties
     */
    public void initSelenideProperties() {
        ConfigurationManager.getSelenideProperties().forEach((key, value) -> {
            log.info("Setting selenide property: {}={}", key, value);
            System.setProperty(key, value);
        });
    }
}
