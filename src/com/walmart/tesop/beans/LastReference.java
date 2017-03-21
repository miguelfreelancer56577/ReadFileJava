package com.walmart.tesop.beans;

/**
 * Operative Treasury - TESOP
 * WalMart Mexico y Centroamerica
 * Class name: 		  LastReference
 * Class description: Clase con la última referencia del último movimiento válido en el reporte MT940.
 * Last Modification: 15/11/2016
 * Last Date:         15/11/2016
 */

public class LastReference {
	
	private String lastReference;

	public LastReference() {
		super();
		this.lastReference = "0000000000";
	}
	
	public String getLastReference() {
		return lastReference;
	}

	public void setLastReference(String lastReference) {
		this.lastReference = lastReference;
	}

}
