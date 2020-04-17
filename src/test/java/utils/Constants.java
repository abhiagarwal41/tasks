package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class Constants {

    public static String remote;
    public static String url;
    public static String logPropertiesPath = "./log4j.properties";
    public static Logger log ;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static DriverType browserType;
    public static SoftAssertions softAssertions;
}
