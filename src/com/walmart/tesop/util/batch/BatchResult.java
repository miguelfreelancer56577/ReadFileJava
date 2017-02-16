package com.walmart.tesop.util.batch;

import java.util.Date;

/**
 * Class to keep the execution result from batch process.
 * 
 * @author Alisson Gomes
 *
 */
public class BatchResult {
	
	private Date  currentDate;//vAccountDt;
	private String processName;//vProcess; 
	private String processTitle;//vMessage;
	private String message;//vMessageSyst; 
	private String user;//vUserId;
	private boolean success;
	public BatchResult(Date currentDate,
                       String processName,
                       String processTitle,
                       String message,
                       String user,
                       boolean success) {
	    super();
	    this.currentDate = currentDate;
	    this.processName = processName;
	    this.processTitle = processTitle;
	    this.message = message;
	    this.user = user;
	    this.success = success;
    }
	public Date getCurrentDate() {
    	return currentDate;
    }
	public void setCurrentDate(Date currentDate) {
    	this.currentDate = currentDate;
    }
	public String getProcessName() {
    	return processName;
    }
	public void setProcessName(String processName) {
    	this.processName = processName;
    }
	public String getProcessTitle() {
    	return processTitle;
    }
	public void setProcessTitle(String processTitle) {
    	this.processTitle = processTitle;
    }
	public String getMessage() {
    	return message;
    }
	public void setMessage(String message) {
    	this.message = message;
    }
	public String getUser() {
    	return user;
    }
	public void setUser(String user) {
    	this.user = user;
    }
	public boolean isSuccess() {
    	return success;
    }
	public void setSuccess(boolean success) {
    	this.success = success;
    }
	
}
