package org.arn.hdsscapture.views;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Immutable
@Table(name="freport")
public class FieldReport {
	
	@Id
	private String id;
	
	@Column(name = "Fieldworker", nullable = false)
	private String Fieldworker;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	@Column(name = "submitDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date submitDate;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "year", nullable = false)
	private String year;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	private Long total;

	public FieldReport() {
	}

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getFieldworker() {
		return Fieldworker;
	}

	public void setFieldworker(String fieldworker) {
		Fieldworker = fieldworker;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
