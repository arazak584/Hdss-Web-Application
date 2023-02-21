package com.arn.hdss.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name="location")
public class Location implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 306859855071930982L;

	@Id
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
		
	@Column(name = "compno", nullable = false)
	private String compno;
	
	@Column(name = "locationName", nullable = false)
	private String locationName;
	
	@Column(name = "longitude")
	private String longitude;
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "accuracy")
	private String accuracy;
	
	@Column(name = "locationType", nullable = false)
	private Integer locationType;
	
	@Column(name = "status", nullable = false)
	private Integer status;
	
	@Column(name = "clusterId", nullable = false)
	private String clusterId;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	
	public Location() {}

	 

	public Location(String extId, Date insertDate, String compno, String locationName, String longitude, String latitude,
			String accuracy, Integer locationType, Integer status, String clusterId, String fw) {
		super();
		this.extId = extId;
		this.insertDate = insertDate;
		this.compno = compno;
		this.locationName = locationName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.accuracy = accuracy;
		this.locationType = locationType;
		this.status = status;
		this.clusterId = clusterId;
		this.fw = fw;
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



	public String getCompno() {
		return compno;
	}



	public void setCompno(String compno) {
		this.compno = compno;
	}



	public String getLocationName() {
		return locationName;
	}



	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}



	public String getLongitude() {
		return longitude;
	}



	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}



	public String getLatitude() {
		return latitude;
	}



	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}



	public String getAccuracy() {
		return accuracy;
	}



	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}



	public Integer getLocationType() {
		return locationType;
	}



	public void setLocationType(Integer locationType) {
		this.locationType = locationType;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public String getClusterId() {
		return clusterId;
	}



	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
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



	//@Override
    //public String toString() {
     //   return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
