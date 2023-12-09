package org.arn.hdsscapture.projection;

import java.util.Date;

public interface LocationProjection {
	
	String getUuid();
	String getCompextId();
	String getAccuracy();
	Date getInsertDate();	
	String getCompno();
	String getLocationName();
	String getLongitude();
	String getLatitude();
	String geAccuracy();
	Integer getLocationType();
	Integer getStatus();
	String getLocationLevel_uuid();
	String getFw_uuid();
	String getSttime();
	String getEdtime();
	String getVill_extId();

}
