package com.grantbroadwater.signInAssistant.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.grantbroadwater.util.Log;

public class ExcelReader {

	private File excelFile;
	private ArrayList<Object> result;
	private int sheetIndex;
	
	public ExcelReader() {
		super();
		this.setExcelFile(null);
		this.setSheetIndex(0);;
		result = new ArrayList<Object>();
	}
	
	public ExcelReader(File excelFile) {
		this();
		this.setExcelFile(excelFile);
	}
	
	public ExcelReader(int sheetIndex) {
		this();
		this.setSheetIndex(sheetIndex);
	}

	public ExcelReader(File excelFile, int sheetIndex) {
		this();
		this.setExcelFile(excelFile);
		this.setSheetIndex(sheetIndex);
	}

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}
	
	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
	
	public void setData(File excelFile, int sheetIndex){
		setExcelFile(excelFile);
		setSheetIndex(sheetIndex);
	}
	
	public void read(){
		try {
			InputStream input = new FileInputStream(excelFile);
			Workbook wb = new XSSFWorkbook(input);
			Sheet sheet = wb.getSheetAt(sheetIndex);
			
			Iterator<Row> rows = sheet.rowIterator();
			while(rows.hasNext()){
				Row row = rows.next();
				
				Iterator<Cell> cells = row.cellIterator();
				int cellCount = 0;
				while(cells.hasNext()){
					Cell cell = cells.next();
					while(cellCount != cell.getColumnIndex()){
						result.add("");
						cellCount++;
					}
					result.add(this.readCell(cell));
					cellCount++;
				}
			}
			
			wb.close();
			input.close();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			new Log(Log.LogType.ERROR, sw.toString());
		}
	}
	
	protected Object readCell(Cell cell){
		Object result = null;
		
		switch(cell.getCellType()){
		case Cell.CELL_TYPE_BLANK:
			result = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			result = new Boolean(cell.getBooleanCellValue());
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
				result = new Integer((int)d);
			else
				result = new Double(d);
			break;
		case Cell.CELL_TYPE_STRING:
			result = cell.getStringCellValue().trim();
			break;
		default:
			break;
		}
		
		return result;
	}
	
	public ArrayList<Object> getResult(){
		return result;
	}
	
	public ArrayList<Object> readAndReturn(){
		read();
		return getResult();
	}
	
	public void read(File excelFile){
		setExcelFile(excelFile);
		read();
	}

	public ArrayList<Object> readAndReturn(File excelFile){
		setExcelFile(excelFile);
		return readAndReturn();
	}
	
	public void read(File excelFile, int sheetIndex){
		setData(excelFile, sheetIndex);
		read();
	}
	
	public ArrayList<Object> readAndReturn(File excelFile, int sheetIndex){
		setData(excelFile, sheetIndex);
		return readAndReturn();
	}
	
	public String capitalize(String str){
		if(str == null || str.length() == 0)
			return "";
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
	
}
