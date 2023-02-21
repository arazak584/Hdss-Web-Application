package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pregnancyoutcome")
public class Pregnancyoutcome {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String uuid;
	
	@Column(name = "mother", nullable = false)
	private String mother;
	
	@Column(name = "father", nullable = false)
	private String father;
	
	@Column(name = "childextId")
	private String childextId;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "outcomeDate", nullable = false)
	private Date outcomeDate;
	
	@Column(name = "type", nullable = false)
	private Integer type;
	
	@Column(name = "childEverBorn", nullable = false)
	private Integer childEverBorn;
	
	@Column(name = "numberOfLiveBirths", nullable = false)
	private Integer numberOfLiveBirths;
	
	@Column(name = "birthPlace")
	private Integer birthPlace;
	
	@Column(name = "visitid", nullable = false)
	private String visitid;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	public Pregnancyoutcome() {}


	public Pregnancyoutcome(String uuid, String mother, String father,String childextId, Date insertDate, Date outcomeDate, Integer type,
			Integer childEverBorn, Integer numberOfLiveBirths, Integer birthPlace, String visitid, String fw) {
		super();
		this.uuid = uuid;
		this.mother = mother;
		this.father = father;
		this.childextId = childextId;
		this.insertDate = insertDate;
		this.outcomeDate = outcomeDate;
		this.type = type;
		this.childEverBorn = childEverBorn;
		this.numberOfLiveBirths = numberOfLiveBirths;
		this.birthPlace = birthPlace;
		this.visitid = visitid;
		this.fw = fw;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getMother() {
		return mother;
	}


	public void setMother(String mother) {
		this.mother = mother;
	}


	public String getFather() {
		return father;
	}


	public void setFather(String father) {
		this.father = father;
	}

	

	public String getChildextId() {
		return childextId;
	}


	public void setChildextId(String childextId) {
		this.childextId = childextId;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public Date getOutcomeDate() {
		return outcomeDate;
	}


	public void setOutcomeDate(Date outcomeDate) {
		this.outcomeDate = outcomeDate;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getChildEverBorn() {
		return childEverBorn;
	}


	public void setChildEverBorn(Integer childEverBorn) {
		this.childEverBorn = childEverBorn;
	}


	public Integer getNumberOfLiveBirths() {
		return numberOfLiveBirths;
	}


	public void setNumberOfLiveBirths(Integer numberOfLiveBirths) {
		this.numberOfLiveBirths = numberOfLiveBirths;
	}


	public Integer getBirthPlace() {
		return birthPlace;
	}


	public void setBirthPlace(Integer birthPlace) {
		this.birthPlace = birthPlace;
	}


	public String getVisitid() {
		return visitid;
	}


	public void setVisitid(String visitid) {
		this.visitid = visitid;
	}


	public String getFw() {
		return fw;
	}


	public void setFw(String fw) {
		this.fw = fw;
	}

	@Override
	public String toString() {
		return uuid;
	}


	//@Override
    //public String toString() {
    //    return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
