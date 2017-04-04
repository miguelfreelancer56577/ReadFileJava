package com.walmart.tesop.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.walmart.tesop.beans.AccountOwnerInformation86;
import com.walmart.tesop.beans.ClosingAvailableBalance64;
import com.walmart.tesop.beans.ClosingBalance62F;
import com.walmart.tesop.beans.OpeningBalance60F;
import com.walmart.tesop.beans.StatementLine;
import com.walmart.tesop.beans.StatementLine61;
import com.walmart.tesop.beans.SuplementaryDetails99;
import com.walmart.tesop.beans.TransactionReferenceNumber20;

public class Tag86 extends WorkBookXls {
	
	public int numRow = 1;

	public Tag86(String pathName) throws IOException {
		super(pathName);
	}
	
	@Override
	protected void createHeadInXls() {
		
		HSSFRow rowhead = sheet.createRow((short)0);
		rowhead.createCell(0).setCellValue("val Tag 86");
		rowhead.createCell(1).setCellValue("size");
		
	}
	
	@Override
	protected void creteRowInXls(final TransactionReferenceNumber20 trn20, final int numRow){}

	protected void creteRowInXls(String valTag86, int numRow) {
		
		HSSFRow rowhead = sheet.createRow((short)numRow);
		
		int contCells = 0;
		
		rowhead.createCell(contCells++).setCellValue(valTag86);
		
		String[] fragmentTag86 = valTag86.split("/");
		
		rowhead.createCell(contCells++).setCellValue(fragmentTag86.length);
		
		for (String string : fragmentTag86) {
			rowhead.createCell(contCells++).setCellValue(string);
		}
		
	}
	
	public void createBodyTag(List<String> movementsTag86){
		
		workbook = new HSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
		
		this.createHeadInXls();
		
		int count = 1;
		
		for (String string : movementsTag86) {
			this.creteRowInXls(string, count);
			count++;
		}
		
	}
		

}
