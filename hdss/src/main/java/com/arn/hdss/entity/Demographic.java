package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name="demographic")
public class Demographic {
	
	@Id
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "religion")
	private Integer religion;
	
	@Column(name = "education")
	private Integer education;
	
	@Column(name = "occupation")
	private Integer occupation;
	
	@Column(name = "marital")
	private Integer marital;
	
	@Column(name = "phone1")
	private String phone1;
	
	@Column(name = "phone2")
	private String phone2;
	
	@Column(name = "tribe")
	private Integer tribe;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	
	public Demographic() {}

	public Demographic(String extId, Date insertDate, Integer religion, Integer education, Integer occupation, Integer marital,
			String fw) {
		super();
		this.extId = extId;
		this.insertDate= insertDate;
		this.religion = religion;
		this.education = education;
		this.occupation = occupation;
		this.marital = marital;
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


	public String getFw() {
		return fw;
	}

	public void setFw(String fw) {
		this.fw = fw;
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
	
	@Override
	public String toString() {
		return extId;
	}

}
