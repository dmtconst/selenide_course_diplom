package pizza.widgets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Order {
    String regForSum = "[^0-9,\\\\+]";
    public SelenideElement orderMenu = $("#menu-item-31");
    public SelenideElement makeOrderButton = $("#place_order");
    public SelenideElement billingFirstName = $("#billing_first_name");
    public SelenideElement billingLastName = $("#billing_last_name");
    public SelenideElement billingAddress = $("#billing_address_1");
    public SelenideElement billingPostcode = $("#billing_postcode");
    public SelenideElement billingCity = $("#billing_city");
    public SelenideElement billingPhone = $("#billing_phone");
    public SelenideElement billing_state = $("#billing_state");
    public SelenideElement calendar = $("#order_date");
    public SelenideElement paymentMethodCod = $("[for='payment_method_cod']");
    public SelenideElement agree = $("#terms");
    public SelenideElement detailsPayMetod = $(".woocommerce-order > p:nth-of-type(2)");
    public SelenideElement orderSum = $(".total .amount:nth-of-type(1)");
    public ElementsCollection totalSum = $$("tfoot  .amount:nth-of-type(1)");
    public SelenideElement titleOrderItGot = $(".post-title");

    @Step("Get total price")
    public String getTotalSum(){
        return totalSum.get(1).getText().replaceAll(regForSum,"");
    }

    @Step("Get order price")
    public String getOrderSum(){
        return orderSum.getText().replaceAll(regForSum, "");
    }

    @Step("Assert is total price = order price?")
    public void assertSumTotalAndOrder(){
        Assertions.assertEquals(getTotalSum(),getOrderSum());
    }

    @Step("Set billing first name")
    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName.setValue(billingFirstName);
    }

    @Step("Set billing address")
    public void setBillingAddress(String billingAddress) {
        this.billingAddress.setValue(billingAddress);
    }

    @Step("Set billing last name")
    public void setBillingLastName(String billingLastName) {
        this.billingLastName.setValue(billingLastName);
    }

    @Step("Set billing postcode")
    public void setBillingPostcode(String billingPostcode) {
        this.billingPostcode.setValue(billingPostcode);
    }

    @Step("set billing city")
    public void setBillingCity(String billingCity) {
        this.billingCity.setValue(billingCity);
    }

    @Step("Set billing phone")
    public void setBillingPhone(String billingPhone) {
        this.billingPhone.setValue(billingPhone);
    }

    @Step("Set billing state")
    public void setBillingState(String billingState) {
        this.billing_state.setValue(billingState);
    }

    @Step("Set payment method cod")
    public void setPaymentMethodCod(){
        paymentMethodCod.click();
    }

    @Step("Agree check")
    public void setAgreeCheck(){
        agree.click();
    }

    @Step("Set local data + 1")
    public void setCalendarPlusOneDate() {
        String nextDay = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        calendar.setValue(nextDay);
    }

    @Step("Check title got order")
    public void visibleOftitleGotOdred(){
        titleOrderItGot.shouldBe(visible);
    }

    @Step("Pay metod should have text 'cash'")
    public void getPaymentMetod(String payMetod){
        detailsPayMetod.shouldHave(text(payMetod));
    }

    @Step("Open order form")
    public void openOrderForm() {
        orderMenu.click();
    }

    @Step("Make order click")
    public void clickMakeOrder() {
        makeOrderButton.click();
    }


}
