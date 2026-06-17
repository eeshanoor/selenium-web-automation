package com.eeshanoor.tests;

import com.eeshanoor.base.BaseTest;
import com.eeshanoor.pages.InventoryPage;
import com.eeshanoor.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Valid login navigates to inventory page")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should be loaded after login");
    }

    @Test(description = "Invalid credentials show error message")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "wrong_password");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @Test(description = "Locked out user sees appropriate error")
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"));
    }

    @Test(description = "Empty credentials show validation error")
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error should appear for empty fields");
    }
}