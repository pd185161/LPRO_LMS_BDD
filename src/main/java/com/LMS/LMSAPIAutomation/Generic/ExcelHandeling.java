/**
 * 
 */
package com.LMS.LMSAPIAutomation.Generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.LMS.LMSAPIAutomation.Resources.Log4j;

/**
 * @author sa185402
 *
 */
public class ExcelHandeling {
	
	@SuppressWarnings("resource")
	public static Boolean createExcelFile(String filePath) throws IOException {
		
		boolean available = false;
		
		try {
				
			File file = new File(filePath);
			if(file.isFile() && file.exists()) {
				available = true;
				Log4j.loginfo(filePath + " Excel File is available");	
			}else {
				FileOutputStream fileOut = new FileOutputStream(new File(filePath));
				XSSFWorkbook workbook = new XSSFWorkbook();
				workbook.write(fileOut);
				fileOut.close();
				available = true;
				Log4j.loginfo(filePath + " Excel File is created sucessfully");	
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.error(e.getMessage());
			Log4j.Warn(filePath + " Excel file is not created");
		}
		
		return available;
		
	}
	
	public static boolean createWorkbook(String filePath, String module) {
		
		String sheetName ="";
		boolean sheet = false;
		
		try {
			if (createExcelFile(filePath)) {
				FileInputStream fileInput = new FileInputStream(new File(filePath));
				XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
				workbook.getNumberOfSheets();
				System.out.println(workbook.getNumberOfSheets());
				for (int i = 0; i <= workbook.getNumberOfSheets()-1; i++) {
					sheetName = workbook.getSheetName(i);
					if (sheetName.equalsIgnoreCase(module)) {
						sheet = true;
						break;
					}else if(i == workbook.getNumberOfSheets()) {
						workbook.createSheet(module);
						sheet = true;
						Log4j.loginfo(module + " workbook is created sucessfully");
					}	
				}
				if (workbook.getNumberOfSheets() == 0) {
					workbook.createSheet(module);
					sheet = true;
					Log4j.loginfo(module + " workbook is created sucessfully");
				}
				FileOutputStream fileOut = new FileOutputStream(new File(filePath));
				workbook.write(fileOut);
				fileOut.close();
			}else {
				Log4j.loginfo("WorkBook is not created File " + filePath + " is not available");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.error(e.getMessage());
		}	
		return sheet;
	}
	
	public static String readExcelFile(String filePath, String module, String testcase,String columnName) {
		
		String cell_Value = "";
		try {
	
			if (createWorkbook(filePath, module)) {
				FileInputStream fileInput = new FileInputStream(new File(filePath));
				XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
				XSSFSheet sheet = workbook.getSheet(module);
				int row = getRow(sheet,filePath,module,testcase );
				int col = getColumn(sheet,filePath,module,columnName);
				XSSFCell cell=	sheet.getRow(row).getCell(col);
				cell_Value = cell.getStringCellValue();
				fileInput.close();
			}
			
		} catch (Exception e) {
			Log4j.error(e.getMessage());
		}
		return cell_Value;
	}
	
	public static int getRow(XSSFSheet sheet, String filePath,String module,String testcase ) {
		
		int row = 0;
		try {
				int row_Count = sheet.getLastRowNum();
				for (int i = 0; i <= row_Count; i++) {
				XSSFCell cell=	sheet.getRow(i).getCell(1);
				String testcase_runtime = cell.getStringCellValue();
					if (testcase.equalsIgnoreCase(testcase_runtime)) {
						row = i;
						break;
					}
				}
		} catch (Exception e) {
			Log4j.error(e.getMessage());
		}
		return row;
		
	}
	
	public static int getColumn(XSSFSheet sheet, String filePath,String module,String columnName) {
		
		int col=0;
		try {
				int col_Count = sheet.getRow(1).getLastCellNum();
				for (int i = 0; i <= col_Count; i++) {
					XSSFCell cell=	sheet.getRow(1).getCell(i);
					String col_Runtime = cell.getStringCellValue();
					if(columnName.equalsIgnoreCase(col_Runtime)) {
						col = i;
						break;
					}
				}
		} catch (Exception e) {
			Log4j.error(e.getMessage());
		}
		return col;
	}
	
	public static void setValue(String filePath, String module,String testcase, String columnName, String value) {
		
		try {	
			if (createWorkbook(filePath, module)) {
				
				FileInputStream fis= new FileInputStream(new File(filePath));
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheet = workbook.getSheet(module);
				int row_number = getRow(sheet,filePath,module,testcase);
				int col_number = getColumn(sheet,filePath,module,columnName);
				
				if(sheet.getRow(row_number) == null) {
					Row row =sheet.createRow(row_number);
					Cell cell = row.createCell(col_number);
					cell.setCellValue(value);
					fis.close();
					FileOutputStream fileout= new FileOutputStream(new File(filePath));
					workbook.write(fileout);
					fileout.close();
				}else {
					Row row1 =sheet.getRow(row_number);
					Cell cell1 = row1.createCell(col_number);
					cell1.setCellValue(value);
					fis.close();
					FileOutputStream fileout= new FileOutputStream(new File(filePath));
					workbook.write(fileout);
					fileout.close();
				}
				
			}
			
		} catch (Exception e) {
			Log4j.error(e.getMessage());
		}
		
	}
	
	
	public static boolean setColumn(String filePath, String module, String testcase, String column ) {
		
		boolean col_Exist = false;
		try {
			if (createWorkbook(filePath, module)) {
				
				FileInputStream fis= new FileInputStream(new File(filePath));
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheet = workbook.getSheet(module);

					if (sheet.getRow(0) != null) {
					int col_Count = sheet.getRow(0).getLastCellNum();
					for (int i = 0; i <= col_Count; i++) {
						XSSFCell xssfcell =  sheet.getRow(0).getCell(i);
						
						if (xssfcell != null) {
							String col_Runtime = xssfcell.getStringCellValue();
							if (col_Runtime.equalsIgnoreCase(column)) {
								col_Exist = true;
								fis.close();
								break;
							}
						}else {
//							Row row =sheet.createRow(0);
							Row row =sheet.getRow(0);
							if (row == null) {
								Row row1 =sheet.createRow(0);
								Cell cell1 = row1.createCell(i);
								cell1.setCellValue(column);
							}
							Cell cell = row.createCell(i);
							cell.setCellValue(column);
							col_Exist = true;
							fis.close();
							FileOutputStream fileout= new FileOutputStream(new File(filePath));
							workbook.write(fileout);
							fileout.close();
							break;
						}
					}
				}
			else {
					
					Row row =sheet.createRow(0);
					Cell cell = row.createCell(0);
					cell.setCellValue(column);
					col_Exist = true;
					fis.close();
					FileOutputStream fileout= new FileOutputStream(new File(filePath));
					workbook.write(fileout);
					fileout.close();
				}
				
			}
			
		} catch (Exception e) {
			Log4j.error(e.getMessage());
		}
		return col_Exist;
		
	}
	
	public static Boolean setTestcaseName(String filePath, String module,String testCase) {
		
		boolean testCase_available = false;
		try {
			if (createWorkbook(filePath, module)) {
				FileInputStream fis = new FileInputStream(new File(filePath));
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheet = workbook.getSheet(module);
				int row_count = sheet.getLastRowNum();
				if (row_count < 1) {

					Row row =sheet.createRow(1);
					Cell cell1 = row.createCell(0);
					cell1.setCellValue(testCase);
					fis.close();
					FileOutputStream fileout= new FileOutputStream(new File(filePath));
					workbook.write(fileout);
					fileout.close();
					testCase_available = true;	
					
				}else {
					for (int i = 0; i <= row_count; i++) {
						XSSFCell cell=	sheet.getRow(i).getCell(0);
						
						if (cell != null) {
							String col_Runtime = cell.getStringCellValue();
							if(testCase.equalsIgnoreCase(col_Runtime)) {
								testCase_available = true;
								fis.close();
								break;
							}else if((i == row_count)) {
								Row row =sheet.createRow(i+1);
								Cell cell1 = row.createCell(0);
								cell1.setCellValue(testCase);
								fis.close();
								FileOutputStream fileout= new FileOutputStream(new File(filePath));
								workbook.write(fileout);
								fileout.close();
								testCase_available = true;
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			Log4j.error(e.getMessage());
		}
		return testCase_available;
		
	}
	
	public static void writeExcel(String filePath, String SheetName,String FileName, String extension) {
		
		try {
			FileInputStream fis= new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(SheetName);
			int rownum = sheet.getLastRowNum();
		//	for (int i = 1; i < count; i++) {
			//	for (int j = 0; j < 2; j++) {
					XSSFRow row = sheet.createRow(rownum+1);
					Cell cell = row.createCell(0);
					cell.setCellValue(FileName);
					Cell cell1 = row.createCell(1);
					cell1.setCellValue(extension);
				//}
				
				fis.close();
				FileOutputStream fileout= new FileOutputStream(new File(filePath));
				workbook.write(fileout);
				fileout.close();
			//}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	
}
