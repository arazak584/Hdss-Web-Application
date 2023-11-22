package org.arn.hdsscapture.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Immutable
@Table(name="query")
public class Queries {
	
	@Id
	private String id;
	
	@Column(name = "id1", nullable = true)
	private String id1;
	
	@Column(name = "id2", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date id2;
	
	@Column(name = "id3", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date id3;
	
	@Column(name = "id4", nullable = true)
	private String id4;
	
	@Column(name = "id5", nullable = true)
	private String id5;
	
	@Column(name = "id6", nullable = true)
	private String id6;
	
	@Column(name = "id7", nullable = true)
	private String id7;
	
	@Column(name = "id8", nullable = true)
	private String id8;

	public Queries() {
	}

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public Date getId2() {
		return id2;
	}

	public void setId2(Date id2) {
		this.id2 = id2;
	}

	public Date getId3() {
		return id3;
	}

	public void setId3(Date id3) {
		this.id3 = id3;
	}

	public String getId4() {
		return id4;
	}

	public void setId4(String id4) {
		this.id4 = id4;
	}

	public String getId5() {
		return id5;
	}

	public void setId5(String id5) {
		this.id5 = id5;
	}

	public String getId6() {
		return id6;
	}

	public void setId6(String id6) {
		this.id6 = id6;
	}

	public String getId7() {
		return id7;
	}

	public void setId7(String id7) {
		this.id7 = id7;
	}

	public String getId8() {
		return id8;
	}

	public void setId8(String id8) {
		this.id8 = id8;
	}

	

}
