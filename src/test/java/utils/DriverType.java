package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static utils.Constants.remote;

public enum DriverType implements DriverSetup {

    FIREFOX {
        public WebDriver getWebDriverObject() {
            FirefoxOptions options = new FirefoxOptions();
//            FirefoxProfile profile = new FirefoxProfile();
//            profile.setAcceptUntrustedCertificates(true);
//            profile.setAssumeUntrustedCertificateIssuer(false);
//            profile.setPreference("network.proxy.type", 0);
//            options.setCapability(FirefoxDriver.PROFILE, profile);

            if (remote.equalsIgnoreCase("false"))
                return new FirefoxDriver(options);
            else {
                try {
                    WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                    ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                    return driver;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
    },
    CHROME {
        public WebDriver getWebDriverObject() {
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--start-maximized");
            //options.addArguments("--ignore-certificate-errors");
            //options.addArguments("--disable-popup-blocking");
            //options.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            //options.addArguments("ignore-certificate-errors");
            //options.addArguments("--test-type");
            //options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

            if (remote.equalsIgnoreCase("false"))
                return new ChromeDriver(options);
            else {
                try {
                    WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                    ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                    return driver;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
    }
}