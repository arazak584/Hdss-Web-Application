package org.arn.hdsscapture.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="acthoh")
public class ActiveHouseholds {
	
	@Id
	@Column(name = "villcode", nullable = false)
	private String villcode;
	
	@Column(name = "District", nullable = false)
	private String District;

	
	@Column(name = "village", nullable = false)
	private String village;
	
	@Column(name = "subvillage", nullable = false)
	private String subvillage;
	
	private String households;
	
	private String compounds;

	public ActiveHouseholds() {
	}

	public String getVillcode() {
		return villcode;
	}

	public void setVillcode(String villcode) {
		this.villcode = villcode;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getSubvillage() {
		return subvillage;
	}

	public void setSubvillage(String subvillage) {
		this.subvillage = subvillage;
	}

	public String getHouseholds() {
		return households;
	}

	public void setHouseholds(String households) {
		this.households = households;
	}

	public String getCompounds() {
		return compounds;
	}

	public void setCompounds(String compounds) {
		this.compounds = compounds;
	}

	
	

}
