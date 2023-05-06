package org.arn.hdsscapture.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

@Audited
@Entity
@Table(name="demographic", indexes = {@Index(name="idx_individual_uuid", columnList="individual_uuid")})
public class Demographic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "individual_uuid", nullable = false, unique=true)
	private String individual_uuid;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	@Column(name = "religion")
	private Integer religion;
	
	@Column(name = "religion_oth")
	private String religion_oth;
	
	@Column(name = "education")
	private Integer education;
	
	@Column(name = "comp_yrs")
	private Integer comp_yrs;
	
	@Column(name = "occupation")
	private Integer occupation;
	
	@Column(name = "occupation_oth")
	private String occupation_oth;
	
	@Column(name = "marital")
	private Integer marital;
	
	@Column(name = "phone1")
	private String phone1;
	
	@Column(name = "phone2")
	private String phone2;
	
	@Column(name = "tribe")
	private Integer tribe;
	
	@Column(name = "tribe_oth")
	private String tribe_oth;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@MapsId
	@OneToOne(optional = false)
	@JoinColumn(name = "individual_uuid", referencedColumnName = "individual_uuid", insertable = true, updatable = true)
	private Individual individual = new Individual();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
	
	
	public Demographic() {}

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

	public Integer getReligion() {
		return religion;
	}

	public void setReligion(Integer religion) {
		this.religion = religion;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getOccupation() {
		return occupation;
	}

	public void setOccupation(Integer occupation) {
		this.occupation = occupation;
	}

	public Integer getMarital() {
		return marital;
	}

	public void setMarital(Integer marital) {
		this.marital = marital;
	}


	public String getFw_uuid() {
		return fw_uuid;
	}

	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}
	
	
	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public Integer getTribe() {
		return tribe;
	}

	public void setTribe(Integer tribe) {
		this.tribe = tribe;
	}
	
	
	
	public String getReligion_oth() {
		return religion_oth;
	}


	public void setReligion_oth(String religion_oth) {
		this.religion_oth = religion_oth;
	}


	public Integer getComp_yrs() {
		return comp_yrs;
	}


	public void setComp_yrs(Integer comp_yrs) {
		this.comp_yrs = comp_yrs;
	}


	public String getOccupation_oth() {
		return occupation_oth;
	}


	public void setOccupation_oth(String occupation_oth) {
		this.occupation_oth = occupation_oth;
	}


	public String getTribe_oth() {
		return tribe_oth;
	}


	public void setTribe_oth(String tribe_oth) {
		this.tribe_oth = tribe_oth;
	}


	@Override
	public String toString() {
		return individual_uuid;
	}

}
