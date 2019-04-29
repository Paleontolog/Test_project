package my_tests;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginForm extends TestsPreparation {

    //"Naglui.eretick@yandex.ru"
    @Step("Enter login")
    public void enterLogin(String login) {
        WebElement logInForm = (new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".passp-login-form"))));
        WebElement element = findAndAllureSc(logInForm, By.name("login"));
        element.click();
        element.sendKeys(login);
        element.sendKeys(Keys.ENTER);
    }

    //"28301230aaMP"
    @Step("Enter password")
    public void enterPassword(String password) {
        WebElement PassForm = (new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".passp-password-form"))));
        WebElement element = findAndAllureSc(PassForm, By.name("passwd"));
        element.sendKeys(password);
        element.sendKeys(Keys.ENTER);
    }
}
