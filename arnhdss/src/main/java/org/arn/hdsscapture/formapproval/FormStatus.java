package org.arn.hdsscapture.formapproval;

import java.util.Date;

public interface FormStatus {
	
String getUuid();
String getPermid();
String getIndividual_uuid();
Integer getCompno();
String getName();
String getComment();
Integer getStatus();
String getEntity();
String getVillage();
String getFw_name();
String getCoordinater();
Date getInsertDate();

}
