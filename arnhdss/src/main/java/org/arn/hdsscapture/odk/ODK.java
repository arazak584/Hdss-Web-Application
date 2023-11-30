package org.arn.hdsscapture.odk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="odkform", uniqueConstraints = @UniqueConstraint(columnNames = "formID"))
public class ODK implements Serializable {
	
	/**
	 * 
	 */
	public static final long serialVersionUID = 3215948801546119135L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name = "formID", nullable = false)
	public String formID;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date insertDate;
	
	@Column(name = "formName", nullable = false)
	public String formName;
	
	@Column(name = "formDesc", nullable = false)
	public String formDesc;
	
	@Column(name = "gender", nullable = false)
	public String gender;
	
	@Column(name = "enabled", nullable = false)
	public String enabled;
	
	@Column(name = "modules", nullable = false)
	public String modules;
	
	@Column(name = "minAge", nullable = false)
	public Integer minAge;
	
	@Column(name = "maxAge", nullable = false)
	public Integer maxAge;
	

	public ODK() {
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFormID() {
		return formID;
	}


	public void setFormID(String formID) {
		this.formID = formID;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public String getFormName() {
		return formName;
	}


	public void setFormName(String formName) {
		this.formName = formName;
	}


	public String getFormDesc() {
		return formDesc;
	}


	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getEnabled() {
		return enabled;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	public String getModules() {
		return modules;
	}


	public void setModules(String modules) {
		this.modules = modules;
	}


	public Integer getMinAge() {
		return minAge;
	}


	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}


	public Integer getMaxAge() {
		return maxAge;
	}


	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	

}
