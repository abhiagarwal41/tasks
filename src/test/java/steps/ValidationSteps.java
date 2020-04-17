package steps;

import cucumber.api.java.en.Then;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static org.junit.Assert.fail;
import static pages.AbstractPage.getTestPage;
import static utils.Constants.*;
import static utils.Functions.doLogging;
import static utils.Init.getInstance;

public class ValidationSteps {

    @Then("^Verify that \"(.*?)\" should be visible$")
    public void shouldBeVisible(String elementName) throws Throwable {
        try {
            WebElement element = getInstance().getDriver().findElement(getTestPage().getElementLocator(elementName));
            softAssertions.assertThat(element.isDisplayed()).isEqualTo(true);
            if (element.isDisplayed() == true)
                doLogging("Following element is visible :" + elementName, "PASS", log, test);
            else
                doLogging("Following element is invisible :" + elementName, "FAIL", log, test);
            softAssertions.assertAll();
        } catch (NoSuchElementException ne) {
            doLogging("Unable to find element on page : " + elementName, "FAIL", log, test);
            fail("Unable to find element on page : " + elementName);
        }
    }


    @Then("Verify that new tab is opened")
    public void verifyThatNewTabIsOpened() {

        ArrayList<String> tabs = new ArrayList<String>(getInstance().getDriver().getWindowHandles());
        softAssertions.assertThat(tabs.size()).isEqualTo(2);
        if (tabs.size() == 2)
            doLogging("New tab opened successfully", "PASS", log, test);
        else
            doLogging("New tab didn't open", "FAIL", log, test);
        softAssertions.assertAll();

    }

}
