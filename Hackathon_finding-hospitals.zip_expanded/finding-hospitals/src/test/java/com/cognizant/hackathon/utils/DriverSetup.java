package com.cognizant.hackathon.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverSetup {
    public static WebDriver webDriver;
    public static WebElement webElement;
    private static Properties properties;
    private static final String DRIVER_PATH = "src/test/resources/drivers/";
    private static final String PROPERTIES_PATH = "src/test/resources/properties/";
    private static final Logger LOGGER = LogManager.getLogger(DriverSetup.class);

    /**
     * Load the url and the drivers as properties.
     *
     * @throws IOException if the properties file is not found
     **/
    private static void loadProperties() throws IOException {

        properties = new Properties();
        LOGGER.info("Loading the url and the drivers as properties");
        properties.load(new FileInputStream(PROPERTIES_PATH + "config.properties"));
    }

    /**
     * Maximize the window.
     * Navigating to that url.
     **/
    private static void loadUrl() {

        LOGGER.info("Maximizing window");
        webDriver.manage().window().maximize();

        final String baseUrl = properties.getProperty("baseUrl");

        LOGGER.debug("Opening url: {}", baseUrl);
        webDriver.get(baseUrl);
    }

    /**
     * Load all the properties for url and desired browser driver.
     *
     * @param choice to choose b/w chrome, firefox and IE browser.
     *               opening the browser in headless mode and loading the url.
     * @return WebDriver after opening the chosen browser in headless mode, IE browser doesn't support headless mode.
     * @throws FileNotFoundException 
     **/
    public static WebDriver init()  {

        LOGGER.info("Start of setting web driver");
        // loads all the properties
        try {
            loadProperties();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        int choice=Integer.parseInt(properties.getProperty("browser"));
        
        switch (choice) {
            case 1:
                System.setProperty("webdriver.chrome.driver", DRIVER_PATH + properties.getProperty("chrome.driver"));
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications", "--incognito");
                webDriver = new ChromeDriver(chromeOptions);
                break;
            case 2:
                System.setProperty("webdriver.gecko.driver", DRIVER_PATH + properties.getProperty("firefox.driver"));
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
            case 3:
    			//Setting the webdriver.ie.driver property to its executable's location
    			 System.setProperty("webdriver.edge.driver", DRIVER_PATH + properties.getProperty("edge.driver"));
    			//Instantiating driver object
    			 webDriver = new EdgeDriver();
    			 break;
            case 4:
                System.setProperty("webdriver.ie.driver", DRIVER_PATH + properties.getProperty("ie.driver"));
                webDriver = new InternetExplorerDriver();
                break;
        }

        LOGGER.debug("Setup done for Webdriver of type: {}", webDriver.getClass().getSimpleName());

        loadUrl();

        LOGGER.info("End of setting web driver");
        return webDriver;
    }

    // Closes the browser
    public static void destroy() {
        LOGGER.info("Closing browser");
        webDriver.quit();
    }
}
