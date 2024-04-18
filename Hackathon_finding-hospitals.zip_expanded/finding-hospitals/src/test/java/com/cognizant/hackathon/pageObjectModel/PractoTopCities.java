package com.cognizant.hackathon.pageObjectModel;

import com.cognizant.hackathon.utils.DriverSetup;
import com.cognizant.hackathon.utils.ExcelUtils;
import com.cognizant.hackathon.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PractoTopCities extends DriverSetup {
    private static final By DIAGNOSTICS_LINK_XPATH;
    private static final By TOP_CITIES_CLASSNAME;

    private static final Logger LOGGER = LogManager.getLogger(PractoHospitals.class);

    static {

        // Reading from excel
        Map<String, String> locators = ExcelUtils.readFromExcel("top_cities_module");

        // setting the locator values
        DIAGNOSTICS_LINK_XPATH = By.xpath(locators.get("DIAGNOSTICS_LINK_XPATH"));
        TOP_CITIES_CLASSNAME = By.className(locators.get("TOP_CITIES_CLASSNAME"));
    }

    // providing implicit wait
    public static void implicitDelay() {

        LOGGER.debug("Providing Implicit delay of 10 seconds");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // taking screenshot
    public static void takeScreenshot() {
        ScreenshotUtils.takeScreenshot(webDriver,"Top-Cities");
    }

    // validating page title
    public static void validatePageTitle() {

        LOGGER.debug("Title of the page is: {}", webDriver.getTitle());
    }

    // getting 'Next' link text
    public static WebElement getDiagnosticsLinkXpath() {
        LOGGER.debug("Retrieving Diagnostics link element using : {}", DIAGNOSTICS_LINK_XPATH);
        return webElement = webDriver.findElement(DIAGNOSTICS_LINK_XPATH);
    }

    // getting 'Body' element
    public static List<WebElement> getTopCitiesElements() {
        LOGGER.debug("Retrieving Top cities elements using : {}", TOP_CITIES_CLASSNAME);
        return webDriver.findElements(TOP_CITIES_CLASSNAME);
    }
}
