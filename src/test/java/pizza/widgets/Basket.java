package pizza.widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Basket {
    String regForSum = "[^0-9,\\\\+]";
    public SelenideElement basket = $("#menu-item-29");
    public SelenideElement valueAdd = $("[type=number]");
    public SelenideElement priceForOneProduct = $(".product-price .amount");
    public SelenideElement checkoutButton = $(".checkout-button");
    public SelenideElement removeItem = $(".cart_item .product-remove");
    public ElementsCollection allRemoveButton = $$(".cart_item .product-remove");
    public SelenideElement totalSumAfterIncrease = $(".product-subtotal bdi");
    public SelenideElement cardUpdated = $(".woocommerce-message");
    public SelenideElement couponeInput = $("#coupon_code");
    public SelenideElement applyCoupone = $("[name='apply_coupon']");
    public SelenideElement summAfterCoupone = $(".order-total .woocommerce-Price-amount bdi");
    public SelenideElement totalPrice = $(".cart-subtotal bdi");
    public SelenideElement titleCouponeApplied = $(".woocommerce-message");
    public SelenideElement titleIncorrectCoupone= $(".woocommerce-error li");
    public SelenideElement polCoupon = $(".cart-discount");
    public SelenideElement goToPay = $(".checkout-button");
    public SelenideElement remove—oupon = $(".woocommerce-remove-coupon");

    @Step("Open basket")
    public void open() {
        basket.click();
    }

    @Step("Increase the amount to = {value}")
    public void addValueItem(int value) {
        valueAdd.setValue(String.valueOf(value)).pressEnter();
    }

    @Step("Update basket")
    public void checkoutBasket() {
        checkoutButton.click();
    }

    @Step("Go to pay")
    public void goToPay() {
        goToPay.shouldBe(visible).click();;
    }

    @Step("Get title- card updated")
    public void getTitleCardUpdated() {
        cardUpdated.shouldHave(text("updated"));
    }

    @Step("Assert price after increase")
    public void assertPriceAfterIncrease() {
        //1. First price * 2
        String priseBefore = priceForOneProduct.getText().replaceAll(regForSum, "");
        String replaceComma = priseBefore.replaceAll(",", ".");
        float increaseTo = Float.parseFloat(replaceComma) * 2;

        //2. Get total price after increase step before
        String priseAfter = totalSumAfterIncrease.getText().replaceAll(regForSum, "");
        String replaceComma2 = priseAfter.replaceAll(",", ".");
        float increaseTo2 = Float.parseFloat(replaceComma2);

        //3. Assert 2 step
        Assertions.assertEquals(increaseTo, increaseTo2);
    }

    @Step("Remove product")
    public void removeItem() {
        for (int i = 0; i <= allRemoveButton.size(); i++) {
            removeItem.click();
        }
    }

    @Step("Set coupone")
    public void setCoupone(String value) {
        couponeInput.shouldBe(visible).setValue(value);
    }

    @Step("Get title coupone applied")
    public void titleCouponeApplied() {
        titleCouponeApplied.shouldBe(visible).shouldHave(text("applied"));
    }

    @Step("Assert title coupone should not have applied")
    public void assertionsTitleCouponeShouldNotApplied() {
        Assertions.assertEquals(" Ó‰ ÍÛÔÓÌ‡ ÛÒÔÂ¯ÌÓ ÔËÏÂÌÂÌ.",titleCouponeApplied.getText());
        new MyAccount().exitInAccount();
    }

    @Step("Get title coupone applied")
    public void assertTitleAppliedCouponOnCyrillic(String expected) {
        Assertions.assertEquals(titleCouponeApplied.getText(),expected);
        remove—oupon.click();
    }

    @Step("Get title incorrect coupone")
    public void titleCouponeIncorrectCoupone() {
        titleIncorrectCoupone.shouldHave(text("ÕÂ‚ÂÌ˚È"));
    }

    @Step("Apply coupone")
    public void applyCoupone(){
        applyCoupone.shouldBe(visible).click();
    }

    @Step("Visibility title of coupone")
    public void visibleLineCoupone(){
        polCoupon.shouldBe(visible);
    }

    @Step("Assert sum after coupone")
    public void assertSumAffterApply10PrecentCoupone(){
        //get total price -> to float without ','
        String priseBefore = totalPrice.getText().replaceAll(regForSum,"");
        String replaceComma = priseBefore.replaceAll(",", ".");
        float increaseTo = Float.parseFloat(replaceComma);

        //applying 10%
        float sumAfterCoupone = increaseTo - ((increaseTo * 10)/100);

        //get total price after apply 10% -> to float without ','
        String priseAfter = summAfterCoupone.getText().replaceAll(regForSum,"");
        String replaceComma2 = priseAfter.replaceAll(",", ".");
        float increaseTo2 = Float.parseFloat(replaceComma2);

        //Assert
        Assertions.assertEquals(sumAfterCoupone,increaseTo2);
        remove—oupon.click();
    }


}
