package com.eeshanoor.stepdefs;

import com.eeshanoor.pages.LoginPage;
import com.eeshanoor.pages.InventoryPage;
import com.eeshanoor.hooks.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage(Hooks.getDriver());
    private final InventoryPage inventoryPage = new InventoryPage(Hooks.getDriver());

    @Given("the user is on the SauceDemo login page")
    public void theUserIsOnLoginPage() {
        Hooks.getDriver().get("https://www.saucedemo.com");
    }

    @When("the user enters username {string} and password {string}")
    public void theUserEntersCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void theUserClicksLogin() {
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the inventory page")
    public void userIsOnInventoryPage() {
        Assert.assertTrue(inventoryPage.isLoaded(), "Should be on inventory page");
    }

    @Then("the page title should contain {string}")
    public void pageTitleContains(String expectedTitle) {
        Assert.assertTrue(Hooks.getDriver().getTitle().contains(expectedTitle));
    }

    @Then("an error message containing {string} should be displayed")
    public void errorMessageContains(String expectedError) {
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error should be visible");
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
            "Error message should contain: " + expectedError);
    }
}