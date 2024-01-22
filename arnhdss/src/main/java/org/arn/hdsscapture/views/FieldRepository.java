package org.arn.hdsscapture.views;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FieldRepository extends JpaRepository <FieldReport, String> {
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name\r\n"
			+ "FROM pregnancyobservation a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location  d on  c.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Preg(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name\r\n"
			+ "FROM death a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location  d on  c.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Dth(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name\r\n"
			+ "FROM inmigration a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location  d on  c.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Img(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name\r\n"
			+ "FROM outmigration a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location  d on  c.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Omg(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name\r\n"
			+ "FROM location a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN locationhierarchy e on a.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY username,name ORDER BY Fieldworker,total")
	List<FieldReport> Loc(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name\r\n"
			+ "FROM visit a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN location  d on  a.location_uuid=d.uuid\r\n"
			+ "INNER JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY username,name ORDER BY Fieldworker,total")
	List<FieldReport> Vis(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.compno as id,concat(b.firstName,' ',b.lastName)as Fieldworker,count(compno) as total,\r\n"
			+ "a.insertDate,date(a.submitDate)submitDate,village as name,CASE when a.`status`=1 then 'Active' \r\n"
			+ "when a.`status`=2 then 'Commercial' when a.`status`=3 then 'Could not locate' when a.`status`=4 then 'Deserted/Broken' \r\n"
			+ "when a.`status`=5 then 'Incomplete' \r\n"
			+ "when a.`status`=6 then 'Unoccupied' else 'Unknown' end as username\r\n"
			+ "FROM listing a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name,a.status ORDER BY Fieldworker,total")
	List<FieldReport> List(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT f.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,count(f.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,Case when type=1 then 'Live Birth' when type=2 then 'Still Birth' when type=3 then 'Miscarriage' when type=4 then 'Abortion' else type end as username,e.name\r\n"
			+ "FROM pregnancyoutcome a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN outcome f on a.uuid=f.preg_uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location  d on  c.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name,type ORDER BY Fieldworker,total")
	List<FieldReport> Outcome(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.socialgroup_uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name\r\n"
			+ "FROM sociodemographic a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN location d on a.location_uuid=d.uuid\r\n"
			+ "INNER JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Ses(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.socialgroup_uuid) as total,\r\n"
			+ "max(a.formcompldate)insertDate,max(date(a.insertDate))submitDate,e.name\r\n"
			+ "FROM sociodemographic a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN location d on a.location_uuid=d.uuid\r\n"
			+ "INNER JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.formcompldate BETWEEN :startDate AND :endDate AND\r\n"
			+ "a.insertDate < (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> SesEdit(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name\r\n"
			+ "FROM individual a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN residency c on a.uuid=c.individual_uuid\r\n"
			+ "INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE c.endType=1 AND a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Ind(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name\r\n"
			+ "FROM relationship a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN residency c on a.individualA_uuid=c.individual_uuid\r\n"
			+ "INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE c.endType=1 AND a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Rel(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(a.uuid) as total,\r\n"
			+ "max(a.editDate)insertDate,max(date(a.submitDate))submitDate,e.name\r\n"
			+ "FROM vaccination a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN location  d on  a.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.editDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Vac(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


	@Query(nativeQuery = true, value = "SELECT a.uuid as id,concat(b.firstName,' ',b.lastName)as Fieldworker,username,count(DISTINCT a.uuid) as total,\r\n"
			+ "max(a.insertDate)insertDate,max(date(a.submitDate))submitDate,e.name\r\n"
			+ "FROM socialgroup a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN residency c on a.uuid=c.socialgroup_uuid\r\n"
			+ "INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid \r\n"
			+ "WHERE c.endType=1 AND a.insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<FieldReport> Hoh(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	

}
