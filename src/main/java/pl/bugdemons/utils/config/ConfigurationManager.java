package pl.bugdemons.utils.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@Slf4j
public class ConfigurationManager {

    private static final String SELENIDE_PREFIX = "selenide.";
    private static final Map<String, String> CONFIGURATION = createConfiguration();

    private ConfigurationManager() {
    }

    /**
     * Loads properties from configuration that starts with SELENIDE_PREFIX
     *
     * @return Map with key-value pairs of selenide properties
     */
    static Map<String, String> getSelenideProperties() {
        Map<String, String> selenideProperties = new HashMap<>();
        for (Map.Entry<String, String> entry : CONFIGURATION.entrySet()) {
            if (entry.getKey().startsWith(SELENIDE_PREFIX)) {
                selenideProperties.put(entry.getKey(), entry.getValue());
            }
        }
        return selenideProperties;
    }

    /**
     * Get value of property from configuration. Configuration need to bede initialized first!
     *
     * @param name full name of property e.g. "selenide.browser"
     * @return String value of property
     */
    public static String getValue(String name) {
        return CONFIGURATION.get(name);
    }

    /**
     * Get value mapped to Boolean
     *
     * @param name full name of property e.g. "selenide.browser"
     * @return Boolean
     */
    public static Boolean getFlag(String name) {
        return Boolean.parseBoolean(getValue(name));
    }

    /**
     * Get value of property from configuration. Configuration need to bede initialized first!
     *
     * @param name of selenide property e.g. "timeout"
     * @return String value of property
     */
    public static String getSelenideValue(String name) {
        return getValue(SELENIDE_PREFIX + name);
    }

    /**
     * Get property value mapped to Boolean from configuration. Configuration need to bede initialized first!
     *
     * @param name of selenide property e.g. "timeout"
     * @return String value of property
     */
    public static Boolean getSelenideFlag(String name) {
        return getFlag(SELENIDE_PREFIX + name);
    }

    /**
     * Add or update configuration value
     *
     * @param name  key of the value
     * @param value desired value as string
     */
    public static void setValue(String name, String value) {
        CONFIGURATION.put(name, value);
    }

    /**
     * Loads all properties from configuration file and converts it into Map
     *
     * @return Map with key-value pairs of properties
     */
    private static Map<String, String> createConfiguration() {
        final Map<String, String> configuration = new HashMap<>();

        loadConfigurationFile("configuration/configuration.properties")
                .forEach((key, value) -> configuration.put((String) key, (String) value));

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
