package com.ashwin.tests.vendorportal;

import com.ashwin.pages.vendorportal.DashboardPage;
import com.ashwin.pages.vendorportal.LoginPage;
import com.ashwin.tests.AbstractTest;
import com.ashwin.tests.vendorportal.model.VendorPortalTestData;
import com.ashwin.util.Config;
import com.ashwin.util.Constants;
import com.ashwin.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData vendorPortalTestData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPages(String testDataPath){
        this.loginPage  = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.vendorPortalTestData = JsonUtil.getTestData(testDataPath,VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        this.loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        this.loginPage.loginToApplication(vendorPortalTestData.username(),vendorPortalTestData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(this.dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),vendorPortalTestData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(),vendorPortalTestData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(),vendorPortalTestData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(),vendorPortalTestData.availableInventory());
        dashboardPage.searchOrderHistory(vendorPortalTestData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(),vendorPortalTestData.searchResultsCount());
        dashboardPage.logout();
    }

}
