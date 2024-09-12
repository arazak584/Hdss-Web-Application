package org.arn.hdsscapture.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name =  "errorLog")
public class ErrorLog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
	@Column
	public String recordUuid;

	public String errorMessage;
	public LocalDateTime timestamp;
	@Column(columnDefinition = "TEXT") // Use TEXT type to store the full stack trace as a string
	public String stackTrace;
	
	public String dataCollector;
	public String tableName;
    
    public ErrorLog() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getRecordUuid() {
		return recordUuid;
	}

	public void setRecordUuid(String recordUuid) {
		this.recordUuid = recordUuid;
	}

	public String getDataCollector() {
		return dataCollector;
	}

	public void setDataCollector(String dataCollector) {
		this.dataCollector = dataCollector;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
    
    

}
