package org.arn.hdsscapture.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Audited
@Table(name="inmigration")
public class Inmigration {
	
	
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "individual_uuid", nullable = false)	
	private String individual_uuid;
	
	@Id
	@Column(name = "residency_uuid", nullable = false)
	private String residency_uuid;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	@Column(name = "recordedDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date recordedDate;
	
	@Column(name = "origin", nullable = false)
	private Integer origin;
	
	@Column(name = "reason", nullable = false)
	private Integer reason;
	
	@Column(name = "reason_oth")
	private String reason_oth;
	
	@Column(name = "migType", nullable = false)
	private Integer migType;
	
	@Column(name = "visit_uuid", nullable = false)
	private String visit_uuid;
	
	@Column(name = "livestock")
	private Integer livestock;
	
	@Column(name = "farm")
	private Integer farm;
	
	@Column(name = "farm_other")
	private String farm_other;
	
	@Column(name = "food_yn")
	private Integer food_yn;
	
	@Column(name = "cash_yn")
	private Integer cash_yn;
	
	@Column(name = "cash_crops")
	private Integer cash_crops;
	
	@Column(name = "livestock_yn")
	private Integer livestock_yn;

	@Column(name = "acres")
	private Integer acres;
	
	@Column(name = "food_crops")
	private Integer food_crops;
	
	@Column(name = "last_occupa")
	private Integer last_occupa;
	
	@Column(name = "last_other")
	private String last_other;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@Column(name = "sttime")
	private String sttime;

	@Column(name = "edtime")
	private String edtime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individual_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Individual individual;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Visit visit;

	
	public Inmigration() {}


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


	public String getVisit_uuid() {
		return visit_uuid;
	}


	public void setVisit_uuid(String visit_uuid) {
		this.visit_uuid = visit_uuid;
	}


	public String getResidency_uuid() {
		return residency_uuid;
	}


	public void setResidency_uuid(String residency_uuid) {
		this.residency_uuid = residency_uuid;
	}



	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public Date getRecordedDate() {
		return recordedDate;
	}


	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}


	public Integer getOrigin() {
		return origin;
	}


	public void setOrigin(Integer origin) {
		this.origin = origin;
	}


	public Integer getReason() {
		return reason;
	}


	public void setReason(Integer reason) {
		this.reason = reason;
	}
	

	public String getReason_oth() {
		return reason_oth;
	}


	public void setReason_oth(String reason_oth) {
		this.reason_oth = reason_oth;
	}


	public Integer getMigType() {
		return migType;
	}


	public void setMigType(Integer migType) {
		this.migType = migType;
	}


	public Visit getVisit() {
		return visit;
	}


	public void setVisit(Visit visit) {
		this.visit = visit;
	}


	public Integer getLivestock() {
		return livestock;
	}


	public void setLivestock(Integer livestock) {
		this.livestock = livestock;
	}


	public Integer getAcres() {
		return acres;
	}


	public void setAcres(Integer acres) {
		this.acres = acres;
	}


	public Integer getFood_crops() {
		return food_crops;
	}


	public void setFood_crops(Integer food_crops) {
		this.food_crops = food_crops;
	}


	public Integer getLast_occupa() {
		return last_occupa;
	}


	public void setLast_occupa(Integer last_occupa) {
		this.last_occupa = last_occupa;
	}


	public String getLast_other() {
		return last_other;
	}


	public void setLast_other(String last_other) {
		this.last_other = last_other;
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


	public Integer getFarm() {
		return farm;
	}


	public void setFarm(Integer farm) {
		this.farm = farm;
	}


	public String getFarm_other() {
		return farm_other;
	}


	public void setFarm_other(String farm_other) {
		this.farm_other = farm_other;
	}


	public Integer getFood_yn() {
		return food_yn;
	}


	public void setFood_yn(Integer food_yn) {
		this.food_yn = food_yn;
	}


	public Integer getCash_yn() {
		return cash_yn;
	}


	public void setCash_yn(Integer cash_yn) {
		this.cash_yn = cash_yn;
	}


	public Integer getCash_crops() {
		return cash_crops;
	}


	public void setCash_crops(Integer cash_crops) {
		this.cash_crops = cash_crops;
	}


	public Integer getLivestock_yn() {
		return livestock_yn;
	}


	public void setLivestock_yn(Integer livestock_yn) {
		this.livestock_yn = livestock_yn;
	}


	@Override
    public String toString() {
        return residency_uuid;
    }

}
