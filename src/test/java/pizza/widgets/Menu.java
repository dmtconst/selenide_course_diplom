package pizza.widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Menu  {
    public SelenideElement menu = $("#menu-item-389");
    public SelenideElement pizza = $("#menu-item-390");
    public ElementsCollection addToBasket = $$(".add_to_cart_button");
    public SelenideElement addedToBasket = $(".added_to_cart");
    public SelenideElement titlePizza = $(".product-name a");
    public SelenideElement promotion = $("#menu-item-396");
    public SelenideElement halyava = $(".content-page strong");

    @Step("Hover under menu and click pizza")
    public void clickIntoPizzaPage() {
        menu.hover();
        pizza.click();
    }

    @Step("Add to basket")
    public void addToBasket(int count) {
        if (count >= addToBasket.size()) {
            System.out.println("Please, set count since 0 to " + (addToBasket.size() - 1));
        } else {
            addToBasket.get(count).click();
            addedToBasket.shouldBe(visible);
        }
    }

    @Step("Go to basket ")
    public void intoBasket() {
        addedToBasket.shouldBe(visible).click();
    }

    @Step("Assert title chosen product")
    public void assertProductText(String name) {
        titlePizza.shouldHave(text(name));
    }

    @Step("Go to promotion")
    public void intoPromotion() {
        promotion.click();
    }

    @Step("Assert title coupone success")
    public void aseertTitleCoupone(String value){
        halyava.shouldHave(text(value));
    }
}
