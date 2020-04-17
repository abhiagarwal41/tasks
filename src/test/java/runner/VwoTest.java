package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.DriverType;

import static utils.Constants.*;
import static utils.Functions.*;


@RunWith(Cucumber.class)
@CucumberOptions(strict = true, features = "src/test/resources/features", glue = "steps",
        snippets = SnippetType.CAMELCASE, tags = {"@test", "not @ignore"}, monochrome = true)
public class VwoTest {


    @BeforeClass
    public static void beforeClass() throws Exception {

        String browser = System.getProperty("browser");
        if (browser == null)
            browserType = DriverType.CHROME;
        else
            browserType = getDriverType(browser);

        remote = System.getProperty("remote");
        if (remote == null) remote = "false";

        url = System.getProperty("url");
        if (url == null) url = getProperty("vwo");


        System.setProperty("file.name", "./logs/testExecution.log");
        PropertyConfigurator.configure(logPropertiesPath);
        log = Logger.getLogger(VwoTest.class);

        log.info("Initialized logs for VWO tests...");
        log.info("starting tests...");

        intializeExtentReporting("./reports/vwo_report.html", "VWO test report", "VWO test report");
    }

    @AfterClass
    public static void afterClass() {
        log.info("Tests complete. Access html report from reports folder, access log from logs folder");
    }

}