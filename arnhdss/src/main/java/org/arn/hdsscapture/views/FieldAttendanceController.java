package org.arn.hdsscapture.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FieldAttendanceController {
    
    @Autowired
    private FieldAttendanceRepository attendanceRepository;
    
    @GetMapping("/hdss/attendance-report")
    public String showAttendanceReport(
            Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(required = false) List<String> entityTypes) {
        
        // Default to current month if no dates provided
        if (startDate == null) {
            startDate = LocalDate.now().withDayOfMonth(1);
        }
        if (endDate == null) {
            endDate = startDate.plusMonths(1).minusDays(1);
        }
        
        // Default to all entity types if none selected
        if (entityTypes == null || entityTypes.isEmpty()) {
            //entityTypes = attendanceRepository.getEntityTypes();
            entityTypes = new ArrayList<>();
            entityTypes.add("visit");
        }
        
        // Get fieldworker names for table headers
        List<String> fieldworkerNames = attendanceRepository.getFieldworkerNames();
        
        // Get available entity types for filter options
        List<String> availableEntityTypes = attendanceRepository.getEntityTypes();
        
        // Get the report data - passing null for fieldworkerId to include all fieldworkers
        List<Map<String, Object>> rawData = attendanceRepository.generateAttendanceReport(startDate, endDate, entityTypes, null);
        
        // Process data for the view
        List<AttendanceReportRow> reportRows = new ArrayList<>();
        for (Map<String, Object> row : rawData) {
            AttendanceReportRow reportRow = new AttendanceReportRow();
            
            // Process visit date
            Object visitDateObj = row.get("visit_date");
            if (visitDateObj != null) {
                Date visitDate = convertToDate(visitDateObj);
                reportRow.setVisitDate(visitDate);
            }
            
            // Process fieldworker data
            for (String fieldworkerName : fieldworkerNames) {
                // Get total count for this fieldworker
                Object countObj = row.get(fieldworkerName + "_count");
                int count = 0;
                if (countObj != null) {
                    if (countObj instanceof Number) {
                        count = ((Number) countObj).intValue();
                    } else {
                        try {
                            count = Integer.parseInt(countObj.toString());
                        } catch (NumberFormatException e) {
                            // Not a number, use 0
                            count = 0;
                        }
                    }
                }
                
                // Add total count (marks as "Absent" if count is 0)
                reportRow.addTotalCount(fieldworkerName, count);
                
                // Process entity-specific counts for this fieldworker
                for (String entityType : entityTypes) {
                    Object entityCountObj = row.get(fieldworkerName + "_" + entityType);
                    int entityCount = 0;
                    if (entityCountObj != null) {
                        if (entityCountObj instanceof Number) {
                            entityCount = ((Number) entityCountObj).intValue();
                        } else {
                            try {
                                entityCount = Integer.parseInt(entityCountObj.toString());
                            } catch (NumberFormatException e) {
                                // Not a number, use 0
                                entityCount = 0;
                            }
                        }
                    }
                    reportRow.addEntityCount(fieldworkerName, entityType, entityCount);
                }
            }
            
            reportRows.add(reportRow);
        }
        
     // Inside your showAttendanceReport method, after creating all reportRows
     // Calculate column totals for each fieldworker
     Map<String, Integer> columnTotals = new HashMap<>();

     for (String fieldworkerName : fieldworkerNames) {
         int total = 0;
         for (AttendanceReportRow row : reportRows) {
             total += row.getTotalCount(fieldworkerName);
         }
         columnTotals.put(fieldworkerName, total);
     }

     	// Add totals to model
     	model.addAttribute("columnTotals", columnTotals);       
        model.addAttribute("fieldworkerNames", fieldworkerNames);
        model.addAttribute("reportData", reportRows);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("selectedEntityTypes", entityTypes);
        model.addAttribute("availableEntityTypes", availableEntityTypes);
        
        return "report/attendance-report";
    }
    
    /**
     * Helper method to convert various date objects to java.util.Date
     * 
     * @param dateObj Object representing a date
     * @return Converted java.util.Date or null if conversion fails
     */
    private Date convertToDate(Object dateObj) {
        if (dateObj instanceof String) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.parse((String) dateObj);
            } catch (ParseException e) {
                System.err.println("Error parsing date: " + e.getMessage());
                return null;
            }
        } else if (dateObj instanceof java.sql.Date) {
            return new Date(((java.sql.Date) dateObj).getTime());
        } else if (dateObj instanceof Date) {
            return (Date) dateObj;
        } else if (dateObj instanceof LocalDate) {
            return Date.from(((LocalDate) dateObj).atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }
}