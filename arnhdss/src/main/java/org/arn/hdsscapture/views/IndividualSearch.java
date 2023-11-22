package org.arn.hdsscapture.views;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Immutable
@Table(name="search")
public class IndividualSearch {
	
	@Id
	private String permid;
	
	@Column(name = "compno", nullable = true)
	private String compno;
	
	@Column(name = "dob", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	@Column(name = "name", nullable = true)
	private String name;
	
	@Column(name = "othername", nullable = true)
	private String othername;
	
	private String gender;
	
	private String motherid;
	
	private String mothername;
	private String endType;
	private String village;

	public IndividualSearch() {
	}

	public String getPermid() {
		return permid;
	}

	public void setPermid(String permid) {
		this.permid = permid;
	}

	public String getCompno() {
		return compno;
	}

	public void setCompno(String compno) {
		this.compno = compno;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOthername() {
		return othername;
	}

	public void setOthername(String othername) {
		this.othername = othername;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMotherid() {
		return motherid;
	}

	public void setMotherid(String motherid) {
		this.motherid = motherid;
	}

	public String getMothername() {
		return mothername;
	}

	public void setMothername(String mothername) {
		this.mothername = mothername;
	}

	public String getEndType() {
		return endType;
	}

	public void setEndType(String endType) {
		this.endType = endType;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	

}
