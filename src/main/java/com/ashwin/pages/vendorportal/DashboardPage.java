package com.ashwin.pages.vendorportal;

import com.ashwin.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningLabel;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningsLabel;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginLabel;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryLabel;

    @FindBy(css = "div#dataTable_filter label input")
    private WebElement search;

    @FindBy(css = ".dataTables_info")
    private WebElement searchResultsCount;

    @FindBy(css = ".img-profile.rounded-circle")
    private WebElement userProfileImage;

    @FindBy(css = "a[data-target='#logoutModal']")
    private WebElement logout;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement modelLogout;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarningLabel));
        return this.monthlyEarningLabel.isDisplayed();
    }

    public String getMonthlyEarning(){
        return this.monthlyEarningLabel.getText();
    }

    public String getAnnualEarning(){
        return this.annualEarningsLabel.getText();
    }

    public String getProfitMargin(){
        return this.profitMarginLabel.getText();
    }

    public String getAvailableInventory(){
        return this.availableInventoryLabel.getText();
    }

    public void searchOrderHistory(String keyword){
        this.search.sendKeys(keyword);
    }

    public int getSearchResultsCount(){
        String count = this.searchResultsCount.getText().split(" ")[3];
        log.info("Results count : {}",count);
        return Integer.valueOf(count);
    }

    public void logout(){
        this.userProfileImage.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logout));
        this.logout.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.modelLogout));
        this.modelLogout.click();
    }
}
