package org.arn.hdsscapture.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="listing")
public class Listing implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1844137379269740194L;
	
	
	@Id
	@Column(name = "compno", nullable = false)
	private String compno;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
		
	@Column(name = "compextId", nullable = false)
	private String compextId;

	
	@Column(name = "status", nullable = false)
	private Integer status;
	
	@Column(name = "fw_name", nullable = false)
	private String fw_name;
	
	@Column(name = "village", nullable = false)
	private String village;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
		
	
	public Listing() {}

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


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFw_name() {
		return fw_name;
	}

	public void setFw_name(String fw_name) {
		this.fw_name = fw_name;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getFw_uuid() {
		return fw_uuid;
	}

	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}

	@Override
	public String toString() {
		return compextId;
	}
	
	

}
