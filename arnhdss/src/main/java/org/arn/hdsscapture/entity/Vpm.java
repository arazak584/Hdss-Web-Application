package org.arn.hdsscapture.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vpm")
public class Vpm {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
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
	
	@Column(name = "deathDate", nullable = false)
	private Date deathDate;
	
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
	
	@Column(name = "fieldworker")
	private String fieldworker;
	
	public Vpm() {}
	
	

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



	public Date getDeathDate() {
		return deathDate;
	}



	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
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



	public String getFieldworker() {
		return fieldworker;
	}



	public void setFieldworker(String fieldworker) {
		this.fieldworker = fieldworker;
	}

	@Override
	public String toString() {
		return individual_uuid;
	}



	//@Override
    //public String toString() {
    //    return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
