package pl.bugdemons.ui.selenide.browser.capabilities;

import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class InternetExplorerCapabilities {

    private InternetExplorerCapabilities() {

    }

    public static DesiredCapabilities getInternetExplorerCapabilities() {
        var options = new InternetExplorerOptions();
        options.addCommandSwitches("-private");

        var capabilities = DesiredCapabilities.internetExplorer();
        capabilities.merge(options);

        return capabilities;
    }
}
