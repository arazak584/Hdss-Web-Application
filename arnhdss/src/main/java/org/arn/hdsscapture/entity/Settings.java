package org.arn.hdsscapture.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="settings")
public class Settings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "hoh_age", nullable = false)
	private Integer hoh_age;
		
	@Column(name = "mother_age", nullable = false)
	private Integer mother_age;
	
	@Column(name = "father_age", nullable = false)
	private Integer father_age;
	
	@Column(name = "rel_age", nullable = false)
	private Integer rel_age;
	
	@Column(name = "earliestDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date earliestDate;
	
	
	public Settings() {}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHoh_age() {
		return hoh_age;
	}

	public void setHoh_age(Integer hoh_age) {
		this.hoh_age = hoh_age;
	}

	public Integer getMother_age() {
		return mother_age;
	}

	public void setMother_age(Integer mother_age) {
		this.mother_age = mother_age;
	}

	public Integer getFather_age() {
		return father_age;
	}

	public void setFather_age(Integer father_age) {
		this.father_age = father_age;
	}

	public Integer getRel_age() {
		return rel_age;
	}

	public void setRel_age(Integer rel_age) {
		this.rel_age = rel_age;
	}


	public Date getEarliestDate() {
		return earliestDate;
	}

	public void setEarliestDate(Date earliestDate) {
		this.earliestDate = earliestDate;
	}


}
