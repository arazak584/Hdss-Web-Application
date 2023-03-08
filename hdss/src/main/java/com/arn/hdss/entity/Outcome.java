package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="outcome")
public class Outcome {
	
	@Id
	private String uuid;
	
	@Column(name = "mother", nullable = false)
	private String mother;

	
	//Child permid
	@Column(name = "childextId")
	private String childextId;
	
	@Column(name = "preg_uuid", nullable = false)
	private String preg_uuid;
	
	@Column(name = "type", nullable = false)
	private Integer type;
	
	
	public Outcome() {}

	public Outcome(String uuid, String mother, String childextId, String preg_uuid, Integer type) {
		super();
		this.uuid = uuid;
		this.mother = mother;
		this.childextId = childextId;
		this.preg_uuid = preg_uuid;
		this.type = type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public String getChildextId() {
		return childextId;
	}

	public void setChildextId(String childextId) {
		this.childextId = childextId;
	}

	public String getPreg_uuid() {
		return preg_uuid;
	}

	public void setPreg_uuid(String preg_uuid) {
		this.preg_uuid = preg_uuid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return uuid;
	}


}
