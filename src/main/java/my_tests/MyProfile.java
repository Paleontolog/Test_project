package my_tests;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MyProfile extends TestsPreparation {

    @Step("Find city inner")
    public WebElement findCityInner() {
        return driver.findElement(By.cssSelector("[class*='settings-list_type_region'] [class*='__inner']"));
    }

    @Step("Find delivery address")
    public WebElement findDeliveryAddress() {
        return driver.findElement(By.cssSelector("[class*='__region'] [class*='__inner']"));
    }

    @Step("Check addresses")
    public void checkAddresses() {
        Assert.assertEquals(findCityInner().getAttribute("textContent"),
                findDeliveryAddress().getAttribute("textContent"));
    }
}