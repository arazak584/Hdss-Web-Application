package com.arn.hdss.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name =  "fieldworker", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Fieldworker {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Long id;
	
	@Column(name = "username", nullable = false)
	private String username;
		
	@Column(name = "fullName", nullable = false)
	private String fullName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	
	public Fieldworker() {}
	
	
	public Fieldworker(Long id, String username, String fullName, String password) {
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.password = password;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
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
