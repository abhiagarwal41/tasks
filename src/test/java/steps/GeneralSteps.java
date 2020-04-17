package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.Init;

import java.util.ArrayList;

import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.frameToBeAvailableAndSwitchToIt;
import static pages.AbstractPage.getTestPage;
import static utils.Constants.*;
import static utils.Functions.doLogging;
import static utils.Init.getInstance;

public class GeneralSteps {

    @Given("^I start application$")
    public void iStartAppOnBrowser() {
        try {
            Init init = getInstance();
            init.startApplication(url);
            doLogging("Opened " + browserType.toString() + " with url : " + url, "INFO", log, test);

        } catch (Exception e) {
            doLogging("Unable to initialize driver" + e.getMessage(), "FAIL", log, test);
            fail("Unable to initialize driver" + e.getMessage());
        }
    }


    @Given("^I close browser$")
    public void iCloseBrowser() {
        getInstance().stopApplication();
    }


    @Before
    public void before(Scenario scenario) {
        log.info("Starting test scenario : " + scenario.getName());
        test = extent.createTest(scenario.getName());
        softAssertions = new SoftAssertions();
    }

    @After
    public void after(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            test.addScreenCaptureFromBase64String(((TakesScreenshot) getInstance().getDriver()).getScreenshotAs(OutputType.BASE64));
        }
        extent.flush();
    }


    @And("I switch to iframe {string}")
    public void iSwitchToIframe(String elementName) {
        By locator = getTestPage().getElementLocator(elementName);
        getInstance().getWebDriverWait().until(frameToBeAvailableAndSwitchToIt(locator));
    }

    @When("I switch to second tab")
    public void iSwitchToSecondTab() {
        ArrayList<String> tabs = new ArrayList<String>(getInstance().getDriver().getWindowHandles());
        getInstance().getDriver().switchTo().window(tabs.get(1));
        doLogging( "Switched to second tab", "INFO", log, test);
    }

    @When("I switch to default content")
    public void iSwitchToDefaultContent() {
        getInstance().getDriver().switchTo().defaultContent();
    }
}
