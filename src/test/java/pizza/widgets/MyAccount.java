package pizza.widgets;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MyAccount {
    public SelenideElement myAccountButton = $("#menu-item-30");
    public SelenideElement nameInput = $("#username");
    public SelenideElement passportImport = $("#password");
    public SelenideElement buttonInto = $(".button");
    public SelenideElement registrationButton = $(".lost_password .custom-register-button");
    public SelenideElement textHelloName = $(".woocommerce-MyAccount-content strong");
    public SelenideElement exitIntAccount = $(".login-woocommerce");
    public SelenideElement loguot = $(".logout");
    public SelenideElement account = $(".account");

    @Step("Click to my account from menu")
    public void intoMyAccForm() {
        if(loguot.isDisplayed()){
            loguot.click();
        }else {
            myAccountButton.click();
        }

    }

    @Step("Set passport {name}")
    public void setName(String name) {
        nameInput.setValue(name);
    }

    @Step("Set passport {passport}")
    public void setPass(String passport) {
        passportImport.setValue(passport);
    }

    @Step("Click button registration")
    public void clickButton() {
        buttonInto.click();
    }

    @Step("Assert title - 'Hello username' after registration, then exit")
    public void assertConfirmAuthorization(String title) {
        textHelloName.shouldHave(text(title));
        exitIntAccount.click();
        account.shouldBe(visible);
    }

    @Step("Click to button for registration")
    public void intoRegForm() {
        registrationButton.click();
    }

    @Step("Loguot")
    public void exitInAccount() {
        if (loguot.getText().equals("Выйти")) {
            loguot.click();
        }
        Assertions.assertEquals(account.getText(),"Войти");
    }

}
