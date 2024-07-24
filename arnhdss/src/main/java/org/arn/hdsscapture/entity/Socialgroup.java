package org.arn.hdsscapture.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;

@Audited
@Entity
@Table(name="socialgroup", indexes = {@Index(name="idx_socialgroup_uuid", columnList="uuid")})
public class Socialgroup {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "extId", nullable = false, unique=true)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	
	@Column(name = "groupName", nullable = false)
	private String groupName;
	
	@Column(name = "groupType", nullable = false)
	private Integer groupType;

	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individual_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
	@NotAudited
	private Individual individual;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "socialgroup")
	private List<Residency> residencies = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "socialgroup")
	private List<Vaccination> vaccinations = new ArrayList<>();
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "socialgroup")
	private Morbidity morbidity;
		
	
	public Socialgroup() {}


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


	public String getExtId() {
		return extId;
	}


	public void setExtId(String extId) {
		this.extId = extId;
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


	public String getFw_uuid() {
		return fw_uuid;
	}


	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}


	@Override
	public String toString() {
		return extId;
	}

}
