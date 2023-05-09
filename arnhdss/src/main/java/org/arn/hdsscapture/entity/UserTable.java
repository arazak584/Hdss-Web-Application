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
import javax.validation.constraints.Size;

import org.jetbrains.annotations.NotNull;

@Entity
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

	@Column(nullable = false, length = 80)
	private String userEmail;

	@Column(nullable = false, length = 80)
	private String userFname;

	@Column(nullable = false, length = 80)
	private String userLname;
	
	@Column(nullable = false)
	private boolean userEnabled;

	@Column(nullable = false, length = 80)
	private String userPassword;

	@Transient
	private String curPassword;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "groupRole"))
	private List<GroupTable> groups = new ArrayList<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFname() {
		return userFname;
	}

	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}

	public String getUserLname() {
		return userLname;
	}

	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}

	public boolean isUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getCurPassword() {
		return curPassword;
	}

	public void setCurPassword(String curPassword) {
		this.curPassword = curPassword;
	}

	public List<GroupTable> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupTable> groups) {
		this.groups = groups;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userEmail);
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
		return Objects.equals(userEmail, other.userEmail);
	}

	@Override
	public String toString() {
		return username;
	}


}
