package org.arn.hdsscapture.views;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AttendanceReportRow {
    private Date visitDate;
    private Map<String, Integer> totalCounts = new HashMap<>();
    private Map<String, Map<String, Integer>> entityCounts = new HashMap<>();
    
    public Date getVisitDate() {
        return visitDate;
    }
    
    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
    
    /**
     * Add total count for a fieldworker
     * 
     * @param fieldworkerName The name of the fieldworker
     * @param count The total count (0 or more)
     */
    public void addTotalCount(String fieldworkerName, int count) {
        // Store the actual count (0 or higher)
        totalCounts.put(fieldworkerName, count);
    }

    
    /**
     * Get the display string for the total count
     * 
     * @param fieldworkerName The name of the fieldworker
     * @return The count as a string (will be "0" for zero count)
     */
    public String getTotalCountDisplay(String fieldworkerName) {
        Integer count = totalCounts.get(fieldworkerName);
        return count != null ? count.toString() : "0";
    }
    
    /**
     * Get the raw count value
     * 
     * @param fieldworkerName The name of the fieldworker
     * @return The count as an integer, or 0 if not found
     */
    public int getTotalCount(String fieldworkerName) {
        Integer count = totalCounts.get(fieldworkerName);
        return count != null ? count : 0;
    }
    
    /**
     * Add entity-specific count for a fieldworker
     * 
     * @param fieldworkerName The name of the fieldworker
     * @param entityType The type of entity
     * @param count The count (0 or more)
     */
    public void addEntityCount(String fieldworkerName, String entityType, int count) {
        Map<String, Integer> workerEntityCounts = entityCounts.computeIfAbsent(fieldworkerName, k -> new HashMap<>());
        workerEntityCounts.put(entityType, count);
    }
    
    /**
     * Get entity-specific count for a fieldworker
     * 
     * @param fieldworkerName The name of the fieldworker
     * @param entityType The type of entity
     * @return The count, or 0 if not found
     */
    public int getEntityCount(String fieldworkerName, String entityType) {
        Map<String, Integer> workerEntityCounts = entityCounts.get(fieldworkerName);
        if (workerEntityCounts == null) {
            return 0;
        }
        Integer count = workerEntityCounts.get(entityType);
        return count != null ? count : 0;
    }
    
    /**
     * Get entity-specific count as a string for display
     * 
     * @param fieldworkerName The name of the fieldworker
     * @param entityType The type of entity
     * @return The count as a string (will be "0" for zero count)
     */
    public String getEntityCountDisplay(String fieldworkerName, String entityType) {
        return String.valueOf(getEntityCount(fieldworkerName, entityType));
    }
}