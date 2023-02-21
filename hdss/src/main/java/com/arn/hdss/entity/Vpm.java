package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vpm")
public class Vpm {
	
	@Id
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "dob", nullable = false)
	private Date dob;
	
	@Column(name = "gender", nullable = false)
	private Integer gender;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	@Column(name = "dod", nullable = false)
	private Date dod;
	
	@Column(name = "compno", nullable = false)
	private String compno;
	
	@Column(name = "househead", nullable = false)
	private String househead;
	
	@Column(name = "compname", nullable = false)
	private String compname;
	
	@Column(name = "villname", nullable = false)
	private String villname;
	
	@Column(name = "villcode", nullable = false)
	private String villcode;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	public Vpm() {}



	public Vpm(String extId, Date dob, Integer gender, String firstName, String lastName, Date dod, String compno,
			String househead, String compname, String villname, String villcode, String fw) {
		super();
		this.extId = extId;
		this.dob = dob;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dod = dod;
		this.compno = compno;
		this.househead = househead;
		this.compname = compname;
		this.villname = villname;
		this.villcode = villcode;
		this.fw = fw;
	}



	public String getExtId() {
		return extId;
	}



	public void setExtId(String extId) {
		this.extId = extId;
	}



	public Date getDob() {
		return dob;
	}



	public void setDob(Date dob) {
		this.dob = dob;
	}



	public Integer getGender() {
		return gender;
	}



	public void setGender(Integer gender) {
		this.gender = gender;
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



	public Date getDod() {
		return dod;
	}



	public void setDod(Date dod) {
		this.dod = dod;
	}



	public String getCompno() {
		return compno;
	}



	public void setCompno(String compno) {
		this.compno = compno;
	}



	public String getHousehead() {
		return househead;
	}



	public void setHousehead(String househead) {
		this.househead = househead;
	}



	public String getCompname() {
		return compname;
	}



	public void setCompname(String compname) {
		this.compname = compname;
	}



	public String getVillname() {
		return villname;
	}



	public void setVillname(String villname) {
		this.villname = villname;
	}



	public String getVillcode() {
		return villcode;
	}



	public void setVillcode(String villcode) {
		this.villcode = villcode;
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
