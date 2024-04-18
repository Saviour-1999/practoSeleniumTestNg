package com.cognizant.hackathon.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ExcelUtils {
    private static final Logger LOGGER = LogManager.getLogger(ExcelUtils.class);
    private static final String EXCEL_PATH = "src/test/resources/excels/";

    // Reads locators from excel file
    public static Map<String, String> readFromExcel(String sheetName) {

        LOGGER.info("Start of reading");

        Workbook workbook = null;
        try (FileInputStream fileInputStream = new FileInputStream(EXCEL_PATH + "locators_testData.xlsx")) {

            workbook = new XSSFWorkbook(fileInputStream);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // opening sheet of provided name
        Sheet sheet = workbook.getSheet(sheetName);
        Row row1 = sheet.getRow(0);
        Row row2 = sheet.getRow(1);

        Map<String, String> locators = new LinkedHashMap<>();

        // storing every value into a map
        for (int i = 0; i < row1.getLastCellNum(); i++) {

            locators.put(row1.getCell(i).getStringCellValue(), row2.getCell(i).getStringCellValue());
        }

        LOGGER.debug("Reading data completed from excel: {}", locators);

        LOGGER.info("End of reading");

        return locators;
    }
    
  //Read Excel Sheet values and return it in form of 2d array
    public static Object[][] readFormValues(String sheetName) {

        LOGGER.info("Start of reading");

        Workbook workbook = null;
        try (FileInputStream fileInputStream = new FileInputStream(EXCEL_PATH + "locators_testData.xlsx")) {

            workbook = new XSSFWorkbook(fileInputStream);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // opening sheet of provided name
        Sheet sheet = workbook.getSheet(sheetName);

        Object[][] formValues = new Object[sheet.getLastRowNum()][5];
        System.out.println("\n\n\n\n\n\nNumber of Datas for Form: " +sheet.getLastRowNum());
        // storing every value into a map
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
        	for(int j = 0;j < 5;j++){
        		formValues[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
        	}
        }

        LOGGER.info("End of reading");
        return formValues;

    }

    // Writes holiday offers and their prices excel file
    public static void writeIntoExcel(Set<String> datas, String sheetName) {

        LOGGER.info("Start of writing");

        Workbook workbook = null;
        try (FileInputStream fileInputStream = new FileInputStream(EXCEL_PATH + "practoReport.xlsx")) {

            workbook = new XSSFWorkbook(fileInputStream);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        assert workbook != null;
        Sheet sheet = (workbook.getSheet(sheetName) == null) ?
                workbook.createSheet(sheetName) : workbook.getSheet(sheetName);

        int rowCount = 0;
        for (String data : datas) {

            Row row = sheet.createRow(rowCount);
            Cell cell = row.createCell(0, CellType.STRING);

            LOGGER.debug("Writing data: {}", data);

            cell.setCellValue(data);

            rowCount++;
        }

        LOGGER.debug("Total written data : {} for sheet : {}", datas.size(), sheetName);

        sheet.autoSizeColumn(0);

        try (FileOutputStream fileOutputStream = new FileOutputStream(EXCEL_PATH + "practoReport.xlsx")) {

            workbook.write(fileOutputStream);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.info("End of writing");
    }
}
