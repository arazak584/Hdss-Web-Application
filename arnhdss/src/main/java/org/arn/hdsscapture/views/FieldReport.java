package org.arn.hdsscapture.views;

import java.util.Date;

public interface FieldReport {
	
	String getId();
	String getFieldworker();
	Date getInsertDate();
	Date getSubmitDate();
	String getUsername();
	String getName();
	String getYear();
	String getType();
	Long getTotal();
	
	

}
