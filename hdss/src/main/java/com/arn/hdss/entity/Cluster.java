package com.arn.hdss.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cluster")
public class Cluster {
	
	@Id
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "town")
	private String town;
	
	@Column(name = "area")
	private String area;
	
	@Column(name = "villageId", nullable = false)
	private String villageId;
	
	@Column(name = "level_uuid", nullable = false)
	private String level_uuid;
	
	public Cluster() {}

	public Cluster(String extId, String name, String town, String area, String villageId,
			String level_uuid) {
		super();
		this.extId = extId;
		this.name = name;
		this.town = town;
		this.area = area;
		this.villageId = villageId;
		this.level_uuid = level_uuid;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getLevel_uuid() {
		return level_uuid;
	}

	public void setLevel_uuid(String level_uuid) {
		this.level_uuid = level_uuid;
	}
	
	@Override
	public String toString() {
		return extId;
	}

}
