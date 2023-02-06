package pizza.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pizza.widgets.*;

public class TestScript1 extends TestBase {
    @Test
    @Epic("Script 1")
    @Feature("MyAccount")
    @Description("* ������������ ���� ������� �� ���� ��������.\n" +
            "* � ���������, �� �������� ������ ���� ��������, ��� �� ���\n" +
            "�� ��������������� �� �����.\n" +
            "* ���� ����� ����� ���� �����, ������� ������\n" +
            "������������������ � ��������� ��� ����� � ���� \n" +
            "���� �������.\n" +
            "* ��� �� �������� �����������.\n" +
            "* ����������� ������������� ������������ ���� \n" +
            "��� ������������, ��� �� ��������� �������� �� �������\n" +
            "������ ����� ��������, ��� ��� ������������ �� �����\n" +
            "������������.\n")
    @DisplayName("�����������")
    public void registration() {
        String name = "name3";
        String email = "dd@dd3.com";
        String passport = "123QWe3";
        String textRegistrationIsComplete = "����������� ���������";

        new MyAccount().intoMyAccForm();
        new MyAccount().intoRegForm();
        new Registration().setName(name);
        new Registration().setEmail(email);
        new Registration().setPassport(passport);
        new Registration().regButtonClick();

        new Registration().assertRegTitle(textRegistrationIsComplete);
        new MyAccount().exitInAccount();
    }

    @Test
    @Epic("Script 1")
    @Feature("MyAccount")
    @Description("* ����������� ������������� ������������ ���� \n" +
            "��� ������������, ��� �� ��������� �������� �� �������\n" +
            "������ ����� ��������, ��� ��� ������������ �� �����\n" +
            "������������.\n")
    @DisplayName("�����������")
    public void authorization() {
        String name = "Tmen";
        String passport = "Tmen123";

        new MyAccount().intoMyAccForm();
        new MyAccount().setName(name);
        new MyAccount().setPass(passport);
        new MyAccount().clickButton();

        new MyAccount().assertConfirmAuthorization(name);
    }

    @Test
    @Epic("Script 1")
    @Feature("Basket")
    @Description("* ����� ���� �������� �����.\n" +
            "* ���� ����� �������, ������� �������� ����� � �������� \n" +
            "� ����� � � �������.\n")
    @DisplayName("���������� ������ � �������")
    public void checkPizzaTitleIntoBasket() {
        String nameOfProduct = "�����";

        new Menu().clickIntoPizzaPage();
        new Menu().addToBasket(1);
        new Menu().intoBasket();
        new Basket().removeItem();

        new Menu().assertProductText(nameOfProduct);
    }

    @Test
    @Epic("Script 1")
    @Feature("Basket")
    @Description("* ������ ���� ����� ���������, � �� ������� ��� ����� �� ������\n" +
            "� �������� (�������������� ��������, ��� ����� ������\n" +
            "����� � ������� �����).\n" +
            "* ���� ����������, ��� � ���� � ������ ������� ����, \n" +
            "������� ���� ����� ����� � �����, � ����������� ����� \n" +
            "�� �������� ������� ���������� ����� � ����� �� ���� ����." +
            "���� ��������� ������� � ��������� ������ ���������� \n" +
            "� ��������� ������.\n")
    @DisplayName("�������� ����� � ������� � ������ ������� ����")
    public void checkSumIntoBasketAndSearchPanel() {
        new Menu().clickIntoPizzaPage();
        new Menu().addToBasket(2);
        new Menu().intoBasket();
        new Basket().removeItem();

        new Search().assertSumNearExitAndBasket();
    }

    @Test
    @Epic("Script 1")
    @Feature("Basket")
    @Description("* ���� ����������, ��� � ���� � ������ ������� ����, \n" +
            "������� ���� ����� ����� � �����, � ����������� ����� \n" +
            "�� �������� ������� ���������� ����� � ����� �� ���� ����.\n" +
            "\n")
    @DisplayName("���������� ���-�� ������ � �������")
    public void checkAddCountIntoBasket() {
        int addProduct = 2;

        new Menu().clickIntoPizzaPage();
        new Menu().addToBasket(1);
        new Menu().intoBasket();
        new Basket().addValueItem(addProduct);
        new Basket().getTitleCardUpdated();

        new Basket().assertPriceAfterIncrease();
    }


    @Test
    @Epic("Script 1")
    @Feature("SearchInput")
    @Description("* ��� ���� ����� ����� � ���� ������� ������ ����� ����� \n" +
            "�� �����. \n" +
            "* �� �������� �������� ���� ��������� ����� � �������.\n")
    @DisplayName("����� ������ ����� ������ ������- �������� �� �����")
    public void searchingName() {
        String searchingItem = "�����";

        new Search().searchItem(searchingItem);

        new Search().assertTitleAfterSearch(searchingItem);
    }

    @Test
    @Epic("Script 1")
    @Feature("SearchInput")
    @DisplayName("����� ������ ����� ������ ������")
    public void addToBasketIntoProductPage() {
        String searchingItem = "�����";

        new Basket().open();
        new Search().searchItem(searchingItem);
        new ProductPage().addToBasket();

        new ProductPage().assertAddedProduct(searchingItem);
    }

    @Test
    @Epic("Script 1")
    @Feature("OrderForm")
    @Description("* ���� ��������� � ���������� ������ ����� ���������������\n" +
            "����.\n" +
            "* ���� ��������� �������� ������ �������.\n" +
            "* ���� �������� ���� ���������� ������ �� ������, ������ \n" +
            "��� ������� �� ����� ��������.\n" +
            "* ���� �������� ������ �� �������� � ������������ �����.\n" +
            "* ���� ����������� ��������� ������������� ������, �����\n" +
            "����� � ���� ������ ������.\n" +
            "\n")
    @DisplayName("�������� ����� �����")
    public void checkTotalSumIntoOrderForm() throws InterruptedException {
        String name = "Tmen";
        String lastName = "Di";
        String passport = "Tmen123";
        String address = "New";
        String city = "New";
        String state = "New";
        String postCode = "111111";
        String phone = "89999999999";
        String payment = "���������";

        new MyAccount().intoMyAccForm();
        new MyAccount().setName(name);
        new MyAccount().setPass(passport);
        new MyAccount().clickButton();
        new Menu().clickIntoPizzaPage();
        new Menu().addToBasket(1);
        new Menu().intoBasket();
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

        new Order().getPaymentMetod(payment);
        new Order().assertSumTotalAndOrder();
        new MyAccount().exitInAccount();
    }
}
