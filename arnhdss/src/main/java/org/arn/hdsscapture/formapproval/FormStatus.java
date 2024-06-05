package org.arn.hdsscapture.formapproval;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="formstatus", indexes = {@Index(name="permid", columnList="permid", unique=false),
		@Index(name = "individual_uuid", columnList = "individual_uuid", unique = false),
		@Index(name = "compno", columnList = "compno", unique = false)})
public class FormStatus {

	
	@Id
	private String uuid;
	
	@Column(name = "permid", nullable = false)
	private String permid;
	
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
		
	@Column(name = "compno", nullable = false)
	private Integer compno;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "comment", nullable = false, length = 1000)
	private String comment;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "entity", nullable = false)
	private String entity;
	
	@Column(name = "village", nullable = false)
	private String village;
	
	@Column(name = "fw_name", nullable = false)
	private String fw_name;
	
	@Column(name = "coordinater", nullable = false)
	private String coordinater;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	
	public FormStatus() {}


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


	public String getPermid() {
		return permid;
	}


	public void setPermid(String permid) {
		this.permid = permid;
	}


	public Integer getCompno() {
		return compno;
	}


	public void setCompno(Integer compno) {
		this.compno = compno;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getEntity() {
		return entity;
	}


	public void setEntity(String entity) {
		this.entity = entity;
	}


	public String getVillage() {
		return village;
	}


	public void setVillage(String village) {
		this.village = village;
	}


	public String getFw_name() {
		return fw_name;
	}


	public void setFw_name(String fw_name) {
		this.fw_name = fw_name;
	}

	public String getCoordinater() {
		return coordinater;
	}


	public void setCoordinater(String coordinater) {
		this.coordinater = coordinater;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	

}
