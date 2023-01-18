package com.epam.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.Browsers.*;
import static com.epam.utils.PropertyUtils.*;

public class WebDriverFactory {

    public static void getWebDriverInstance() {
        createWebDriver();
    }

    public static void closeWebDriverInstance() {
        shutdownWebDriver();
    }

    private static void createWebDriver() {

        if (isRemoteExecution()) {
            Configuration.remote = getRemoteHost();
        }

        switch (getBrowser()) {
            case CHROME:
                Configuration.driverManagerEnabled = isDriverManagerEnabled();
                break;
            case EDGE:
                Configuration.reopenBrowserOnFail = isReopenBrowserOnFail();
                break;
            case FIREFOX:
                break;
            default:
                System.out.println("Please check properties file for any mismatch. Current browser is: " + getBrowser());
                break;
        }
        Configuration.browser = getBrowser();
        Configuration.pageLoadStrategy = getPageLoadStrategy();
        Configuration.timeout = getTimeout();
        Configuration.headless = isHeadless();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    private static void shutdownWebDriver() {
        WebDriverRunner.closeWebDriver();
    }
}
