package com.walmart.tesop.beans;

public enum Templates {
	
	BCMl1("Layout 1"),
	BCMl2("Layout 2"),
	BCMl3("Layout 3"),
	BCMl4("Layout 4"),
	BCMl5("Layout 5");
	
	public String nameTemplete;
	
	Templates(String nameTemplete){
		this.nameTemplete = nameTemplete;
	} 

	public String getNameTemplete() {
		return nameTemplete;
	}
	
	
	
}
