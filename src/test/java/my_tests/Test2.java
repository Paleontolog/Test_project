package my_tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class Test2 extends TestsPreparation {

    @Parameters("cityName")
    @Test
    public void test_2(String cityName) {
        FirstPage page = new FirstPage();
        page.clickCityInner();
        page.changeCityName(cityName);
        page.checkCityName();
        LoginForm loginForm = page.clickButtonAccount();
        String email = "Naglui.eretick@yandex.ru";
        loginForm.enterLogin(email);
        String password = "28301230aaMP";
        loginForm.enterPassword(password);
        MyProfile address = page.goToMyProfile();
        address.findCityInner();
        address.findDeliveryAddress();
        address.checkAddresses();
    }
}
