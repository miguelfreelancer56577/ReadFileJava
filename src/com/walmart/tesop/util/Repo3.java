package com.walmart.tesop.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.walmart.tesop.beans.StatementLine;
import com.walmart.tesop.beans.TransactionReferenceNumber20;

public class Repo3 extends WorkBookXls {
	
	public Repo3(String pathName) throws IOException {
		super(pathName);
	}
	
	@Override
	protected void createHeadInXls() {
		HSSFRow rowhead = sheet.createRow((short)0);
		rowhead.createCell(0).setCellValue("Account");
        rowhead.createCell(1).setCellValue("Currency Code");
        rowhead.createCell(2).setCellValue("It has movements");
        rowhead.createCell(3).setCellValue("Number of movements");
	}

	@Override
	protected void creteRowInXls(TransactionReferenceNumber20 trn20, int numRow) {
		
		HSSFRow row = sheet.createRow((short)numRow);
		row.createCell(0).setCellValue(trn20.getAccountIdentification25());
        row.createCell(1).setCellValue(trn20.getOpeningBalance60().getCurrencyCode());
        row.createCell(2).setCellValue("no");
        row.createCell(3).setCellValue(0);
        
		List<StatementLine> statementLineList = trn20.getStatementLineList();
		
		if(statementLineList != null && statementLineList.size() > 0){
			row.createCell(2).setCellValue("yes");
	        row.createCell(3).setCellValue(statementLineList.size());
		}
		
	}

}
