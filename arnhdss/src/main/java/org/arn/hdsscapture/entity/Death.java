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
@Table(name="death")
public class Death {
	
	@Id
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
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
	
	@Column(name = "deathPlace_oth")
	private String deathPlace_oth;
	
	@Column(name = "visit_uuid", nullable = false)
	private String visit_uuid;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@Column(name = "sttime")
	private String sttime;

	@Column(name = "edtime")
	private String edtime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false, nullable=false)
	private Fieldworker fieldworker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Visit visit;
	
	public Death() {}


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


	public String getFw_uuid() {
		return fw_uuid;
	}


	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}

	public String getDeathPlace_oth() {
		return deathPlace_oth;
	}


	public void setDeathPlace_oth(String deathPlace_oth) {
		this.deathPlace_oth = deathPlace_oth;
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


	//@Override
    //public String toString() {
    //    return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
