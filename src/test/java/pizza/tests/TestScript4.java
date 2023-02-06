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
    @Description("* ������� ������ ������������.\n" +
            "������� ������ ����� � ���������� GIVEMEHALYAVA \n" +
            "�� �������� ������.\n" +
            "* ������� ������ ����� � ���� �� ����������.\n" +
            "���������, ��� �������� ������ ��� �� ��������.\n")
    @Link(url = "https://docs.google.com/spreadsheets/d/1aCwICZ7i_eRi0f1QJMIIusreBHXHaNsl2cmXBi2ruyk/edit?usp=sharing", name = "GoogleTable_BugReport")
    @DisplayName("����� �� ����������� ��������")
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
        //������� ������ ������������
        new MyAccount().intoMyAccForm();
        new MyAccount().intoRegForm();
        new Registration().setName(name);
        new Registration().setEmail(email);
        new Registration().setPassport(passport);
        new Registration().regButtonClick();
        //�������� ����� � �������
        new Menu().clickIntoPizzaPage();
        new Menu().addToBasket(1);
        new Menu().intoBasket();
        new Basket().setCoupone(coupon);
        new Basket().applyCoupone();
        new Basket().visibleLineCoupone();
        new Basket().titleCouponeApplied();
        //������� ������ ����� 1
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
        //�������� ����� � ������� 2
        new Menu().clickIntoPizzaPage();
        new Menu().addToBasket(1);
        new Menu().intoBasket();
        new Basket().setCoupone(coupon);
        new Basket().applyCoupone();

        //���������, ��� �������� ������ ��� �� ��������
        new Basket().assertionsTitleCouponeShouldNotApplied();
    }

    @Test
    @Epic("Script 4")
    @Feature("Promotion")
    @Description("* ����� �� �������� ������, ��������� ���������\n" +
            "���������� � ������ GIVEMEHALYAVA.\n")
    @DisplayName("�������� ������� ������ � ������� '�����'")
    public void shouldHavePromotionGIVEMEHALYAVA() {
        String coupon = "GIVEMEHALYAVA";

        new Menu().intoPromotion();

        new Menu().aseertTitleCoupone(coupon);
    }
}
