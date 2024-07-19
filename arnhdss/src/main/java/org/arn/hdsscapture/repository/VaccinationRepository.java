package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VaccinationRepository extends JpaRepository <Vaccination, String> {
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM vaccination a LEFT JOIN \r\n"
			+ "death b ON a.individual_uuid=b.individual_uuid where b.individual_uuid is null LIMIT ?1 OFFSET ?2")
	List<Vaccination> findVaccination(int pageSize, int offset);
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM vaccination a WHERE a.`status`=2 AND a.editDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
    List<Vaccination> rejected();
	
	@Query(nativeQuery = true, value = "SELECT * FROM vaccination WHERE uuid = :uuid LIMIT 1")
    List<Vaccination> findByUuids(@Param("uuid") String uuid);
	
	
	@Query(nativeQuery = true, value ="SELECT individual_uuid,admission, admitDate,arti,artitreat,bcg,bednet,chlbednet,diarrhoea,diarrhoeatreat,b.dob as dob,\r\n"
			+ "dpt_hepb_hib1,dpt_hepb_hib2,dpt_hepb_hib3,editDate,a.edtime,fever,fevertreat,concat(f.firstName,' ',f.lastName) as fw_uuid,hcard,hl,hod,\r\n"
			+ "hom,a.insertDate,ipv,itn,c.compno as location_uuid,measles_rubella1,measles_rubella2,menA,muac,\r\n"
			+ "nhis,onet,opv0,opv1,opv2,opv3,pneumo1,pneumo2,pneumo3,rea,\r\n"
			+ "rea_oth,reason,reason_oth,rota1,rota2,rota3,rtss18,rtss6,rtss7,rtss9,\r\n"
			+ "sbf,scar,slpbednet,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as socialgroup_uuid,stm,a.sttime,sty,a.uuid,vitaminA12,vitaminA18,\r\n"
			+ "vitaminA6,weight,yellow_fever,comment,a.status,supervisor,approveDate FROM vaccination a INNER JOIN individual b \r\n"
			+ "ON a.individual_uuid=b.uuid INNER JOIN location c on a.location_uuid=c.uuid INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid \r\n"
			+ "where a.editDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND a.uuid = :uuid LIMIT 1")
	List<Vaccination> findByUuid(@Param("uuid") String uuid);

		
	@Query(nativeQuery = true, value ="SELECT individual_uuid,admission, admitDate,arti,artitreat,bcg,bednet,chlbednet,diarrhoea,diarrhoeatreat,b.dob as dob,\r\n"
			+ "dpt_hepb_hib1,dpt_hepb_hib2,dpt_hepb_hib3,editDate,a.edtime,fever,fevertreat,concat(f.firstName,' ',f.lastName) as fw_uuid,hcard,hl,hod,\r\n"
			+ "hom,a.insertDate,ipv,itn,c.compno as location_uuid,measles_rubella1,measles_rubella2,menA,muac,\r\n"
			+ "nhis,onet,opv0,opv1,opv2,opv3,pneumo1,pneumo2,pneumo3,rea,\r\n"
			+ "rea_oth,reason,reason_oth,rota1,rota2,rota3,rtss18,rtss6,rtss7,rtss9,\r\n"
			+ "sbf,scar,slpbednet,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as socialgroup_uuid,stm,a.sttime,sty,a.uuid,vitaminA12,vitaminA18,\r\n"
			+ "vitaminA6,weight,yellow_fever,comment,a.status,supervisor,approveDate FROM vaccination a INNER JOIN individual b \r\n"
			+ "ON a.individual_uuid=b.uuid INNER JOIN location c on a.location_uuid=c.uuid INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid \r\n"
			+ "where a.fw_uuid= :fw AND a.editDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Vaccination> findItem(@Param("fw") String fw);
	
	@Query(nativeQuery = true, value ="SELECT individual_uuid,admission, admitDate,arti,artitreat,bcg,bednet,chlbednet,diarrhoea,diarrhoeatreat,b.dob as dob,\r\n"
			+ "dpt_hepb_hib1,dpt_hepb_hib2,dpt_hepb_hib3,editDate,a.edtime,fever,fevertreat,concat(f.firstName,' ',f.lastName) as fw_uuid,hcard,hl,hod,\r\n"
			+ "hom,a.insertDate,ipv,itn,c.compno as location_uuid,measles_rubella1,measles_rubella2,menA,muac,\r\n"
			+ "nhis,onet,opv0,opv1,opv2,opv3,pneumo1,pneumo2,pneumo3,rea,\r\n"
			+ "rea_oth,reason,reason_oth,rota1,rota2,rota3,rtss18,rtss6,rtss7,rtss9,\r\n"
			+ "sbf,scar,slpbednet,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as socialgroup_uuid,stm,a.sttime,sty,a.uuid,vitaminA12,vitaminA18,\r\n"
			+ "vitaminA6,weight,yellow_fever,comment,a.status,supervisor,approveDate FROM vaccination a INNER JOIN individual b \r\n"
			+ "ON a.individual_uuid=b.uuid INNER JOIN location c on a.location_uuid=c.uuid INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid \r\n"
			+ "where a.`status`=3 AND a.editDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Vaccination> findItems();
	
	@Query(nativeQuery = true, value ="SELECT individual_uuid,admission, admitDate,arti,artitreat,bcg,bednet,chlbednet,diarrhoea,diarrhoeatreat,b.dob as dob,\r\n"
			+ "dpt_hepb_hib1,dpt_hepb_hib2,dpt_hepb_hib3,editDate,a.edtime,fever,fevertreat,concat(f.firstName,' ',f.lastName) as fw_uuid,hcard,hl,hod,\r\n"
			+ "hom,a.insertDate,ipv,itn,c.compno as location_uuid,measles_rubella1,measles_rubella2,menA,muac,\r\n"
			+ "nhis,onet,opv0,opv1,opv2,opv3,pneumo1,pneumo2,pneumo3,rea,\r\n"
			+ "rea_oth,reason,reason_oth,rota1,rota2,rota3,rtss18,rtss6,rtss7,rtss9,\r\n"
			+ "sbf,scar,slpbednet,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as socialgroup_uuid,stm,a.sttime,sty,a.uuid,vitaminA12,vitaminA18,\r\n"
			+ "vitaminA6,weight,yellow_fever,comment,a.status,supervisor,approveDate FROM vaccination a INNER JOIN individual b \r\n"
			+ "ON a.individual_uuid=b.uuid INNER JOIN location c on a.location_uuid=c.uuid INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid \r\n"
			+ "where a.`status`=2 AND a.editDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Vaccination> findRej();

}
