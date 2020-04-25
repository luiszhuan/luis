package com.luis.excel;

import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriter {
    public static void main(String[] args) {
        try (XSSFWorkbook workbook = new XSSFWorkbook();) {
            String file = "/home/luis/Documents/test.xlsx";
            //XSSFWorkbook Newworkbook = new XSSFWorkbook();

            XSSFSheet testSheet = workbook.getSheet("Test");
            if (testSheet == null) {
                testSheet = workbook.createSheet("Test");
            }
            XSSFRow row = testSheet.createRow(0);
            XSSFCellStyle intCellStyle = workbook.createCellStyle();
            intCellStyle.setDataFormat(1);
            XSSFCell cell = row.createCell(1);
            cell.setCellValue(100);
            cell.setCellStyle(intCellStyle);
            workbook.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
