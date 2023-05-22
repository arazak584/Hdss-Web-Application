package org.arn.hdsscapture.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uuid;

    @Column(nullable = false)
    public LocalDateTime timestamp;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String type;
    
    @Column(nullable = false)
    private Integer total;

    public Task() {}


    public Task(Integer uuid, LocalDateTime timestamp, String data, String fileName, String type, Integer total) {
		super();
		this.uuid = uuid;
		this.timestamp = timestamp;
		this.data = data;
		this.fileName = fileName;
		this.type = type;
		this.total = total;
	}




	public Integer getUuid() {
		return uuid;
	}



	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}



	public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}
    
}
