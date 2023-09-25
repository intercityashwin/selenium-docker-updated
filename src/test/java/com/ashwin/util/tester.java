package com.ashwin.util;

public class tester {

    public static void main(String[] args) {
        System.setProperty("browser","firefox");
        System.setProperty("selenium.grid.enabled","true");
        Config.initialize();
        System.out.println(Config.get("browser"));
    }
}
