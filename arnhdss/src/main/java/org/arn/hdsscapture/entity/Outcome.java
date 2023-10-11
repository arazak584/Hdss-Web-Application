package org.arn.hdsscapture.entity;

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
@Table(name="outcome")
public class Outcome {
	
	@Id
	private String uuid;
	
	//Child permid
	@Column(name = "childuuid")
	private String childuuid;
	
	@Column
	private String preg_uuid;
	
	@Column(name = "mother_uuid", nullable = false)
	private String mother_uuid;
	
	@Column(name = "type", nullable = false)
	private Integer type;
	
	//Was the child weighed at birth?
	private Integer chd_weight;
	//How much did the child weigh (estimated baby size)
	private Integer chd_size;
	//Record weight in kilograms from Health Card
	private String weig_hcard;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "preg_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	private Pregnancyoutcome pregnancyoutcome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mother_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	private Individual individual;
	
	
	public Outcome() {}


	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getChilduuid() {
		return childuuid;
	}

	public void setChilduuid(String childuuid) {
		this.childuuid = childuuid;
	}


	public String getPreg_uuid() {
		return preg_uuid;
	}

	public void setPreg_uuid(String preg_uuid) {
		this.preg_uuid = preg_uuid;
	}

	public String getMother_uuid() {
		return mother_uuid;
	}

	public void setMother_uuid(String mother_uuid) {
		this.mother_uuid = mother_uuid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	

	public Integer getChd_weight() {
		return chd_weight;
	}

	public Integer getChd_size() {
		return chd_size;
	}

	public void setChd_size(Integer chd_size) {
		this.chd_size = chd_size;
	}

	public String getWeig_hcard() {
		return weig_hcard;
	}

	public void setWeig_hcard(String weig_hcard) {
		this.weig_hcard = weig_hcard;
	}

	public void setChd_weight(Integer chd_weight) {
		this.chd_weight = chd_weight;
	}
	

	@Override
	public String toString() {
		return uuid;
	}


}
