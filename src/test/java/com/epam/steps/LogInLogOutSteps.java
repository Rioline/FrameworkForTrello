package com.epam.steps;

import com.epam.pages.BoardsPage;
import com.epam.pages.LoginPage;
import com.epam.pages.MainPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogInLogOutSteps extends AbstractStep {

    private final MainPage mainPage;
    private final LoginPage loginPage;
    private final BoardsPage boardsPage;

    public LogInLogOutSteps(MainPage mainPage, LoginPage loginPage, BoardsPage boardsPage) {
        this.mainPage = mainPage;
        this.loginPage = loginPage;
        this.boardsPage = boardsPage;
    }

    @When("^Opened main page$")
    public void openMainPage() {
        mainPage.openHomePage();
    }

    @When("^I click log in$")
    public void clickOnLogIn() {
        mainPage.clickLogInButton();
    }

    @When("^I enter user email and press continue$")
    public void enterUserEmailAndPressContinue() {
        loginPage.fillUserEmail();
        loginPage.clickContinueButton();
    }

    @When("^I enter password and press log in$")
    public void enterPasswordAndPressLogIn() {
        loginPage.fillUserPassword();
        loginPage.clickLogInButton();
    }

    @Then("^Boards page is opened$")
    public void checkTitleOfBoardsPage() {
        boardsPage.isBoardsPageDisplayed();
    }

    @When("^I click log out and confirm it$")
    public void clickLogOutAndConfirmIt() {
        boardsPage.clickAccountButton();
        boardsPage.clickLogOutButton();
        boardsPage.clickLogOutSubmitButton();
    }

    @Then("^Main page is opened$")
    public void checkTitleOfMainPage() {
        mainPage.isMainPageDisplayed();
    }

    @When("^I create new board$")
    public void createNewRandomTitleBoard() {
        boardsPage.inputBoardTitleWithRandomNameAndGoOnBoards();
    }

    @When("^I delete all boards$")
    public void deleteAllBoards() {
        boardsPage.deleteAllBoards();
    }

    @Then("^All boards are deleted$")
    public void isBoardsEmpty() {
        boardsPage.isBoardsEmpty();
    }
}
