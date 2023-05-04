package org.arn.hdsscapture.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="death")
public class Death {
	
	
	
	@Id
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Column(name = "death_uuid", nullable = false)
	private String death_uuid;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "deathCause", nullable = false)
	private Integer deathCause;
	
	@Column(name = "deathDate", nullable = false)
	private Date deathDate;
	
	@Column(name = "deathPlace", nullable = false)
	private Integer deathPlace;
	
	@Column(name = "visit_uuid", nullable = false)
	private String visit_uuid;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	public Death() {}




	public Death(String death_uuid, String individual_uuid, Date insertDate, Integer deathCause,
			Date deathDate, Integer deathPlace, String visit_uuid, String fw_uuid) {
		super();
		this.death_uuid = death_uuid;
		this.individual_uuid = individual_uuid;
		this.insertDate = insertDate;
		this.deathCause = deathCause;
		this.deathDate = deathDate;
		this.deathPlace = deathPlace;
		this.visit_uuid = visit_uuid;
		this.fw_uuid = fw_uuid;
	}


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
