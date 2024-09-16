package org.arn.hdsscapture.formapproval;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="approvals")
public class approvals implements Serializable {

	public static final long serialVersionUID = 3215948801546119135L;

	@Id
	private String id;
	
	@Column(name = "event", nullable = false)
	private String event;
	
	@Column(name = "supervisor", nullable = false)
	private String supervisor;
	
	@Column(name = "Number", nullable = false)
	private String Number;
	
	@Column(name = "Approved", nullable = false)
	private String Approved;
	
	@Column(name = "Rejected", nullable = false)
	private String Rejected;
	
	@Column(name = "Resolved", nullable = false)
	private String Resolved;
	
	public approvals() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getApproved() {
		return Approved;
	}

	public void setApproved(String approved) {
		Approved = approved;
	}

	public String getRejected() {
		return Rejected;
	}

	public void setRejected(String rejected) {
		Rejected = rejected;
	}

	public String getResolved() {
		return Resolved;
	}

	public void setResolved(String resolved) {
		Resolved = resolved;
	}
	
	
}
