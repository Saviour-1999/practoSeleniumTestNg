package com.cognizant.hackathon.tests;

import com.cognizant.hackathon.appModules.FindHospitals;
import com.cognizant.hackathon.appModules.FindTopCities;
import com.cognizant.hackathon.appModules.InvalidCheck;
import com.cognizant.hackathon.utils.DriverSetup;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * @author TASC
 * @see "target/test-output for report" and "excels/practoReport.xlsx for output data"
 */
public class PractoTest {

	
    @DataProvider(name = "practoHospitalSearchTestData")
    public Object[][] provideHospitalSearchData() {
        return new Object[][]{FindHospitals.getFindHospitalTestData()};
    }

    @DataProvider(name = "practoCorporateWellnessTestData")
    public Object[][] provideCorporateWellnessData() {
       return InvalidCheck.invalidCheckTestData();
    }

    @BeforeMethod(alwaysRun=true)
    public void init() {
		DriverSetup.init();
    }

    @Test(dataProvider = "practoHospitalSearchTestData", priority = 1, groups = "smoke")
    public void hospitalFindTest(String location, String searchType, String rating) {

        FindHospitals.openAllHospitalPages(location, searchType);
        FindHospitals.applyFilters();
        FindHospitals.takeScreenshot();
        FindHospitals.loadAllHospitalsByScrolling();
        FindHospitals.findFilteredHospitals(rating);
    }

    @Test(priority = 2, groups = "smoke")
    public void topCitiesNameTest() {

        FindTopCities.openDiagnosticsPage();
        FindTopCities.takeScreenshot();
        FindTopCities.getTopCities();
    }

    @Test(dataProvider = "practoCorporateWellnessTestData", priority = 3, groups = {"smoke", "regression"})
    public void invalidCheckModule(String name, String organizationName, String officialEmailId,
                                   String phoneNumber, String organizationSize) {

    	InvalidCheck.scrollDown();
        InvalidCheck.openCorporateWellnessPage();
        InvalidCheck.fillFormDetails(name, organizationName, officialEmailId, phoneNumber, organizationSize);
        InvalidCheck.getAlertMessage();
    }

    @AfterMethod(alwaysRun=true)
    public void destroy() {
        DriverSetup.destroy();
    }
}
