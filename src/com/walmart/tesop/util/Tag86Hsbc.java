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

public class Tag86Hsbc extends WorkBookXls {
	
	public int numRow = 1;

	public Tag86Hsbc(String pathName) throws IOException {
		super(pathName);
	}
	
	@Override
	protected void createHeadInXls() {
		
		HSSFRow rowhead = sheet.createRow((short)0);
		rowhead.createCell(0).setCellValue("val Tag 86");
		rowhead.createCell(1).setCellValue("size");
		rowhead.createCell(1).setCellValue("it has reference");
		
	}
	
	@Override
	protected void creteRowInXls(final TransactionReferenceNumber20 trn20, final int numRow){}

	protected void creteRowInXls(String valTag86, int numRow) {
		
		HSSFRow rowhead = sheet.createRow((short)numRow);
		
		rowhead.createCell(0).setCellValue(valTag86);
		
		String[] fragmentTag86 = valTag86.split("/");
		
		rowhead.createCell(1).setCellValue(fragmentTag86.length);
		
		rowhead.createCell(2).setCellValue("no");
		
		Matcher matcher = StringUtils.findPattern("REF", valTag86);
		
		if(matcher.find())
			rowhead.createCell(2).setCellValue("si");
		
//		Expression has to validate if the tag 86 contains the possible reference number
//		String expBancomer = "(B[ANCOMER]{2,7}\\s?\\d+)|(RECIBIDO\\D*\\d{1,})|(B[ANCOS]{1,5}\\s*\\d+)|(TERCERO\\D*\\d+)|(CE\\d+)|(CB\\d+)";
		String expBanorte = "(REFERENCIA:\\D*\\d+)|(\\d+\\s+REFERENCIA)|((REF.|REF)\\s+\\d+)|(REFERENCIA CLIENTE\\s+\\d+)";
		
		matcher = StringUtils.findPattern(expBanorte, valTag86);
		
		if(matcher.find()){
			String group = matcher.group(0);
			group = group.replaceAll("\\D", "");
			rowhead.createCell(3).setCellValue(group);
		}
			
		
//		for (String string : fragmentTag86) {
//			rowhead.createCell(contCells++).setCellValue(string);
//		}
		
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
