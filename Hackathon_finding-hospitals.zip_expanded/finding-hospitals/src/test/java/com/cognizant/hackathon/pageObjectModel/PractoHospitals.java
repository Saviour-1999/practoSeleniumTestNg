package com.cognizant.hackathon.pageObjectModel;

import com.cognizant.hackathon.utils.DriverSetup;
import com.cognizant.hackathon.utils.ExcelUtils;
import com.cognizant.hackathon.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PractoHospitals extends DriverSetup {
    private static final By LOCATION_SEARCH_B0X_XPATH;
    private static final By CITY_OPTION_CLASSNAME;
    private static final By HOSPITAL_SEARCH_BOX_XPATH;
    private static final By SEARCH_TYPE_OPTION_CSS_SELECTOR;
    private static final By OPEN_24X7_CHECK_BOX_XPATH;
    private static final By ALL_FILTERS_OPTION_XPATH;
    private static final By CAR_PARKING_CHECK_BOX_XPATH;
    private static final By ALL_HOSPITALS_CSS_SELECTOR;
    private static final By HOSPITAL_NAME_TAG_NAME;
    private static final By HOSPITAL_RATING_CLASSNAME;
    private static final By NEXT_LINK_TEXT;
    private static final By BODY_TAG_NAME;

    private static final Logger LOGGER = LogManager.getLogger(PractoHospitals.class);

    static {

        // Reading from excel
        Map<String, String> locators = ExcelUtils.readFromExcel("hospital_list_module");

        // setting the locator values
        LOCATION_SEARCH_B0X_XPATH = By.xpath(locators.get("LOCATION_SEARCH_B0X_XPATH"));
        CITY_OPTION_CLASSNAME = By.className(locators.get("CITY_OPTION_CLASSNAME"));
        HOSPITAL_SEARCH_BOX_XPATH = By.xpath(locators.get("HOSPITAL_SEARCH_BOX_XPATH"));
        SEARCH_TYPE_OPTION_CSS_SELECTOR = By.cssSelector(locators.get("SEARCH_TYPE_OPTION_CSS_SELECTOR"));
        OPEN_24X7_CHECK_BOX_XPATH = By.xpath(locators.get("OPEN_24X7_CHECK_BOX_XPATH"));
        ALL_FILTERS_OPTION_XPATH = By.xpath(locators.get("ALL_FILTERS_OPTION_XPATH"));
        CAR_PARKING_CHECK_BOX_XPATH = By.xpath(locators.get("CAR_PARKING_CHECK_BOX_XPATH"));
        ALL_HOSPITALS_CSS_SELECTOR = By.cssSelector(locators.get("ALL_HOSPITALS_CSS_SELECTOR"));
        HOSPITAL_NAME_TAG_NAME = By.cssSelector(locators.get("HOSPITAL_NAME_TAG_NAME"));
        HOSPITAL_RATING_CLASSNAME = By.className(locators.get("HOSPITAL_RATING_CLASSNAME"));
        NEXT_LINK_TEXT = By.linkText(locators.get("NEXT_LINK_TEXT"));
        BODY_TAG_NAME = By.tagName(locators.get("BODY_TAG_NAME"));
    }

    // providing implicit wait
    public static void implicitDelay() {

        LOGGER.debug("Providing Implicit delay of 10 seconds");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // providing explicit wait
    public static void delay() {

        LOGGER.debug("Providing explicit delay of 1 seconds");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // taking screenshot
    public static void takeScreenshot() {
        ScreenshotUtils.takeScreenshot(webDriver,"Hospital-Search");
    }

    // getting web element for "Location Search Box" link
    public static WebElement locationSearchBoxElement() {

        LOGGER.debug("Finding Location search box using : {}", LOCATION_SEARCH_B0X_XPATH);
        return webElement = webDriver.findElement(LOCATION_SEARCH_B0X_XPATH);
    }

    public static void clearSearchBoxElement() {
    	locationSearchBoxElement().click();
    	locationSearchBoxElement().sendKeys(Keys.chord(Keys.CONTROL,"a"), Keys.DELETE);
    }

    // getting desired 'City' option to select city
    public static WebElement getCityOptionElement() {

        LOGGER.debug("Getting City option using : {}", CITY_OPTION_CLASSNAME);
        return webElement = webDriver.findElement(CITY_OPTION_CLASSNAME);
    }

    // validating page title
    public static void validatePageTitle() {

        LOGGER.debug("Title of the page is: {}", webDriver.getTitle());
    }

    // getting "Hospital Search Box" web element
    public static WebElement hospitalSearchBoxElement() {

        LOGGER.debug("Finding Hospital search box using : {}", HOSPITAL_SEARCH_BOX_XPATH);
        return webElement = webDriver.findElement(HOSPITAL_SEARCH_BOX_XPATH);
    }

    // getting "Hospital Option" web element
    public static WebElement searchTypeOptionElement() {

        LOGGER.debug("Getting Hospital option using : {}", SEARCH_TYPE_OPTION_CSS_SELECTOR);
        return webElement = webDriver.findElement(SEARCH_TYPE_OPTION_CSS_SELECTOR);
    }

    // getting "All filters option" web element
    public static WebElement getAllFiltersOptionElement() {

        LOGGER.debug("Getting All filters option using : {}", ALL_FILTERS_OPTION_XPATH);
        return webElement = webDriver.findElement(ALL_FILTERS_OPTION_XPATH);
    }

    // getting "Car parking checkbox" web element
    public static WebElement getCarParkingCheckBoxElement() {

        LOGGER.debug("Getting Car parking checkbox using : {}", CAR_PARKING_CHECK_BOX_XPATH);
        return webElement = webDriver.findElement(CAR_PARKING_CHECK_BOX_XPATH);
    }

    // getting "Open 24X7 checkbox" web element
    public static WebElement getOpen24X7CheckBoxElement() throws StaleElementReferenceException {

        LOGGER.debug("Getting Open 24X7 checkbox using : {}", OPEN_24X7_CHECK_BOX_XPATH);
        WebDriverWait wait=new WebDriverWait(webDriver,30);
        wait.until(ExpectedConditions.elementToBeClickable(OPEN_24X7_CHECK_BOX_XPATH));
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return webDriver.findElement(OPEN_24X7_CHECK_BOX_XPATH);
    }

    // getting list of web elements for all hospitals
    public static List<WebElement> getAllHospitalElements() {

        LOGGER.debug("Retrieving All hospitals using : {}", ALL_HOSPITALS_CSS_SELECTOR);
        return webDriver.findElements(ALL_HOSPITALS_CSS_SELECTOR);
    }

    // getting sub web element of a hospital to fetch its name
    public static WebElement getHospitalNameElement(WebElement element) {
        LOGGER.debug("Retrieving Hospital name using : {}", HOSPITAL_NAME_TAG_NAME);
        return webElement = element.findElement(HOSPITAL_NAME_TAG_NAME);
    }

    // getting sub web element of a hospital to fetch its rating
    public static WebElement getHospitalRatingElement(WebElement element) {
        LOGGER.debug("Retrieving Hospital rating using : {}", HOSPITAL_RATING_CLASSNAME);
        return webElement = element.findElement(HOSPITAL_RATING_CLASSNAME);
    }

    // getting 'Next' link text
    public static WebElement getNextLinkText() {
        LOGGER.debug("Retrieving Next link element using : {}", NEXT_LINK_TEXT);
        return webElement = webDriver.findElement(NEXT_LINK_TEXT);
    }

    // getting 'Body' element
    public static WebElement getBodyElement() {
        LOGGER.debug("Retrieving Body element using : {}", BODY_TAG_NAME);
        return webElement = webDriver.findElement(BODY_TAG_NAME);
    }

}
