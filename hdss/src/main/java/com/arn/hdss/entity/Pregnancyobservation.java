package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pregnancyobservation")
public class Pregnancyobservation {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "recordedDate", nullable = false)
	private Date recordedDate;
	
	@Column(name = "expectedDeliveryDate", nullable = false)
	private Date expectedDeliveryDate;
	
	@Column(name = "visitid", nullable = false)
	private String visitid;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	private Integer anteNatalClinic;//Have you been to an ANC clinic?
	private Integer ageOfPregFromPregNotes;//Age of pregnancy from pregnancy notes (In Weeks; Use the information in the ANC Booklet)
	private Integer estimatedAgeOfPreg;//Number of months pregnant (Use the information in the ANC Booklet)
	private Integer attend_you;//Who attended to you?
	private Integer attend_you_other;//Other, Specify
	private Integer first_rec;//How many months pregnant were you when you first received antenatal care for this pregnancy
	private Integer anc_visits;//How many Antenatal care visits have you made for this pregnancy
	private Integer why_no;//In no, Why?
	private String why_no_other;//Other, Specify
	private Integer own_bnet;//Do you have a bed net?
	private Integer how_many;//How many?
	private Integer bnet_sou;//Source of bednet
	private String bnet_sou_other;//Other, Specify
	private Integer bnet_loc;//Location of bednet
	private String bnet_loc_other;//Other, Specify
	private Integer slp_bednet;//Did you sleep under a bed net last night?
	private Integer trt_bednet;//Is the bed net you slept under last night treated?
	private Date lastClinicVisitDate;//Last clinic visit date
	private Integer healthfacility;//Have you attended a Health Facility other than visiting for anc service?
	private Integer medicineforpregnancy;//Have you received any medicine for the pregnancy?
	private String othermedicine;//Specify if other medicine
	private Integer ttinjection;//Have you received TT injection?
	private Integer first_preg;//Is this your first Pregnancy?
	private Integer pregnancyNumber;//Total number of pregnancies to date
	private Integer outcome;
	private Date outcome_date;//Select Date of Outcome
			

	
	public Pregnancyobservation() {}


	public Pregnancyobservation(String uuid, String extId, Date insertDate, Date recordedDate,
			Date expectedDeliveryDate, String visitid, String fw, Integer anteNatalClinic,
			Integer ageOfPregFromPregNotes, Integer estimatedAgeOfPreg, Integer attend_you, Integer attend_you_other,
			Integer first_rec, Integer anc_visits, Integer why_no, String why_no_other, Integer own_bnet,
			Integer how_many, Integer bnet_sou, String bnet_sou_other, Integer bnet_loc, String bnet_loc_other,
			Integer slp_bednet, Integer trt_bednet, Date lastClinicVisitDate, Integer healthfacility,
			Integer medicineforpregnancy, String othermedicine, Integer ttinjection, Integer first_preg,
			Integer pregnancyNumber, Integer outcome, Date outcome_date) {
		super();
		this.uuid = uuid;
		this.extId = extId;
		this.insertDate = insertDate;
		this.recordedDate = recordedDate;
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.visitid = visitid;
		this.fw = fw;
		this.anteNatalClinic = anteNatalClinic;
		this.ageOfPregFromPregNotes = ageOfPregFromPregNotes;
		this.estimatedAgeOfPreg = estimatedAgeOfPreg;
		this.attend_you = attend_you;
		this.attend_you_other = attend_you_other;
		this.first_rec = first_rec;
		this.anc_visits = anc_visits;
		this.why_no = why_no;
		this.why_no_other = why_no_other;
		this.own_bnet = own_bnet;
		this.how_many = how_many;
		this.bnet_sou = bnet_sou;
		this.bnet_sou_other = bnet_sou_other;
		this.bnet_loc = bnet_loc;
		this.bnet_loc_other = bnet_loc_other;
		this.slp_bednet = slp_bednet;
		this.trt_bednet = trt_bednet;
		this.lastClinicVisitDate = lastClinicVisitDate;
		this.healthfacility = healthfacility;
		this.medicineforpregnancy = medicineforpregnancy;
		this.othermedicine = othermedicine;
		this.ttinjection = ttinjection;
		this.first_preg = first_preg;
		this.pregnancyNumber = pregnancyNumber;
		this.outcome = outcome;
		this.outcome_date = outcome_date;
	}




	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getExtId() {
		return extId;
	}


	public void setExtId(String extId) {
		this.extId = extId;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public Date getRecordedDate() {
		return recordedDate;
	}


	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}


	public Date getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}


	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}


	public String getVisitid() {
		return visitid;
	}


	public void setVisitid(String visitid) {
		this.visitid = visitid;
	}
	
	public Integer getOutcome() {
		return outcome;
	}


	public void setOutcome(Integer outcome) {
		this.outcome = outcome;
	}


	public String getFw() {
		return fw;
	}


	public void setFw(String fw) {
		this.fw = fw;
	}
	
	

	public Integer getAnteNatalClinic() {
		return anteNatalClinic;
	}



	public void setAnteNatalClinic(Integer anteNatalClinic) {
		this.anteNatalClinic = anteNatalClinic;
	}



	public Integer getAgeOfPregFromPregNotes() {
		return ageOfPregFromPregNotes;
	}



	public void setAgeOfPregFromPregNotes(Integer ageOfPregFromPregNotes) {
		this.ageOfPregFromPregNotes = ageOfPregFromPregNotes;
	}



	public Integer getEstimatedAgeOfPreg() {
		return estimatedAgeOfPreg;
	}



	public void setEstimatedAgeOfPreg(Integer estimatedAgeOfPreg) {
		this.estimatedAgeOfPreg = estimatedAgeOfPreg;
	}



	public Integer getAttend_you() {
		return attend_you;
	}



	public void setAttend_you(Integer attend_you) {
		this.attend_you = attend_you;
	}



	public Integer getAttend_you_other() {
		return attend_you_other;
	}



	public void setAttend_you_other(Integer attend_you_other) {
		this.attend_you_other = attend_you_other;
	}



	public Integer getFirst_rec() {
		return first_rec;
	}



	public void setFirst_rec(Integer first_rec) {
		this.first_rec = first_rec;
	}



	public Integer getAnc_visits() {
		return anc_visits;
	}



	public void setAnc_visits(Integer anc_visits) {
		this.anc_visits = anc_visits;
	}



	public Integer getWhy_no() {
		return why_no;
	}



	public void setWhy_no(Integer why_no) {
		this.why_no = why_no;
	}



	public String getWhy_no_other() {
		return why_no_other;
	}



	public void setWhy_no_other(String why_no_other) {
		this.why_no_other = why_no_other;
	}



	public Integer getOwn_bnet() {
		return own_bnet;
	}



	public void setOwn_bnet(Integer own_bnet) {
		this.own_bnet = own_bnet;
	}



	public Integer getHow_many() {
		return how_many;
	}



	public void setHow_many(Integer how_many) {
		this.how_many = how_many;
	}



	public Integer getBnet_sou() {
		return bnet_sou;
	}



	public void setBnet_sou(Integer bnet_sou) {
		this.bnet_sou = bnet_sou;
	}



	public String getBnet_sou_other() {
		return bnet_sou_other;
	}



	public void setBnet_sou_other(String bnet_sou_other) {
		this.bnet_sou_other = bnet_sou_other;
	}



	public Integer getBnet_loc() {
		return bnet_loc;
	}



	public void setBnet_loc(Integer bnet_loc) {
		this.bnet_loc = bnet_loc;
	}



	public String getBnet_loc_other() {
		return bnet_loc_other;
	}



	public void setBnet_loc_other(String bnet_loc_other) {
		this.bnet_loc_other = bnet_loc_other;
	}



	public Integer getSlp_bednet() {
		return slp_bednet;
	}



	public void setSlp_bednet(Integer slp_bednet) {
		this.slp_bednet = slp_bednet;
	}



	public Integer getTrt_bednet() {
		return trt_bednet;
	}



	public void setTrt_bednet(Integer trt_bednet) {
		this.trt_bednet = trt_bednet;
	}



	public Date getLastClinicVisitDate() {
		return lastClinicVisitDate;
	}



	public void setLastClinicVisitDate(Date lastClinicVisitDate) {
		this.lastClinicVisitDate = lastClinicVisitDate;
	}



	public Integer getHealthfacility() {
		return healthfacility;
	}



	public void setHealthfacility(Integer healthfacility) {
		this.healthfacility = healthfacility;
	}



	public Integer getMedicineforpregnancy() {
		return medicineforpregnancy;
	}



	public void setMedicineforpregnancy(Integer medicineforpregnancy) {
		this.medicineforpregnancy = medicineforpregnancy;
	}



	public String getOthermedicine() {
		return othermedicine;
	}



	public void setOthermedicine(String othermedicine) {
		this.othermedicine = othermedicine;
	}



	public Integer getTtinjection() {
		return ttinjection;
	}



	public void setTtinjection(Integer ttinjection) {
		this.ttinjection = ttinjection;
	}



	public Integer getFirst_preg() {
		return first_preg;
	}



	public void setFirst_preg(Integer first_preg) {
		this.first_preg = first_preg;
	}



	public Integer getPregnancyNumber() {
		return pregnancyNumber;
	}



	public void setPregnancyNumber(Integer pregnancyNumber) {
		this.pregnancyNumber = pregnancyNumber;
	}


	public Date getOutcome_date() {
		return outcome_date;
	}



	public void setOutcome_date(Date outcome_date) {
		this.outcome_date = outcome_date;
	}
	
	

	@Override
	public String toString() {
		return extId;
	}


	//@Override
    //public String toString() {
    //    return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
