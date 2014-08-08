package com.boopon.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "coupon_message")
public class CouponMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "coupon_serial_number")
	private String coupon_serial_number;
	@Id
	@Column(name = "coupon_message")
	private String coupon_message;
	@Id
	@Column(name = "message_date")
	private String message_date;
	public String getCoupon_serial_number() {
		return coupon_serial_number;
	}
	public void setCoupon_serial_number(String coupon_serial_number) {
		this.coupon_serial_number = coupon_serial_number;
	}
	public String getCoupon_message() {
		return coupon_message;
	}
	public void setCoupon_message(String coupon_message) {
		this.coupon_message = coupon_message;
	}
	public String getMessage_date() {
		return message_date;
	}
	public void setMessage_date(String message_date) {
		this.message_date = message_date;
	}

}
