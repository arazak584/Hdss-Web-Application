package org.arn.hdsscapture.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;



@Entity
@Audited
@Table(name="locationhierarchy", indexes = {@Index(name="idx_hierarchy_uuid", columnList="uuid")})
public class Locationhierarchy implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "extId", nullable = false, unique=true)
	private String extId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "town")
	private String town;
	
	@Column(name = "area")
	private String area;
	
	@Column(name = "parent_uuid")
	private String parent_uuid;
	
	@Column(name = "level_uuid")
	private String level_uuid;
	
	@Column(nullable = true)
    public String fw_name;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_name", referencedColumnName = "username", insertable = false, updatable = false)
	private Fieldworker fieldworker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "level_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
	private Locationhierarchylevel locationhierarchylevel;
	
	@OneToMany(mappedBy = "locationhierarchy", cascade = CascadeType.ALL)
    private Set<Locationhierarchy> locationhierarchies = new HashSet<>();	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
    private Locationhierarchy locationhierarchy;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "locationhierarchy")
	private List<Location> locations = new ArrayList<>();
	
	public Locationhierarchy() {}


	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getExtId() {
		return extId;
	}


	public void setExtId(String extId) {
		this.extId = extId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	
	public String getParent_uuid() {
		return parent_uuid;
	}

	public void setParent_uuid(String parent_uuid) {
		this.parent_uuid = parent_uuid;
	}

	public String getLevel_uuid() {
		return level_uuid;
	}

	public void setLevel_uuid(String level_uuid) {
		this.level_uuid = level_uuid;
	}
	
	
	public String getFw_name() {
		return fw_name;
	}


	public void setFw_name(String fw_name) {
		this.fw_name = fw_name;
	}


	@Override
	public String toString() {
		return extId;
	}

	
}
