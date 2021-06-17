package com.xcart.mobile.hotDeals;

import com.xcart.mobile.pages.BestSellers;
import com.xcart.mobile.pages.HomePage;
import com.xcart.mobile.pages.SalePage;
import com.xcart.mobile.retryAnalyzer.RetryAnalyzer;
import com.xcart.mobile.testbase.TestBase;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HotDealsTest extends TestBase {
    SoftAssert softAssert = new SoftAssert();
    HomePage homepage = new HomePage();
    SalePage sale = new SalePage();
    BestSellers bestSellers = new BestSellers();

    @Test(groups = {"smoke", "regression"},retryAnalyzer = RetryAnalyzer.class)
    public void verifySaleProductsArrangeAlphabetically() throws InterruptedException {

        homepage.mouseHoverOnHotDeals();
        homepage.clickOnSalesOrBestSellers("Sale");
        Thread.sleep(2000);
        String expectedSaleText = "Sale";
        softAssert.assertEquals(expectedSaleText, sale.actualSaleText());

        sale.selectSortByFilter("Name A - Z");
        Thread.sleep(2000);

        List<String> actualProductNameList = sale.getProductNames();
        List<String> expectedProductNameList = new ArrayList<>(actualProductNameList);
        Collections.sort(expectedProductNameList);
        softAssert.assertEquals(expectedProductNameList, actualProductNameList);
    }

    @Test(groups = {"smoke", "regression"},retryAnalyzer = RetryAnalyzer.class)
    public void verifySaleProductsPriceArrangeLowToHigh() throws InterruptedException {

        homepage.mouseHoverOnHotDeals();
        homepage.clickOnSalesOrBestSellers("Sale");
        Thread.sleep(2000);
        String expectedSaleText = "Sale";
        softAssert.assertEquals(expectedSaleText, sale.actualSaleText());

        sale.selectSortByFilter("Price Low - High");
        Thread.sleep(2000);

        List<Double> actualProductPriceList = sale.getProductPrices();
        List<Double> expectedProductPriceList = new ArrayList<>(actualProductPriceList);
        Collections.sort(expectedProductPriceList);
        softAssert.assertEquals(expectedProductPriceList, actualProductPriceList);

    }

    @Test(groups = {"smoke", "sanity", "regression"},retryAnalyzer = RetryAnalyzer.class)
    public void verifySaleProductsArrangeByRates() throws InterruptedException {

        homepage.mouseHoverOnHotDeals();
        homepage.clickOnSalesOrBestSellers("Sale");
        Thread.sleep(2000);
        String expectedSaleText = "Sale";
        softAssert.assertEquals(expectedSaleText, sale.actualSaleText());

        sale.selectSortByFilter("Rates");
        Thread.sleep(2000);

        List<Double> actualProductRating = sale.getProductRates();
        List<Double> expectedProductRating = new ArrayList<>(actualProductRating);
        Collections.sort(expectedProductRating, Collections.<Double>reverseOrder());
        softAssert.assertEquals(expectedProductRating, actualProductRating);
    }

}
