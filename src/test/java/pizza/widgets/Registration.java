package pizza.widgets;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Registration {
    public String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyy"));
    public String rndm = String.valueOf(1 + (int) (Math.random() * 1000));
    public SelenideElement name = $("#reg_username");
    public SelenideElement email = $("#reg_email");
    public SelenideElement passport = $("#reg_password");
    public SelenideElement button = $(".woocommerce-form-register__submit");
    public SelenideElement textRegistrationComplete = $(".content-page");
    public SelenideElement exit = $(".login-woocommerce");

    @Step("Set name + Random + LocalDate = {name}")
    public void setName(String name) {
        this.name.setValue(name + rndm + localDate);
    }

    @Step("Set email  + LocalDate + Random = {email}")
    public void setEmail(String email) {
        this.email.setValue(localDate + rndm + email);
    }

    @Step("Set passport = {passport}")
    public void setPassport(String passport) {
        this.passport.setValue(passport);
    }

    @Step("Click registration button")
    public void regButtonClick() {
        button.click();
    }

    @Step("assert title success registration, then exit")
    public void assertRegTitle(String textRegistrationIsComplete) {
        textRegistrationComplete.shouldHave(text(textRegistrationIsComplete));
    }
}
