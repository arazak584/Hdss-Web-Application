package org.arn.hdsscapture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="locationhierarchy")
public class Locationhierarchy {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "villcode", nullable = false)
	private String villcode;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "town")
	private String town;
	
	@Column(name = "area")
	private String area;
	
	@Column(name = "parent_uuid")
	private String parent_uuid;
	
	@Column(name = "level_uuid")
	private String level_uuid;
	
	public Locationhierarchy() {}

	

	public Locationhierarchy(String uuid, String villcode, String name, String town, String area, String parent_uuid,
			String level_uuid) {
		super();
		this.uuid = uuid;
		this.villcode = villcode;
		this.name = name;
		this.town = town;
		this.area = area;
		this.parent_uuid = parent_uuid;
		this.level_uuid = level_uuid;
	}



	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getVillcode() {
		return villcode;
	}

	public void setVillcode(String villcode) {
		this.villcode = villcode;
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

	
	public String getParent_uuid() {
		return parent_uuid;
	}

	public void setParent_uuid(String parent_uuid) {
		this.parent_uuid = parent_uuid;
	}

	public String getLevel_uuid() {
		return level_uuid;
	}

	public void setLevel_uuid(String level_uuid) {
		this.level_uuid = level_uuid;
	}
	
	@Override
	public String toString() {
		return villcode;
	}

}
