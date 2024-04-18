package com.cognizant.hackathon.appModules;

import com.cognizant.hackathon.pageObjectModel.PractoTopCities;
import com.cognizant.hackathon.utils.ExcelUtils;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashSet;
import java.util.Set;

public class FindTopCities {

    // opening diagnostics page
    public static void openDiagnosticsPage() {

        PractoTopCities.getDiagnosticsLinkXpath().click();
        PractoTopCities.implicitDelay();

        PractoTopCities.validatePageTitle();
    }

    // taking screenshot
    public static void takeScreenshot() {

        PractoTopCities.takeScreenshot();
        PractoTopCities.implicitDelay();
    }

    // getting name of the top cities
    public static void getTopCities() {

        Set<String> cities = new LinkedHashSet<>();
        for (WebElement topCitiesElement : PractoTopCities.getTopCitiesElements()) {

            cities.add(topCitiesElement.getText());
        }

        ExcelUtils.writeIntoExcel(cities, "topCities");
    }
}
