package com.epam.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverConditions;

import static com.codeborne.selenide.Selenide.*;
import static com.epam.utils.PropertyUtils.getBaseUrl;

public class MainPage {


    private final SelenideElement logInButton = $x("//header[@data-testid='bignav']//a[@href='/login']");

    public void openHomePage() {
        open(getBaseUrl());
    }

    public void isMainPageDisplayed() {
        String TITLE = "Manage Your Team’s Projects From Anywhere | Trello";
        webdriver().shouldHave(WebDriverConditions.title(TITLE));
    }

    public void clickLogInButton() {
        logInButton.click();
    }
}
