package pl.bugdemons.ui.selenide.browser.capabilities;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeCapabilities {

    private ChromeCapabilities() {
    }

    public static DesiredCapabilities getChromeCapabilities() {
        var options = new ChromeOptions();
        options.addArguments("--incognito");

        var capabilities = new DesiredCapabilities();
        capabilities.merge(options);

        return capabilities;
    }
}
