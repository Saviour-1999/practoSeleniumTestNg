package com.cognizant.hackathon.appModules;

import com.cognizant.hackathon.pageObjectModel.PractoInvalidCheck;
import com.cognizant.hackathon.utils.ExcelUtils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.Select;

import java.util.Collections;

public class InvalidCheck {

	// Fetching test data
    public static Object[][] invalidCheckTestData() {
    	return  ExcelUtils.readFormValues("invalidFillupTestData");
    }

    // Scroll down
    public static void scrollDown() {

        PractoInvalidCheck.getBodyElement().sendKeys(Keys.END);
        PractoInvalidCheck.implicitDelay();
    }

    // open Corporate wellness page
    public static void openCorporateWellnessPage() {

        PractoInvalidCheck.getCorporateWellnessLink().click();
        PractoInvalidCheck.implicitDelay();

        PractoInvalidCheck.validatePageTitle();
    }

    // Fill up form details
    public static void fillFormDetails(String name, String organizationName, String officialEmailId,
                                       String officialPhoneNumber, String organizationSize) {

        PractoInvalidCheck.getNewTab();

        PractoInvalidCheck.getNameInputField().sendKeys(name);
        PractoInvalidCheck.implicitDelay();

        PractoInvalidCheck.getOrganizationNameInputField().sendKeys(organizationName);
        PractoInvalidCheck.implicitDelay();

        PractoInvalidCheck.getOfficialEmailIdInputField().sendKeys(officialEmailId);
        PractoInvalidCheck.implicitDelay();

        PractoInvalidCheck.getOfficialPhoneNoInputField().sendKeys(officialPhoneNumber);
        PractoInvalidCheck.implicitDelay();

        selectOrganizationSize(organizationSize);
        PractoInvalidCheck.implicitDelay();
        
        PractoInvalidCheck.takeScreenshot();
        PractoInvalidCheck.implicitDelay();

        PractoInvalidCheck.getScheduleButtonElement().click();
        PractoInvalidCheck.implicitDelay();
    }

    // select organization size
    private static void selectOrganizationSize(String organizationSize) {

        new Select(
                PractoInvalidCheck.getOrganizationSizeSelectElement()

        ).selectByValue(organizationSize);
    }

    // Getting alert message
    public static void getAlertMessage() {

        // provided to enter the image captcha manually
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            ExcelUtils.writeIntoExcel(
                    Collections.singleton(
                            PractoInvalidCheck.getAlertElement().getText()
                    ), "errorMessage"
            );

            PractoInvalidCheck.implicitDelay();

            PractoInvalidCheck.getAlertElement().accept();

        } catch (NoAlertPresentException e) {
            System.err.println(e.getMessage() + " : Didn't fill Captcha in time");
        }
    }
}
