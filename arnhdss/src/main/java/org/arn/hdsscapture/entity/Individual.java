package org.arn.hdsscapture.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;

@Audited
@Entity
@Table(name="individual", indexes = {@Index(name="idx_individual_uuid", columnList="uuid", unique=true)})
public class Individual implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8535563948748501772L;

	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "extId", nullable = false, unique=true)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;

	
	@Column(name = "dob", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	@Column(name = "dobAspect", nullable = false)
	private Integer dobAspect;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	@Column(name = "otherName")
	private String otherName;
	
	@Column(name = "gender", nullable = false)
	private Integer gender;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@Column(name = "ghanacard", nullable=true)
	private String ghanacard;
	
	@Column(name = "mother_uuid", nullable=false)
    private String mother_uuid;

	@Column(name = "father_uuid", nullable=false)
    private String father_uuid;
	
	@Column(name = "sttime")
	private String sttime;

	@Column(name = "edtime")
	private String edtime;
	
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "individual")
	private List<Outmigration> outmigrations = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "individual")
	private List<Inmigration> inmigrations = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "individual")
	private List<Residency> residency = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "individual")
	private List<Pregnancyobservation> pregnancyobservations = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "individual")
	private List<Pregnancyoutcome> pregnancyoutcomes = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "individual")
	private List<Relationship> relationships = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "individual")
	private List<Socialgroup> socialgroups = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "individual")
	private List<Sociodemographic> sociodemographics = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "individual")
	private List<Outcome> outcomes = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "individual")
	private List<Amendment> amendments = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
	
	
	@OneToMany(mappedBy = "mother", cascade = CascadeType.ALL)
    private Set<Individual> children = new HashSet<>();	

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
    private Individual mother;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "father_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
    private Individual father;
	
	@OneToMany(mappedBy = "father", cascade = CascadeType.ALL)
    private Set<Individual> offspring = new HashSet<>();
	
	
	public Individual() {}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMother_uuid() {
        return mother_uuid;
    }

    public void setMother_uuid(String mother_uuid) {
        this.mother_uuid = mother_uuid;
    }

    public String getFather_uuid() {
        return father_uuid;
    }

    public void setFather_uuid(String father_uuid) {
        this.father_uuid = father_uuid;
    }  


	public String getExtId() {
		return extId;
	}


	public void setExtId(String extId) {
		this.extId = extId;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public Integer getDobAspect() {
		return dobAspect;
	}


	public void setDobAspect(Integer dobAspect) {
		this.dobAspect = dobAspect;
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


	public String getOtherName() {
		return otherName;
	}


	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}


	public Integer getGender() {
		return gender;
	}


	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getFw_uuid() {
		return fw_uuid;
	}


	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}
	
	
	public String getGhanacard() {
		return ghanacard;
	}


	public void setGhanacard(String ghanacard) {
		this.ghanacard = ghanacard;
	}
	
	
	public String getSttime() {
		return sttime;
	}

	public void setSttime(String sttime) {
		this.sttime = sttime;
	}

	public String getEdtime() {
		return edtime;
	}

	public void setEdtime(String edtime) {
		this.edtime = edtime;
	}

	@Override
	public String toString() {
		return extId;
	}
	
	

}
