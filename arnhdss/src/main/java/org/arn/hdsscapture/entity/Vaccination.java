package org.arn.hdsscapture.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;

@Audited
@Entity
@Table(name="vaccination")
public class Vaccination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "individual_uuid", nullable = false, unique=true)
	private String individual_uuid;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	@Column(name = "editDate", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date editDate;
	
	@Column(name = "location_uuid", nullable=false)
	private String location_uuid;
	
	@Column(name = "socialgroup_uuid", nullable=false)
	private String socialgroup_uuid;
	
	@Column(name = "uuid", nullable=false)
	private String uuid;
	
	@Column(name = "dob", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	@Column(name = "bcg", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bcg;
	
	@Column(name = "opv0", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date opv0;
	
	@Column(name = "opv1", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date opv1;
	
	@Column(name = "opv2", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date opv2;
	
	@Column(name = "opv3", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date opv3;
	
	@Column(name = "dpt_hepb_hib1", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dpt_hepb_hib1;
	
	@Column(name = "dpt_hepb_hib2", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dpt_hepb_hib2;
	
	@Column(name = "dpt_hepb_hib3", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dpt_hepb_hib3;
	
	@Column(name = "pneumo1", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pneumo1;
	
	@Column(name = "pneumo2", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pneumo2;
	
	@Column(name = "pneumo3", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pneumo3;
	
	@Column(name = "rota1", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rota1;
	
	@Column(name = "rota2", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rota2;
	
	@Column(name = "rota3", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rota3;
	
	@Column(name = "ipv", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ipv;
	
	@Column(name = "vitaminA6", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date vitaminA6;
	
	@Column(name = "vitaminA12", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date vitaminA12;
	
	@Column(name = "vitaminA18", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date vitaminA18;
	
	@Column(name = "rtss6", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rtss6;
	
	@Column(name = "rtss7", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rtss7;
	
	@Column(name = "rtss9", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rtss9;
	
	@Column(name = "rtss18", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rtss18;
	
	@Column(name = "measles_rubella1", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date measles_rubella1;
	
	@Column(name = "measles_rubella2", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date measles_rubella2;
	
	@Column(name = "yellow_fever", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date yellow_fever;
	
	@Column(name = "menA", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date menA;
	
	@Column(name = "itn", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date itn;
		
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@Column(name = "hcard")
	private Integer hcard;
	
	@Column(name = "reason")
	private Integer reason;
	
	@Column(name = "reason_oth")
	private String reason_oth;
	
	@Column(name = "onet")
	private Integer onet;
	
	@Column(name = "rea")
	private Integer rea;
	
	@Column(name = "rea_oth")
	private String rea_oth;
	
	@Column(name = "hl")
	private Integer hl;
	
	@Column(name = "hod")
	private Integer hod;
	
	@Column(name = "hom")
	private Integer hom;
	
	@Column(name = "nhis")
	private Integer nhis;
	
	@Column(name = "sbf")
	private Integer sbf;
	
	@Column(name = "stm")
	private Integer stm;
	
	@Column(name = "sty")
	private Integer sty;
	
	@Column(name = "admission")
	private Integer admission;
	
	@Column(name = "bednet")
	private Integer bednet;//Does your household have a bednet?

	@Column(name = "slpbednet")
	private Integer slpbednet;//Did the baby sleep under bednet last Night?
	
	@Column(name = "chlbednet")
	private Integer chlbednet;//How many children sleep under the bednet?
	
	@Column(name = "weight")
	private String weight;//Weight of child at birth in Kg?
	
	@Column(name = "scar")
	private Integer scar;//Does the child have the BCG scar? (Observe)
	
	@Column(name = "admitDate", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date admitDate;//Date of Admission
	
	@Column(name = "fever")
	private Integer fever;//In the last two weeks did the child report Fever
	
	@Column(name = "fevertreat")
	private Integer fevertreat;//Did you seek for treatment
	
	@Column(name = "diarrhoea")
	private Integer diarrhoea;//In the last two weeks did the child report diarrhoea
	
	@Column(name = "diarrhoeatreat")
	private Integer diarrhoeatreat;//Did you seek for treatment
	
	@Column(name = "arti")
	private Integer arti;//In the last two weeks did the child report ARTI
	
	@Column(name = "artitreat")
	private Integer artitreat;//Did you seek for treatment
	
	@Column(name = "muac")
	private Integer muac;//Mid-Upper Arm circumference MUAC
	
	@Column(name = "sttime")
	private String sttime;

	@Column(name = "edtime")
	private String edtime;
	
	@Column(name = "comment", nullable = true, length = 1000)
	private String comment;
	
	@Column(name = "status", nullable = false)
	private Integer status = 0;
	
	@Column(name = "supervisor", nullable = true)
	private String supervisor;
	
	@Column(name = "approveDate", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date approveDate;
		
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false, nullable=false)
	private Fieldworker fieldworker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "socialgroup_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Socialgroup socialgroup;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
	@NotAudited
	private Location location;
	
	
	public Vaccination() {}

	public String getIndividual_uuid() {
		return individual_uuid;
	}

	public void setIndividual_uuid(String individual_uuid) {
		this.individual_uuid = individual_uuid;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public String getFw_uuid() {
		return fw_uuid;
	}

	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}
	
	
	public String getLocation_uuid() {
		return location_uuid;
	}

	public void setLocation_uuid(String location_uuid) {
		this.location_uuid = location_uuid;
	}

	public String getSocialgroup_uuid() {
		return socialgroup_uuid;
	}

	public void setSocialgroup_uuid(String socialgroup_uuid) {
		this.socialgroup_uuid = socialgroup_uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getBcg() {
		return bcg;
	}

	public void setBcg(Date bcg) {
		this.bcg = bcg;
	}

	public Date getOpv0() {
		return opv0;
	}

	public void setOpv0(Date opv0) {
		this.opv0 = opv0;
	}

	public Date getOpv1() {
		return opv1;
	}

	public void setOpv1(Date opv1) {
		this.opv1 = opv1;
	}

	public Date getOpv2() {
		return opv2;
	}

	public void setOpv2(Date opv2) {
		this.opv2 = opv2;
	}

	public Date getOpv3() {
		return opv3;
	}

	public void setOpv3(Date opv3) {
		this.opv3 = opv3;
	}

	public Date getDpt_hepb_hib1() {
		return dpt_hepb_hib1;
	}

	public void setDpt_hepb_hib1(Date dpt_hepb_hib1) {
		this.dpt_hepb_hib1 = dpt_hepb_hib1;
	}

	public Date getDpt_hepb_hib2() {
		return dpt_hepb_hib2;
	}

	public void setDpt_hepb_hib2(Date dpt_hepb_hib2) {
		this.dpt_hepb_hib2 = dpt_hepb_hib2;
	}

	public Date getDpt_hepb_hib3() {
		return dpt_hepb_hib3;
	}

	public void setDpt_hepb_hib3(Date dpt_hepb_hib3) {
		this.dpt_hepb_hib3 = dpt_hepb_hib3;
	}

	public Date getPneumo1() {
		return pneumo1;
	}

	public void setPneumo1(Date pneumo1) {
		this.pneumo1 = pneumo1;
	}

	public Date getPneumo2() {
		return pneumo2;
	}

	public void setPneumo2(Date pneumo2) {
		this.pneumo2 = pneumo2;
	}

	public Date getPneumo3() {
		return pneumo3;
	}

	public void setPneumo3(Date pneumo3) {
		this.pneumo3 = pneumo3;
	}

	public Date getRota1() {
		return rota1;
	}

	public void setRota1(Date rota1) {
		this.rota1 = rota1;
	}

	public Date getRota2() {
		return rota2;
	}

	public void setRota2(Date rota2) {
		this.rota2 = rota2;
	}

	public Date getRota3() {
		return rota3;
	}

	public void setRota3(Date rota3) {
		this.rota3 = rota3;
	}

	public Date getIpv() {
		return ipv;
	}

	public void setIpv(Date ipv) {
		this.ipv = ipv;
	}

	public Date getVitaminA6() {
		return vitaminA6;
	}

	public void setVitaminA6(Date vitaminA6) {
		this.vitaminA6 = vitaminA6;
	}

	public Date getVitaminA12() {
		return vitaminA12;
	}

	public void setVitaminA12(Date vitaminA12) {
		this.vitaminA12 = vitaminA12;
	}

	public Date getVitaminA18() {
		return vitaminA18;
	}

	public void setVitaminA18(Date vitaminA18) {
		this.vitaminA18 = vitaminA18;
	}

	public Date getRtss6() {
		return rtss6;
	}

	public void setRtss6(Date rtss6) {
		this.rtss6 = rtss6;
	}

	public Date getRtss7() {
		return rtss7;
	}

	public void setRtss7(Date rtss7) {
		this.rtss7 = rtss7;
	}
	

	public Date getRtss9() {
		return rtss9;
	}

	public void setRtss9(Date rtss9) {
		this.rtss9 = rtss9;
	}

	public Date getRtss18() {
		return rtss18;
	}

	public void setRtss18(Date rtss18) {
		this.rtss18 = rtss18;
	}

	public Date getMeasles_rubella1() {
		return measles_rubella1;
	}

	public void setMeasles_rubella1(Date measles_rubella1) {
		this.measles_rubella1 = measles_rubella1;
	}

	public Date getMeasles_rubella2() {
		return measles_rubella2;
	}

	public void setMeasles_rubella2(Date measles_rubella2) {
		this.measles_rubella2 = measles_rubella2;
	}

	public Date getYellow_fever() {
		return yellow_fever;
	}

	public void setYellow_fever(Date yellow_fever) {
		this.yellow_fever = yellow_fever;
	}

	public Date getMenA() {
		return menA;
	}

	public void setMenA(Date menA) {
		this.menA = menA;
	}

	public Date getItn() {
		return itn;
	}

	public void setItn(Date itn) {
		this.itn = itn;
	}


	public Integer getHcard() {
		return hcard;
	}

	public void setHcard(Integer hcard) {
		this.hcard = hcard;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public Integer getOnet() {
		return onet;
	}

	public void setOnet(Integer onet) {
		this.onet = onet;
	}

	public Integer getRea() {
		return rea;
	}

	public void setRea(Integer rea) {
		this.rea = rea;
	}

	public Integer getHl() {
		return hl;
	}

	public void setHl(Integer hl) {
		this.hl = hl;
	}

	public Integer getHod() {
		return hod;
	}

	public void setHod(Integer hod) {
		this.hod = hod;
	}

	public Integer getHom() {
		return hom;
	}

	public void setHom(Integer hom) {
		this.hom = hom;
	}

	public Integer getNhis() {
		return nhis;
	}

	public void setNhis(Integer nhis) {
		this.nhis = nhis;
	}

	public Integer getSbf() {
		return sbf;
	}

	public void setSbf(Integer sbf) {
		this.sbf = sbf;
	}

	public Integer getStm() {
		return stm;
	}

	public void setStm(Integer stm) {
		this.stm = stm;
	}

	public Integer getSty() {
		return sty;
	}

	public void setSty(Integer sty) {
		this.sty = sty;
	}

	public String getReason_oth() {
		return reason_oth;
	}

	public void setReason_oth(String reason_oth) {
		this.reason_oth = reason_oth;
	}

	public String getRea_oth() {
		return rea_oth;
	}

	public void setRea_oth(String rea_oth) {
		this.rea_oth = rea_oth;
	}

	public Integer getAdmission() {
		return admission;
	}

	public void setAdmission(Integer admission) {
		this.admission = admission;
	}
	

	public Integer getBednet() {
		return bednet;
	}

	public void setBednet(Integer bednet) {
		this.bednet = bednet;
	}

	public Integer getSlpbednet() {
		return slpbednet;
	}

	public void setSlpbednet(Integer slpbednet) {
		this.slpbednet = slpbednet;
	}

	public Integer getChlbednet() {
		return chlbednet;
	}

	public void setChlbednet(Integer chlbednet) {
		this.chlbednet = chlbednet;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Integer getScar() {
		return scar;
	}

	public void setScar(Integer scar) {
		this.scar = scar;
	}

	public Date getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}

	public Integer getFever() {
		return fever;
	}

	public void setFever(Integer fever) {
		this.fever = fever;
	}

	public Integer getFevertreat() {
		return fevertreat;
	}

	public void setFevertreat(Integer fevertreat) {
		this.fevertreat = fevertreat;
	}

	public Integer getDiarrhoea() {
		return diarrhoea;
	}

	public void setDiarrhoea(Integer diarrhoea) {
		this.diarrhoea = diarrhoea;
	}

	public Integer getDiarrhoeatreat() {
		return diarrhoeatreat;
	}

	public void setDiarrhoeatreat(Integer diarrhoeatreat) {
		this.diarrhoeatreat = diarrhoeatreat;
	}

	public Integer getArti() {
		return arti;
	}

	public void setArti(Integer arti) {
		this.arti = arti;
	}

	public Integer getArtitreat() {
		return artitreat;
	}

	public void setArtitreat(Integer artitreat) {
		this.artitreat = artitreat;
	}


	public Integer getMuac() {
		return muac;
	}

	public void setMuac(Integer muac) {
		this.muac = muac;
	}
	
	public String getSttime() {
		return sttime;
	}

	public void setSttime(String sttime) {
		this.sttime = sttime;
	}

	public String getEdtime() {
		return edtime;
	}

	public void setEdtime(String edtime) {
		this.edtime = edtime;
	}
	
	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	
	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public String getSupervisor() {
		return supervisor;
	}



	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}



	public Date getApproveDate() {
		return approveDate;
	}



	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	@Override
	public String toString() {
		return individual_uuid;
	}

}
