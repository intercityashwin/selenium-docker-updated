package com.ashwin.util;

import com.ashwin.tests.flightreservation.model.FlightReservationTestData;
import com.ashwin.tests.vendorportal.model.VendorPortalTestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtil {

    public static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    public static final ObjectMapper mapper = new ObjectMapper();


    public static <T> T getTestData(String path, Class<T> type){
        try(InputStream stream = ResourceLoader.getResource(path)){
            return mapper.readValue(stream,type);
        }catch (Exception e){
            log.error("unable to read test data {}",path, e);
        }
        return null;
    }
}
