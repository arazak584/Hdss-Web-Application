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
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Audited
@Table(name="visit", indexes = {@Index(name="idx_visit_uuid", columnList="uuid")})
public class Visit implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "uuid", nullable = false, unique=true)
	private String uuid;
	
	
	@Column(name = "extId", nullable = false)
	private String extId;
	
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
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@Column(name = "location_uuid", nullable = false)
	private String location_uuid;
	
	@Column(name = "respondent")
	private String respondent;
	
	@Column(name = "sttime")
	private String sttime;

	@Column(name = "edtime")
	private String edtime;
	
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
	@JoinColumn(name = "location_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
	@NotAudited
	private Location location;
	
	
	public Visit() {}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getLocation_uuid() {
		return location_uuid;
	}


	public void setLocation_uuid(String location_uuid) {
		this.location_uuid = location_uuid;
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


	@Override
	public String toString() {
		return uuid;
	}

}
