package com.walmart.tesop.util;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.walmart.tesop.beans.TransactionReferenceNumber20;

public class Repo1 extends WorkBookXls {

	private static final long serialVersionUID = 7200884271633818508L;

	public Repo1(String pathName) throws IOException {
		super(pathName);
	}
	
	protected void createHeadInXls(){
		HSSFRow rowhead = sheet.createRow((short)0);
        rowhead.createCell(0).setCellValue(cellHeadName);
	}
	
	protected void creteRowInXls(final TransactionReferenceNumber20 trn20, final int numRow){
		HSSFRow row = sheet.createRow((short)numRow);
        row.createCell(0).setCellValue(trn20.getAccountIdentification25());
	}
	
}
