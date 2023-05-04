package org.arn.hdsscapture.entity;


import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

@Audited
@Entity
@Table(name="individual")
public class Individual {
	
	@Id
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	@Column(name = "submitDate")
	private LocalDateTime submitDate;
	
	@Column(name = "dob", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	@Column(name = "dobAspect", nullable = false)
	private Integer dobAspect;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	@Column(name = "otherName")
	private String otherName;
	
	@Column(name = "gender", nullable = false)
	private Integer gender;
	
	@Column(name = "mother_uuid", nullable = false)
	private String mother_uuid;
	
	@Column(name = "father_uuid", nullable = false)
	private String father_uuid;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@Column(name = "ghanacard")
	private String ghanacard;
	
	
	public Individual() {}

	@PrePersist
    protected void onCreate() {
        submitDate = LocalDateTime.now();
    }

	public Individual(String individual_uuid, String extId, Date insertDate,LocalDateTime submitDate, Date dob, Integer dobAspect, String firstName,
			String lastName, String otherName, Integer gender, String mother_uuid, String father_uuid, String fw_uuid,String ghanacard) {
		super();
		this.individual_uuid = individual_uuid;
		this.extId = extId;
		this.insertDate = insertDate;
		this.submitDate = submitDate;
		this.dob = dob;
		this.dobAspect = dobAspect;
		this.firstName = firstName;
		this.lastName = lastName;
		this.otherName = otherName;
		this.gender = gender;
		this.mother_uuid = mother_uuid;
		this.father_uuid = father_uuid;
		this.fw_uuid = fw_uuid;
		this.ghanacard = ghanacard;
	}


	public String getIndividual_uuid() {
		return individual_uuid;
	}


	public void setIndividual_uuid(String individual_uuid) {
		this.individual_uuid = individual_uuid;
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
	

	public LocalDateTime getSubmitDate() {
		return submitDate;
	}


	public void setSubmitDate(LocalDateTime submitDate) {
		this.submitDate = submitDate;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public Integer getDobAspect() {
		return dobAspect;
	}


	public void setDobAspect(Integer dobAspect) {
		this.dobAspect = dobAspect;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getOtherName() {
		return otherName;
	}


	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}


	public Integer getGender() {
		return gender;
	}


	public void setGender(Integer gender) {
		this.gender = gender;
	}


	public String getMother_uuid() {
		return mother_uuid;
	}


	public void setMother_uuid(String mother_uuid) {
		this.mother_uuid = mother_uuid;
	}


	public String getFather_uuid() {
		return father_uuid;
	}


	public void setFather_uuid(String father_uuid) {
		this.father_uuid = father_uuid;
	}


	public String getFw_uuid() {
		return fw_uuid;
	}


	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}
	
	
	public String getGhanacard() {
		return ghanacard;
	}


	public void setGhanacard(String ghanacard) {
		this.ghanacard = ghanacard;
	}


	@Override
	public String toString() {
		return extId;
	}

}
