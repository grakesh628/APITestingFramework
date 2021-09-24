package com.w2a.APITestingFramework.utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.w2a.APITestingFramework.setup.BaseTest;


public class DataUtil extends BaseTest{

	@DataProvider(name="data",parallel=true)
	public Object[][] getData(Method m) {

		int rows = excel.getRowCount(config.getProperty("testDataSheet"));
		System.out.println("Total rows are : "+rows);

		String testName = m.getName();
		System.out.println("Test name is : "+testName);
		//Find the test case start row

		int testCaseRowNum  =1;
		for(testCaseRowNum =1;testCaseRowNum<rows;testCaseRowNum++) {
			String testCaseName = excel.getCellData(config.getProperty("testDataSheet"), 0, testCaseRowNum);
			if(testCaseName.equalsIgnoreCase(testName))
				break;

		}
		System.out.println("Test case starts from row num "+testCaseRowNum);

		//Checking total rows in test case
		int dataStartRowNum =testCaseRowNum+2;
		int testRows = 0;
		while(!excel.getCellData(config.getProperty("testDataSheet"), 0, dataStartRowNum + testRows).equals("")) {
			testRows++;
		}
		System.out.println("No.of Test cases are : "+testRows);

		//Checking total columns in test cases

		int testCols=0;
		int colStartNum = testCaseRowNum+1;
		while(!excel.getCellData(config.getProperty("testDataSheet"), testCols, colStartNum).equals("")) {
			testCols++;
		}

		System.out.println("total cols are : "+testCols);

		//printing Data
		Object[][] data = new Object[testRows][1];
		int i=0;
		for(int rNum=dataStartRowNum; rNum<(dataStartRowNum + testRows);rNum++) {

			Hashtable<String,String> table = new Hashtable<String, String>();
			
			for(int cNum=0;cNum<testCols;cNum++) {
//				System.out.println(excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
//				data[rNum-dataStartRowNum][cNum] = excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
				String testData = excel.getCellData(config.getProperty("testDataSheet"), cNum, rNum);
				String colName = excel.getCellData(config.getProperty("testDataSheet"), cNum, colStartNum);
				table.put(colName, testData);
			}
			data[i][0] = table;
			i++;
		}
//		System.out.print("sheetName "+m.getName());
//		String sheetName = m.getName();
//
//		int rows = excel.getRowCount(sheetName);
//		int cols = excel.getColumnCount(sheetName);
//
//		System.out.println("No.of rows : " + rows);
//		System.out.println("No.of cols : " + cols);
//
//		Object[][] data = new Object[rows - 1][cols];
//
//		for (int rowNum = 2; rowNum <= rows; rowNum++) {
//			for (int colNum = 0; colNum < cols; colNum++) {
//				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
//			}
//		}
		return data;
	}
}
