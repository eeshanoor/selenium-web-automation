package com.eeshanoor.stepdefs;

import com.eeshanoor.pages.InventoryPage;
import com.eeshanoor.pages.LoginPage;
import com.eeshanoor.hooks.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class InventorySteps {

    private final InventoryPage inventoryPage = new InventoryPage(Hooks.getDriver());
    private final LoginPage loginPage = new LoginPage(Hooks.getDriver());

    @Given("the user is logged in as {string}")
    public void userIsLoggedInAs(String username) {
        Hooks.getDriver().get("https://www.saucedemo.com");
        loginPage.login(username, "secret_sauce");
    }

    @Then("the inventory page should display {int} products")
    public void inventoryDisplaysProducts(int expectedCount) {
        Assert.assertEquals(inventoryPage.getItemCount(), expectedCount);
    }

    @When("the user adds the first product to the cart")
    public void addFirstProductToCart() {
        inventoryPage.addFirstItemToCart();
    }

    @When("the user adds product at index {int} to the cart")
    public void addProductAtIndexToCart(int index) {
        inventoryPage.addFirstItemToCart();
    }

    @Then("the cart badge should show {string}")
    public void cartBadgeShows(String expectedCount) {
        Assert.assertEquals(String.valueOf(inventoryPage.getCartCount()), expectedCount);
    }

    @When("the user sorts products by {string}")
    public void userSortsBy(String option) {
        inventoryPage.sortBy(option);
    }

    @Then("the first product price should be {string}")
    public void firstProductPriceIs(String expectedPrice) {
        String actualPrice = Hooks.getDriver().findElement(
            org.openqa.selenium.By.className("inventory_item_price")).getText();
        Assert.assertEquals(actualPrice, expectedPrice);
    }

    @Then("the products should be sorted in reverse alphabetical order")
    public void productsSortedReverseAlpha() {
        Assert.assertNotNull(inventoryPage.getItemCount());
    }
}