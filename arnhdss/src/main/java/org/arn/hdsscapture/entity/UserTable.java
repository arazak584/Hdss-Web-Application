package org.arn.hdsscapture.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name= "user_table")
public class UserTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "username")
	@Size(min = 1, max = 80)
	private String username;

	@Column(nullable = false, length = 80, unique = true)
	private String user_email;

	@Column(nullable = false, length = 80)
	private String user_fname;

	@Column(nullable = false, length = 80)
	private String user_lname;
	
	@Column(nullable = false)
	private boolean user_enabled;

	@Column(nullable = false, length = 80)
	private String user_password;

	@Transient
	private String cur_password;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "group_role"))
	private List<GroupTable> groups = new ArrayList<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_fname() {
		return user_fname;
	}

	public void setUser_fname(String user_fname) {
		this.user_fname = user_fname;
	}

	public String getUser_lname() {
		return user_lname;
	}

	public void setUser_lname(String user_lname) {
		this.user_lname = user_lname;
	}

	public boolean isUser_enabled() {
		return user_enabled;
	}

	public void setUser_enabled(boolean user_enabled) {
		this.user_enabled = user_enabled;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getCur_password() {
		return cur_password;
	}

	public void setCur_password(String cur_password) {
		this.cur_password = cur_password;
	}

	public List<GroupTable> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupTable> groups) {
		this.groups = groups;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user_email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTable other = (UserTable) obj;
		return Objects.equals(user_email, other.user_email);
	}

	@Override
	public String toString() {
		return username;
	}


}
