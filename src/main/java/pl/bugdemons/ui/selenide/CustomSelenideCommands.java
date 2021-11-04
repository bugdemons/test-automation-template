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
        Commands.getInstance().add("setValueWithTab", new SetValueWithTabCommand());
        Commands.getInstance().add("setValueWithEnter", new SetValueWithEnterCommand());
    }

    public static UiElement $_(By by) {
        return ElementFinder.wrap(driver(), UiElement.class, null, by, 0);
    }
}
