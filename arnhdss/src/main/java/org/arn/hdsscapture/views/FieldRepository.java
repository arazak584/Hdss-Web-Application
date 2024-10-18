package org.arn.hdsscapture.views;

import java.util.Date;
import java.util.List;

import org.arn.hdsscapture.projection.FieldReports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FieldRepository extends JpaRepository <FieldReport, String> {
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name,year(recordedDate) as year,Case when outcome_date IS NULL then 'No Outcome' else 'Outcome' end as type\r\n"
			+ "FROM pregnancyobservation a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location  d on  c.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name,year,type ORDER BY Fieldworker,total")
	List<FieldReport> Preg(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name,year(deathDate) as year,null as type\r\n"
			+ "FROM death a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location  d on  c.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name,year ORDER BY Fieldworker,total")
	List<FieldReport> Dth(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name,year(recordedDate) as year,null as type\r\n"
			+ "FROM inmigration a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location  d on  c.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name,year ORDER BY Fieldworker,total")
	List<FieldReport> Img(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name,year(recordedDate) as year,null as type\r\n"
			+ "FROM outmigration a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location  d on  c.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name,year ORDER BY Fieldworker,total")
	List<FieldReport> Omg(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name,null as year,null as type\r\n"
			+ "FROM location a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN locationhierarchy e on a.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY username,name ORDER BY Fieldworker,total")
	List<FieldReport> Loc(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name,null as year,null as type\r\n"
			+ "FROM visit a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN location  d on  a.location_uuid=d.uuid\r\n"
			+ "INNER JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY username,name ORDER BY Fieldworker,total")
	List<FieldReport> Vis(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.compno as id,concat(b.firstName,' ',b.lastName)as Fieldworker,count(compno) as total,\r\n"
			+ "a.insertDate,date(a.submitDate)submitDate,village as name,CASE when a.`status`=1 then 'Active' \r\n"
			+ "when a.`status`=2 then 'Commercial' when a.`status`=3 then 'Could not locate' when a.`status`=4 then 'Deserted/Broken' \r\n"
			+ "when a.`status`=5 then 'Incomplete' \r\n"
			+ "when a.`status`=6 then 'Unoccupied' else 'Unknown' end as username,null as year,null as type\r\n"
			+ "FROM listing a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name,a.status ORDER BY Fieldworker,total")
	List<FieldReport> List(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT f.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,count(f.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,username,e.name,year(outcomeDate) as year,Case when type=1 then 'Live Birth' when type=2 then 'Still Birth' when type=3 then 'Miscarriage' when type=4 then 'Abortion' else type end as type\r\n"
			+ "FROM pregnancyoutcome a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN outcome f on a.uuid=f.preg_uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location  d on  c.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name,year,type ORDER BY Fieldworker,total")
	List<FieldReport> Outcome(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.socialgroup_uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name,null as year,null as type\r\n"
			+ "FROM sociodemographic a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN location d on a.location_uuid=d.uuid\r\n"
			+ "INNER JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Ses(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.socialgroup_uuid) as total,\r\n"
			+ "max(a.formcompldate)insertDate,max(date(a.insertDate))submitDate,e.name,null as year,null as type\r\n"
			+ "FROM sociodemographic a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN location d on a.location_uuid=d.uuid\r\n"
			+ "INNER JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.formcompldate BETWEEN :startDate AND :endDate AND\r\n"
			+ "a.insertDate < (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> SesEdit(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name,null as year,null as type\r\n"
			+ "FROM individual a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN residency c on a.uuid=c.individual_uuid\r\n"
			+ "INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Ind(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name,null as year,null as type\r\n"
			+ "FROM relationship a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN residency c on a.individualA_uuid=c.individual_uuid\r\n"
			+ "INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Rel(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.editDate)insertDate,max(date(a.submitDate))submitDate,e.name,null as year,null as type\r\n"
			+ "FROM vaccination a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN location  d on  a.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.editDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Vac(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(DISTINCT a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name,null as year,null as type\r\n"
			+ "FROM socialgroup a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN residency c on a.uuid=c.socialgroup_uuid\r\n"
			+ "INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid \r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Hoh(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(a.insertDate)submitDate,e.name,null as year,null as type\r\n"
			+ "FROM morbidity a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN location  d on  a.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE date(a.insertDate) BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Mor(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	

	@Query(nativeQuery = true, value = "WITH latest_round AS ( "
	        + " SELECT r.startDate FROM round r ORDER BY r.roundNumber DESC LIMIT 1 "
	        + "), visit_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT a.uuid) AS visit "
	        + " FROM fieldworker f "
	        + " LEFT JOIN visit a ON f.fw_uuid = a.fw_uuid "
	        + " AND a.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND a.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + "), listing_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT b.compno) AS listing "
	        + " FROM fieldworker f "
	        + " LEFT JOIN listing b ON f.fw_uuid = b.fw_uuid "
	        + " AND b.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND b.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + "), inmigration_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT c.uuid) AS inmigration "
	        + " FROM fieldworker f "
	        + " LEFT JOIN inmigration c ON f.fw_uuid = c.fw_uuid "
	        + " AND c.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND c.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + "), outmigration_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT d.uuid) AS outmigration "
	        + " FROM fieldworker f "
	        + " LEFT JOIN outmigration d ON f.fw_uuid = d.fw_uuid "
	        + " AND d.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND d.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + "), vaccination_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT g.uuid) AS vaccination "
	        + " FROM fieldworker f "
	        + " LEFT JOIN vaccination g ON f.fw_uuid = g.fw_uuid "
	        + " AND g.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND g.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + "), morbidity_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT h.uuid) AS morbidity "
	        + " FROM fieldworker f "
	        + " LEFT JOIN morbidity h ON f.fw_uuid = h.fw_uuid "
	        + " AND h.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND h.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + "), relationship_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT i.uuid) AS relationship "
	        + " FROM fieldworker f "
	        + " LEFT JOIN relationship i ON f.fw_uuid = i.fw_uuid "
	        + " AND i.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND i.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + "), death_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT j.uuid) AS death "
	        + " FROM fieldworker f "
	        + " LEFT JOIN death j ON f.fw_uuid = j.fw_uuid "
	        + " AND j.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND j.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + "), ses_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT k.uuid) AS ses "
	        + " FROM fieldworker f "
	        + " LEFT JOIN sociodemographic k ON f.fw_uuid = k.fw_uuid "
	        + " AND k.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND k.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + "), location_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT l.uuid) AS location "
	        + " FROM fieldworker f "
	        + " LEFT JOIN location l ON f.fw_uuid = l.fw_uuid "
	        + " AND l.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND l.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + "), pregnancy_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT p.uuid) AS pregnancy "
	        + " FROM fieldworker f "
	        + " LEFT JOIN pregnancyobservation p ON f.fw_uuid = p.fw_uuid "
	        + " AND p.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND p.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + "), outcome_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT o.uuid) AS outcome "
	        + " FROM fieldworker f "
	        + " LEFT JOIN pregnancyoutcome o ON f.fw_uuid = o.fw_uuid "
	        + " AND o.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND o.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + "), demo_counts AS ( "
	        + " SELECT f.fw_uuid, COUNT(DISTINCT dd.individual_uuid) AS demo "
	        + " FROM fieldworker f "
	        + " LEFT JOIN demographic dd ON f.fw_uuid = dd.fw_uuid "
	        + " AND dd.insertDate > (SELECT startDate FROM latest_round) "
	        + " AND dd.insertDate BETWEEN :startDate AND :endDate "
	        + " GROUP BY f.fw_uuid "
	        + ") "
	        + "SELECT concat(fw.firstName,' ',fw.lastName) AS name, "
	        + "COALESCE(v.visit, 0) AS visit_count, COALESCE(l.listing, 0) AS listing_count, "
	        + "COALESCE(im.inmigration, 0) AS inmigration_count, COALESCE(om.outmigration, 0) AS outmigration_count, "
	        + "COALESCE(vc.vaccination, 0) AS vaccination_count, COALESCE(mo.morbidity, 0) AS morbidity_count, "
	        + "COALESCE(rel.relationship, 0) AS relationship_count, COALESCE(death.death, 0) AS death_count, "
	        + "COALESCE(ses.ses, 0) AS ses_count, COALESCE(loc.location, 0) AS location_count, "
	        + "COALESCE(preg.pregnancy, 0) AS pregnancy_count, COALESCE(outs.outcome, 0) AS outcome_count, "
	        + "COALESCE(dem.demo, 0) AS demo_count "
	        + "FROM fieldworker fw "
	        + "LEFT JOIN visit_counts v ON fw.fw_uuid = v.fw_uuid "
	        + "LEFT JOIN listing_counts l ON fw.fw_uuid = l.fw_uuid "
	        + "LEFT JOIN inmigration_counts im ON fw.fw_uuid = im.fw_uuid "
	        + "LEFT JOIN outmigration_counts om ON fw.fw_uuid = om.fw_uuid "
	        + "LEFT JOIN vaccination_counts vc ON fw.fw_uuid = vc.fw_uuid "
	        + "LEFT JOIN morbidity_counts mo ON fw.fw_uuid = mo.fw_uuid "
	        + "LEFT JOIN relationship_counts rel ON fw.fw_uuid = rel.fw_uuid "
	        + "LEFT JOIN death_counts death ON fw.fw_uuid = death.fw_uuid "
	        + "LEFT JOIN ses_counts ses ON fw.fw_uuid = ses.fw_uuid "
	        + "LEFT JOIN location_counts loc ON fw.fw_uuid = loc.fw_uuid "
	        + "LEFT JOIN pregnancy_counts preg ON fw.fw_uuid = preg.fw_uuid "
	        + "LEFT JOIN outcome_counts outs ON fw.fw_uuid = outs.fw_uuid "
	        + "LEFT JOIN demo_counts dem ON fw.fw_uuid = dem.fw_uuid "
	        + "WHERE fw.status = 1 "
	        + "ORDER BY fw.fw_uuid")
	List<FieldReports> getFieldworkerReport(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
