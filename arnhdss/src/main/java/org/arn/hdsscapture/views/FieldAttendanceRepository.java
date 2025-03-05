package org.arn.hdsscapture.views;

import java.time.LocalDate;
import java.util.ArrayList;
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
    
    public List<String> getEntityTypes() {
        List<String> entityTypes = new ArrayList<>();
        // Add visit as the first item
        entityTypes.add("visit");
        entityTypes.add("listing");
        entityTypes.add("compound");
        entityTypes.add("inmigration");
        entityTypes.add("demographic");
        entityTypes.add("outmigration");
        entityTypes.add("pregnancyoutcome");
        entityTypes.add("individual");
        entityTypes.add("socialgroup");
        entityTypes.add("pregnancyobservation");
        entityTypes.add("relationship");
        entityTypes.add("death");
        entityTypes.add("vaccination");
        entityTypes.add("sociodemographic");
        entityTypes.add("morbidity");
        return entityTypes;
    }
    
    public List<Map<String, Object>> generateAttendanceReport(LocalDate startDate, LocalDate endDate, List<String> selectedEntities, String fieldworkerId) {
        String sql = generateReportSql(startDate, endDate, selectedEntities, fieldworkerId);
        return jdbcTemplate.queryForList(sql);
    }
    
    private String generateReportSql(LocalDate startDate, LocalDate endDate, List<String> selectedEntities, String fieldworkerId) {
        StringBuilder sql = new StringBuilder();
        
        // Get fieldworker info - either all or specific one
        String fieldworkerQuery = "SELECT fw_uuid, CONCAT(firstname, ' ', lastname) AS full_name FROM fieldworker where `status`=1";
        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
            fieldworkerQuery += " AND fw_uuid = '" + fieldworkerId + "'";
        }
        
        List<Map<String, Object>> fieldworkers = jdbcTemplate.queryForList(fieldworkerQuery);
        
        // Build the calendar CTE
        sql.append("WITH RECURSIVE calendar AS (")
           .append("  SELECT '").append(startDate).append("' AS visit_date")
           .append("  UNION ALL")
           .append("  SELECT DATE_ADD(visit_date, INTERVAL 1 DAY)")
           .append("  FROM calendar")
           .append("  WHERE visit_date < '").append(endDate).append("'")
           .append("),");
        
        // Build entity-specific CTEs for each selected entity type
        if (selectedEntities != null && !selectedEntities.isEmpty()) {
            int count = 0;
            for (String entity : selectedEntities) {
                if (count > 0) {
                    sql.append(",");
                }
                
                
                switch (entity) {
                    case "visit":
                        sql.append("visit_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'visit' AS entity_type")
                           .append("  FROM visit")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;
                    case "listing":
                        sql.append("listing_data AS (")
                           .append("  SELECT COUNT(DISTINCT location_uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'listing' AS entity_type")
                           .append("  FROM listing")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;
                    case "compound":
                        sql.append("compound_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'compound' AS entity_type")
                           .append("  FROM location")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;    
                    case "inmigration":
                        sql.append("inmigration_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'inmigration' AS entity_type")
                           .append("  FROM inmigration")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;
                    case "demographic":
                        sql.append("demographic_data AS (")
                           .append("  SELECT COUNT(DISTINCT individual_uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'demographic' AS entity_type")
                           .append("  FROM demographic")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;                           
                    case "outmigration":
                        sql.append("outmigration_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'outmigration' AS entity_type")
                           .append("  FROM outmigration")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;
                    case "pregnancyoutcome":
                        sql.append("pregnancyoutcome_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'pregnancyoutcome' AS entity_type")
                           .append("  FROM pregnancyoutcome")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;
                    case "individual":
                        sql.append("individual_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'individual' AS entity_type")
                           .append("  FROM individual")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;
                    case "socialgroup":
                        sql.append("socialgroup_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'socialgroup' AS entity_type")
                           .append("  FROM socialgroup")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;
                    case "pregnancyobservation":
                        sql.append("pregnancyobservation_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'pregnancyobservation' AS entity_type")
                           .append("  FROM pregnancyobservation")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;
                    case "relationship":
                        sql.append("relationship_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'relationship' AS entity_type")
                           .append("  FROM relationship")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;
                    case "death":
                        sql.append("death_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'death' AS entity_type")
                           .append("  FROM death")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;
                    case "vaccination":
                        sql.append("vaccination_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'vaccination' AS entity_type")
                           .append("  FROM vaccination")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;    
                    case "sociodemographic":
                        sql.append("sociodemographic_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'sociodemographic' AS entity_type")
                           .append("  FROM sociodemographic")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;
                    case "morbidity":
                        sql.append("morbidity_data AS (")
                           .append("  SELECT COUNT(DISTINCT uuid) as total,DATE(insertDate) AS entity_date, fw_uuid, 'morbidity' AS entity_type")
                           .append("  FROM morbidity")
                           .append("  WHERE DATE(insertDate) BETWEEN '").append(startDate).append("' AND '").append(endDate).append("'");
                        if (fieldworkerId != null && !fieldworkerId.isEmpty()) {
                            sql.append("  AND fw_uuid = '").append(fieldworkerId).append("'");
                        }
                        sql.append("  GROUP BY DATE(insertDate), fw_uuid");
                        sql.append(")");
                        break;    
                }
                count++;
            }
            
            // Union all entity data
            sql.append(",\nall_entity_data AS (");
            count = 0;
            for (String entity : selectedEntities) {
                if (count > 0) {
                    sql.append("\nUNION ALL\n");
                }
                sql.append("SELECT * FROM ").append(entity).append("_data");
                count++;
            }
            sql.append(")");
        } else {
            // If no entities are selected, create an empty CTE
            sql.append("all_entity_data AS (")
               .append("  SELECT NULL as total, NULL AS entity_date, NULL AS fw_uuid, NULL AS entity_type")
               .append("  WHERE 1=0")
               .append(")");
        }
        
     // Main query
        sql.append("\nSELECT c.visit_date");
        
        // Add columns for each fieldworker
        for (Map<String, Object> fw : fieldworkers) {
            String workerId = (String) fw.get("fw_uuid");
            String workerName = (String) fw.get("full_name");
            
            // Total count for this fieldworker
            sql.append(",\n  COALESCE(SUM(CASE WHEN d.fw_uuid = '").append(workerId)
               .append("' THEN d.total ELSE 0 END), 0) AS `").append(workerName).append("_count`");
            
            // Add entity-specific columns for each fieldworker
            if (selectedEntities != null && !selectedEntities.isEmpty()) {
                for (String entity : selectedEntities) {
                    sql.append(",\n  COALESCE(SUM(CASE WHEN d.fw_uuid = '").append(workerId)
                       .append("' AND d.entity_type = '").append(entity)
                       .append("' THEN d.total ELSE 0 END), 0) AS `").append(workerName).append("_").append(entity).append("`");
                }
            }
        }
        
        
        sql.append("\nFROM calendar c")
        .append("\nLEFT JOIN all_entity_data d ON c.visit_date = d.entity_date")
        .append("\nGROUP BY c.visit_date")
        .append("\nORDER BY c.visit_date");
     
        return sql.toString();
    }
}