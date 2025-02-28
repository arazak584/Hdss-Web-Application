package org.arn.hdsscapture.views;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FieldAttendanceRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<String> getFieldworkerNames() {
        return jdbcTemplate.queryForList(
            "SELECT CONCAT(firstname, ' ', lastname) AS full_name FROM fieldworker where `status`=1 ORDER BY full_name", 
            String.class
        );
    }
    
    public List<Map<String, Object>> generateAttendanceReport(LocalDate startDate, LocalDate endDate) {
        String sql = generateReportSql(startDate, endDate);
        return jdbcTemplate.queryForList(sql);
    }
    
    private String generateReportSql(LocalDate startDate, LocalDate endDate) {
        StringBuilder sql = new StringBuilder();
        
        // Get all fieldworker info
        List<Map<String, Object>> fieldworkers = jdbcTemplate.queryForList(
            "SELECT fw_uuid, CONCAT(firstname, ' ', lastname) AS full_name FROM fieldworker where `status`=1"
        );
        
        // Build the dynamic SQL
        sql.append("WITH RECURSIVE calendar AS (")
           .append("  SELECT '").append(startDate).append("' AS visit_date")
           .append("  UNION ALL")
           .append("  SELECT DATE_ADD(visit_date, INTERVAL 1 DAY)")
           .append("  FROM calendar")
           .append("  WHERE visit_date < '").append(endDate).append("'")
           .append(")")
           .append("SELECT c.visit_date");
        
        // Add a column for each fieldworker
        for (Map<String, Object> fw : fieldworkers) {
            String workerId = (String) fw.get("fw_uuid");
            String workerName = (String) fw.get("full_name");
            
            sql.append(", COALESCE((SELECT COUNT(v.uuid)")
               .append("    FROM visit v")
               .append("    WHERE v.visitDate = c.visit_date")
               .append("    AND v.fw_uuid = '").append(workerId).append("'")
               .append("  ), 'Absent') AS `").append(workerName).append("`");
        }
        
        sql.append(" FROM calendar c")
           .append(" GROUP BY c.visit_date")
           .append(" ORDER BY c.visit_date");
        
        return sql.toString();
    }

}
