package pizza.widgets;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class Search {
    String regForSum = "[^0-9,\\\\+]";
    public SelenideElement searchPanel = $(".search-field");
    public SelenideElement prodTitle = $(".product_title");
    public SelenideElement totalSum = $(".wcmenucart-contents");
    public SelenideElement totalSumBasket = $(".product-subtotal bdi");

    @Step("Set searching nate of product, then enter")
    public void searchItem(String item){
        searchPanel.setValue(item).pressEnter();
    }

    @Step("Assert title after search")
    public void assertTitleAfterSearch(String item){
        prodTitle.shouldHave(text(item));
    }

    @Step("Get sum into search panel")
    public String getSumIntoSearchPanel(){
        return totalSum.getText().replaceAll(regForSum,"");
    }

    @Step("Get sum into basket")
    public String getSumIntoSBasket(){
        return totalSumBasket.getText().replaceAll(regForSum,"");
    }

    @Step("Assert sum near exit and basket")
    public void assertSumNearExitAndBasket(){
        Assertions.assertEquals(getSumIntoSearchPanel(),getSumIntoSBasket());
    }




}
