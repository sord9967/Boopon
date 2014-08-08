package com.boopon.bean;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "coupon_info")
public class Parameters {
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
