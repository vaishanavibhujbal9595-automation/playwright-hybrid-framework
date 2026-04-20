package com.automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static Object[][] getTestData(String filePath, String sheetName) {
        Object[][] data = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum();

            // THE FIX: Hardcode to strictly read ONLY the first 3 columns.
            // This prevents TestNG crashes if Excel has hidden/empty columns to the right.
            int expectedColumns = 3;

            data = new Object[rowCount][expectedColumns];

            // Loop through rows (skipping header row 0)
            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i + 1);

                // Failsafe: If the entire row is completely blank, skip it
                if (row == null) continue;

                for (int j = 0; j < expectedColumns; j++) {
                    Cell cell = row.getCell(j);
                    // Convert whatever is in the cell to a String safely
                    data[i][j] = (cell == null) ? "" : cell.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}