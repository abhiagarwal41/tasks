package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

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
					// no jQuery present
					return true;
				}
			};

			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};

			getInstance().getWebDriverWait().until(pageLoadCondition);
			getInstance().getWebDriverWait().until(jQueryLoad);
		}
		catch(TimeoutException e) {
			doLogging("Timeout exception waiting for page to load: " + pageName, "FAIL", log, test);
			fail("Timeout exception waiting for page to load: " + pageName);
		}
		try {
			setTestPage(pageName);
			Thread.sleep(1000);
		} catch (Exception e) {
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


	@When("I Wait until \"(.*?)\" is clickable$")
	public static void waitUntilClickable(String elementName) {
		doLogging("Waiting for clickability of : " + elementName, "INFO", log, test);
		By locator = getTestPage().getElementLocator(elementName);
		try {
			 getInstance().getWebDriverWait().until(elementToBeClickable(locator));
		}
		catch(TimeoutException e) {
			doLogging("Timeout exception waiting for clickability of : " + elementName, "FAIL", log, test);
			fail("Timeout exception waiting for clickability of : " + elementName);
		}

	}



	@When("I Wait until \"(.*?)\" is invisible$")
	public void waitUntilInVisible(String elementName){
		doLogging("Waiting for invisibility of : " + elementName, "INFO", log, test);
		By locator = getTestPage().getElementLocator(elementName);
		try {
			getInstance().getWebDriverWait().until(invisibilityOfElementLocated(locator));
		}catch(TimeoutException e) {
			doLogging("Timeout exception waiting for invisibility of : " + elementName, "FAIL", log, test);
			fail("Timeout exception waiting for invisibility of : " + elementName);
		}
	}

	


	@And("Wait until {string} has text {string}")
	public void waitUntilHasText(String elementName, String text) {
		By locator = getTestPage().getElementLocator(elementName);
		try {
			getInstance().getWebDriverWait().until(new ExpectedCondition<Object>() {
				@NullableDecl
				@Override
				public Object apply(@NullableDecl WebDriver driver) {
					try {
						String elementText = driver.findElement(locator).getText().toLowerCase().trim();
						return elementText.contains(text.toLowerCase().trim());
					} catch (StaleElementReferenceException var3) {
						return null;
					}
				}
			});
			doLogging("Text matched for " + elementName + " : " + text, "PASS", log, test);
		}catch(TimeoutException e) {
			doLogging("Timeout exception waiting for " + elementName + " to contain " + text, "FAIL", log, test);
			fail("Timeout exception waiting for " + elementName + " to contain " + text);
		}
	}
}
