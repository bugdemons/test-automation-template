package pl.bugdemons.temp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class ConfigurationManager {
    private static final String SELENIDE_PREFIX = "selenide.";

    private static final Map<String, String> configuration = createConfiguration();

    /**
     * Loads properties from configuration that starts with SELENIDE_PREFIX
     *
     * @return Map with key-value pairs of selenide properties
     */
    public static Map<String, String> getSelenideProperties() {
        Map<String, String> selenideProperties = new HashMap<>();
        for (Map.Entry<String, String> entry : configuration.entrySet()) {
            if (entry.getKey().startsWith(SELENIDE_PREFIX)) {
                selenideProperties.put(entry.getKey(), entry.getValue());
            }
        }
        return selenideProperties;
    }

    /**
     * Get value of property from configuration. Configuration need to bede initialized first!
     *
     * @param name full name of property e.g. "termchecker.email"
     * @return String value of property
     */
    public static String getValue(String name) {
        return configuration.get(name);
    }

    /**
     * Get value of property from configuration. Configuration need to bede initialized first!
     *
     * @param name of selenide property e.g. "timeout"
     * @return String value of property
     */
    public static String geSelenidePropertyValue(String name) {
        return getValue(SELENIDE_PREFIX + name);
    }


    /**
     * Add or update configuration value
     *
     * @param name  key of the value
     * @param value desired value as string
     */
    public static void setValue(String name, String value) {
        configuration.put(name, value);
    }

    /**
     * Loads all properties from configuration file and converts it into Map
     *
     * @return Map with key-value pairs of properties
     */
    private static Map<String, String> createConfiguration() {
        final Map<String, String> configuration = new HashMap<>();

        loadConfigurationFile("configuration/configuration.properties").forEach((key, value) -> configuration.put((String) key, (String) value));

        return configuration;
    }

    /**
     * Loads all properties from file
     *
     * @param filename path to configuration file in resources
     * @return set of properties
     */
    private static Properties loadConfigurationFile(String filename) {
        try {
            var properties = PropertiesLoaderUtils.loadAllProperties(filename);
            if (properties.isEmpty()) {
                throw new RuntimeException("Properties are empty!");
            }
            return properties;
        } catch (Exception exception) {
            log.error("Could not load configuration from file: {}", filename);
            throw new RuntimeException("Closing program - It would not work without configuration anyway");
        }
    }
}
