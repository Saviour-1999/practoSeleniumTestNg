package com.cognizant.hackathon.appModules;

import com.cognizant.hackathon.pageObjectModel.PractoHospitals;
import com.cognizant.hackathon.utils.ExcelUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FindHospitals {

    // Fetching test data
    public static Object[] getFindHospitalTestData() {

        return ExcelUtils.readFromExcel("hospitalTestData").values().toArray();
    }

    // Open hospital main page in required city and required type
    public static void openAllHospitalPages(String location, String searchType) {

        PractoHospitals.validatePageTitle();
        PractoHospitals.implicitDelay();

        PractoHospitals.clearSearchBoxElement();
        PractoHospitals.locationSearchBoxElement().sendKeys(location);
        PractoHospitals.delay();

        boolean visible = true;
        while (visible) {
            try {

                PractoHospitals.getCityOptionElement().click();
                visible = false;

            } catch (StaleElementReferenceException ignored) {
            }
        }
        PractoHospitals.implicitDelay();

        PractoHospitals.hospitalSearchBoxElement().sendKeys(searchType);
        PractoHospitals.implicitDelay();

        visible = true;
        while (visible) {
            try {

                PractoHospitals.searchTypeOptionElement().click();
                visible = false;

            } catch (StaleElementReferenceException ignored) {
            }
        }
        PractoHospitals.implicitDelay();
    }

    // hospitals having car parking and 24X7 opened
    public static void applyFilters() {

        PractoHospitals.getAllFiltersOptionElement().click();
        PractoHospitals.implicitDelay();
        PractoHospitals.getCarParkingCheckBoxElement().click();
        PractoHospitals.implicitDelay();

        boolean visible = true;
        while (visible) {
            try {
            	Thread.sleep(4000);
                PractoHospitals.getOpen24X7CheckBoxElement().click();
                visible = false;

            } catch (Exception ignored) {
            }
        }
        PractoHospitals.implicitDelay();
    }

    // Taking screenshot
    public static void takeScreenshot() {

        PractoHospitals.delay();

        PractoHospitals.takeScreenshot();
        PractoHospitals.implicitDelay();
    }

    // Loading all hospital by scrolling down
    public static void loadAllHospitalsByScrolling() {

        while (true) {

            try {
                PractoHospitals.getNextLinkText();

            } catch (NoSuchElementException e) {
                break;
            }

            PractoHospitals.getBodyElement().sendKeys(Keys.PAGE_DOWN);
            PractoHospitals.delay();
        }
    }

    // hospitals with rating more than required
    public static void findFilteredHospitals(String rating) {

        List<WebElement> hospitals = PractoHospitals.getAllHospitalElements();
        System.out.println("Number of hospitals: " + hospitals.size());

        Set<String> hospitalNames = new LinkedHashSet<>();
        for (WebElement hospital : hospitals) {

            try {

                if (Double.parseDouble(PractoHospitals.getHospitalRatingElement(hospital).getText()) > Double.parseDouble(rating.replaceAll("[^0-9.]", "")))
                    hospitalNames.add(PractoHospitals.getHospitalNameElement(hospital).getText());

            } catch (Exception ignored) {
            }
        }

        ExcelUtils.writeIntoExcel(hospitalNames, "hospitalNames");
    }
}
