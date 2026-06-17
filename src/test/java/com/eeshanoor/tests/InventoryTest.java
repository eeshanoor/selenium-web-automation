package com.eeshanoor.tests;

import com.eeshanoor.base.BaseTest;
import com.eeshanoor.pages.InventoryPage;
import com.eeshanoor.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest {

    @BeforeMethod
    public void loginFirst() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
    }

    @Test(description = "Inventory page displays 6 products")
    public void testProductCount() {
        InventoryPage page = new InventoryPage(driver);
        Assert.assertEquals(page.getItemCount(), 6, "Should display 6 products");
    }

    @Test(description = "Add item to cart increments badge")
    public void testAddToCart() {
        InventoryPage page = new InventoryPage(driver);
        page.addFirstItemToCart();
        Assert.assertEquals(page.getCartCount(), 1, "Cart should show 1 item");
    }
}