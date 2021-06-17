package com.xcart.mobile.homePage;

import com.xcart.mobile.customlisteners.CustomListner;
import com.xcart.mobile.pages.*;
import com.xcart.mobile.retryAnalyzer.RetryAnalyzer;
import com.xcart.mobile.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(CustomListner.class)
public class TopMenuTest extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    HomePage homepage = new HomePage();
    ShippingPage shipping = new ShippingPage();
    NewArrivals newArrivals = new NewArrivals();
    ComingSoon comingSoon = new ComingSoon();
    ContactUs contactUs = new ContactUs();


    @Test(groups = {"smoke", "regression"},retryAnalyzer = RetryAnalyzer.class)
    public void verifyUserShouldNavigateToShippingPageSuccessfully() throws InterruptedException {


        homepage.topTabs("Shipping");
        Thread.sleep(3000);
        String expectedShippingText = "Shipping";
        softAssert.assertEquals(expectedShippingText, shipping.actualShippingText());
    }

    @Test(groups = {"smoke", "sanity", "regression"},retryAnalyzer = RetryAnalyzer.class)
    public void verifyUserShouldNavigateToNewPageSuccessfully() throws InterruptedException {

        homepage.topTabs("New!");
        Thread.sleep(3000);
        String expectedNewArrivalText = "New Arrivals";
        softAssert.assertEquals(expectedNewArrivalText, newArrivals.actualNewArrivalText());
    }

    @Test(groups = {"sanity", "regression"},retryAnalyzer = RetryAnalyzer.class)
    public void verifyUserShouldNavigateToComingSoonPageSuccessfully() throws InterruptedException {

        homepage.topTabs("Coming soon");
        Thread.sleep(3000);
        String expectedComingSoonText = "Coming soon";
        softAssert.assertEquals(expectedComingSoonText, comingSoon.actualComingSoonText());
    }

    @Test(groups = {"regression"},retryAnalyzer = RetryAnalyzer.class)
    public void verifyUserShouldNavigateToContactUsPageSuccessfully() throws InterruptedException {

        homepage.topTabs("Contact us");
        Thread.sleep(3000);
        String expectedContactUsText = "Contact ";
        Assert.assertEquals(expectedContactUsText, contactUs.actualContactUsText());
    }

}
