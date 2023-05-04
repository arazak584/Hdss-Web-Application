package org.arn.hdsscapture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="locationhierarchylevel")
public class Locationhierarchylevel {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "keyIdentifier", nullable = false)
	private String keyIdentifier;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	public Locationhierarchylevel () {}

	public Locationhierarchylevel(String uuid, String keyIdentifier, String name) {
		super();
		this.uuid = uuid;
		this.keyIdentifier = keyIdentifier;
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getKeyIdentifier() {
		return keyIdentifier;
	}

	public void setKeyIdentifier(String keyIdentifier) {
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
