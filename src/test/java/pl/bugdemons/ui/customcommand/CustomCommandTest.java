package pl.bugdemons.ui.customcommand;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import pl.bugdemons.ui.page.calculator.CalculatorPage;
import pl.bugdemons.ui.selenide.browser.BrowserConfig;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomCommandTest {

    CalculatorPage calculator;

    @BeforeAll
    public void setup() {
        BrowserConfig.initBrowser();
        open("https://web2.0calc.com/");
        calculator = new CalculatorPage();
    }

    @Test
    public void calculatorTest() {
        String calc1 = "35*999+(100/4)";
        String calc2 = "cos(pi)";
        String calc3 = "sqrt(81)";

        var result = calculator
                .calculateEquation(calc1)
                .getInputValue();

        assertThat(result).isEqualTo("34990");

        result = calculator
                .selectRadRadioButton()
                .calculateEquation(calc2)
                .getInputValue();

        assertThat(result).isEqualTo("-1");

        result = calculator
                .calculateEquation(calc3)
                .getInputValue();

        assertThat(result).isEqualTo("9");

        var historyElements = calculator.getHistoryElements();

        assertThat(historyElements).contains(calc1, calc2, calc3);
    }

}
