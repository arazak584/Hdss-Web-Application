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

	@Override
	public String toString() {
		return uuid;
	}


}
