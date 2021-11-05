package pl.bugdemons.utils.config;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import pl.bugdemons.utils.junit.tag.Unit;

import static org.assertj.core.api.Assertions.assertThat;

@Unit
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
        for (var property : selenideProperties) {
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
