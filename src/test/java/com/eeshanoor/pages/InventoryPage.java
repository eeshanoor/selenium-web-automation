package com.eeshanoor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class InventoryPage {
    private final WebDriver driver;

    private final By inventoryContainer = By.id("inventory_container");
    private final By inventoryItems     = By.className("inventory_item");
    private final By sortDropdown       = By.className("product_sort_container");
    private final By addToCartButtons   = By.cssSelector("[data-test^='add-to-cart']");
    private final By cartBadge          = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        return driver.findElement(inventoryContainer).isDisplayed();
    }

    public int getItemCount() {
        return driver.findElements(inventoryItems).size();
    }

    public void sortBy(String option) {
        new Select(driver.findElement(sortDropdown)).selectByVisibleText(option);
    }

    public void addFirstItemToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) buttons.get(0).click();
    }

    public int getCartCount() {
        List<WebElement> badges = driver.findElements(cartBadge);
        return badges.isEmpty() ? 0 : Integer.parseInt(badges.get(0).getText());
    }
}