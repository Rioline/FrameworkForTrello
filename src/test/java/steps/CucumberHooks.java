package steps;

import hooks.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks {

    @Before
    public void initSelenideConfig() {
        WebDriverFactory.getWebDriverInstance();
    }

    @After
    public void tearDown() {
        WebDriverFactory.closeWebDriverInstance();
    }
}
