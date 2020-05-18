package com.qa.hubspot.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {

    public static Workbook book;
    public static Sheet sheet;

    public static String TESTDATA_SHEET_PATH = ".\\src\\main\\resources\\HubSpotTestData.xlsx";

    public static Object[][] getTestData(String sheetName){
        Object data[][] = null;
        try {
            FileInputStream ip = new FileInputStream(TESTDATA_SHEET_PATH);
            book = WorkbookFactory.create(ip);
            sheet = book.getSheet(sheetName);

            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

            for(int i =0;i<sheet.getLastRowNum(); i++){
                for(int j =0; j<sheet.getRow(0).getLastCellNum(); j++){
                    data[i][j] = sheet.getRow(i+1).getCell(j).toString();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return data;
    }

}
