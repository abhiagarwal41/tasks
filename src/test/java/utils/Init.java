package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static utils.Constants.browserType;


public class Init {

    private static Init init = null;

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    private static final String operatingSystem = System.getProperty("os.name").toUpperCase();

    private Init() {

        if (operatingSystem.contains("MAC")) {
            System.setProperty("webdriver.chrome.driver", "./drivers/mac/chromedriver");
            System.setProperty("webdriver.gecko.driver", "./drivers/mac/geckodriver");
        } else if (operatingSystem.contains("WINDOWS")) {
            System.setProperty("webdriver.chrome.driver", "./drivers/windows/chromedriver2.exe");
            System.setProperty("webdriver.gecko.driver", "./drivers/windows/geckodriver.exe");
        }

    }

    public WebDriver getDriver() {
        if (driver == null) {
            throw new RuntimeException("webDriver has not been initialized yet");
        }
        return driver;
    }


    public static Init getInstance() {
        if (init == null) {
            init = new Init();
        }
        return init;
    }

    public void startApplication(String url) {

        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Browser Selection: " + browserType);
        driver = browserType.getWebDriverObject();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, 30);
        driver.get(url);

    }


    public void stopApplication() {
        if (driver!=null)
            driver.quit();
    }

    public WebDriverWait getWebDriverWait() {
        if (webDriverWait == null) {
            throw new RuntimeException("webDriverWait has not been initialized yet");
        }
        return webDriverWait;
    }

}
