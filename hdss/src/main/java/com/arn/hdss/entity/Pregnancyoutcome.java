package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pregnancyoutcome")
public class Pregnancyoutcome {
	
	@Id
	private String uuid;
	
	@Column(name = "mother", nullable = false)
	private String mother;
	
	@Column(name = "father", nullable = false)
	private String father;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "outcomeDate", nullable = false)
	private Date outcomeDate;
	
	@Column(name = "conceptionDate")
	private Date conceptionDate;
	
	@Column(name = "childEverBorn", nullable = false)
	private Integer childEverBorn;
	
	@Column(name = "numberOfLiveBirths", nullable = false)
	private Integer numberOfLiveBirths;
	
	@Column(name = "b_place")
	private Integer b_place;
	
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
	//Which health facility
	private Integer whlth_fac;
	private String whlth_fac_Other;
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
	private Integer weig_hcard;

	
	@Column(name = "visitid", nullable = false)
	private String visitid;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	public Pregnancyoutcome() {}



	public Pregnancyoutcome(String uuid, String mother, String father, Date insertDate,
			Date outcomeDate, Date conceptionDate, Integer childEverBorn, Integer numberOfLiveBirths,
			Integer b_place, Integer not_del, String not_del_other, Integer ass_del, String ass_del_other,
			Integer how_del, String how_del_other, Integer first_nb, Integer l_birth, Integer rec_anc,
			Integer where_anc, String where_anc_Other, Integer whlth_fac, String whlth_fac_Other, Integer who_anc,
			Integer month_pg, Integer num_anc, Integer why_no_anc, Integer rec_ipt, Integer first_rec, Integer many_ipt,
			Integer chd_weight, Integer chd_size, Integer weig_hcard, String visitid, String fw) {
		super();
		this.uuid = uuid;
		this.mother = mother;
		this.father = father;
		this.insertDate = insertDate;
		this.outcomeDate = outcomeDate;
		this.conceptionDate = conceptionDate;
		this.childEverBorn = childEverBorn;
		this.numberOfLiveBirths = numberOfLiveBirths;
		this.b_place = b_place;
		this.not_del = not_del;
		this.not_del_other = not_del_other;
		this.ass_del = ass_del;
		this.ass_del_other = ass_del_other;
		this.how_del = how_del;
		this.how_del_other = how_del_other;
		this.first_nb = first_nb;
		this.l_birth = l_birth;
		this.rec_anc = rec_anc;
		this.where_anc = where_anc;
		this.where_anc_Other = where_anc_Other;
		this.whlth_fac = whlth_fac;
		this.whlth_fac_Other = whlth_fac_Other;
		this.who_anc = who_anc;
		this.month_pg = month_pg;
		this.num_anc = num_anc;
		this.why_no_anc = why_no_anc;
		this.rec_ipt = rec_ipt;
		this.first_rec = first_rec;
		this.many_ipt = many_ipt;
		this.chd_weight = chd_weight;
		this.chd_size = chd_size;
		this.weig_hcard = weig_hcard;
		this.visitid = visitid;
		this.fw = fw;
	}


	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	public String getMother() {
		return mother;
	}



	public void setMother(String mother) {
		this.mother = mother;
	}



	public String getFather() {
		return father;
	}



	public void setFather(String father) {
		this.father = father;
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

	public Integer getChildEverBorn() {
		return childEverBorn;
	}



	public void setChildEverBorn(Integer childEverBorn) {
		this.childEverBorn = childEverBorn;
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



	public Integer getWhlth_fac() {
		return whlth_fac;
	}



	public void setWhlth_fac(Integer whlth_fac) {
		this.whlth_fac = whlth_fac;
	}



	public String getWhlth_fac_Other() {
		return whlth_fac_Other;
	}



	public void setWhlth_fac_Other(String whlth_fac_Other) {
		this.whlth_fac_Other = whlth_fac_Other;
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



	public Integer getChd_size() {
		return chd_size;
	}



	public void setChd_size(Integer chd_size) {
		this.chd_size = chd_size;
	}



	public Integer getWeig_hcard() {
		return weig_hcard;
	}



	public void setWeig_hcard(Integer weig_hcard) {
		this.weig_hcard = weig_hcard;
	}



	public String getVisitid() {
		return visitid;
	}



	public void setVisitid(String visitid) {
		this.visitid = visitid;
	}



	public String getFw() {
		return fw;
	}



	public void setFw(String fw) {
		this.fw = fw;
	}



	@Override
	public String toString() {
		return uuid;
	}


	//@Override
    //public String toString() {
    //    return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
