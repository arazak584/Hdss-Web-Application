package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="death")
public class Death {
	
	@Id
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "AgeAtDeath", nullable = false)
	private Integer AgeAtDeath;
	
	@Column(name = "deathCause", nullable = false)
	private Integer deathCause;
	
	@Column(name = "deathDate", nullable = false)
	private Date deathDate;
	
	@Column(name = "deathPlace", nullable = false)
	private Integer deathPlace;
	
	@Column(name = "visitid", nullable = false)
	private String visitid;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	public Death() {}


	public Death(String extId, Date insertDate, Integer ageAtDeath, Integer deathCause, Date deathDate,
			Integer deathPlace, String visitid, String fw) {
		super();
		this.extId = extId;
		this.insertDate = insertDate;
		AgeAtDeath = ageAtDeath;
		this.deathCause = deathCause;
		this.deathDate = deathDate;
		this.deathPlace = deathPlace;
		this.visitid = visitid;
		this.fw = fw;
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


	public Integer getAgeAtDeath() {
		return AgeAtDeath;
	}


	public void setAgeAtDeath(Integer ageAtDeath) {
		AgeAtDeath = ageAtDeath;
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


	public String getVisitid() {
		return visitid;
	}


	public void setVisitid(String visitid) {
		this.visitid = visitid;
	}


	public String getFw() {
		return fw;
	}


	public void setFw(String fw) {
		this.fw = fw;
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
