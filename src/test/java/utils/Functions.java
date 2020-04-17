package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static utils.Constants.extent;
import static utils.Constants.htmlReporter;


public class Functions {


    public static String getProperty(String name) throws IOException {
        File src = new File("./config.properties");
        FileInputStream fis = new FileInputStream(src);
        Properties pro = new Properties();
        pro.load(fis);
        return pro.getProperty(name);
    }


    public static void intializeExtentReporting(String file_name, String doc_title, String report_name) {
        htmlReporter = new ExtentHtmlReporter(file_name);
        htmlReporter.config().setDocumentTitle(doc_title);
        htmlReporter.config().setReportName(report_name);
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));

    }


    public static void doLogging(String msg, String status, Logger log, ExtentTest test) {
        log.info(msg);
        test.log(Status.valueOf(status), msg);
    }


    public static DriverType getDriverType(String browser) {

        switch (browser.toUpperCase()) {

            case "CHROME":
                return DriverType.CHROME;
            case "FIREFOX":
                return DriverType.FIREFOX;
            default:
                return DriverType.CHROME;
        }

    }

}
