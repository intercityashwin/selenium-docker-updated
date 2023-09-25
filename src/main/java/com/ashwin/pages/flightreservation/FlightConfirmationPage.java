package com.ashwin.pages.flightreservation;

import com.ashwin.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage {
    private static final Logger log  = LoggerFactory.getLogger(FlightConfirmationPage.class);
    @FindBy(xpath = "//div[normalize-space(text())='Flight Confirmation #']/following-sibling::div/p")
    private WebElement flightConfirmationText;

    @FindBy(xpath = "//div[normalize-space(text())='Total Price']/following-sibling::div/p")
    private WebElement totalPriceText;


    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationText));
        return this.flightConfirmationText.isDisplayed();
    }

    public String getPrice(){
        String confirmation = this.flightConfirmationText.getText();
        String price = this.totalPriceText.getText();
        log.info("Flight confirmation# : {}", confirmation);
        log.info("Price# : {}", price);
        return this.totalPriceText.getText();
    }
}
