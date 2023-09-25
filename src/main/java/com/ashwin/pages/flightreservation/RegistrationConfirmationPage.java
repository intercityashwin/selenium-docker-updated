package com.ashwin.pages.flightreservation;

import com.ashwin.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement gotToFlightSearchButton;

    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
         this.wait.until(ExpectedConditions.visibilityOf(this.gotToFlightSearchButton));
         return this.gotToFlightSearchButton.isDisplayed();
    }

    public void setGotToFlightSearch(){
        this.gotToFlightSearchButton.click();
    }
}
