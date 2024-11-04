package com.qa.opencar.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/registerationdata.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {
		// initalizing object array to collect rows and columns from excel
		// declaring object array called data
		Object data[][] = null;
		try {
			// for streaming the input of the file
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			try {

				// workbookfactory- create method making in connection with fileinput stream. it
				// returns workbook ref
				book = WorkbookFactory.create(ip);
				// gets the sheet name of the workbook
				sheet = book.getSheet(sheetName);
				// initalizing object array to collect rows and columns from excel
				// data= new Object[4][5]; //constant row and col
				// get last row and column = 0th row last cell no fives column
				data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

				for (int i = 0; i < sheet.getLastRowNum(); i++) // for the row
				{
					for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) // fro the col
					{
						// row(i+1) bcoz 2nd row in excel carries data.. first row is just col names
						data[i][j] = sheet.getRow(i + 1).getCell(j).toString();  // to string bcoz excel carries excel data convert it to string
					}
				}

			} catch (InvalidFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return data;
	}

}
