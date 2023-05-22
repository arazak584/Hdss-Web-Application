package org.arn.hdsscapture.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "group_table")
public class GroupTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, length = 80)
	private String group_role;

	@Column(nullable = false, length = 80)
	private String group_desc;

	@ManyToMany(mappedBy = "groups")
	private List<UserTable> users;

	

	public String getGroup_role() {
		return group_role;
	}

	public void setGroup_role(String group_role) {
		this.group_role = group_role;
	}

	public String getGroup_desc() {
		return group_desc;
	}

	public void setGroup_desc(String group_desc) {
		this.group_desc = group_desc;
	}

	public List<UserTable> getUsers() {
		return users;
	}

	public void setUsers(List<UserTable> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(group_role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupTable other = (GroupTable) obj;
		return Objects.equals(group_role, other.group_role);
	}

	@Override
	public String toString() {
		return group_role;
	}

}
