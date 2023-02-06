package pizza.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pizza.widgets.*;

public class TestScript2and3 extends TestBase {
    @Test
    @Epic("Script 2")
    @Feature("Basket")
    @Description("* Самым быстрым образом наполнить корзину (одного товара\n" +
            "достаточно).\n" +
            "* В корзине применить скидочный купон GIVEMEHALYAVA.\n" +
            "* Убедиться, что общая сумма заказа уменьшилась на 10%.\n")
    @DisplayName("Применение купона в 10%")
    public void totalSumDecreaseBy10percent() {
        String coupon = "GIVEMEHALYAVA";

        new Menu().clickIntoPizzaPage();
        new Menu().addToBasket(1);
        new Menu().intoBasket();
        new Basket().setCoupone(coupon);
        new Basket().applyCoupone();
        new Basket().visibleLineCoupone();

        new Basket().assertSumAffterApply10PrecentCoupone();
        new Basket().removeItem();
    }

    @Test
    @Epic("Script 3")
    @Feature("Basket")
    @Description("* Самым быстрым образом наполнить корзину (одного товара\n" +
            "достаточно).\n" +
            "* В корзине применить скидочный купон c неизвестным\n" +
            "именем.\n" +
            "*  Убедиться, что высветилось сообщение об ошибке и сумма\n" +
            "заказа не изменилась.\n ")
    @DisplayName("Применение невалидного купона")
    public void incorrectCouponeAndTotalSumIsNotChange() {
        String coupon = "GIVE";

        new Menu().clickIntoPizzaPage();
        new Menu().addToBasket(1);
        new Menu().intoBasket();
        new Basket().setCoupone(coupon);
        new Basket().applyCoupone();

        new Basket().titleCouponeIncorrectCoupone();
        new Basket().removeItem();
    }

    @Test
    @Epic("Script 2")
    @Feature("Basket")
    @Description("* Самым быстрым образом наполнить корзину (одного товара\n" +
            "достаточно).\n" +
            "* В корзине применить скидочный купон GIVEMEHALYAVA.\n" +
            "* Убедиться, что общая сумма заказа уменьшилась на 10%.\n")
    @Link(url = "https://docs.google.com/spreadsheets/d/1WFhqUJN0BebhjGHJityCSarGJQtdnsJ2c-yAJCcQXf0/edit?usp=sharing",
            name = "GoogleTable_BugReport")
    @DisplayName("Проверка локализации(текст сообщения об применении купона)")
    public void titleSuccessfullyAppliedCouponOnCyrillic() {
        String coupon = "GIVEMEHALYAVA";
        String exited = "Код купона успешно применен.";

        new Menu().clickIntoPizzaPage();
        new Menu().addToBasket(1);
        new Menu().intoBasket();
        new Basket().setCoupone(coupon);
        new Basket().applyCoupone();

        new Basket().assertTitleAppliedCouponOnCyrillic(exited);
    }
}
