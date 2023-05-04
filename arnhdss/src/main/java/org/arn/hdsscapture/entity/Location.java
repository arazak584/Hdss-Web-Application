package org.arn.hdsscapture.entity;

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
	private static final long serialVersionUID = -1844137379269740194L;
	
	
	@Column(name = "location_uuid", nullable = false)
	private String location_uuid;
	
	@Id
	@Column(name = "compextId", nullable = false)
	private String compextId;
	
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
	
	@Column(name = "locationLevel_uuid", nullable = false)
	private String locationLevel_uuid;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	
	public Location() {}

	public Location(String location_uuid, String compextId, Date insertDate, String compno, String locationName,
			String longitude, String latitude, String accuracy, Integer locationType, Integer status,
			String locationLevel_uuid, String fw_uuid) {
		super();
		this.location_uuid = location_uuid;
		this.compextId = compextId;
		this.insertDate = insertDate;
		this.compno = compno;
		this.locationName = locationName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.accuracy = accuracy;
		this.locationType = locationType;
		this.status = status;
		this.locationLevel_uuid = locationLevel_uuid;
		this.fw_uuid = fw_uuid;
	}



	public String getLocation_uuid() {
		return location_uuid;
	}

	public void setLocation_uuid(String location_uuid) {
		this.location_uuid = location_uuid;
	}

	public String getCompextId() {
		return compextId;
	}

	public void setCompextId(String compextId) {
		this.compextId = compextId;
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

	public String getLocationLevel_uuid() {
		return locationLevel_uuid;
	}

	public void setLocationLevel_uuid(String locationLevel_uuid) {
		this.locationLevel_uuid = locationLevel_uuid;
	}

	public String getFw_uuid() {
		return fw_uuid;
	}

	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return compextId;
	}
	
	

}
