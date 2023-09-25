package com.ashwin.util;

import com.ashwin.tests.vendorportal.model.VendorPortalTestData;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Demo {
    public static void main(String[] args) throws Exception {
        InputStream stream = ResourceLoader.getResource("test-data/vendor-portal/dummy.txt");
        String content = IOUtils.toString(stream, StandardCharsets.UTF_8);
        System.out.println(content);

        VendorPortalTestData vendorPortalTestData = JsonUtil.getTestData("test-data/vendor-portal/john.json",VendorPortalTestData.class);
        System.out.println(vendorPortalTestData.annualEarning());
        System.out.println(vendorPortalTestData.availableInventory());
        System.out.println(vendorPortalTestData.monthlyEarning());
        System.out.println(vendorPortalTestData.profitMargin());
        System.out.println(vendorPortalTestData.searchResultsCount());
    }
}

