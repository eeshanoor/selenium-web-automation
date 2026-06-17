package com.eeshanoor.tests;

import com.eeshanoor.base.BaseTest;
import com.eeshanoor.factory.TestDataFactory;
import com.eeshanoor.pages.InventoryPage;
import com.eeshanoor.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Map;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void loginFirst() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
    }

    @Test(description = "Complete E2E checkout flow with factory test data")
    public void testCompleteCheckoutFlow() {
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addFirstItemToCart();
        Assert.assertEquals(inventory.getCartCount(), 1);

        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.cssSelector("[data-test='checkout']")).click();

        Map<String, String> user = TestDataFactory.validCheckoutUser();
        driver.findElement(By.id("first-name")).sendKeys(user.get("firstName"));
        driver.findElement(By.id("last-name")).sendKeys(user.get("lastName"));
        driver.findElement(By.id("postal-code")).sendKeys(user.get("postalCode"));
        driver.findElement(By.cssSelector("[data-test='continue']")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two"));
        driver.findElement(By.cssSelector("[data-test='finish']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-complete"));
        Assert.assertTrue(driver.findElement(By.className("complete-header"))
            .getText().contains("Thank you"));
    }

    @Test(description = "Missing first name shows validation error")
    public void testCheckoutMissingFirstName() {
        new InventoryPage(driver).addFirstItemToCart();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.cssSelector("[data-test='checkout']")).click();
        driver.findElement(By.cssSelector("[data-test='continue']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='error']"))
            .getText().contains("First Name is required"));
    }
}