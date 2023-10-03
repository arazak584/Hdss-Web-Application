package org.arn.hdsscapture.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;

@Audited
@Entity
@Table(name="residency", indexes = {@Index(name="idx_residency_uuid", columnList="uuid")})
public class Residency {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	@Column(name = "startDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@Column(name = "endDate", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@Column(name = "startType", nullable = false)
	private Integer startType;
	
	@Column(name = "endType", nullable = false)
	private Integer endType;
	
	@Column(name = "socialgroup_uuid", nullable = false)
	private String socialgroup_uuid;
	
	@Column(name = "location_uuid", nullable = false)
	private String location_uuid;
	
	@Column(name = "rltn_head", nullable = false)
	private Integer rltn_head;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@Column(name = "sttime")
	private String sttime;

	@Column(name = "edtime")
	private String edtime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false, nullable=false)
	private Fieldworker fieldworker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individual_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Individual individual;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "socialgroup_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Socialgroup socialgroup;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
	@NotAudited
	private Location location;
	
	public Residency() {}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getIndividual_uuid() {
		return individual_uuid;
	}


	public void setIndividual_uuid(String individual_uuid) {
		this.individual_uuid = individual_uuid;
	}


	public String getSocialgroup_uuid() {
		return socialgroup_uuid;
	}


	public void setSocialgroup_uuid(String socialgroup_uuid) {
		this.socialgroup_uuid = socialgroup_uuid;
	}

	public String getLocation_uuid() {
		return location_uuid;
	}


	public void setLocation_uuid(String location_uuid) {
		this.location_uuid = location_uuid;
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
	
	public String getSttime() {
		return sttime;
	}

	public void setSttime(String sttime) {
		this.sttime = sttime;
	}

	public String getEdtime() {
		return edtime;
	}

	public void setEdtime(String edtime) {
		this.edtime = edtime;
	}


	@Override
    public String toString() {
        return uuid;
    }

	


}
