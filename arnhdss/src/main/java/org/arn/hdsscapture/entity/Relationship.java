package org.arn.hdsscapture.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name="relationship")
public class Relationship {
	
	@Id
	@Column(name = "rel_uuid", nullable = false)
	private String rel_uuid;
	
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Column(name = "man_uuid", nullable = false)
	private String man_uuid;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate")
	private Date endDate;
	
	@Column(name = "aIsToB", nullable = false)
	private Integer aIsToB;
	
	@Column(name = "endType", nullable = false)
	private Integer endType;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	private Integer mar;//Is this the first marriage of the woman?
	private Integer tnbch;//Total Number of biological children
	private Integer nchdm;//Number of biological children from this marriage
	private Integer polygamous;//Are you in a polygamous marriage
	private Integer nwive;//Number of wives of husband(including you)
	private Integer lcow;//Does women live in the same household with co-wife(s)
	private Integer mrank;//Woman's rank (In current marriage)
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individual_uuid", referencedColumnName = "individual_uuid", insertable = false, updatable = false)
	private Individual individual;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "man_uuid", referencedColumnName = "individual_uuid", insertable = false, updatable = false)
	private Individual individuals;

	
	public Relationship() {}



	public String getRel_uuid() {
		return rel_uuid;
	}


	public void setRel_uuid(String rel_uuid) {
		this.rel_uuid = rel_uuid;
	}


	public String getIndividual_uuid() {
		return individual_uuid;
	}


	public void setIndividual_uuid(String individual_uuid) {
		this.individual_uuid = individual_uuid;
	}


	public String getMan_uuid() {
		return man_uuid;
	}


	public void setMan_uuid(String man_uuid) {
		this.man_uuid = man_uuid;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
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


	public Integer getaIsToB() {
		return aIsToB;
	}


	public void setaIsToB(Integer aIsToB) {
		this.aIsToB = aIsToB;
	}


	public Integer getEndType() {
		return endType;
	}


	public void setEndType(Integer endType) {
		this.endType = endType;
	}


	public String getFw_uuid() {
		return fw_uuid;
	}


	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}


	public Integer getMar() {
		return mar;
	}


	public void setMar(Integer mar) {
		this.mar = mar;
	}


	public Integer getTnbch() {
		return tnbch;
	}


	public void setTnbch(Integer tnbch) {
		this.tnbch = tnbch;
	}


	public Integer getNchdm() {
		return nchdm;
	}


	public void setNchdm(Integer nchdm) {
		this.nchdm = nchdm;
	}


	public Integer getPolygamous() {
		return polygamous;
	}


	public void setPolygamous(Integer polygamous) {
		this.polygamous = polygamous;
	}


	public Integer getNwive() {
		return nwive;
	}


	public void setNwive(Integer nwive) {
		this.nwive = nwive;
	}


	public Integer getLcow() {
		return lcow;
	}


	public void setLcow(Integer lcow) {
		this.lcow = lcow;
	}


	public Integer getMrank() {
		return mrank;
	}


	public void setMrank(Integer mrank) {
		this.mrank = mrank;
	}


	@Override
	public String toString() {
		return rel_uuid;
	}


}
