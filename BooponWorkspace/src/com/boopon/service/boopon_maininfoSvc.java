package com.boopon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boopon.bean.CouponCompany;
import com.boopon.bean.CouponInfo;
import com.boopon.bean.CouponMessage;
import com.boopon.bean.json.CouponInfoRequest;
import com.boopon.persistence.boopon_maininfoDAO;

@Service("mainInfoSvc")
public class boopon_maininfoSvc {
	@Autowired
	private boopon_maininfoDAO mainInfoDAO;

	public void setMainInfoDAO(boopon_maininfoDAO mainInfoDAO) {
		this.mainInfoDAO = mainInfoDAO;
	}

	public CouponInfo getBooponMaininfo(String id) {
		return mainInfoDAO.getMainInfo(id);
	}

	public void addMessage(CouponInfoRequest info) {
		 mainInfoDAO.addMessage(info);
	}

	public String thumbUp(String serialNum) {
		return mainInfoDAO.thumbUp(serialNum);
	}

	public String thumbDown(String serialNum) {
		return mainInfoDAO.thumbDown(serialNum);
	}

	public void saveNewCoupon(CouponInfoRequest info) {
		mainInfoDAO.saveNewCoupon(info);
	}

	public void reportUsed(String serialNum) {
		 mainInfoDAO.reportUsed(serialNum);
	}

	public List<CouponInfo> getAllCoupon() {
		return mainInfoDAO.getAllCoupon();
	}

	public List <CouponInfo> searchCoupon(String serialNum) {
		return mainInfoDAO.SearchCoupon(serialNum);
	}

	public List<CouponMessage> searchMsg(String guid) {
		return mainInfoDAO.getMessageList(guid);
	}

	public List<CouponCompany> getCompanyList() {
		return mainInfoDAO.getCompanyList();
	}

	public Long getVisitorNum() {
		return mainInfoDAO.getVisitorNum();
	}

	public void saveVisitorNum(Long num) {
		 mainInfoDAO.saveVisitorNum(num);
	}

	public List<CouponInfo> searchSerial(String serialNum) {
		return mainInfoDAO.searchSerial(serialNum);
	}


}