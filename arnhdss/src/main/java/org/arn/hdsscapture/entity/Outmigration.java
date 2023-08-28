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
@Table(name="outmigration")
public class Outmigration {
	
	
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Id
	@Column(name = "residency_uuid", nullable = false)
	private String residency_uuid;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	@Column(name = "recordedDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	
	@Column(name = "sttime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sttime;
	
	@Column(name = "edtime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date edtime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individual_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Individual individual;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Visit visit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
	
//	@MapsId
//	@OneToOne(optional = false)
//	@JoinColumn(name = "residency_uuid", referencedColumnName = "residency_uuid", insertable =false, updatable = false)
//	private Residency residency = new Residency();

	
	public Outmigration() {}

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


	public String getFw_uuid() {
		return fw_uuid;
	}


	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}

	public Date getSttime() {
		return sttime;
	}

	public void setSttime(Date sttime) {
		this.sttime = sttime;
	}

	public Date getEdtime() {
		return edtime;
	}

	public void setEdtime(Date edtime) {
		this.edtime = edtime;
	}

	@Override
    public String toString() {
        return residency_uuid;
    }

}
