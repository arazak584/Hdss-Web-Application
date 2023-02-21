package com.arn.hdss.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.envers.Audited;

@Audited
@Entity
public class Biodata implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String mRecordid;

	@Column
	private Date mDob;

	@Column
	private String mHdss;

	@Column
	private String mNhis;

	@Column
	private String mGhcard;

	@Column
	private Integer mFacid;

	@Column
	private String mName;

	@Column
	private String mCompid;

	@Column
	private String mPhone1;

	@Column
	private String mPhone2;

	@Column
	private int mStatus;

	@Column
	private String mSubjid;

	public String getmRecordid() {
		return mRecordid;
	}

	public void setmRecordid(String mRecordid) {
		this.mRecordid = mRecordid;
	}

	public Date getmDob() {
		return mDob;
	}

	public void setmDob(Date mDob) {
		this.mDob = mDob;
	}

	public String getmNhis() {
		return mNhis;
	}

	public void setmNhis(String mNhis) {
		this.mNhis = mNhis;
	}

	public String getmGhcard() {
		return mGhcard;
	}

	public void setmGhcard(String mGhcard) {
		this.mGhcard = mGhcard;
	}

	public Integer getmFacid() {
		return mFacid;
	}

	public void setmFacid(Integer mFacid) {
		this.mFacid = mFacid;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmHdss() {
		return mHdss;
	}

	public void setmHdss(String mHdss) {
		this.mHdss = mHdss;
	}

	public int getmStatus() {
		return mStatus;
	}

	public void setmStatus(int mStatus) {
		this.mStatus = mStatus;
	}

	public String getmSubjid() {
		return mSubjid;
	}

	public void setmSubjid(String mSubjid) {
		this.mSubjid = mSubjid;
	}

	public String getmCompid() {
		return mCompid;
	}

	public void setmCompid(String mCompid) {
		this.mCompid = mCompid;
	}

	public String getmPhone1() {
		return mPhone1;
	}

	public void setmPhone1(String mPhone1) {
		this.mPhone1 = mPhone1;
	}

	public String getmPhone2() {
		return mPhone2;
	}

	public void setmPhone2(String mPhone2) {
		this.mPhone2 = mPhone2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mRecordid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Biodata other = (Biodata) obj;
		return Objects.equals(mRecordid, other.mRecordid);
	}

	@Override
	public String toString() {
		return mRecordid;
	}

}
