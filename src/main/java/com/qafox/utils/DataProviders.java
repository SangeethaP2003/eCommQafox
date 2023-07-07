package com.qafox.utils;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

public class DataProviders {
	
	

	public static String[][] getTableArray(String sheetName) throws Exception {
		try {
			File datafile = new File(".\\TestData\\TestData.xls");
			String path = datafile.getCanonicalPath();
			System.out.println(path);
			Workbook workbook = Workbook.getWorkbook(new File(path));
			Sheet sheet = workbook.getSheet(sheetName);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();

			String[][] tabArray = new String[rows - 1][cols];
			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					tabArray[i - 1][j] = sheet.getCell(j, i).getContents();
				}
			}
			workbook.close();
			return (tabArray);
		} catch (Exception e) {
			System.out.println(e + Thread.currentThread().getStackTrace()[1].getClassName() + " dataprovider");
			return null;
		}

	}
}
