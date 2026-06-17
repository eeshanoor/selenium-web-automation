# Selenium Web Automation
> **Eesha Noor** | SDET | Java + Selenium WebDriver + TestNG + Maven

## Tech Stack
- Java 11
- Selenium WebDriver 4.x
- TestNG
- Maven
- Page Object Model (POM)
- Extent Reports

## Test Coverage (SauceDemo)
- Login: valid/invalid credentials, locked user
- Inventory: product sorting, add to cart
- Cart: item count, remove item, proceed to checkout

## Run Tests
```bash
mvn clean test
mvn clean test -Dbrowser=chrome
mvn clean test -Denv=staging
```

## Project Structure
```
src/test/java/com/eeshanoor/
├── base/        BaseTest.java
├── pages/       LoginPage, InventoryPage, CartPage
└── tests/       LoginTest, CartTest, InventoryTest
```