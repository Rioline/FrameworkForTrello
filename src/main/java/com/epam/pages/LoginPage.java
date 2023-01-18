package com.epam.pages;

import com.codeborne.selenide.SelenideElement;
import com.epam.utils.PropertyUtils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement enterEmailField = $x("//*[@id='user']");
    private final SelenideElement enterPasswordField = $x("//*[@id='password']");
    private final SelenideElement continueButton = $("#login[value='Continue']");
    private final SelenideElement logInButton = $("#login-submit");

    public void fillUserEmail() {
        enterEmailField.setValue(PropertyUtils.getUserEmail());
    }

    public void fillUserPassword() {
        enterPasswordField.setValue(PropertyUtils.getUserPassword());
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void clickLogInButton() {
        logInButton.click();
    }
}
