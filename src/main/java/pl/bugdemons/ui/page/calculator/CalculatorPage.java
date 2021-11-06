package pl.bugdemons.ui.page.calculator;

import java.util.List;
import java.util.stream.Collectors;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static pl.bugdemons.ui.selenide.CustomSelenideCommands.$;

public class CalculatorPage {

    private static final CookiesComponent COOKIES_COMPONENT = new CookiesComponent();
    private static final String INPUT_LOCATOR = "#input";
    private static final String RESULT_LOCATOR = "#result";
    private static final String RAD_RADIO_BUTTON_LOCATOR = "#trigorad";
    private static final String HISTORY_DROPDOWN_LOCATOR = ".dropdown-toggle.pull-right";
    private static final String HISTORY_LOCATOR = "#histframe";

    public CalculatorPage() {
        COOKIES_COMPONENT.closePopup();
    }

    public CalculatorPage calculateEquation(String value) {
        //example of using custom command
        $(INPUT_LOCATOR).setValueWithEnter(value);
        waitForResult();

        return this;
    }

    public CalculatorPage waitForResult() {
        var expectedCondition = Condition.attribute("style", "display: inline;");
        $(RESULT_LOCATOR).shouldHave(expectedCondition);

        return this;
    }

    public String getInputValue() {
        return $(INPUT_LOCATOR).getValue();
    }

    public CalculatorPage selectRadRadioButton() {
        $(RAD_RADIO_BUTTON_LOCATOR).click();

        return this;
    }

    public List<String> getHistoryElements() {
        $(HISTORY_DROPDOWN_LOCATOR).click();
        var history = $(HISTORY_LOCATOR);
        history.shouldHave(Condition.cssClass("open"));

        return history.$$(By.cssSelector("p[data-inp"))
                .stream()
                .map(element -> element.getAttribute("title"))
                .collect(Collectors.toList());
    }
}
