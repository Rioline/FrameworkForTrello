package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.Browsers.*;
import static utils.PropertyUtils.*;

public class WebDriverFactory {

    public static void getWebDriverInstance() {
        createWebDriver();
    }

    public static void closeWebDriverInstance() {
        shutdownWebDriver();
    }

    private static void createWebDriver() {

        String browser =
                getPropString(  "selenide.browser",              "selenide.properties");
        String pageLoadStrategy =
                getPropString(  "selenide.pageLoadStrategy",     "selenide.properties");
        Integer timeout =
                getPropInteger( "selenide.timeout",              "selenide.properties");
        Boolean headless =
                getPropBoolean( "selenide.headless",             "selenide.properties");
        Boolean driverManagerEnabled =
                getPropBoolean( "selenide.driverManagerEnabled", "selenide.properties");
        Boolean reopenBrowserOnFail =
                getPropBoolean( "selenide.reopenBrowserOnFail",  "selenide.properties");
        Boolean remoteExecution =
                getPropBoolean( "selenide.remote.execution",     "selenide.properties");
        String  remoteHost =
                getPropString(  "selenide.remote.host",          "selenide.properties");

        if(remoteExecution) {
            Configuration.remote = remoteHost;
        }

        switch (browser) {
            case CHROME:
                Configuration.driverManagerEnabled = driverManagerEnabled;
                break;
            case EDGE:
                Configuration.reopenBrowserOnFail = reopenBrowserOnFail;
                break;
            case FIREFOX:
                break;
            default:
                System.out.println("Please check properties file for any mismatch. Specified browser is: " + browser);
                break;
        }
        Configuration.browser = browser;
        Configuration.pageLoadStrategy = pageLoadStrategy;
        Configuration.timeout = timeout;
        Configuration.headless = headless;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    private static void shutdownWebDriver() {
        WebDriverRunner.closeWebDriver();
    }
}
