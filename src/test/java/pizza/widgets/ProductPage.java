package pizza.widgets;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    public SelenideElement addToBasket = $(".alt");
    public SelenideElement assertTitle = $(".woocommerce-message");
    public SelenideElement removeItem = $(".cart_item .product-remove");

    @Step("Add to basket button")
    public void addToBasket() {
        addToBasket.click();
    }

    @Step("Add to basket button")
    public void assertAddedProduct(String title) {
        assertTitle.shouldHave(text(title));
    }

    public void removeItem() {
        removeItem.click();
    }
}
