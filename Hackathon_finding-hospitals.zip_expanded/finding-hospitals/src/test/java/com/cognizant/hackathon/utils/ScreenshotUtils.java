package com.cognizant.hackathon.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {
    private static final String SCREENSHOT_PATH = "src/test/resources/screenshots/";
    private static final Logger LOGGER = LogManager.getLogger(ScreenshotUtils.class);

    /**
     * Casting webDriver to TakeScreenshot object.
     * Taking the screenshot and storing into an required file.
     * @param webDriver of type Chrome/Firefox/IE based on the choice
     **/
    public static void takeScreenshot(WebDriver webDriver,String fileName) {

        LOGGER.info("Start of taking screenshot");

        TakesScreenshot screenshot = (TakesScreenshot) webDriver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(SCREENSHOT_PATH + String.format("%s.png", fileName));

        LOGGER.debug("Screenshot taken and stored into file: {}", SCREENSHOT_PATH);

        try {

            FileUtils.copyFile(sourceFile, destinationFile);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.info("End of taking screenshot");
    }
}
