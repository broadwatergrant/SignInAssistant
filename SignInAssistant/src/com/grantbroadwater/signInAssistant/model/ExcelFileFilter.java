package com.grantbroadwater.signInAssistant.model;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ExcelFileFilter extends FileFilter {

	public ExcelFileFilter() {
		
	}

	@Override
	public boolean accept(File f) {
		if(f.isDirectory())
			return true;
		
		String ext = getExtension(f);
		if(ext != null){
			return ext.equals("xlsx"); // TODO: Add more functionality
		}else{
			return false;
		}
	}

	@Override
	public String getDescription() {
		return "Excel Workbooks | *.xlsx";
	}

	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

}
