package my_tests;

import io.qameta.allure.Step;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ProductListPage extends TestsPreparation {
    private List<WebElement> productList;

    @Step("Input minimum price")
    public void inputMinimumPrice(int price) {
        WebElement fieldPriceFrom = findAndAllureSc(driver, By.id("glpricefrom"));
        fieldPriceFrom.click();
        String priceStr = Integer.toString(price);
        fieldPriceFrom.sendKeys(priceStr);
        WebElement wind = driver.findElement(By.cssSelector("[class*='_1PQIIOelRL']"));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOf(wind));
    }

    @Step("Input maximum price")
    public void inputMaximumPrice(int price) {
        WebElement fieldPriceTo = findAndAllureSc(driver, By.id("glpriceto"));
        fieldPriceTo.click();
        String priceStr = Integer.toString(price);
        fieldPriceTo.sendKeys(priceStr);
        WebElement wind = driver.findElement(By.cssSelector("[class*='_1PQIIOelRL']"));
        (new WebDriverWait(driver, 40)).until(ExpectedConditions.visibilityOf(wind));
    }

    @Step("Show all product")
    public void showAllProduct() {
        while(true) {
            try {
                WebElement showNewElement = findAndAllureSc(driver, By.cssSelector(".n-pager-more__button"));
                showNewElement.click();
            } catch (Exception e) {
                break;
            }
        }
    }

    @Step("Get all products")
    public void getListAllProducts() {
        final int countElement = Integer.parseInt(
                findAndAllureSc(driver, By.cssSelector(".n-search-preciser__results-count"))
                        .getAttribute("textContent").split(" ")[1]);

        (new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver) {
                return driver.findElements(By
                        .cssSelector("[class*='grid-snippet_react']")).size() == countElement;
            }
        });

        productList = driver.findElements(By.cssSelector("[class*='grid-snippet_react']"));
    }

    @Step("Check list not empty")
    public void checkListNotEmpty() {
        Assert.assertNotNull(productList);
        Assert.assertTrue(productList.size() != 0);
    }

    @Step("Check price in range")
    public void checkPriceInRange(int minPrice, int maxPrice) {
        boolean flag = true;
        JSONParser parser = new JSONParser();
        for (WebElement element : productList) {
            try {
                JSONObject jsonObject = (JSONObject) parser.parse(element.getAttribute("data-bem"));
                int price = Integer.parseInt((((JSONObject)
                        ((JSONObject) jsonObject.get("b-zone")).get("data")).get("price")).toString());
                if (price  < minPrice || price > maxPrice) {
                    flag = false;
                    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(flag);
    }

    @Step("Add product in basket")
    public void addToBasket() {
        findAndAllureSc(productList.get(productList.size() - 2), By.cssSelector("[class*='_2w0qPDYwej']")).click();
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .cssSelector("[class*='_1sjxYfIabK _26mXJDBxtH']")));
    }

    @Step("Go to my basket")
    public ShoppingCartPage toMyBasket() {
        findAndAllureSc(productList.get(productList.size() - 2), By.cssSelector("[class*='_2w0qPDYwej']")).click();

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class*='_3AlSA6AOKL']")));
        return new ShoppingCartPage();
    }
}


