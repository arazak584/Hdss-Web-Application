package org.arn.hdsscapture.entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name =  "round", uniqueConstraints = @UniqueConstraint(columnNames = "roundNumber"))
public class Round {
		
	@Id
    @Column(name = "uuid")
	public String uuid;
	
	@Column(name = "roundNumber", nullable = false)
	private Integer roundNumber;
	
	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate", nullable = false)
	private Date endDate;
	
	@Column(name = "remark", nullable = false)
	private String remark;
	
	public Round() {}
	



	
	public Round(String uuid, Integer roundNumber, Date startDate, Date endDate, String remark) {
		super();
		this.uuid = uuid;
		this.roundNumber = roundNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remark = remark;
	}





	public String getUuid() {
		return uuid;
	}





	public void setUuid(String uuid) {
		this.uuid = uuid;
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
