package org.arn.hdsscapture.projection;

import java.util.Date;

public interface IndividualProjection {

String getUuid();
String getMother_uuid();
String getFather_uuid();
String getExtId();
Date getInsertDate();
Date getDob();
Integer getDobAspect();
String getFirstName();
String getLastName();
String getOtherName();
Integer getGender();
String getFw_uuid();
String getGhanacard();
String getSttime();
String getEdtime();
Integer getEndType();
String getCompno();
String getVillage();
String getHohID();
String getPhone1();

}
