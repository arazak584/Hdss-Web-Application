package com.arn.hdss.entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="round")
public class Round {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	public Long id;
	
	@Column(name = "roundNumber", nullable = false)
	private Integer roundNumber;
	
	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate", nullable = false)
	private Date endDate;
	
	@Column(name = "remark", nullable = false)
	private String remark;
	
	public Round() {}
	
	

	public Round(Long id, Integer roundNumber, Date startDate, Date endDate, String remark) {
		super();
		this.id = id;
		this.roundNumber = roundNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remark = remark;
	}


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(Integer roundNumber) {
		this.roundNumber = roundNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
