package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PopulationRepository extends JpaRepository <Population, String> {
	
	
	
	@Query(nativeQuery = true, value ="SELECT ageGroup,SUM(CASE WHEN Gender != 0 THEN 1 ELSE 0 END) as pop, SUM(CASE WHEN Gender = 1 THEN 1 ELSE 0 END) AS male,SUM(CASE WHEN Gender = 2 THEN 1 ELSE 0 END) AS female\r\n"
			+ "FROM (\r\n"
			+ "    SELECT\r\n"
			+ "        TIMESTAMPDIFF(YEAR, dob, CURDATE()) AS Age,\r\n"
			+ "        CASE\r\n"
			+ "            WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 0 AND 4 THEN '0-4'\r\n"
			+ "            WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 5 AND 9 THEN '5-9'\r\n"
			+ "            WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 10 AND 14 THEN '10-14'\r\n"
			+ "            WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 15 AND 19 THEN '15-19'\r\n"
			+ "            WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 20 AND 24 THEN '20-24'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 25 AND 29 THEN '25-29'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 30 AND 34 THEN '30-34'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 35 AND 39 THEN '35-39'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 40 AND 44 THEN '40-44'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 45 AND 49 THEN '45-49'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 50 AND 54 THEN '50-54'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 55 AND 59 THEN '55-59'\r\n"
			+ "            WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 60 AND 64 THEN '60-64'\r\n"
			+ "            WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 65 AND 69 THEN '65-69'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 70 AND 74 THEN '70-74'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 75 AND 79 THEN '75-79'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 80 AND 84 THEN '80-84'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 85 AND 89 THEN '85-89'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 90 AND 94 THEN '90-94'\r\n"
			+ "						WHEN TIMESTAMPDIFF(YEAR, dob, CURDATE()) BETWEEN 95 AND 99 THEN '95-99'\r\n"
			+ "            ELSE '100+'\r\n"
			+ "        END AS AgeGroup,\r\n"
			+ "        gender,\r\n"
			+ "        endType\r\n"
			+ "    FROM individual a\r\n"
			+ "    INNER JOIN residency b ON a.uuid = b.individual_uuid\r\n"
			+ "    WHERE endType = 1\r\n"
			+ ") AS Subquery\r\n"
			+ "GROUP BY AgeGroup\r\n"
			+ "ORDER BY\r\n"
			+ "    CASE\r\n"
			+ "        WHEN AgeGroup = '0-4' THEN 1\r\n"
			+ "        WHEN AgeGroup = '5-9' THEN 2\r\n"
			+ "        WHEN AgeGroup = '10-14' THEN 3\r\n"
			+ "        WHEN AgeGroup = '15-19' THEN 4\r\n"
			+ "        WHEN AgeGroup = '20-24' THEN 5\r\n"
			+ "				WHEN AgeGroup = '25-29' THEN 6\r\n"
			+ "        WHEN AgeGroup = '30-34' THEN 7\r\n"
			+ "        WHEN AgeGroup = '35-39' THEN 8\r\n"
			+ "        WHEN AgeGroup = '40-44' THEN 9			\r\n"
			+ "				WHEN AgeGroup = '45-49' THEN 10\r\n"
			+ "        WHEN AgeGroup = '50-54' THEN 11\r\n"
			+ "        WHEN AgeGroup = '55-59' THEN 12\r\n"
			+ "				WHEN AgeGroup = '60-64' THEN 13\r\n"
			+ "        WHEN AgeGroup = '65-69' THEN 14\r\n"
			+ "        WHEN AgeGroup = '70-74' THEN 15\r\n"
			+ "        WHEN AgeGroup = '75-79' THEN 16				\r\n"
			+ "				WHEN AgeGroup = '80-84' THEN 17\r\n"
			+ "        WHEN AgeGroup = '85-89' THEN 18\r\n"
			+ "        WHEN AgeGroup = '90-94' THEN 19\r\n"
			+ "        WHEN AgeGroup = '95-99' THEN 20\r\n"
			+ "				WHEN AgeGroup = '100+' THEN 21\r\n"
			+ "        -- Add more numeric values as needed\r\n"
			+ "        ELSE 999\r\n"
			+ "    END;")
	List<Population> pyramid();

}
