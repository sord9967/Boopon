package com.boopon.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="boopon_maininfo")
public class BooponMaininfo {
    @Id
	private int id;
    @Column(name="serial_No")
	private String serialNo;

	public BooponMaininfo() {
	}

	public BooponMaininfo(int id) {
		this.id = id;
	}

	public BooponMaininfo(int id, String serialNo) {
		this.id = id;
		this.serialNo = serialNo;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

}
