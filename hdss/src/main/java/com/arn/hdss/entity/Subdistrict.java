package com.arn.hdss.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="subdistrict")
public class Subdistrict {
	
	@Id
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "town")
	private String town;
	
	@Column(name = "area")
	private String area;
	
	@Column(name = "districtId", nullable = false)
	private String districtId;
	
	@Column(name = "level_uuid", nullable = false)
	private String level_uuid;
	
	public Subdistrict() {}

	public Subdistrict(String extId, String name, String town, String area, String districtId,
			String level_uuid) {
		super();
		this.extId = extId;
		this.name = name;
		this.town = town;
		this.area = area;
		this.districtId = districtId;
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

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
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
