package org.arn.hdsscapture.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name =  "fieldworker", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Fieldworker {
	
	@Id
    @Column(name = "fw_uuid")
	public String fw_uuid;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date insertDate;
	
	@Column(name = "username", nullable = false)
	public String username;
		
	@Column(name = "firstName", nullable = false)
	public String firstName;
	
	@Column(name = "lastName", nullable = false)
	public String lastName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	
	public Fieldworker() {}





	public Fieldworker(String fw_uuid, Date insertDate, String username, String firstName, String lastName,
			String password, String status) {
		super();
		this.fw_uuid = fw_uuid;
		this.insertDate = insertDate;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.status = status;
	}



	public String getFw_uuid() {
		return fw_uuid;
	}





	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}





	public Date getInsertDate() {
		return insertDate;
	}





	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}





	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getFirstName() {
		return firstName;
	}





	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}





	public String getLastName() {
		return lastName;
	}





	public void setLastName(String lastName) {
		this.lastName = lastName;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}





	@Override
	public String toString() {
		return username;
	}
}
