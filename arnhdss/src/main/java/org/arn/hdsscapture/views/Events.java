package org.arn.hdsscapture.views;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Events {
	
	@Id
	private Integer Year;
	private Integer total;
	private Integer male;
	private Integer female;
	private Integer lbr;
	private Integer sbr;
	private Integer mis;
	private Integer abt;
	public Integer getYear() {
		return Year;
	}
	public void setYear(Integer year) {
		Year = year;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getMale() {
		return male;
	}
	public void setMale(Integer male) {
		this.male = male;
	}
	public Integer getFemale() {
		return female;
	}
	public void setFemale(Integer female) {
		this.female = female;
	}
	public Integer getLbr() {
		return lbr;
	}
	public void setLbr(Integer lbr) {
		this.lbr = lbr;
	}
	public Integer getSbr() {
		return sbr;
	}
	public void setSbr(Integer sbr) {
		this.sbr = sbr;
	}
	public Integer getMis() {
		return mis;
	}
	public void setMis(Integer mis) {
		this.mis = mis;
	}
	public Integer getAbt() {
		return abt;
	}
	public void setAbt(Integer abt) {
		this.abt = abt;
	}
	

}
