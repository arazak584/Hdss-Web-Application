package org.arn.hdsscapture.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name="socialgroup")
public class Socialgroup {
	
	@Id
	@Column(name = "socialgroup_uuid", nullable = false)
	private String socialgroup_uuid;
	
	@Column(name = "houseExtId", nullable = false)
	private String houseExtId;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	
	@Column(name = "groupName", nullable = false)
	private String groupName;
	
	@Column(name = "groupType", nullable = false)
	private Integer groupType;
	
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individual_uuid", referencedColumnName = "individual_uuid", insertable = false, updatable = false)
	private Individual individual;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "socialgroup")
	private List<Residency> residencies = new ArrayList<>();
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "socialgroup")
	private Sociodemographic sociodemographic;
		
	
	public Socialgroup() {}

	public String getSocialgroup_uuid() {
		return socialgroup_uuid;
	}




	public void setSocialgroup_uuid(String socialgroup_uuid) {
		this.socialgroup_uuid = socialgroup_uuid;
	}




	public String getHouseExtId() {
		return houseExtId;
	}




	public void setHouseExtId(String houseExtId) {
		this.houseExtId = houseExtId;
	}




	public Date getInsertDate() {
		return insertDate;
	}




	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}




	public String getGroupName() {
		return groupName;
	}




	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}




	public Integer getGroupType() {
		return groupType;
	}




	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}


	public String getIndividual_uuid() {
		return individual_uuid;
	}




	public void setIndividual_uuid(String individual_uuid) {
		this.individual_uuid = individual_uuid;
	}




	public String getFw_uuid() {
		return fw_uuid;
	}




	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}




	@Override
	public String toString() {
		return houseExtId;
	}

}
