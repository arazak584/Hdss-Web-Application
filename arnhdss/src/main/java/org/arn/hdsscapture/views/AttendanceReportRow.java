package org.arn.hdsscapture.views;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class AttendanceReportRow {
	
	private Date visitDate;
    private Map<String, String> fieldworkerAttendance = new LinkedHashMap<>();
    
    public Date getVisitDate() {
        return visitDate;
    }
    
    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
    
    public Map<String, String> getFieldworkerAttendance() {
        return fieldworkerAttendance;
    }
    
    public void addAttendance(String fieldworkerName, String attendance) {
        fieldworkerAttendance.put(fieldworkerName, attendance);
    }

}
