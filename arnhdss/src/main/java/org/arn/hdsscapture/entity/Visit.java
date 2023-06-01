package org.arn.hdsscapture.entity;




import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Audited
@Table(name="visit", indexes = {@Index(name="idx_visit_uuid", columnList="visit_uuid")})
public class Visit implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "visit_uuid", nullable = false, unique=true)
	private String visit_uuid;
	
	
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
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "visit")
	private List<Outmigration> outmigrations = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "visit")
	private List<Inmigration> inmigrations = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "visit")
	private List<Death> deaths = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "visit")
	private List<Pregnancyobservation> pregnancyobservations = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "visit")
	private List<Pregnancyoutcome> pregnancyoutcomes = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_uuid", referencedColumnName = "location_uuid", insertable = false, updatable = false)
	private Location location;
	
	
	public Visit() {}


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
		return visit_uuid;
	}

}
