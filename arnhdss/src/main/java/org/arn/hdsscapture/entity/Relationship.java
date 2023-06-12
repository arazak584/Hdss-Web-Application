package org.arn.hdsscapture.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Audited
@Table(name="relationship")
public class Relationship {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "individualA_uuid", nullable = false)
	private String individualA_uuid;
	
	@Column(name = "individualB_uuid", nullable = false)
	private String individualB_uuid;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	@Column(name = "startDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@Column(name = "endDate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	@JoinColumn(name = "individualA_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
	@NotAudited
	private Individual individual;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individualB_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
	@NotAudited
	private Individual individuals;

	
	public Relationship() {}



	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	public String getIndividualA_uuid() {
		return individualA_uuid;
	}



	public void setIndividualA_uuid(String individualA_uuid) {
		this.individualA_uuid = individualA_uuid;
	}



	public String getIndividualB_uuid() {
		return individualB_uuid;
	}



	public void setIndividualB_uuid(String individualB_uuid) {
		this.individualB_uuid = individualB_uuid;
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
		return uuid;
	}


}
