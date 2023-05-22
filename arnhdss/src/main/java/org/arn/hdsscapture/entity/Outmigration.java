package org.arn.hdsscapture.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name="outmigration")
public class Outmigration {
	
	
	@Column(name = "omg_uuid", nullable = false)
	private String omg_uuid;
	
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Id
	@Column(name = "residency_uuid", nullable = false)
	private String residency_uuid;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "recordedDate", nullable = false)
	private Date recordedDate;
	
	@Column(name = "destination", nullable = false)
	private Integer destination;
	
	@Column(name = "reason", nullable = false)
	private Integer reason;
	
	@Column(name = "reason_oth")
	private String reason_oth;
	
	@Column(name = "visit_uuid", nullable = false)
	private String visit_uuid;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "individual_uuid", referencedColumnName = "individual_uuid", insertable = false, updatable = false)
//	private Individual individual;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "visit_uuid", referencedColumnName = "visit_uuid", insertable = false, updatable = false)
//	private Visit visit;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
//	private Fieldworker fieldworker;
	
//	@MapsId
//	@OneToOne(optional = false)
//	@JoinColumn(name = "residency_uuid", referencedColumnName = "residency_uuid", insertable =false, updatable = false)
//	private Residency residency = new Residency();

	
	public Outmigration() {}


	public String getOmg_uuid() {
		return omg_uuid;
	}


	public void setOmg_uuid(String omg_uuid) {
		this.omg_uuid = omg_uuid;
	}


	public String getIndividual_uuid() {
		return individual_uuid;
	}


	public void setIndividual_uuid(String individual_uuid) {
		this.individual_uuid = individual_uuid;
	}
	


	public String getResidency_uuid() {
		return residency_uuid;
	}



	public void setResidency_uuid(String residency_uuid) {
		this.residency_uuid = residency_uuid;
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


	public Integer getDestination() {
		return destination;
	}


	public void setDestination(Integer destination) {
		this.destination = destination;
	}


	public Integer getReason() {
		return reason;
	}


	public void setReason(Integer reason) {
		this.reason = reason;
	}


	public String getReason_oth() {
		return reason_oth;
	}



	public void setReason_oth(String reason_oth) {
		this.reason_oth = reason_oth;
	}



	public String getVisit_uuid() {
		return visit_uuid;
	}


	public void setVisit_uuid(String visit_uuid) {
		this.visit_uuid = visit_uuid;
	}


	public String getFw_uuid() {
		return fw_uuid;
	}


	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}


	@Override
	public String toString() {
		return omg_uuid;
	}



	//@Override
    //public String toString() {
    //    return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
