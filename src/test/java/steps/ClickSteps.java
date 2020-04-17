package steps;

import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static pages.AbstractPage.getTestPage;
import static utils.Constants.log;
import static utils.Constants.test;
import static utils.Functions.doLogging;
import static utils.Init.getInstance;


public class ClickSteps {

    @When("^I click on \"(.*?)\"$")
    public static void iClickOn(String elementName) throws InterruptedException {
        doLogging("Clicking on element : " + elementName, "INFO", log, test);
        try {
            WebElement element = getInstance().getWebDriverWait().until(elementToBeClickable(getTestPage().getElementLocator(elementName)));
            Actions action = new Actions(getInstance().getDriver());
            action.moveToElement(element).click().perform();
        } catch (Exception e) {
            doLogging("Unable to click. Error :  " + e.getMessage(), "FAIL", log, test);
            fail("Unable to click. Error : " + e.getMessage());
        }
    }

}
