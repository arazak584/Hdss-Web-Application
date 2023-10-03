package org.arn.hdsscapture.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Audited
@Table(name="pregnancyoutcome")
public class Pregnancyoutcome {
	
	@Id
	private String uuid;
	
	@Column(name = "mother_uuid", nullable = false)
	private String mother_uuid;
	
	@Column(name = "father_uuid", nullable = false)
	private String father_uuid;
	
	@Column(name = "insertDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insertDate;
	
	@Column(name = "outcomeDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date outcomeDate;
	
	@Column(name = "conceptionDate", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date conceptionDate;
	
	@Column(name = "numberofBirths", nullable = false)
	private Integer numberofBirths; //number of outcomes from specific pregnancy
	
	@Column(name = "numberOfLiveBirths", nullable = false)
	private Integer numberOfLiveBirths;//number of live outcomes from specific pregnancy
	
	@Column(name = "b_place")
	private Integer b_place;
	
	@Column(name = "sttime")
	private String sttime;

	@Column(name = "edtime")
	private String edtime;
	
	//Why was child not delivered at health facility
	private Integer not_del;
	
	//Other
	private String not_del_other;
	//Who assisted you during deivery?
	private Integer ass_del;
	//Other, Specify
	private String ass_del_other;
	//How was the child delivered?
	private Integer how_del;
	//Other, Specify
	private String how_del_other;
	//Is this your first live birth
	private Integer first_nb;
	//How many previous live births have you had?
	private Integer l_birth;
	//During the time that you were pregnant, did you receive any Antenatal Care?
	private Integer rec_anc;
	//Where did you receive the ANC?
	private Integer where_anc;
	private String where_anc_Other;
	//Who attended to you?
	private Integer who_anc;
	//How many months pregnant were you when you first received  Antenatal Care?
	private Integer month_pg;
	//How many ANC visits did you make before you delivered?
	private Integer num_anc;
	//Why, No?
	private Integer why_no_anc;
	//During the time that you were pregnant, did you receive IPT infront of a nurse?
	private Integer rec_ipt;
	//How many months pregnant were you when you first received  IPT?
	private Integer first_rec;
	//How many times did you take IPT in front of a nurse during the pregnancy?
	private Integer many_ipt;
	//Was the child weighed at birth?
	private Integer chd_weight;
	//How much did the child weigh (estimated baby size)
	private Integer chd_size;
	//Record weight in kilograms from Health Card
	private String weig_hcard;

	
	@Column(name = "visit_uuid", nullable = false)
	private String visit_uuid;
	
	@Column(name = "fw_uuid", nullable = false)
	private String fw_uuid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mother_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Individual individual;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "father_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Individual individuals;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_uuid", referencedColumnName = "uuid", insertable = false, updatable = false, nullable=false)
	@NotAudited
	private Visit visit;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "pregnancyoutcome")
	private List<Outcome> outcomes = new ArrayList<>();
	
	
	
	public Pregnancyoutcome() {}


	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getVisit_uuid() {
		return visit_uuid;
	}


	public void setVisit_uuid(String visit_uuid) {
		this.visit_uuid = visit_uuid;
	}


	public String getMother_uuid() {
		return mother_uuid;
	}



	public void setMother_uuid(String mother_uuid) {
		this.mother_uuid = mother_uuid;
	}



	public String getFather_uuid() {
		return father_uuid;
	}



	public void setFather_uuid(String father_uuid) {
		this.father_uuid = father_uuid;
	}



	public Date getInsertDate() {
		return insertDate;
	}



	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}



	public Date getOutcomeDate() {
		return outcomeDate;
	}



	public void setOutcomeDate(Date outcomeDate) {
		this.outcomeDate = outcomeDate;
	}



	public Date getConceptionDate() {
		return conceptionDate;
	}



	public void setConceptionDate(Date conceptionDate) {
		this.conceptionDate = conceptionDate;
	}



	public Integer getNumberofBirths() {
		return numberofBirths;
	}



	public void setNumberofBirths(Integer numberofBirths) {
		this.numberofBirths = numberofBirths;
	}
	

	public Integer getNumberOfLiveBirths() {
		return numberOfLiveBirths;
	}


	public void setNumberOfLiveBirths(Integer numberOfLiveBirths) {
		this.numberOfLiveBirths = numberOfLiveBirths;
	}


	public Integer getB_place() {
		return b_place;
	}



	public void setB_place(Integer b_place) {
		this.b_place = b_place;
	}



	public Integer getNot_del() {
		return not_del;
	}



	public void setNot_del(Integer not_del) {
		this.not_del = not_del;
	}



	public String getNot_del_other() {
		return not_del_other;
	}



	public void setNot_del_other(String not_del_other) {
		this.not_del_other = not_del_other;
	}



	public Integer getAss_del() {
		return ass_del;
	}



	public void setAss_del(Integer ass_del) {
		this.ass_del = ass_del;
	}



	public String getAss_del_other() {
		return ass_del_other;
	}



	public void setAss_del_other(String ass_del_other) {
		this.ass_del_other = ass_del_other;
	}



	public Integer getHow_del() {
		return how_del;
	}



	public void setHow_del(Integer how_del) {
		this.how_del = how_del;
	}



	public String getHow_del_other() {
		return how_del_other;
	}



	public void setHow_del_other(String how_del_other) {
		this.how_del_other = how_del_other;
	}



	public Integer getFirst_nb() {
		return first_nb;
	}



	public void setFirst_nb(Integer first_nb) {
		this.first_nb = first_nb;
	}



	public Integer getL_birth() {
		return l_birth;
	}



	public void setL_birth(Integer l_birth) {
		this.l_birth = l_birth;
	}



	public Integer getRec_anc() {
		return rec_anc;
	}



	public void setRec_anc(Integer rec_anc) {
		this.rec_anc = rec_anc;
	}



	public Integer getWhere_anc() {
		return where_anc;
	}



	public void setWhere_anc(Integer where_anc) {
		this.where_anc = where_anc;
	}



	public String getWhere_anc_Other() {
		return where_anc_Other;
	}



	public void setWhere_anc_Other(String where_anc_Other) {
		this.where_anc_Other = where_anc_Other;
	}


	public Integer getWho_anc() {
		return who_anc;
	}



	public void setWho_anc(Integer who_anc) {
		this.who_anc = who_anc;
	}



	public Integer getMonth_pg() {
		return month_pg;
	}



	public void setMonth_pg(Integer month_pg) {
		this.month_pg = month_pg;
	}



	public Integer getNum_anc() {
		return num_anc;
	}



	public void setNum_anc(Integer num_anc) {
		this.num_anc = num_anc;
	}



	public Integer getWhy_no_anc() {
		return why_no_anc;
	}



	public void setWhy_no_anc(Integer why_no_anc) {
		this.why_no_anc = why_no_anc;
	}



	public Integer getRec_ipt() {
		return rec_ipt;
	}



	public void setRec_ipt(Integer rec_ipt) {
		this.rec_ipt = rec_ipt;
	}



	public Integer getFirst_rec() {
		return first_rec;
	}



	public void setFirst_rec(Integer first_rec) {
		this.first_rec = first_rec;
	}



	public Integer getMany_ipt() {
		return many_ipt;
	}



	public void setMany_ipt(Integer many_ipt) {
		this.many_ipt = many_ipt;
	}



	public Integer getChd_weight() {
		return chd_weight;
	}



	public void setChd_weight(Integer chd_weight) {
		this.chd_weight = chd_weight;
	}

	public String getFw_uuid() {
		return fw_uuid;
	}



	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}



	public String getSttime() {
		return sttime;
	}

	public void setSttime(String sttime) {
		this.sttime = sttime;
	}

	public String getEdtime() {
		return edtime;
	}

	public void setEdtime(String edtime) {
		this.edtime = edtime;
	}

	public Integer getChd_size() {
		return chd_size;
	}

	public void setChd_size(Integer chd_size) {
		this.chd_size = chd_size;
	}

	public String getWeig_hcard() {
		return weig_hcard;
	}

	public void setWeig_hcard(String weig_hcard) {
		this.weig_hcard = weig_hcard;
	}

	@Override
	public String toString() {
		return uuid;
	}

}
