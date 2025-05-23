package org.arn.hdsscapture.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;


@Entity
@Audited
@Table(name="locationhierarchylevel")
public class Locationhierarchylevel {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "keyIdentifier", nullable = false)
	private Integer keyIdentifier;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "locationhierarchylevel")
	private List<Locationhierarchy> locationhierarchies = new ArrayList<>();

	
	public Locationhierarchylevel () {}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getKeyIdentifier() {
		return keyIdentifier;
	}

	public void setKeyIdentifier(Integer keyIdentifier) {
		this.keyIdentifier = keyIdentifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return uuid;
	}

}
