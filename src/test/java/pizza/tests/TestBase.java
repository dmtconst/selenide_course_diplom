package pizza.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    String url = "http://pizzeria.skillbox.cc";

    @BeforeEach
    @Step("Open Pizzeria")
    public void openSite() {
        open(url);
    }

    @BeforeAll
    @DisplayName("Init Allure")
    static void initializeAllureSelenide(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

}
