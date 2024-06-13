package org.arn.hdsscapture.entity;


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

@Entity
@Audited
@Table(name="pregnancyobservation")
public class Pregnancyobservation {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	@Column(name = "recordedDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date recordedDate;
	
	@Column(name = "expectedDeliveryDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expectedDeliveryDate;
	
	@Column(name = "visit_uuid", nullable = false)
	private String visit_uuid;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@Column(name = "sttime")
	private String sttime;

	@Column(name = "edtime")
	private String edtime;
	
	private Integer anteNatalClinic;//Have you been to an ANC clinic?
	private Integer ageOfPregFromPregNotes;//Age of pregnancy from pregnancy notes (In Weeks; Use the information in the ANC Booklet)
	private Integer estimatedAgeOfPreg;//Number of months pregnant (Use the information in the ANC Booklet)
	private Integer attend_you;//Who attended to you?
	private String attend_you_other;//Other, Specify
	private Integer first_rec;//How many months pregnant were you when you first received antenatal care for this pregnancy
	private Integer anc_visits;//How many Antenatal care visits have you made for this pregnancy
	private Integer why_no;//If no, Why?
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
	private Integer ttinjection;//Have you received TT injection?
	private Integer first_preg;//Is this your first Pregnancy?
	private Integer pregnancyNumber;//Total number of pregnancies to date
	private Integer outcome;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date outcome_date;//Select Date of Outcome
	
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
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individual_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Individual individual;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Visit visit;
	
	public Pregnancyobservation() {}




	public String getUuid() {
		return uuid;
	}




	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getIndividual_uuid() {
		return individual_uuid;
	}


	public void setIndividual_uuid(String individual_uuid) {
		this.individual_uuid = individual_uuid;
	}




	public String getVisit_uuid() {
		return visit_uuid;
	}




	public void setVisit_uuid(String visit_uuid) {
		this.visit_uuid = visit_uuid;
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


	public String getFw_uuid() {
		return fw_uuid;
	}


	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
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


	public String getAttend_you_other() {
		return attend_you_other;
	}


	public void setAttend_you_other(String attend_you_other) {
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


	public Integer getOutcome() {
		return outcome;
	}


	public void setOutcome(Integer outcome) {
		this.outcome = outcome;
	}


	public Date getOutcome_date() {
		return outcome_date;
	}


	public void setOutcome_date(Date outcome_date) {
		this.outcome_date = outcome_date;
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
		return uuid;
	}


	//@Override
    //public String toString() {
    //    return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
