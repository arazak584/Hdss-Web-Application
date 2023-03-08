package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="relationship")
public class Relationship {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "extIdB", nullable = false)
	private String extIdB;
	
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
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	private Integer mar;//Is this the first marriage of the woman?
	private Integer tnbch;//Total Number of biological children
	private Integer nchdm;//Number of biological children from this marriage
	private Integer polygamous;//Are you in a polygamous marriage
	private Integer nwive;//Number of wives of husband(including you)
	private Integer lcow;//Does women live in the same household with co-wife(s)
	private Integer mrank;//Woman's rank (In current marriage)

	
	public Relationship() {}

	public Relationship(String uuid, String extId, String extIdB, Date insertDate, Date startDate, Date endDate,
			Integer aIsToB, Integer endType, String fw, Integer mar, Integer tnbch, Integer nchdm, Integer polygamous,
			Integer nwive, Integer lcow, Integer mrank) {
		super();
		this.uuid = uuid;
		this.extId = extId;
		this.extIdB = extIdB;
		this.insertDate = insertDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.aIsToB = aIsToB;
		this.endType = endType;
		this.fw = fw;
		this.mar = mar;
		this.tnbch = tnbch;
		this.nchdm = nchdm;
		this.polygamous = polygamous;
		this.nwive = nwive;
		this.lcow = lcow;
		this.mrank = mrank;
	}






	public String getExtId() {
		return extId;
	}


	public void setExtId(String extId) {
		this.extId = extId;
	}


	public String getExtIdB() {
		return extIdB;
	}


	public void setExtIdB(String extIdB) {
		this.extIdB = extIdB;
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
	
	
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public Integer getEndType() {
		return endType;
	}


	public void setEndType(Integer endType) {
		this.endType = endType;
	}


	public String getFw() {
		return fw;
	}


	public void setFw(String fw) {
		this.fw = fw;
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

	//@Override
    //public String toString() {
    //    return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
