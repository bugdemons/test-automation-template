package pl.bugdemons.ui.selenide;

import com.codeborne.selenide.SelenideElement;

public interface CustomElement extends SelenideElement {

    CustomElement setValueWithTab(String value);

    CustomElement setValueWithEnter(String value);
}
