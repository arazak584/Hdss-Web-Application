package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="inmigration")
public class Inmigration {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "recordedDate", nullable = false)
	private Date recordedDate;
	
	@Column(name = "origin", nullable = false)
	private Integer origin;
	
	@Column(name = "reason", nullable = false)
	private Integer reason;
	
	@Column(name = "migType", nullable = false)
	private Integer migType;
	
	@Column(name = "visitid", nullable = false)
	private String visitid;
	
	@Column(name = "livestock")
	private Integer livestock;

	@Column(name = "acres")
	private Integer acres;
	
	@Column(name = "food_crops")
	private Integer food_crops;
	
	@Column(name = "last_occupa")
	private Integer last_occupa;
	
	@Column(name = "last_other")
	private String last_other;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	public Inmigration() {}





	
	public Inmigration(String uuid, String extId, Date insertDate, Date recordedDate, Integer origin, Integer reason,
			Integer migType, String visitid, Integer livestock, Integer acres, Integer food_crops, Integer last_occupa,
			String last_other, String fw) {
		super();
		this.uuid = uuid;
		this.extId = extId;
		this.insertDate = insertDate;
		this.recordedDate = recordedDate;
		this.origin = origin;
		this.reason = reason;
		this.migType = migType;
		this.visitid = visitid;
		this.livestock = livestock;
		this.acres = acres;
		this.food_crops = food_crops;
		this.last_occupa = last_occupa;
		this.last_other = last_other;
		this.fw = fw;
	}






	public String getUuid() {
		return uuid;
	}






	public void setUuid(String uuid) {
		this.uuid = uuid;
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






	public Integer getMigType() {
		return migType;
	}






	public void setMigType(Integer migType) {
		this.migType = migType;
	}






	public String getVisitid() {
		return visitid;
	}






	public void setVisitid(String visitid) {
		this.visitid = visitid;
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






	public String getFw() {
		return fw;
	}






	public void setFw(String fw) {
		this.fw = fw;
	}




	@Override
    public String toString() {
        return extId;
    }

}
