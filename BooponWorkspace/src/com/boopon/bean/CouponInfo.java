package com.boopon.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.boopon.util.BooponUtils;

@Entity
@Table(name = "coupon_info")
public class CouponInfo {
	@Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "guid")
	private String guid;
	@Column(name = "serial_Number")
	private String serialNumber;
	@Column(name = "company")
	private String company;
	@Column(name = "type")
	private String type;
	@Column(name = "discount")
	private String discount;
	@Column(name = "valid_Date")
	private String validDate;
	@Column(name = "vote_Up")
	private Integer voteUp;
	@Column(name = "vote_Down")
	private Integer voteDown;
	@Column(name = "used")
	private Integer used;
	@Column(name = "limitPrice")
	private Integer limitPrice;
	
	@Transient
	private Integer messageNum;
	@Transient
	private String rate;
	
	
	
	public Integer getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(Integer limitPrice) {
		this.limitPrice = limitPrice;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}


	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public Integer getVoteUp() {
		return voteUp;
	}

	public void setVoteUp(Integer voteUp) {
		this.voteUp = voteUp;
	}

	public Integer getVoteDown() {
		return voteDown;
	}

	public void setVoteDown(Integer voteDown) {
		this.voteDown = voteDown;
	}
	@Transient
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Transient
	public Integer getMessageNum() {
		return messageNum;
	}


	public void setMessageNum(Integer messageNum) {
		this.messageNum = messageNum;
	}

	
}
