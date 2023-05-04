package org.arn.hdsscapture.entity;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="visit")
public class Visit {
	
	
	@Column(name = "visit_uuid", nullable = false)
	private String visit_uuid;
	
	@Id
	@Column(name = "visitExtId", nullable = false)
	private String visitExtId;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
		
	@Column(name = "realVisit", nullable = false)
	private Integer realVisit;
	
	@Column(name = "roundNumber", nullable = false)
	private Integer roundNumber;
	
	@Column(name = "visitDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date visitDate;
	
	@Column(name = "location_uuid", nullable = false)
	private String location_uuid;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@Column(name = "respondent")
	private String respondent;
	
	
	public Visit() {}




	public Visit(String visit_uuid, String visitExtId, Date insertDate, Integer realVisit, Integer roundNumber,
			Date visitDate, String location_uuid, String fw_uuid, String respondent) {
		super();
		this.visit_uuid = visit_uuid;
		this.visitExtId = visitExtId;
		this.insertDate = insertDate;
		this.realVisit = realVisit;
		this.roundNumber = roundNumber;
		this.visitDate = visitDate;
		this.location_uuid = location_uuid;
		this.fw_uuid = fw_uuid;
		this.respondent = respondent;
	}




	public String getVisit_uuid() {
		return visit_uuid;
	}



	public void setVisit_uuid(String visit_uuid) {
		this.visit_uuid = visit_uuid;
	}



	public String getVisitExtId() {
		return visitExtId;
	}


	public void setVisitExtId(String visitExtId) {
		this.visitExtId = visitExtId;
	}



	public Date getInsertDate() {
		return insertDate;
	}



	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}



	public Integer getRealVisit() {
		return realVisit;
	}



	public void setRealVisit(Integer realVisit) {
		this.realVisit = realVisit;
	}



	public Integer getRoundNumber() {
		return roundNumber;
	}



	public void setRoundNumber(Integer roundNumber) {
		this.roundNumber = roundNumber;
	}



	public Date getVisitDate() {
		return visitDate;
	}



	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}



	public String getLocation_uuid() {
		return location_uuid;
	}



	public void setLocation_uuid(String location_uuid) {
		this.location_uuid = location_uuid;
	}



	public String getFw_uuid() {
		return fw_uuid;
	}



	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}






	public String getRespondent() {
		return respondent;
	}




	public void setRespondent(String respondent) {
		this.respondent = respondent;
	}




	@Override
	public String toString() {
		return visitExtId;
	}

}
