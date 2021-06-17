package com.xcart.mobile.shopping;

import com.xcart.mobile.pages.*;
import com.xcart.mobile.retryAnalyzer.RetryAnalyzer;
import com.xcart.mobile.testbase.TestBase;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingTest extends TestBase {
    SoftAssert softAssert = new SoftAssert();
    HomePage homepage = new HomePage();
    SalePage sale = new SalePage();
    YourShoppingCart yourShoppingCart = new YourShoppingCart();
    LoginAccount logInAccount = new LoginAccount();
    TargetCheckout targetCheckout = new TargetCheckout();
    BestSellers bestSellers = new BestSellers();


    @Test(groups = {"smoke", "sanity", "regression"},retryAnalyzer = RetryAnalyzer.class)
    public void verifyThatUserShouldPlaceOrderSuccessfullyForAvengersFabricationsPlush() throws InterruptedException {

        homepage.mouseHoverOnHotDeals();
        homepage.clickOnSalesOrBestSellers("Sale");
        Thread.sleep(2000);
        String expectedSaleText = "Sale";
        softAssert.assertEquals(expectedSaleText, sale.actualSaleText());

        Thread.sleep(2000);
        sale.selectSortByFilter("Name A - Z");
        Thread.sleep(2000);

        sale.addProductToCart();
        Thread.sleep(2000);
        String expectedAddToCartMessage = "Product has been added to your cart";
        softAssert.assertEquals(expectedAddToCartMessage, sale.actualAddToCartDisplayedMessage());
        sale.closeAddToCartMessage();
        Thread.sleep(2000);
        sale.clickOnYourCartAndViewCart();

        Thread.sleep(2000);
        String expectedYourShoppingCartText1 = "Your shopping cart - 1 item";
        softAssert.assertEquals(expectedYourShoppingCartText1, yourShoppingCart.actualYourShoppingCartText());

        Thread.sleep(2000);
        yourShoppingCart.clearExistingProductQuantity();
        //Thread.sleep(3000);
        yourShoppingCart.updateProductQuantity("2");

        Thread.sleep(5000);
        String expectedYourShoppingCartText2 = "Your shopping cart - 2 items";
        softAssert.assertEquals(expectedYourShoppingCartText2, yourShoppingCart.actualYourShoppingCartText());

        Thread.sleep(2000);
        String expectedSubTotal = "$29.98";
        softAssert.assertEquals(expectedSaleText, yourShoppingCart.actualSubTotal());

        Thread.sleep(2000);
        String expectedTotal = "$36.00";
        softAssert.assertEquals(expectedTotal, yourShoppingCart.actualTotal());
        yourShoppingCart.clickOnCheckOutButton();

        Thread.sleep(2000);
        String expectedLogInToYourAccountText = "Log in to your account";
        softAssert.assertEquals(expectedLogInToYourAccountText, logInAccount.actualLogInToYourAccountText());

        Thread.sleep(2000);
        logInAccount.enterEmailId("rkpatel", "@gmail.com");
        logInAccount.clickOnContinueButton();

        Thread.sleep(2000);
        String expectedSecureCheckoutText = "Secure Checkout";
        softAssert.assertEquals(expectedSecureCheckoutText, targetCheckout.actualSecureCheckoutText());

        Thread.sleep(2000);
        targetCheckout.enterFirstName("RK");
        targetCheckout.enterLastName("Patel");
        targetCheckout.enterAddress("3 park street");
        Thread.sleep(2000);
        targetCheckout.enterCity("southampton");
        Thread.sleep(2000);
        targetCheckout.selectCountryFromDropDownMenu("United Kingdom");
        targetCheckout.enterState("hampshire");
        Thread.sleep(2000);
        targetCheckout.enterZipCode("so15 3ng");
        Thread.sleep(3000);
        targetCheckout.clickOnCreateAnAccountCheckBox();
        targetCheckout.enterPassword("TdtefeU123");
        Thread.sleep(2000);
        targetCheckout.clickLocalShippingRadioButton();
        Thread.sleep(2000);
        targetCheckout.clickCODRadioButton();

        Thread.sleep(2000);
        String expectedTotalPrice = "$37.03";
        softAssert.assertEquals(expectedTotalPrice, targetCheckout.actualTotalPrice());

        Thread.sleep(2000);
        targetCheckout.clickOnPlaceOrderButton();

        String expectedThankYouForYourOrderText = "Thank you";
        softAssert.assertEquals(expectedThankYouForYourOrderText, targetCheckout.actualThankYouForYourOrderText());

    }

    @Test(groups = {"sanity", "regression"},retryAnalyzer = RetryAnalyzer.class)
    public void verifyThatUserShouldClearShoppingCartSuccessfully() throws InterruptedException {

        homepage.mouseHoverOnHotDeals();
        homepage.clickOnSalesOrBestSellers("Bestsellers");
        Thread.sleep(2000);
        String expectedBestSellerText = "Bestsellers";
        softAssert.assertEquals(expectedBestSellerText, bestSellers.actualBestSellerText());

        bestSellers.selectSortByFilter("Name A - Z");
        Thread.sleep(2000);

        bestSellers.addVinylIdolsToCart();
        Thread.sleep(2000);
        String expectedAddToCartMessage = "Product has been added to your cart";
        softAssert.assertEquals(expectedAddToCartMessage, bestSellers.actualAddToCartDisplayedMessage());
        bestSellers.closeAddToCartMessage();
        Thread.sleep(2000);
        bestSellers.clickOnYourCartAndViewCart();

        Thread.sleep(2000);
        String expectedYourShoppingCartText1 = "Your shopping cart - 1 item";
        softAssert.assertEquals(expectedYourShoppingCartText1, yourShoppingCart.actualYourShoppingCartText());

        Thread.sleep(2000);
        yourShoppingCart.clickOnEmptyYourCart();
        String expectedAlertMessage = "Are you sure you want to clear your cart?";
        String actualAlertMessage = yourShoppingCart.alertMessage();
        softAssert.assertEquals(expectedAlertMessage, actualAlertMessage);

        Thread.sleep(2000);
        yourShoppingCart.clickOnAlertMessage();

        String expectedGreenBarMessage = "Item(s) deleted from your cart";
        softAssert.assertEquals(expectedGreenBarMessage, targetCheckout.actualGreenBarMessage());
        targetCheckout.clickCloseOnGreenBarMessage();

        Thread.sleep(2000);
        String expectedYourCartIsEmptyMessage = "Your cart is empty";
        softAssert.assertEquals(expectedYourCartIsEmptyMessage, targetCheckout.actualYourCartIsEmptyMessage());

    }
}
