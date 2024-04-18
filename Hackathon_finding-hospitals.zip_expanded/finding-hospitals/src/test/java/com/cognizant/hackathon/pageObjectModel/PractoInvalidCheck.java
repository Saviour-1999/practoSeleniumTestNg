package com.cognizant.hackathon.pageObjectModel;

import com.cognizant.hackathon.utils.DriverSetup;
import com.cognizant.hackathon.utils.ExcelUtils;
import com.cognizant.hackathon.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class PractoInvalidCheck extends DriverSetup {
    private static final By BODY_TAG_NAME;
    private static final By CORPORATE_WELLNESS_LINK_TEXT;
    private static final By NAME_ID;
    private static final By ORGANIZATION_NAME_ID;
    private static final By OFFICIAL_EMAIL_ID;
    private static final By OFFICIAL_PHONE_NO_ID;
    private static final By ORGANIZATION_SIZE_ID;
    private static final By SCHEDULE_BUTTON_ID;
    
    private static final Logger LOGGER = LogManager.getLogger(PractoInvalidCheck.class);
	
    private static int ssCount=1;

    static {

        // reading all locator values from excel
        Map<String, String> locators = ExcelUtils.readFromExcel("corporate_wellness_module");

        // setting the locator values
        BODY_TAG_NAME = By.tagName(locators.get("BODY_TAG_NAME"));
        CORPORATE_WELLNESS_LINK_TEXT = By.linkText(locators.get("CORPORATE_WELLNESS_LINK_TEXT"));
        NAME_ID = By.id(locators.get("NAME_ID"));
        ORGANIZATION_NAME_ID = By.id(locators.get("ORGANIZATION_NAME_ID"));
        OFFICIAL_EMAIL_ID = By.id(locators.get("OFFICIAL_EMAIL_ID"));
        OFFICIAL_PHONE_NO_ID = By.id(locators.get("OFFICIAL_PHONE_NO_ID"));
        ORGANIZATION_SIZE_ID = By.id(locators.get("ORGANIZATION_SIZE_ID"));
        SCHEDULE_BUTTON_ID = By.id(locators.get("SCHEDULE_BUTTON_ID"));
    }

    // providing implicit wait
    public static void implicitDelay() {

        LOGGER.debug("Providing Implicit delay of 30 seconds");
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    // validating page title
    public static void validatePageTitle() {

        LOGGER.debug("Title of the page is: {}", webDriver.getTitle());
    }

    // taking screenshot
    public static void takeScreenshot() {
    	
        ScreenshotUtils.takeScreenshot(webDriver,"Form-Fillup"+ssCount++);
    }

    // getting web element for "body" tag
    public static WebElement getBodyElement() {

        LOGGER.debug("Finding Body tag using : {}", BODY_TAG_NAME);
        return webElement = webDriver.findElement(BODY_TAG_NAME);
    }

    // getting web element for "Corporate Wellness" link
    public static WebElement getCorporateWellnessLink() {

        LOGGER.debug("Finding Corporate Wellness link using : {}", CORPORATE_WELLNESS_LINK_TEXT);
        return webElement = webDriver.findElement(CORPORATE_WELLNESS_LINK_TEXT);
    }

    public static void getNewTab() {

        Set<String> handles = webDriver.getWindowHandles();

        for (String handle : handles) webDriver.switchTo().window(handle);

        LOGGER.debug("Opened tab : {}", webDriver.getTitle());
    }

    // getting web element for "Name" input field
    public static WebElement getNameInputField() {

        LOGGER.debug("Finding Name input field using : {}", NAME_ID);
        return webElement = webDriver.findElement(NAME_ID);
    }

    // getting web element for "Organization Name" input field
    public static WebElement getOrganizationNameInputField() {

        LOGGER.debug("Finding Name input field using : {}", ORGANIZATION_NAME_ID);
        return webElement = webDriver.findElement(ORGANIZATION_NAME_ID);
    }

    // getting web element for "Official Email id" input field
    public static WebElement getOfficialEmailIdInputField() {

        LOGGER.debug("Finding Official Email id input field using : {}", OFFICIAL_EMAIL_ID);
        return webElement = webDriver.findElement(OFFICIAL_EMAIL_ID);
    }

    // getting web element for "Official Phone number" input field
    public static WebElement getOfficialPhoneNoInputField() {

        LOGGER.debug("Finding Official Phone number input field using : {}", OFFICIAL_PHONE_NO_ID);
        return webElement = webDriver.findElement(OFFICIAL_PHONE_NO_ID);
    }

    // getting web element for "Organization size" select field
    public static WebElement getOrganizationSizeSelectElement() {

        LOGGER.debug("Finding Organization size select field using : {}", ORGANIZATION_SIZE_ID);
        return webElement = webDriver.findElement(ORGANIZATION_SIZE_ID);
    }

    // getting web element for "Schedule Button" field
    public static WebElement getScheduleButtonElement() {

        LOGGER.debug("Finding Schedule Button field using : {}", SCHEDULE_BUTTON_ID);
        return webElement = webDriver.findElement(SCHEDULE_BUTTON_ID);
    }

    // getting Alert element to capture the alert message
    public static Alert getAlertElement() {

        LOGGER.debug("Getting Alert pop-up");
        return webDriver.switchTo().alert();
    }
}
