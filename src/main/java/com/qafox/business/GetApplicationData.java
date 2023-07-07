package com.qafox.business;

public class GetApplicationData {

	private static String invoiceNum;
	private static String salesOrderNum;
	private static String schemeName;
	public static String schemeProdID;

	public GetApplicationData(String invoiceNum, String salesOrderNum) {
		this.invoiceNum = invoiceNum;
		this.salesOrderNum = salesOrderNum;
	}

	public GetApplicationData() {

	}
	
	public String getInvoiceNumber() {
		return invoiceNum;
	}

	public String getSalesOrderNumber() {
		return salesOrderNum;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public String getSchemeProdID() {
		return schemeProdID;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public void setSchemeProdID(String schemeProdID) {
		this.schemeProdID = schemeProdID;
	}
	
	public String getHoldOrderNum() {
		return this.invoiceNum;
	}

	public String getMrp() {
		return this.salesOrderNum;
	}
}
