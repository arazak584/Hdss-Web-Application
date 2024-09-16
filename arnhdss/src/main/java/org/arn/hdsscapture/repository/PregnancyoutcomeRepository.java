package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Pregnancyoutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PregnancyoutcomeRepository extends JpaRepository <Pregnancyoutcome, String> {

	
	@Query(nativeQuery = true, value = "SELECT a.* FROM pregnancyoutcome as a INNER JOIN outcome b on a.uuid=b.preg_uuid WHERE b.uuid = :uuid LIMIT 1")
    List<Pregnancyoutcome> findByUuids(@Param("uuid") String uuid);
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM pregnancyoutcome a WHERE a.`status`=2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND a.fw_uuid = :fw")
    List<Pregnancyoutcome> rejected(@Param("fw")String fw);
	
	
	@Query(nativeQuery = true, value ="SELECT	h.uuid as uuid,ass_del,ass_del_other,b_place,pregnancy_uuid,conceptionDate,first_nb,TIMESTAMPDIFF(year,dob,CURDATE()) as first_rec,\r\n"
			+ "concat(f.firstName,' ',f.lastName) as fw_uuid,how_del, how_del_other,a.insertDate,l_birth,many_ipt,month_pg,\r\n"
			+ "concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as mother_uuid,not_del,not_del_other,num_anc,numberofBirths,\r\n"
			+ "outcomeDate, rec_anc,rec_ipt,compno as visit_uuid,h.weig_hcard as father_uuid,where_anc, where_anc_Other,who_anc,why_no_anc,numberOfLiveBirths,type as edtime,\r\n"
			+ " dob as	sttime,approveDate,`comment`,a.`status`,supervisor,id1001,id1002,id1003,id1004,id1005,id1006,id1007,id1008,id1009 FROM pregnancyoutcome a INNER JOIN individual b on a.mother_uuid=b.uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN outcome h on a.uuid=h.preg_uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND h.uuid = :uuid LIMIT 1")
	List<Pregnancyoutcome> findByUuid(@Param("uuid") String uuid);

		
	@Query(nativeQuery = true, value ="SELECT	h.uuid as uuid,ass_del,ass_del_other,b_place,pregnancy_uuid,h.chd_weight as father_uuid,conceptionDate,a.first_nb,i.`status` as first_rec,\r\n"
			+ "concat(f.firstName,' ',f.lastName) as how_del_other,how_del, a.fw_uuid,a.insertDate,	l_birth,many_ipt,month_pg,\r\n"
			+ "concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as mother_uuid,not_del,not_del_other,num_anc,numberofBirths,\r\n"
			+ "outcomeDate, rec_anc,rec_ipt,compno as visit_uuid,h.weig_hcard,where_anc, where_anc_Other,who_anc,why_no_anc,numberOfLiveBirths,type as edtime,\r\n"
			+ " dob as	sttime,a.approveDate,a.`comment`,a.`status`,a.supervisor,id1001,id1002,id1003,id1004,id1005,id1006,id1007,id1008,id1009 FROM pregnancyoutcome a INNER JOIN individual b on a.mother_uuid=b.uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN outcome h on a.uuid=h.preg_uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "INNER JOIN pregnancyobservation i ON a.pregnancy_uuid=i.uuid\r\n"
			+ "where a.fw_uuid= :fw AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Pregnancyoutcome> findItem(@Param("fw") String fw);
	
	@Query(nativeQuery = true, value ="SELECT	h.uuid as uuid,ass_del,ass_del_other,b_place,pregnancy_uuid,h.chd_weight as father_uuid,conceptionDate,first_nb,first_rec,\r\n"
			+ "concat(f.firstName,' ',f.lastName) as how_del_other,how_del, a.fw_uuid,a.insertDate,	l_birth,many_ipt,month_pg,\r\n"
			+ "concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as mother_uuid,not_del,not_del_other,num_anc,numberofBirths,\r\n"
			+ "outcomeDate, rec_anc,rec_ipt,compno as visit_uuid,h.weig_hcard,where_anc, where_anc_Other,who_anc,why_no_anc,numberOfLiveBirths,type as edtime,\r\n"
			+ " dob as	sttime,approveDate,`comment`,a.`status`,supervisor,id1001,id1002,id1003,id1004,id1005,id1006,id1007,id1008,id1009 FROM pregnancyoutcome a INNER JOIN individual b on a.mother_uuid=b.uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN outcome h on a.uuid=h.preg_uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.status=2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Pregnancyoutcome> findRej();
	
	@Query(nativeQuery = true, value ="SELECT	h.uuid as uuid,ass_del,ass_del_other,b_place,pregnancy_uuid,h.chd_weight as father_uuid,conceptionDate,a.first_nb,i.`status` as first_rec,\r\n"
			+ "concat(f.firstName,' ',f.lastName) as how_del_other,how_del, a.fw_uuid,a.insertDate,	l_birth,many_ipt,month_pg,\r\n"
			+ "concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as mother_uuid,not_del,not_del_other,num_anc,numberofBirths,\r\n"
			+ "outcomeDate, rec_anc,rec_ipt,compno as visit_uuid,h.weig_hcard,where_anc, where_anc_Other,who_anc,why_no_anc,numberOfLiveBirths,type as edtime,\r\n"
			+ " dob as	sttime,a.approveDate,a.`comment`,a.`status`,a.supervisor,id1001,id1002,id1003,id1004,id1005,id1006,id1007,id1008,id1009 FROM pregnancyoutcome a INNER JOIN individual b on a.mother_uuid=b.uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN outcome h on a.uuid=h.preg_uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "INNER JOIN pregnancyobservation i ON a.pregnancy_uuid=i.uuid\r\n"
			+ "where a.`status`= 3 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Pregnancyoutcome> findItems();

}
