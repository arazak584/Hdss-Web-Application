package org.arn.hdsscapture.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Population {
	
	@Id
	@Column(name = "ageGroup")
	private String ageGroup;
	
	@Column(name = "pop")
	private Long pop;
	
	@Column(name = "female")
	private Long female;
		
	@Column(name = "male")
	private Long male;

	
	
	public Population() {}

	public String getAgeGroup() {
		return ageGroup;
	}


	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}


	public Long getFemale() {
		return female;
	}


	public void setFemale(Long female) {
		this.female = female;
	}


	public Long getMale() {
		return male;
	}


	public void setMale(Long male) {
		this.male = male;
	}

	public Long getPop() {
		return pop;
	}

	public void setPop(Long pop) {
		this.pop = pop;
	}



}
