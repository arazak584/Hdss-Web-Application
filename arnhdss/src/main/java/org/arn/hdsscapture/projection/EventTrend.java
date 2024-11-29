package org.arn.hdsscapture.projection;

import java.util.Date;

public interface EventTrend {
	String getId();
    String getLocation_name();
    String getVillage_name();
    Date getStartDate();
    Date getEndDate();
    Integer getInmigration_count();
    Integer getRoundNumber();
    Integer getOutmigration_count();
    Integer getDeath_count();
    Integer getPregnancy_count();
    Integer getOutcome_count();

}
