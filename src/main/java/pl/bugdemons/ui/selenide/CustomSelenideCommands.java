package pl.bugdemons.ui.selenide;

import com.codeborne.selenide.commands.Commands;
import com.codeborne.selenide.impl.ElementFinder;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import pl.bugdemons.ui.selenide.customcommand.SetValueWithEnterCommand;
import pl.bugdemons.ui.selenide.customcommand.SetValueWithTabCommand;

import static com.codeborne.selenide.WebDriverRunner.driver;

@Slf4j
public class CustomSelenideCommands {

    private CustomSelenideCommands() {
    }

    public static void setUpSelenideCustomCommands() {
        log.debug("Setting up custom commands...");
        //source code linked in readme.md
        Commands.getInstance().add("setValueWithTab", new SetValueWithTabCommand());
        Commands.getInstance().add("setValueWithEnter", new SetValueWithEnterCommand());
        log.debug("Custom commands loaded successfully");
    }

    /**
     * Replacement for standard Selenide `$` method.
     *
     * @param by By class with specified selector
     * @return CustomElement - an "advanced" version of `SelenideElement` with custom methods
     */
    public static CustomElement $_(By by) {
        return ElementFinder.wrap(driver(), CustomElement.class, null, by, 0);
    }

    /**
     * Replacement for standard Selenide `$` method.
     *
     * @param selector css selector
     * @return CustomElement - an "advanced" version of `SelenideElement` with custom methods
     */
    public static CustomElement $_(String selector) {
        return ElementFinder.wrap(driver(), CustomElement.class, null, By.cssSelector(selector), 0);
    }
}
