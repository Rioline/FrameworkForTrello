package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverConditions;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement logInButton = $x("//header[@data-testid='bignav']//a[@href='/login']");

    public void isMainPageDisplayed() {
        String TITLE = "Manage Your Team’s Projects From Anywhere | Trello";
        Selenide.webdriver().shouldHave(WebDriverConditions.title(TITLE));
    }

    public void clickLogInButton() {
        logInButton.click();
    }
}
