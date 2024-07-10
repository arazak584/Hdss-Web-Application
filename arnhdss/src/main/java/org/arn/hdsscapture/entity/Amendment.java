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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Audited
@Table(name="amendment", indexes = {@Index(name="idx_individual_uuid", columnList="individual_uuid")})
public class Amendment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1844137379269740194L;
	
	@Id
	@Column(nullable = false)
    public String uuid;
	
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	@Column(nullable = false)
    public String orig_firstName;
	@Column(nullable = false)
    public String orig_lastName;
	@Column(nullable = false)
    public Integer orig_gender;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date orig_dob;
	@Column(nullable = true)
    public String orig_otherName;
	@Column(nullable = true)
    public String orig_ghanacard;

    //Amendment
	@Column
    public String repl_firstName;
	@Column
    public String repl_lastName;
	@Column
    public Integer repl_gender;
	@Column
    public Date repl_dob;
	@Column
    public String repl_otherName;
	@Column
    public String repl_ghanacard;

	@Column(nullable = false)
    public Integer yn_firstName;
	@Column(nullable = false)
    public Integer yn_lastName;
	@Column(nullable = false)
    public Integer yn_gender;
	@Column(nullable = false)
    public Integer yn_dob;
	@Column(nullable = false)
    public Integer yn_otherName;
	@Column(nullable = false)
    public Integer yn_ghanacard;
	@Column(nullable = false)
    public Integer complete;
	@Column(nullable = false)
    public String fw_uuid;
	@Column
	public String mother_uuid;
	@Column
    public String father_uuid;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individual_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
	@NotAudited
	private Individual individual;
		
	
	public Amendment() {}
	

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




	public Date getInsertDate() {
		return insertDate;
	}




	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}




	public String getOrig_firstName() {
		return orig_firstName;
	}




	public void setOrig_firstName(String orig_firstName) {
		this.orig_firstName = orig_firstName;
	}




	public String getOrig_lastName() {
		return orig_lastName;
	}




	public void setOrig_lastName(String orig_lastName) {
		this.orig_lastName = orig_lastName;
	}




	public Integer getOrig_gender() {
		return orig_gender;
	}




	public void setOrig_gender(Integer orig_gender) {
		this.orig_gender = orig_gender;
	}




	public Date getOrig_dob() {
		return orig_dob;
	}




	public void setOrig_dob(Date orig_dob) {
		this.orig_dob = orig_dob;
	}




	public String getOrig_otherName() {
		return orig_otherName;
	}




	public void setOrig_otherName(String orig_otherName) {
		this.orig_otherName = orig_otherName;
	}




	public String getOrig_ghanacard() {
		return orig_ghanacard;
	}




	public void setOrig_ghanacard(String orig_ghanacard) {
		this.orig_ghanacard = orig_ghanacard;
	}




	public String getRepl_firstName() {
		return repl_firstName;
	}




	public void setRepl_firstName(String repl_firstName) {
		this.repl_firstName = repl_firstName;
	}




	public String getRepl_lastName() {
		return repl_lastName;
	}




	public void setRepl_lastName(String repl_lastName) {
		this.repl_lastName = repl_lastName;
	}




	public Integer getRepl_gender() {
		return repl_gender;
	}




	public void setRepl_gender(Integer repl_gender) {
		this.repl_gender = repl_gender;
	}




	public Date getRepl_dob() {
		return repl_dob;
	}




	public void setRepl_dob(Date repl_dob) {
		this.repl_dob = repl_dob;
	}




	public String getRepl_otherName() {
		return repl_otherName;
	}




	public void setRepl_otherName(String repl_otherName) {
		this.repl_otherName = repl_otherName;
	}




	public String getRepl_ghanacard() {
		return repl_ghanacard;
	}




	public void setRepl_ghanacard(String repl_ghanacard) {
		this.repl_ghanacard = repl_ghanacard;
	}




	public Integer getYn_firstName() {
		return yn_firstName;
	}




	public void setYn_firstName(Integer yn_firstName) {
		this.yn_firstName = yn_firstName;
	}




	public Integer getYn_lastName() {
		return yn_lastName;
	}




	public void setYn_lastName(Integer yn_lastName) {
		this.yn_lastName = yn_lastName;
	}




	public Integer getYn_gender() {
		return yn_gender;
	}




	public void setYn_gender(Integer yn_gender) {
		this.yn_gender = yn_gender;
	}




	public Integer getYn_dob() {
		return yn_dob;
	}




	public void setYn_dob(Integer yn_dob) {
		this.yn_dob = yn_dob;
	}




	public Integer getYn_otherName() {
		return yn_otherName;
	}




	public void setYn_otherName(Integer yn_otherName) {
		this.yn_otherName = yn_otherName;
	}




	public Integer getYn_ghanacard() {
		return yn_ghanacard;
	}




	public void setYn_ghanacard(Integer yn_ghanacard) {
		this.yn_ghanacard = yn_ghanacard;
	}




	public String getFw_uuid() {
		return fw_uuid;
	}




	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
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


	@Override
	public String toString() {
		return individual_uuid;
	}
	
	

}
