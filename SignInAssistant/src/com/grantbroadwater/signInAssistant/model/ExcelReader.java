package com.grantbroadwater.signInAssistant.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private File excelFile;
	private String[][] result;
	
	public ExcelReader() {
		excelFile = null;
		result = null;
	}

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}
	
	public void read(){
		try {
			InputStream input = new FileInputStream(excelFile);
			Workbook wb = new XSSFWorkbook(input);
			Sheet sheet = wb.getSheetAt(0);
			
			result = new String[sheet.getLastRowNum() + 1][sheet.getRow(0).getLastCellNum() + 1];
			
			Iterator<Row> rows = sheet.rowIterator();
			int rcount = 0;
			while(rows.hasNext()){
				Row row = rows.next();
				
				Iterator<Cell> cells = row.cellIterator();
				int ccount = 0;
				while(cells.hasNext()){
					Cell cell = cells.next();
					String str = this.readCell(cell);
					result[rcount][ccount] = str;
					ccount++;
				}
				rcount++;
			}
			
			wb.close();
			input.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String readCell(Cell cell){
		String result = null;
		switch(cell.getCellType()){
		case Cell.CELL_TYPE_BLANK:
			result = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			result = cell.getBooleanCellValue() + "";
			break;
		case Cell.CELL_TYPE_ERROR:
			result = "";
			break;
		case Cell.CELL_TYPE_FORMULA:
			result = cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			double d = cell.getNumericCellValue();
			if(d % 1 == 0)
				result = ((int)d) + "";
			else
				result = d + "";
			break;
		case Cell.CELL_TYPE_STRING:
			result = cell.getStringCellValue();
			break;
		default:
			break;
		}
		return result;
	}
	
	public String[][] getResult(){
		return result;
	}
	
	public String[][] readAndReturn(){
		read();
		return getResult();
	}
	
	public void read(File excelFile){
		setExcelFile(excelFile);
		read();
	}

	public String[][] readAndReturn(File excelFile){
		setExcelFile(excelFile);
		return readAndReturn();
	}
}
