package pl.bugdemons.temp.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ConfigurationTest {
    @Test
    public void notInitializedConfigShouldNotBeVisibleInSystemProperties() {
        var selenideProperties = getSelenidePropertiesFromSystem();
        assertThat(selenideProperties).isEmpty();
    }

    @Test
    public void configInitsProperly() {
        Configuration.initSelenideProperties();
        var selenideProperties = getSelenidePropertiesFromSystem();
        assertThat(selenideProperties).isNotEmpty();
    }

    @AfterEach
    public void cleanup() {
        var selenideProperties = getSelenidePropertiesFromSystem();
        var systemProperties = System.getProperties();
        for (var property :
                selenideProperties) {
            systemProperties.remove(property);
        }
    }

    private List<String> getSelenidePropertiesFromSystem() {
        var systemProperties = System.getProperties();
        return systemProperties
                .stringPropertyNames()
                .stream()
                .filter(name -> name.contains("selenide"))
                .collect(Collectors.toList());
    }
}
