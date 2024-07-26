package org.arn.hdsscapture.projection;

import java.util.Date;

public interface Img {
	
	String getResidency_uuid(); 
	Integer getAcres(); 
	Integer getFood_crops(); 
	String getFw_uuid(); 
	String getIndividual_uuid(); 
	Date getInsertDate(); 
	Integer getLast_occupa(); 
	String getLast_other(); 
	Integer getLivestock(); 
	Integer getMigType(); 
	Integer getOrigin(); 
	Integer getReason(); 
	String getReason_oth(); 
	Date getRecordedDate(); 
	String getUuid(); 
	String getVisit_uuid(); 
	Integer getCash_crops(); 
	Integer getCash_yn(); 
	Integer getFarm(); 
	String getFarm_other(); 
	Integer getFood_yn(); 
	Integer getLivestock_yn(); 
	String getEdtime(); 
	String getSttime(); 
	Date getApproveDate(); 
	String getComment(); 
	Integer getStatus(); 
	String getSupervisor();
	String getLocation_uuid();

}
