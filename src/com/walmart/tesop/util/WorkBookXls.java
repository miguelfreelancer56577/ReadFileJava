package com.walmart.tesop.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.walmart.tesop.beans.TransactionReferenceNumber20;

public abstract class WorkBookXls extends File{
	
	private static final long serialVersionUID = 6483946532992521752L;
	
	protected HSSFWorkbook workbook;
	protected HSSFSheet sheet;
	
	protected String pathName;
	protected String cellHeadName;
	protected String sheetName;
	
	protected FileOutputStream fileOut;
	
	
	public WorkBookXls(String pathName) throws IOException {
		
		super(pathName);
		
		cellHeadName = "row by default";
		sheetName = "sheet by default";
		
		if(!super.exists())
			super.createNewFile();
		
	}
	
	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getCellHeadName() {
		return cellHeadName;
	}

	public void setCellHeadName(String cellHeadName) {
		this.cellHeadName = cellHeadName;
	}

	protected abstract void createHeadInXls();
	
	protected abstract void creteRowInXls(final TransactionReferenceNumber20 trn20, final int numRow);
	
	public final void createBodyXls(List<TransactionReferenceNumber20> movements){
		
		workbook = new HSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
		
		createHeadInXls();
		
		int count = 1;
		
		for (TransactionReferenceNumber20 transactionReferenceNumber20 : movements) {
			creteRowInXls(transactionReferenceNumber20, count);
			count++;
		}
		
	}
	
	public final boolean createFileXls(){
		try {
			fileOut = new FileOutputStream(this);
			workbook.write(fileOut);
			System.out.println("Your excel file has been generated successfully!");
			return true;
		} catch (IOException e) {
			System.out.println("Error to write a file");
			e.printStackTrace();
			return false;
		}finally{
			if(fileOut != null)
				try {
					fileOut.close();
					System.out.println( pathName + " it has been closed successfully");
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Error to closed " + pathName + " file.");
				}
		}
		
	}
	
	
}
