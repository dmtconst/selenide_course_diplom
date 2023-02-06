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
    @Description("* Пользователь Петя заходит на сайт магазина.\n" +
            "* К сожалению, на странице оплаты Петя понимает, что он ещё\n" +
            "не зарегистрирован на сайте.\n" +
            "* Петя очень любит нашу пиццу, поэтому решает\n" +
            "зарегистрироваться и переходит для этого в меню \n" +
            "«Мой аккаунт».\n" +
            "* Там он проходит регистрацию.\n" +
            "* Регистрация автоматически авторизирует Петю \n" +
            "как пользователя, это он проверяет взглядом на верхнюю\n" +
            "правую часть страницы, где его приветствуют по имени\n" +
            "пользователя.\n")
    @DisplayName("Регистрация")
    public void registration() {
        String name = "name3";
        String email = "dd@dd3.com";
        String passport = "123QWe3";
        String textRegistrationIsComplete = "Регистрация завершена";

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
    @Description("* Регистрация автоматически авторизирует Петю \n" +
            "как пользователя, это он проверяет взглядом на верхнюю\n" +
            "правую часть страницы, где его приветствуют по имени\n" +
            "пользователя.\n")
    @DisplayName("Авторизация")
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
    @Description("* Через меню выбирает пиццы.\n" +
            "* Петя любит ветчину, поэтому выбирает пиццу с ветчиной \n" +
            "и кладёт её в корзину.\n")
    @DisplayName("Добавление товара в корзину")
    public void checkPizzaTitleIntoBasket() {
        String nameOfProduct = "пицца";

        new Menu().clickIntoPizzaPage();
        new Menu().addToBasket(1);
        new Menu().intoBasket();
        new Basket().removeItem();

        new Menu().assertProductText(nameOfProduct);
    }

    @Test
    @Epic("Script 1")
    @Feature("Basket")
    @Description("* Теперь Петя готов заплатить, и он кликает для этого на иконку\n" +
            "с корзиной (предварительно проверив, что сумма заказа\n" +
            "рядом с иконкой верна).\n" +
            "* Петя вспоминает, что у него в гостях подруга Таня, \n" +
            "которая тоже любит пиццу и какао, и увеличивает прямо \n" +
            "на странице корзины количество пиццы и какао до двух штук." +
            "Петя обновляет корзину и проверяет заново количество \n" +
            "и стоимость заказа.\n")
    @DisplayName("Проверка суммы в корзине и правом верхнем углу")
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
    @Description("* Петя вспоминает, что у него в гостях подруга Таня, \n" +
            "которая тоже любит пиццу и какао, и увеличивает прямо \n" +
            "на странице корзины количество пиццы и какао до двух штук.\n" +
            "\n")
    @DisplayName("Увеличение кол-ва товара в корзине")
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
    @Description("* Ещё Петя любит какао и ищет продукт «какао» через поиск \n" +
            "на сайте. \n" +
            "* На странице продукта Петя добавляет какао в корзину.\n")
    @DisplayName("Поиск товара через панель поиска- проверка по имени")
    public void searchingName() {
        String searchingItem = "какао";

        new Search().searchItem(searchingItem);

        new Search().assertTitleAfterSearch(searchingItem);
    }

    @Test
    @Epic("Script 1")
    @Feature("SearchInput")
    @DisplayName("Поиск товара через панель поиска")
    public void addToBasketIntoProductPage() {
        String searchingItem = "какао";

        new Basket().open();
        new Search().searchItem(searchingItem);
        new ProductPage().addToBasket();

        new ProductPage().assertAddedProduct(searchingItem);
    }

    @Test
    @Epic("Script 1")
    @Feature("OrderForm")
    @Description("* Петя переходит к оформлению заказа через соответствующее\n" +
            "меню.\n" +
            "* Петя заполняет формуляр своими данными.\n" +
            "* Петя выбирает дату оформления заказа на завтра, потому \n" +
            "что сегодня не такой голодный.\n" +
            "* Петя выбирает оплату по доставке и подтверждает заказ.\n" +
            "* Петя внимательно проверяет подтверждение заказа, общую\n" +
            "сумму и свои личные данные.\n" +
            "\n")
    @DisplayName("Проверка общей суммы")
    public void checkTotalSumIntoOrderForm() throws InterruptedException {
        String name = "Tmen";
        String lastName = "Di";
        String passport = "Tmen123";
        String address = "New";
        String city = "New";
        String state = "New";
        String postCode = "111111";
        String phone = "89999999999";
        String payment = "наличными";

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
