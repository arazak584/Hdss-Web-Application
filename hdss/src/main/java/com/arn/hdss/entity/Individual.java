package com.arn.hdss.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
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
	
	@Column(name = "nickName")
	private String nickName;
	
	@Column(name = "gender", nullable = false)
	private Integer gender;
	
	@Column(name = "mother", nullable = false)
	private String mother;
	
	@Column(name = "father", nullable = false)
	private String father;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	
	public Individual() {}

	public Individual(String extId, Date insertDate, Date dob, Integer dobAspect, String firstName, String lastName,
			String nickName, Integer gender, String mother, String father, String fw) {
		super();
		this.extId = extId;
		this.insertDate = insertDate;
		this.dob = dob;
		this.dobAspect = dobAspect;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.gender = gender;
		this.mother = mother;
		this.father = father;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
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

}
