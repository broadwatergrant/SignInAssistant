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

import com.grantbroadwater.school.Administrator;
import com.grantbroadwater.util.Log;

public class AdminExcelReader extends ExcelReader {

	private ArrayList<Administrator> adminList;
	
	public AdminExcelReader() {
		super();
		adminList = new ArrayList<Administrator>();
	}

	public AdminExcelReader(File excelFile) {
		super(excelFile);
		adminList = new ArrayList<Administrator>();
	}

	public AdminExcelReader(int sheetIndex) {
		super(sheetIndex);
		adminList = new ArrayList<Administrator>();
	}

	public AdminExcelReader(File excelFile, int sheetIndex) {
		super(excelFile, sheetIndex);
		adminList = new ArrayList<Administrator>();
	}

	@Override
	public void read(){
		try {
			InputStream input = new FileInputStream(getExcelFile());
			Workbook wb = new XSSFWorkbook(input);
			Sheet sheet = wb.getSheetAt(getSheetIndex());
			ArrayList<String> format = new ArrayList<String>();
			ArrayList<Object> contents = new ArrayList<Object>();
			
			Iterator<Row> rows = sheet.rowIterator();
			while(rows.hasNext()){
				Row row = rows.next();
				contents.clear();
				
				Iterator<Cell> cells = row.cellIterator();
				int cellCount = 0;
				while(cells.hasNext()){
					Cell cell = cells.next();
					
					if(row.getRowNum() == 0)
						format.add(cell.getStringCellValue());
					else
						while(cellCount != cell.getColumnIndex()){
							contents.add("");
							cellCount++;
						}
						contents.add(readCell(cell));
						cellCount++;
				}
				if(row.getRowNum() != 0)
					adminList.add(createAdminFromData(format.toArray(new String[format.size()]), contents.toArray()));
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
	
	@Override
	public ArrayList<Object> getResult(){
		return new ArrayList<Object>(adminList);
	}
	
	private Administrator createAdminFromData(String[] format, Object[] contents){
		if(format.length != contents.length)
			throw new IllegalArgumentException("Format array and Content array must have the same number of elements " + format.length + " != " + contents.length);
		
		String first = null, last = null, pin = null;
		
		for(int i=0; i<format.length; i++){
			String f = format[i].toLowerCase();
			
			if(f.indexOf("first") != -1)
				first = contents[i] + "";
			else if(f.indexOf("last") != -1)
				last = contents[i] + "";
			else if(f.indexOf("pin") != -1 || f.indexOf("password") != -1)
				pin = contents[i] + "";
			else
				throw new IllegalArgumentException("Format " + i + " (" + f + ") does not contain any recognizable keywords");
		
		}
		
		return new Administrator(first, last, pin);
	}
	
}
