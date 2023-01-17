package pages;

import com.codeborne.selenide.*;
import com.github.javafaker.Faker;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static utils.CustomCondition.clickable;

public class BoardsPage {

    private final SelenideElement accountButton = $x("//button[@aria-label='Open member menu']");
    private final SelenideElement logOutButton = $x("//button[@data-testid='account-menu-logout']");
    private final SelenideElement logOutSubmitButton = $x("//button[@id='logout-submit']");
    private final SelenideElement createNewBoard = $(".board-tile.mod-add");
    private final SelenideElement boardTitle = $x("//*[text()='Board title']/following-sibling::input");
    private final SelenideElement openBoardsLink = $x("//a[@data-testid='open-boards-link']");
    private final SelenideElement createButton = $x("//button[contains(text(),'Create')]");
    private final ElementsCollection listOfTables = $$x(
            "//*[contains(text(),'Your boards')]/ancestor::div[@data-testid='collapsible-list']//li");
    private final SelenideElement dropDownMenuInBoards = $x("//button[@aria-label='Board actions menu']");
    private final SelenideElement closeBoardButton = $x("//button[@title='Close board']");
    private final SelenideElement closeButton = $x("//button[@title='Close']");
    private final SelenideElement settingsTab = $x("//a[@data-testid='home-team-settings-tab']");
    private final SelenideElement noBoardsTitle = $x("//*[@data-testid='boards-list-empty-state']//p");
    private final SelenideElement deleteButton = $x("//button[contains(text(), 'Delete this Workspace')]");
    private final SelenideElement showMoreButton = $x("//button//*[contains(text(),'Show more')]");

    public void isBoardsPageDisplayed() {
        $x("//span[contains(text(),'Boards')]").shouldBe(appear);
        String TITLE = "Boards | Trello";
        Selenide.webdriver().shouldHave(WebDriverConditions.title(TITLE));
    }

    public void clickAccountButton() {
        accountButton.click();
    }

    public void clickLogOutButton() {
        logOutButton.click();
    }

    public void clickLogOutSubmitButton() {
        logOutSubmitButton.click();
    }

    public void inputBoardTitleWithRandomNameAndGoOnBoards() {
        for (int i = 0; i < 3; i++) {
            createNewBoard.shouldBe(clickable);
            createNewBoard.click();
            String boardName = new Faker().funnyName().name();
            boardTitle.setValue(boardName);
            createButton.click();
            createNewBoard.shouldBe(disappear);
            openBoardsLink.click();
            createNewBoard.shouldBe(clickable);
            if (showMoreButton.is(clickable)) {
                showMoreButton.click();
                $x("//span[contains(text(),'Show less')]").shouldBe(appear);
            }
            listOfTables.shouldHave(CollectionCondition.itemWithText(boardName));
        }
    }

    public void deleteAllBoards() {
        settingsTab.click();
        deleteButton.shouldBe(clickable);
        while (listOfTables.size() > 0) {
            for (SelenideElement listOfTable : listOfTables) {
                listOfTable.shouldBe(appear);
                listOfTable.hover();
                dropDownMenuInBoards.shouldBe(clickable).click();
                closeBoardButton.shouldBe(clickable).click();
                closeButton.shouldBe(clickable).click();
                listOfTable.shouldBe(disappear);
            }
        }
    }

    public void isBoardsEmpty() {
        noBoardsTitle.shouldBe(visible);
    }
}
