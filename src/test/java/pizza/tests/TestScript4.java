package pizza.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pizza.widgets.*;

public class TestScript4 extends TestBase {
    @Test
    @Epic("Script 4")
    @Feature("Order")
    @Description("* Создать нового пользователя.\n" +
            "Сделать первый заказ с промокодом GIVEMEHALYAVA \n" +
            "на странице «Акции».\n" +
            "* Сделать второй заказ с этим же промокодом.\n" +
            "Проверить, что промокод второй раз не сработал.\n")
    @Link(url = "https://docs.google.com/spreadsheets/d/1aCwICZ7i_eRi0f1QJMIIusreBHXHaNsl2cmXBi2ruyk/edit?usp=sharing", name = "GoogleTable_BugReport")
    @DisplayName("Купон не применяется повторно")
    public void couponeNotUseSecondTime() {
        String name = "namea";
        String email = "dd@dd.com";
        String passport = "123QWe";
        String lastName = "Dia";
        String address = "New";
        String city = "New";
        String state = "New";
        String postCode = "111111";
        String phone = "89999999999";
        String coupon = "GIVEMEHALYAVA";

        new Menu().intoPromotion();
        //Создать нового пользователя
        new MyAccount().intoMyAccForm();
        new MyAccount().intoRegForm();
        new Registration().setName(name);
        new Registration().setEmail(email);
        new Registration().setPassport(passport);
        new Registration().regButtonClick();
        //Добавить товар в корзину
        new Menu().clickIntoPizzaPage();
        new Menu().addToBasket(1);
        new Menu().intoBasket();
        new Basket().setCoupone(coupon);
        new Basket().applyCoupone();
        new Basket().visibleLineCoupone();
        new Basket().titleCouponeApplied();
        //Сделать первый заказ 1
        new Order().openOrderForm();
        new Order().setBillingFirstName(name);
        new Order().setBillingLastName(lastName);
        new Order().setBillingAddress(address);
        new Order().setBillingCity(city);
        new Order().setBillingState(state);
        new Order().setBillingPostcode(postCode);
        new Order().setCalendarPlusOneDate();
        new Order().setBillingPhone(phone);
        new Order().setPaymentMethodCod();
        new Order().setAgreeCheck();
        new Order().clickMakeOrder();
        new Order().visibleOftitleGotOdred();
        //Добавить товар в корзину 2
        new Menu().clickIntoPizzaPage();
        new Menu().addToBasket(1);
        new Menu().intoBasket();
        new Basket().setCoupone(coupon);
        new Basket().applyCoupone();

        //Проверить, что промокод второй раз не сработал
        new Basket().assertionsTitleCouponeShouldNotApplied();
    }

    @Test
    @Epic("Script 4")
    @Feature("Promotion")
    @Description("* Зайти на страницу «Акции», проверить текстовую\n" +
            "информацию о скидке GIVEMEHALYAVA.\n")
    @DisplayName("Проверка наличия купона в разделе 'Акции'")
    public void shouldHavePromotionGIVEMEHALYAVA() {
        String coupon = "GIVEMEHALYAVA";

        new Menu().intoPromotion();

        new Menu().aseertTitleCoupone(coupon);
    }
}
