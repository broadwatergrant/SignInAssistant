package com.grantbroadwater.signInAssistant.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.grantbroadwater.school.BellSchedule;
import com.grantbroadwater.school.ClassPeriod;
import com.grantbroadwater.util.Log;

public class ScheduleExcelReader extends ExcelReader {

	ArrayList<BellSchedule> scheduleList;
	
	public ScheduleExcelReader() {
		super();
		scheduleList = new ArrayList<BellSchedule>();
	}

	public ScheduleExcelReader(File excelFile) {
		super(excelFile);
		scheduleList = new ArrayList<BellSchedule>();
	}

	public ScheduleExcelReader(int sheetIndex) {
		super(sheetIndex);
		scheduleList = new ArrayList<BellSchedule>();
	}

	public ScheduleExcelReader(File excelFile, int sheetIndex) {
		super(excelFile, sheetIndex);
		scheduleList = new ArrayList<BellSchedule>();
	}
	
	@Override
	public void read(){
		try {
			InputStream input = new FileInputStream(getExcelFile());
			Workbook wb = new XSSFWorkbook(input); // TODO: Determine xssf or hssf
			Sheet sheet = wb.getSheetAt(getSheetIndex());
			ClassPeriod period = null;
			BellSchedule schedule = null;
			
			Iterator<Row> rows = sheet.rowIterator();
			while(rows.hasNext()){
				Row row = rows.next();
				period = new ClassPeriod();
				
				Iterator<Cell> cells = row.cellIterator();
				while(cells.hasNext()){
					Cell cell = cells.next();
					
					if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
						continue;
					
					if(row.getCell(0).getCellType() == (Cell.CELL_TYPE_STRING)){ // String type therfore name
						if(row.getRowNum() == 0){ // First row (Current Schedule is empty)
							schedule = new BellSchedule(capitalizePhrase(cell.getStringCellValue()));
						}else{ // Not first row (Current schedule should not be empty)
							scheduleList.add(schedule);
							schedule = new BellSchedule(capitalizePhrase(cell.getStringCellValue()));
						}
					}else{ // Multiple cells in row (Class period of current schedule)
						if(cell.getColumnIndex() == 0){
							GregorianCalendar date = new GregorianCalendar();
							java.util.Date cellValue = cell.getDateCellValue();
							date.setTime(cellValue);
							period.setStart(date);
						}else if(cell.getColumnIndex() == 1){
							GregorianCalendar date = new GregorianCalendar();
							date.setTime(cell.getDateCellValue());
							period.setStop(date);
							schedule.add(period);
						}else{
							new Log(Log.LogType.WARN, "Index "+cell.getColumnIndex()+" encountered");
						}
					}	
				}
			}
			scheduleList.add(schedule);
			
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
		return new ArrayList<Object>(scheduleList);
	}
	
	public String capitalizePhrase(String str){
		String[] parts = str.split(" ");
		String result = "";
		for(String s : parts){
			result += capitalize(s) + " ";
		}
		return result.trim();
	}
	

}
