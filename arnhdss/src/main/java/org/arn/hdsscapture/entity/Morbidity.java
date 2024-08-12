package org.arn.hdsscapture.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Audited
@Table(name="morbidity", indexes = {@Index(name="individual_uuid", columnList="individual_uuid", unique=false)})
public class Morbidity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "individual_uuid", nullable = false)
	private String individual_uuid;
	
	@Column(name = "socialgroup_uuid", nullable = false)
	private String socialgroup_uuid;
	
	@Column(name = "location_uuid", nullable = false)
	private String location_uuid;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date insertDate;
	
    @Column(nullable = false)
    public String fw_uuid;

    @Column(nullable = true)
    private String ind_name;
    @Column(nullable = true)
    private Integer fever_days;
    @Column(nullable = true)
    private Integer fever_treat;
    
    
    @Column(nullable = false)
    public Integer fever;
    
    @Column(nullable = false)
    public Integer hypertension;
    @Column(nullable = true)
    public Integer hypertension_dur;
    @Column(nullable = true)
    public Integer hypertension_trt;
    
    @Column(nullable = false)
    public Integer diabetes;
    @Column(nullable = true)
    public Integer diabetes_dur;
    @Column(nullable = true)
    public Integer diabetes_trt;
    
    @Column(nullable = true)
    public Integer heart;
    @Column(nullable = true)
    public Integer heart_dur;
    @Column(nullable = true)
    public Integer heart_trt;
    
    @Column(nullable = false)
    public Integer stroke;
    @Column(nullable = true)
    public Integer stroke_dur;
    @Column(nullable = true)
    public Integer stroke_trt;
    
    @Column(nullable = false)
    public Integer sickle;
    @Column(nullable = true)
    public Integer sickle_dur;
    @Column(nullable = true)
    public Integer sickle_trt;
    
    @Column(nullable = false)
    public Integer asthma;
    @Column(nullable = true)
    public Integer asthma_dur;
    @Column(nullable = true)
    public Integer asthma_trt;
    
    @Column(nullable = false)
    public Integer epilepsy;
    @Column(nullable = true)
    public Integer epilepsy_dur;
    @Column(nullable = true)
    public Integer epilepsy_trt;
    @Column(name = "comment", nullable = true, length = 1000)
	private String comment;
	
	@Column(name = "status", nullable = false)
	private Integer status = 0;
	
	@Column(name = "supervisor", nullable = true)
	private String supervisor;
	
	@Column(name = "approveDate", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date approveDate;
	
	@Column(name = "fw_name", nullable = true)
	private String fw_name;
	
	@Column(name = "compno", nullable = true)
	private String compno;
    
   
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individual_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Individual individual;    
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
	@NotAudited
	private Location location;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "socialgroup_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Socialgroup socialgroup;
    
//    @OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "socialgroup_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
//	@NotAudited
//	private Socialgroup socialgroup;
    
    public Morbidity() {}

	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getIndividual_uuid() {
		return individual_uuid;
	}


	public void setIndividual_uuid(String individual_uuid) {
		this.individual_uuid = individual_uuid;
	}


	public String getSocialgroup_uuid() {
		return socialgroup_uuid;
	}


	public void setSocialgroup_uuid(String socialgroup_uuid) {
		this.socialgroup_uuid = socialgroup_uuid;
	}


	public String getLocation_uuid() {
		return location_uuid;
	}


	public void setLocation_uuid(String location_uuid) {
		this.location_uuid = location_uuid;
	}

	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public String getFw_uuid() {
		return fw_uuid;
	}


	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}



	public String getInd_name() {
		return ind_name;
	}


	public void setInd_name(String ind_name) {
		this.ind_name = ind_name;
	}








	public Integer getFever_days() {
		return fever_days;
	}








	public void setFever_days(Integer fever_days) {
		this.fever_days = fever_days;
	}








	public Integer getFever_treat() {
		return fever_treat;
	}








	public void setFever_treat(Integer fever_treat) {
		this.fever_treat = fever_treat;
	}








	public Integer getFever() {
		return fever;
	}








	public void setFever(Integer fever) {
		this.fever = fever;
	}








	public Integer getHypertension() {
		return hypertension;
	}








	public void setHypertension(Integer hypertension) {
		this.hypertension = hypertension;
	}








	public Integer getHypertension_dur() {
		return hypertension_dur;
	}








	public void setHypertension_dur(Integer hypertension_dur) {
		this.hypertension_dur = hypertension_dur;
	}








	public Integer getHypertension_trt() {
		return hypertension_trt;
	}








	public void setHypertension_trt(Integer hypertension_trt) {
		this.hypertension_trt = hypertension_trt;
	}








	public Integer getDiabetes() {
		return diabetes;
	}








	public void setDiabetes(Integer diabetes) {
		this.diabetes = diabetes;
	}








	public Integer getDiabetes_dur() {
		return diabetes_dur;
	}








	public void setDiabetes_dur(Integer diabetes_dur) {
		this.diabetes_dur = diabetes_dur;
	}








	public Integer getDiabetes_trt() {
		return diabetes_trt;
	}








	public void setDiabetes_trt(Integer diabetes_trt) {
		this.diabetes_trt = diabetes_trt;
	}








	public Integer getHeart() {
		return heart;
	}








	public void setHeart(Integer heart) {
		this.heart = heart;
	}








	public Integer getHeart_dur() {
		return heart_dur;
	}








	public void setHeart_dur(Integer heart_dur) {
		this.heart_dur = heart_dur;
	}








	public Integer getHeart_trt() {
		return heart_trt;
	}








	public void setHeart_trt(Integer heart_trt) {
		this.heart_trt = heart_trt;
	}








	public Integer getStroke() {
		return stroke;
	}








	public void setStroke(Integer stroke) {
		this.stroke = stroke;
	}








	public Integer getStroke_dur() {
		return stroke_dur;
	}








	public void setStroke_dur(Integer stroke_dur) {
		this.stroke_dur = stroke_dur;
	}








	public Integer getStroke_trt() {
		return stroke_trt;
	}








	public void setStroke_trt(Integer stroke_trt) {
		this.stroke_trt = stroke_trt;
	}








	public Integer getSickle() {
		return sickle;
	}








	public void setSickle(Integer sickle) {
		this.sickle = sickle;
	}








	public Integer getSickle_dur() {
		return sickle_dur;
	}








	public void setSickle_dur(Integer sickle_dur) {
		this.sickle_dur = sickle_dur;
	}








	public Integer getSickle_trt() {
		return sickle_trt;
	}








	public void setSickle_trt(Integer sickle_trt) {
		this.sickle_trt = sickle_trt;
	}








	public Integer getAsthma() {
		return asthma;
	}








	public void setAsthma(Integer asthma) {
		this.asthma = asthma;
	}








	public Integer getAsthma_dur() {
		return asthma_dur;
	}








	public void setAsthma_dur(Integer asthma_dur) {
		this.asthma_dur = asthma_dur;
	}








	public Integer getAsthma_trt() {
		return asthma_trt;
	}








	public void setAsthma_trt(Integer asthma_trt) {
		this.asthma_trt = asthma_trt;
	}








	public Integer getEpilepsy() {
		return epilepsy;
	}








	public void setEpilepsy(Integer epilepsy) {
		this.epilepsy = epilepsy;
	}








	public Integer getEpilepsy_dur() {
		return epilepsy_dur;
	}








	public void setEpilepsy_dur(Integer epilepsy_dur) {
		this.epilepsy_dur = epilepsy_dur;
	}








	public Integer getEpilepsy_trt() {
		return epilepsy_trt;
	}








	public void setEpilepsy_trt(Integer epilepsy_trt) {
		this.epilepsy_trt = epilepsy_trt;
	}








	public String getComment() {
		return comment;
	}








	public void setComment(String comment) {
		this.comment = comment;
	}








	public Integer getStatus() {
		return status;
	}








	public void setStatus(Integer status) {
		this.status = status;
	}








	public String getSupervisor() {
		return supervisor;
	}








	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}








	public Date getApproveDate() {
		return approveDate;
	}








	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}








	public String getFw_name() {
		return fw_name;
	}








	public void setFw_name(String fw_name) {
		this.fw_name = fw_name;
	}








	public String getCompno() {
		return compno;
	}








	public void setCompno(String compno) {
		this.compno = compno;
	}








	@Override
	public String toString() {
		return individual_uuid;
	}

}
