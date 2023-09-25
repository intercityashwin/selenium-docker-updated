package com.ashwin.tests.flightreservation;

import com.ashwin.pages.flightreservation.*;
import com.ashwin.tests.AbstractTest;
import com.ashwin.tests.flightreservation.model.FlightReservationTestData;
import com.ashwin.util.Config;
import com.ashwin.util.Constants;
import com.ashwin.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    private RegistrationPage registrationPage;
    private RegistrationConfirmationPage registrationConfirmationPage;
    private FlightSearchPage flightSearchPage;
    private FlightSelectionPage flightSelectionPage;
    private FlightConfirmationPage flightConfirmationPage;

    private FlightReservationTestData flightReservationTestData;

    @BeforeTest
    @Parameters({"passenger"})
    public void setPagesParameters(String passenger){
        this.flightReservationTestData = JsonUtil.getTestData(passenger,FlightReservationTestData.class);
        this.registrationPage = new RegistrationPage(driver);
        this.registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        this.flightSearchPage = new FlightSearchPage(driver);
        this.flightSelectionPage = new FlightSelectionPage(driver);
        this.flightConfirmationPage = new FlightConfirmationPage(driver);
    }

    @Test
    public void userRegistration(){
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(flightReservationTestData.firstName(),flightReservationTestData.lastName());
        registrationPage.enterUserCredentials(flightReservationTestData.email(),flightReservationTestData.password());
        registrationPage.enterAddress(flightReservationTestData.street(),flightReservationTestData.city(),flightReservationTestData.zip());
        registrationPage.register();
    }


    @Test(dependsOnMethods = "userRegistration")
    public void registrationConfirmationTest(){
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.setGotToFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest(){
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(flightReservationTestData.passengerCount());
        flightSearchPage.clickSearchFlights();
    }


    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest(){
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmFlight();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTest(){
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(),flightReservationTestData.expectedPrice());
    }

}
