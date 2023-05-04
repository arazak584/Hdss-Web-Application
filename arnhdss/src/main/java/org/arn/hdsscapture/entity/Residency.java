package org.arn.hdsscapture.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name="residency")
public class Residency {
	
	@Id
	@Column(name = "residency_uuid", nullable = false)
	private String residency_uuid;
	
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate")
	private Date endDate;
	
	@Column(name = "startType", nullable = false)
	private Integer startType;
	
	@Column(name = "endType", nullable = false)
	private Integer endType;
	
	@Column(name = "location_uuid", nullable = false)
	private String location_uuid;

	
	@Column(name = "socialgroup_uuid", nullable = false)
	private String socialgroup_uuid;
	
	@Column(name = "rltn_head", nullable = false)
	private Integer rltn_head;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	public Residency() {}

	public Residency(String residency_uuid, String individual_uuid, Date insertDate, Date startDate, Date endDate,
			Integer startType, Integer endType, String location_uuid, String socialgroup_uuid,Integer rltn_head, String fw_uuid) {
		super();
		this.residency_uuid = residency_uuid;
		this.individual_uuid = individual_uuid;
		this.insertDate = insertDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startType = startType;
		this.endType = endType;
		this.location_uuid = location_uuid;
		this.socialgroup_uuid = socialgroup_uuid;
		this.rltn_head = rltn_head;
		this.fw_uuid = fw_uuid;
	}

	public String getResidency_uuid() {
		return residency_uuid;
	}

	public void setResidency_uuid(String residency_uuid) {
		this.residency_uuid = residency_uuid;
	}

	public String getIndividual_uuid() {
		return individual_uuid;
	}

	public void setIndividual_uuid(String individual_uuid) {
		this.individual_uuid = individual_uuid;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStartType() {
		return startType;
	}

	public void setStartType(Integer startType) {
		this.startType = startType;
	}

	public Integer getEndType() {
		return endType;
	}

	public void setEndType(Integer endType) {
		this.endType = endType;
	}

	public String getLocation_uuid() {
		return location_uuid;
	}

	public void setLocation_uuid(String location_uuid) {
		this.location_uuid = location_uuid;
	}

	public String getSocialgroup_uuid() {
		return socialgroup_uuid;
	}

	public void setSocialgroup_uuid(String socialgroup_uuid) {
		this.socialgroup_uuid = socialgroup_uuid;
	}
	

	public Integer getRltn_head() {
		return rltn_head;
	}

	public void setRltn_head(Integer rltn_head) {
		this.rltn_head = rltn_head;
	}

	public String getFw_uuid() {
		return fw_uuid;
	}

	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}

	


}
