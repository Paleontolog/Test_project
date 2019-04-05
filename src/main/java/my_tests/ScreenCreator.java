package my_tests;

import io.qameta.allure.Attachment;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.remote.Augmenter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.cropper.indent.BlurFilter;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ScreenCreator {
    private WebDriver driver = null;
    private String path;
    ScreenCreator (WebDriver driver, String path) {
        this.driver = driver;
        this.path = path;
    }


    @Attachment(value = "{0}", type = "image/png")
    public byte[] saveAllureScreenshotError(String name) {
        return ((TakesScreenshot)(new Augmenter().augment(driver)))
                .getScreenshotAs(OutputType.BYTES);
    }


    @Attachment(value = "{1}", type = "image/png")
    public byte[] saveAllureScreenshot(WebElement element, String name) {
        return pageScreenAshot(element, name);
    }


    byte[] pageScreenAshot(WebElement element, String name) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = null;

        File to = new File(path + "\\" +  name + ".png");

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String code = "window.scroll(" + (element.getLocation().x) + ","
                    + (element.getLocation().y - 10) + ");";

            js.executeScript(code, element, 0, -10);

            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);

          /*  js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    element, "color: red; border: 2px solid red; border-color: red;");*/

            Screenshot sc = new AShot().imageCropper(new IndentCropper(1000).addIndentFilter(new BlurFilter()))
                    .takeScreenshot(driver, element);

            BufferedImage img = sc.getImage();
            ImageIO.write(img, "png", to);
            ImageIO.write(img, "png", baos);
            baos.flush();
            bytes = baos.toByteArray();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }
}