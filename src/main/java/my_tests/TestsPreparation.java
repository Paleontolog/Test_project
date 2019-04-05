package my_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestsPreparation {

    public static WebDriver driver;

    public static ScreenCreator screen;


    public WebElement findWithScreen(WebDriver d, By by) {
        WebElement temp = d.findElement(by);
        DateFormat formatForDateNow = new SimpleDateFormat("yyyy-mm-dd hh.mm.ss");
        screen.saveAllureScreenshot(temp, formatForDateNow.format(new Date()));
        return temp;
    }

    public WebElement findWithScreen(WebElement d, By by) {
        WebElement temp = d.findElement(by);
        DateFormat formatForDateNow = new SimpleDateFormat("yyyy-mm-dd hh.mm.ss");
        screen.saveAllureScreenshot(temp, formatForDateNow.format(new Date()));
        return temp;
    }

    @BeforeClass
    public void preparation() {
        //Указываем путь к драйверу
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("start-fullscreen");
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("disable-extensions"); // disabling extensions
        options.addArguments("disable-gpu"); // applicable to windows os only
        options.addArguments("disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("no-sandbox"); // Bypass OS security model
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
        screen = new ScreenCreator(driver, "C:\\Users\\Heretic\\IdeaProjects\\sample");
        // testContext.setAttribute("ScreenCreator", screen);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.get("https://beru.ru");

        WebElement el = findWithScreen(driver, By.cssSelector("[class*='_1ZYDKa22GJ']"));
        el.click();
    }

    @AfterClass
    public void clear() {
        driver.quit();
    }
}