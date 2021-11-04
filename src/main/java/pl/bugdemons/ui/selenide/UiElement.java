package pl.bugdemons.ui.selenide;

import com.codeborne.selenide.SelenideElement;

public interface UiElement extends SelenideElement {

    UiElement setValueWithTab(String value);

    UiElement setValueWithEnter(String value);
}
