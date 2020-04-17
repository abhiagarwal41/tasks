package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import utils.NuSuchPageException;

import static pages.AbstractPage.getTestPage;
import static pages.AbstractPage.setTestPage;
import static utils.Constants.*;
import static utils.Functions.doLogging;
import static utils.Init.getInstance;
import static java.lang.Thread.sleep;
import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WaitSteps {

	@When("I Wait for \"(.*?)\" to load$")
	public static void waitForPageLoad(String pageName) {
		doLogging("Waiting for page to load : " + pageName, "INFO", log, test);
		try {

			ExpectedCondition jQueryLoad = driver ->{
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			};

			ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

			getInstance().getWebDriverWait().until(pageLoadCondition);
			getInstance().getWebDriverWait().until(jQueryLoad);
		}
		catch(TimeoutException e) {
			doLogging("Timeout exception waiting for page to load: " + pageName, "FAIL", log, test);
			fail("Timeout exception waiting for page to load: " + pageName);
		}
		try {
			setTestPage(pageName);
		} catch (NuSuchPageException e) {
			doLogging(e.getMessage(), "FAIL", log, test);
			fail(e.getMessage());
		}
	}

	@When("I Wait for \"(.*?)\" seconds$")
	public void waitForSeconds(long seconds) throws InterruptedException {
		doLogging("Waiting for seconds : " + seconds, "INFO", log, test);
		sleep(seconds*1000);
	}

	@When("I Wait until \"(.*?)\" is present$")
	public static void waitUntilPresent(String elementName){
		doLogging("Waiting for presence of : " + elementName, "INFO", log, test);
		By locator = getTestPage().getElementLocator(elementName);
		try {
			getInstance().getWebDriverWait().until(presenceOfElementLocated(locator));
		}
		catch(TimeoutException e) {
			doLogging("Timeout exception waiting for presence of : " + elementName, "FAIL", log, test);
			fail("Timeout exception waiting for presence of : " + elementName);
		}
	}


	@When("I Wait until \"(.*?)\" is visible$")
	public static void waitUntilVisible(String elementName) {
		doLogging("Waiting for visibility of : " + elementName, "INFO", log, test);
		By locator = getTestPage().getElementLocator(elementName);
		try {
			getInstance().getWebDriverWait().until(visibilityOfElementLocated(locator));
			doLogging("Element is visible ", "PASS", log, test);
		}
		catch(TimeoutException e) {
			doLogging("Timeout exception waiting for visibility of : " + elementName, "FAIL", log, test);
			fail("Timeout exception waiting for visibility of : " + elementName);
		}
	}

}
