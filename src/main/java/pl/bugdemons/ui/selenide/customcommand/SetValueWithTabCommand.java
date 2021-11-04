package pl.bugdemons.ui.selenide.customcommand;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.Keys;

import pl.bugdemons.ui.selenide.UiElement;

@ParametersAreNonnullByDefault
public class SetValueWithTabCommand implements Command<UiElement> {

    @Nonnull
    @Override
    public UiElement execute(SelenideElement element, WebElementSource locator, @Nullable Object[] args) {
        element.setValue((String) args[0]).sendKeys(Keys.TAB);

        return (UiElement) element;
    }
}
