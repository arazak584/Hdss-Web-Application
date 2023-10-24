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
    
    @Column(nullable = true)
    public String dup1_uuid;
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date dup1_dob;
    @Column(nullable = true)
    public String dup1_fname;
    @Column(nullable = true)
    public String dup1_lname;
    
    @Column(nullable = true)
    public String dup2_uuid;
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date dup2_dob;
    @Column(nullable = true)
    public String dup2_fname;
    @Column(nullable = true)
    public String dup2_lname;
    
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
    
	public String getDup1_uuid() {
		return dup1_uuid;
	}

	public void setDup1_uuid(String dup1_uuid) {
		this.dup1_uuid = dup1_uuid;
	}

	public Date getDup1_dob() {
		return dup1_dob;
	}

	public void setDup1_dob(Date dup1_dob) {
		this.dup1_dob = dup1_dob;
	}

	public String getDup1_fname() {
		return dup1_fname;
	}

	public void setDup1_fname(String dup1_fname) {
		this.dup1_fname = dup1_fname;
	}

	public String getDup1_lname() {
		return dup1_lname;
	}

	public void setDup1_lname(String dup1_lname) {
		this.dup1_lname = dup1_lname;
	}

	public String getDup2_uuid() {
		return dup2_uuid;
	}

	public void setDup2_uuid(String dup2_uuid) {
		this.dup2_uuid = dup2_uuid;
	}

	public Date getDup2_dob() {
		return dup2_dob;
	}

	public void setDup2_dob(Date dup2_dob) {
		this.dup2_dob = dup2_dob;
	}

	public String getDup2_fname() {
		return dup2_fname;
	}

	public void setDup2_fname(String dup2_fname) {
		this.dup2_fname = dup2_fname;
	}

	public String getDup2_lname() {
		return dup2_lname;
	}

	public void setDup2_lname(String dup2_lname) {
		this.dup2_lname = dup2_lname;
	}

	@Override
	public String toString() {
		return individual_uuid;
	}

}
