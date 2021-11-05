package pl.bugdemons.ui.page.calculator;

import com.codeborne.selenide.Condition;

import static pl.bugdemons.ui.selenide.CustomSelenideCommands.$_;

public class CookiesComponent {

    private static final String COOKIES_POPUP_LOCATOR = ".fc-dialog-container";
    private static final String ACCEPT_BUTTON_LOCATOR = "[aria-label='Consent']";

    public boolean isCookiesPopupDisplayed() {
        return $_(COOKIES_POPUP_LOCATOR).isDisplayed();
    }

    public void closePopup() {
        if (isCookiesPopupDisplayed()) {
            $_(ACCEPT_BUTTON_LOCATOR).click();
            $_(COOKIES_POPUP_LOCATOR).should(Condition.disappear);
        }
    }
}
