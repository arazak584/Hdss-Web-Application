package org.arn.hdsscapture.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        
        // Default to current month if no dates provided
        if (startDate == null) {
            startDate = LocalDate.now().withDayOfMonth(1);
        }
        if (endDate == null) {
            endDate = startDate.plusMonths(1).minusDays(1);
        }
        
        // Get fieldworker names for table headers
        List<String> fieldworkerNames = attendanceRepository.getFieldworkerNames();
        
        // Get the report data
        List<Map<String, Object>> rawData = attendanceRepository.generateAttendanceReport(startDate, endDate);
        
        // Process data for the view
        List<AttendanceReportRow> reportRows = new ArrayList<>();
        for (Map<String, Object> row : rawData) {
            AttendanceReportRow reportRow = new AttendanceReportRow();
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                if (entry.getKey().equals("visit_date")) {
                    // Fix: Convert String to Date if needed
                    if (entry.getValue() instanceof String) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = sdf.parse((String) entry.getValue());
                            reportRow.setVisitDate(date);
                        } catch (ParseException e) {
                            // Log the error
                            System.err.println("Error parsing date: " + e.getMessage());
                        }
                    } else if (entry.getValue() instanceof java.sql.Date) {
                        // Convert SQL Date to util.Date
                        reportRow.setVisitDate(new Date(((java.sql.Date) entry.getValue()).getTime()));
                    } else if (entry.getValue() instanceof Date) {
                        reportRow.setVisitDate((Date) entry.getValue());
                    } else if (entry.getValue() instanceof LocalDate) {
                        // Convert LocalDate to Date
                        reportRow.setVisitDate(Date.from(((LocalDate) entry.getValue()).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    }
                } else {
                    reportRow.addAttendance(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : "Absent");
                }
            }
            reportRows.add(reportRow);
        }
        
        model.addAttribute("fieldworkerNames", fieldworkerNames);
        model.addAttribute("reportData", reportRows);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        
        return "report/attendance-report";
    }

}
