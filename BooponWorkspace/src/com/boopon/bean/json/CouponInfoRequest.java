package com.boopon.bean.json;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class CouponInfoRequest {
	private String serialNum;
	private String upOrDown;
	private String searchText;
	private String company;
	private String availableRate;
	private String price;
	private String validDate;
	private String type;
	private String message;
	private String limitPrice;
	
	
	public static final String THUMB_UP="UP";
	public static final String THUMB_DOWN="DOWN";

	public String getUpOrDown() {
		return upOrDown;
	}

	public void setUpOrDown(String upOrDown) {
		this.upOrDown = upOrDown;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAvailableRate() {
		return availableRate;
	}

	public void setAvailableRate(String availableRate) {
		this.availableRate = availableRate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(String limitPrice) {
		this.limitPrice = limitPrice;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new ReflectionToStringBuilder(this).toString();
	}

}
