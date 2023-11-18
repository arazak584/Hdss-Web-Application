package org.arn.hdsscapture.entity;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Audited
@Table(name =  "fieldworker", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Fieldworker implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "fw_uuid")
	public String fw_uuid;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date insertDate;
	
	@Column(name = "username", nullable = false, unique=true)
	public String username;
		
	@Column(name = "firstName", nullable = false)
	public String firstName;
	
	@Column(name = "lastName", nullable = false)
	public String lastName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Socialgroup> socialgroups = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Death> deaths = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Demographic> demographics = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Individual> individuals = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Inmigration> inmigrations = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Location> locations = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Outmigration> outmigrations = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Pregnancyobservation> pregnancyobservations = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Pregnancyoutcome> pregnancyoutcomes = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Relationship> relationships = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Residency> residencies = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Sociodemographic> sociodemographics = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Visit> visit = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "fieldworker")
	private List<Amendment> amendment = new ArrayList<>();
	
	
	public Fieldworker() {}


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
