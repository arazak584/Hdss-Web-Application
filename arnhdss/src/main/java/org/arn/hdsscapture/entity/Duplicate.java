package org.arn.hdsscapture.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Audited
@Table(name="duplicate")
public class Duplicate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date insertDate;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date dob;

    @Column(nullable = false)
    public String fname;

    @Column(nullable = false)
    public String lname;

    @Column(nullable = false)
    public String dup_uuid;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date dup_dob;
    @Column(nullable = false)
    public String dup_fname;
    @Column(nullable = false)
    public String dup_lname;
    @Column(nullable = false)
    public String fw_uuid;
    
    public Duplicate() {}

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDup_uuid() {
		return dup_uuid;
	}

	public void setDup_uuid(String dup_uuid) {
		this.dup_uuid = dup_uuid;
	}

	public Date getDup_dob() {
		return dup_dob;
	}

	public void setDup_dob(Date dup_dob) {
		this.dup_dob = dup_dob;
	}

	public String getDup_fname() {
		return dup_fname;
	}

	public void setDup_fname(String dup_fname) {
		this.dup_fname = dup_fname;
	}

	public String getDup_lname() {
		return dup_lname;
	}

	public void setDup_lname(String dup_lname) {
		this.dup_lname = dup_lname;
	}

	public String getFw_uuid() {
		return fw_uuid;
	}

	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}
    
	@Override
	public String toString() {
		return individual_uuid;
	}

}
