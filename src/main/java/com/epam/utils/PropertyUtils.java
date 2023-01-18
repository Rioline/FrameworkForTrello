package com.epam.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

    String result = "";
    InputStream inputStream;

    public static String getPropString(String propTerm, String fromPropertyFile) {
        return instance().getPropertyValueFromSpecifiedFile(propTerm, fromPropertyFile);
    }

    public static Boolean getPropBoolean(String propTerm, String fromPropertyFile) {
        return Boolean.parseBoolean(instance().getPropertyValueFromSpecifiedFile(propTerm, fromPropertyFile));
    }

    public static Integer getPropInteger(String propTerm, String fromPropertyFile) {
        return Integer.parseInt(instance().getPropertyValueFromSpecifiedFile(propTerm, fromPropertyFile));
    }

    private static PropertyUtils instance() {
        return new PropertyUtils();
    }

    private String getPropertyValueFromSpecifiedFile(String propTerm, String propFileName) {
        return getPropertyValueFromFile(propTerm, propFileName);
    }

    private String getPropertyValueFromFile(String propTerm, String propFile) {
        try {
            Properties property = new Properties();

            inputStream = getClass().getClassLoader().getResourceAsStream(propFile);

            if (inputStream != null) {
                property.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + propFile + "' not found in the classpath");
            }

            result = property.getProperty(propTerm);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String getBaseUrl() {
        return PropertyUtils.getPropString("baseUrl", "environment.properties");
    }

    public static String getUserEmail() {
        return PropertyUtils.getPropString("userEmail", "environment.properties");
    }

    public static String getUserPassword() {
        return PropertyUtils.getPropString("userPassword", "environment.properties");
    }

    public static String getBrowser() {
        return getPropString("selenide.browser", "selenide.properties");
    }

    public static String getRemoteHost() {
        return getPropString("selenide.remote.host", "selenide.properties");
    }

    public static String getPageLoadStrategy() {
        return getPropString("selenide.pageLoadStrategy", "selenide.properties");
    }

    public static int getTimeout() {
        return getPropInteger("selenide.timeout", "selenide.properties");
    }

    public static boolean isDriverManagerEnabled() {
        return getPropBoolean("selenide.driverManagerEnabled", "selenide.properties");
    }

    public static boolean isReopenBrowserOnFail() {
        return getPropBoolean("selenide.reopenBrowserOnFail", "selenide.properties");
    }

    public static boolean isRemoteExecution() {
        return getPropBoolean("selenide.remote.execution", "selenide.properties");
    }

    public static boolean isHeadless() {
        return getPropBoolean("selenide.headless", "selenide.properties");
    }
}
