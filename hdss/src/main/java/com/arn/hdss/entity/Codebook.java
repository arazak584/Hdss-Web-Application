package com.arn.hdss.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="codebook")
public class Codebook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "codeFeature", nullable = false)
	private String codeFeature;
		
	@Column(name = "codeValue", nullable = false)
	private Integer codeValue;
	
	@Column(name = "codeLabel", nullable = false)
	private String codeLabel;
	
	
	public Codebook() {}

	
	public Codebook(Integer id, String codeFeature, Integer codeValue, String codeLabel) {
		super();
		this.id = id;
		this.codeFeature = codeFeature;
		this.codeValue = codeValue;
		this.codeLabel = codeLabel;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCodeFeature() {
		return codeFeature;
	}


	public void setCodeFeature(String codeFeature) {
		this.codeFeature = codeFeature;
	}


	public Integer getCodeValue() {
		return codeValue;
	}


	public void setCodeValue(Integer codeValue) {
		this.codeValue = codeValue;
	}


	public String getCodeLabel() {
		return codeLabel;
	}


	public void setCodeLabel(String codeLabel) {
		this.codeLabel = codeLabel;
	}



}
