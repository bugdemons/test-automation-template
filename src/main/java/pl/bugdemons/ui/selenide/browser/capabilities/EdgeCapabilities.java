package pl.bugdemons.ui.selenide.browser.capabilities;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class EdgeCapabilities {

    private EdgeCapabilities() {

    }

    public static DesiredCapabilities getEdgeCapabilities() {
        var options = new EdgeOptions();
        options.setCapability("InPrivate", true);

        var capabilities = new DesiredCapabilities();
        capabilities.merge(options);

        return capabilities;
    }
}
