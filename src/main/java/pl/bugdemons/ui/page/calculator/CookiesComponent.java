package pl.bugdemons.ui.page.calculator;

import com.codeborne.selenide.Condition;

import static pl.bugdemons.ui.selenide.CustomSelenideCommands.$;

public class CookiesComponent {

    private static final String COOKIES_POPUP_LOCATOR = ".fc-dialog-container";
    private static final String ACCEPT_BUTTON_LOCATOR = "[aria-label='Consent']";

    public boolean isCookiesPopupDisplayed() {
        return $(COOKIES_POPUP_LOCATOR).isDisplayed();
    }

    public void closePopup() {
        if (isCookiesPopupDisplayed()) {
            $(ACCEPT_BUTTON_LOCATOR).click();
            $(COOKIES_POPUP_LOCATOR).should(Condition.disappear);
        }
    }
}
