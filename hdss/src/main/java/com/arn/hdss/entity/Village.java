package com.arn.hdss.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="village")
public class Village {
	
	@Id
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "town")
	private String town;
	
	@Column(name = "area")
	private String area;
	
	@Column(name = "subdistrictId", nullable = false)
	private String subdistrictId;
	
	@Column(name = "level_uuid", nullable = false)
	private String level_uuid;
	
	public Village() {}

	public Village(String extId, String name, String town, String area, String subdistrictId,
			String level_uuid) {
		super();
		this.extId = extId;
		this.name = name;
		this.town = town;
		this.area = area;
		this.subdistrictId = subdistrictId;
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

	public String getSubdistrictId() {
		return subdistrictId;
	}

	public void setSubdistrictId(String subdistrictId) {
		this.subdistrictId = subdistrictId;
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
