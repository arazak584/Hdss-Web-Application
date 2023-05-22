package org.arn.hdsscapture.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Audited
@Table(name="death", indexes = {@Index(name="idx_individual_uuid", columnList="individual_uuid, visit_uuid")})
public class Death {
	
	@Id
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Column(name = "death_uuid", nullable = false)
	private String death_uuid;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	@Column(name = "deathCause", nullable = false)
	private Integer deathCause;
	
	@Column(name = "deathDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date deathDate;
	
	@Column(name = "deathPlace", nullable = false)
	private Integer deathPlace;
	
	@Column(name = "visit_uuid", nullable = false)
	private String visit_uuid;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
//	private Fieldworker fieldworker;
//	
//	@MapsId
//	@OneToOne(optional = false)
//	@JoinColumn(name = "individual_uuid", referencedColumnName = "individual_uuid", insertable = false, updatable = false)
//	private Individual individual = new Individual();
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "visit_uuid", referencedColumnName = "visit_uuid", insertable = false, updatable = false)
//	private Visit visit;
	
	public Death() {}


	public String getDeath_uuid() {
		return death_uuid;
	}


	public void setDeath_uuid(String death_uuid) {
		this.death_uuid = death_uuid;
	}




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



	public Integer getDeathCause() {
		return deathCause;
	}


	public void setDeathCause(Integer deathCause) {
		this.deathCause = deathCause;
	}


	public Date getDeathDate() {
		return deathDate;
	}


	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}


	public Integer getDeathPlace() {
		return deathPlace;
	}


	public void setDeathPlace(Integer deathPlace) {
		this.deathPlace = deathPlace;
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
		return death_uuid;
	}


	//@Override
    //public String toString() {
    //    return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
