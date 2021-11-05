package pl.bugdemons.ui.selenide.browser;

import java.util.Objects;

import com.codeborne.selenide.Configuration;
import lombok.extern.slf4j.Slf4j;

import pl.bugdemons.utils.config.ConfigurationManager;

import static pl.bugdemons.ui.selenide.browser.capabilities.ChromeCapabilities.getChromeCapabilities;
import static pl.bugdemons.ui.selenide.browser.capabilities.EdgeCapabilities.getEdgeCapabilities;
import static pl.bugdemons.ui.selenide.browser.capabilities.FirefoxCapabilities.getFirefoxCapabilities;

@Slf4j
public class BrowserConfig {

    private BrowserConfig() {
    }

    public static void initBrowser() {
        log.debug("Setting up browser configuration...");
        var browser = getBrowserType();
        if (Objects.nonNull(System.getProperty("grid"))) {
            Configuration.remote = ConfigurationManager.getValue("hub.url");
        }

        switch (browser) {
            case EDGE:
                Configuration.browserCapabilities = getEdgeCapabilities();
                break;
            case FIREFOX:
                Configuration.browserCapabilities = getFirefoxCapabilities();
                break;
            case CHROME:
            default:
                Configuration.browserCapabilities = getChromeCapabilities();
        }
        log.debug("Browser configuration loaded successfully");
    }

    private static Browsers getBrowserType() {
        var browser = ConfigurationManager.geSelenidePropertyValue("browser");
        var sysPropBrowser = System.getProperty("browser");
        if (Objects.nonNull(sysPropBrowser)) {
            log.debug("Overriding selenide.browser [{}] by system browser property [{}]", browser, sysPropBrowser);
            Configuration.browser = sysPropBrowser;
            browser = sysPropBrowser;
        }

        return Browsers.valueOf(browser);
    }
}
