package org.arn.hdsscapture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="outcome")
public class Outcome {
	
	@Id
	private String uuid;
	
	//Child permid
	@Column(name = "childuuid")
	private String childuuid;
	
	@Column
	private String preg_uuid;
	
	@Column(name = "mother_uuid", nullable = false)
	private String mother_uuid;
	
	@Column(name = "type", nullable = false)
	private Integer type;
	
	
	public Outcome() {}

	public Outcome(String uuid, String childuuid,String preg_uuid, String mother_uuid, Integer type) {
		super();
		this.uuid = uuid;
		this.childuuid = childuuid;
		this.preg_uuid = preg_uuid;
		this.mother_uuid = mother_uuid;
		this.type = type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getChilduuid() {
		return childuuid;
	}

	public void setChilduuid(String childuuid) {
		this.childuuid = childuuid;
	}


	public String getPreg_uuid() {
		return preg_uuid;
	}

	public void setPreg_uuid(String preg_uuid) {
		this.preg_uuid = preg_uuid;
	}

	public String getMother_uuid() {
		return mother_uuid;
	}

	public void setMother_uuid(String mother_uuid) {
		this.mother_uuid = mother_uuid;
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
