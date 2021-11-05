package pl.bugdemons.ui.selenide.browser.capabilities;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxCapabilities {

    private FirefoxCapabilities() {
    }

    public static DesiredCapabilities getFirefoxCapabilities() {
        var options = new FirefoxOptions();
        options.addArguments("-private");

        var capabilities = new DesiredCapabilities();
        capabilities.merge(options);

        return capabilities;
    }
}
