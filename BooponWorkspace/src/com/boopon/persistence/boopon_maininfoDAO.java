package com.boopon.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.boopon.bean.CouponCompany;
import com.boopon.bean.CouponInfo;
import com.boopon.bean.CouponMessage;
import com.boopon.bean.CouponVisitor;
import com.boopon.bean.json.CouponInfoRequest;
import com.boopon.util.BooponUtils;
@Repository("mainInfoDAO")//This the id by which it will be auto wired
public class boopon_maininfoDAO extends BaseDAO{
		private Date date = new Date();
		private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
      public CouponInfo getMainInfo(String id){
           return null;
      }

	public void saveNewCoupon(CouponInfoRequest info) {
		CouponInfo ci = new CouponInfo();
		ci.setSerialNumber(info.getSerialNum());
		ci.setType(info.getType());
		ci.setCompany(info.getCompany());
		ci.setValidDate(info.getValidDate());
		ci.setDiscount(info.getPrice());
		ci.setVoteUp(0);
		ci.setVoteDown(0);
		ci.setLimitPrice(Integer.parseInt(info.getLimitPrice()));
		Session session =  getSession();
		session.beginTransaction();
		session.save(ci);
		session.getTransaction().commit();
		session.flush();
		session.close();
	}

	public List<CouponInfo> getAllCoupon() {
		return getSession().createCriteria(CouponInfo.class).add(Restrictions.isNull("used")).add(Restrictions.gt("validDate",sf.format(date)) ).addOrder(Order.asc("validDate") ).list();
	}

	public String thumbUp(String serialNum) {
		  Session session =  getSession();
		  session.beginTransaction();
		  Query query = session.createQuery( "from CouponInfo where serial_Number = :serialNum" );
		  query.setString( "serialNum", serialNum );
		  CouponInfo ci  =(CouponInfo) query.uniqueResult();
		  ci.setVoteUp(ci.getVoteUp()+1);
		  session.save(ci);
		  session.getTransaction().commit();
		  session.flush();
		  session.close();
		  return BooponUtils.getAvaliableRate(ci.getVoteUp(),ci.getVoteDown());
	}

	public String thumbDown(String serialNum) {
		  Session session =  getSession();
		  session.beginTransaction();
		  Query query = session.createQuery( "from CouponInfo where serial_Number = :serialNum" );
		  query.setString( "serialNum", serialNum );
		  CouponInfo ci  =(CouponInfo) query.uniqueResult();
		  ci.setVoteDown(ci.getVoteDown()+1);
		  session.save(ci);
		  session.getTransaction().commit();
		  session.flush();
		  session.close();
		  return BooponUtils.getAvaliableRate(ci.getVoteUp(),ci.getVoteDown());
	}

	public List<CouponInfo>  SearchCoupon(String companyName) {
		  Session session =  getSession();
		  Query query = session.createQuery( "from CouponInfo where company = :company and validDate >:validDate and used is null order by validDate" );
		  query.setString( "company", companyName );
		  query.setString( "validDate", sf.format(date) );
		  List<CouponInfo> ci  = query.list();
		return ci ;
	}

	public void reportUsed(String serialNum) {
		  Session session =  getSession();
		  session.beginTransaction();
		  Query query = session.createQuery( "from CouponInfo where serial_Number = :serialNum" );
		  query.setString( "serialNum", serialNum );
		  CouponInfo ci  =(CouponInfo) query.uniqueResult();
		  ci.setUsed(1);
		  session.save(ci);
		  session.getTransaction().commit();
		  session.flush();
		  session.close();
	}

	public List<CouponCompany> getCompanyList() {
		return getSession().createCriteria(CouponCompany.class).list();
	}

	public void addMessage(CouponInfoRequest info) {
		CouponMessage cm = new CouponMessage();
		cm.setCoupon_serial_number(info.getSerialNum());
		cm.setCoupon_message(info.getMessage());
		Session session =  getSession();
		session.beginTransaction();
		session.save(cm);
		session.getTransaction().commit();
		session.flush();
		session.close();
	}


	public List<CouponMessage> getMessageList(String coupon_serial_number) {
		  Session session =  getSession();
		  Query query = session.createQuery( "from CouponMessage where coupon_serial_number = :coupon_serial_number" );
		  query.setString( "coupon_serial_number", coupon_serial_number );
		  List<CouponMessage> ls  = query.list();
		return ls;
	}

	public List<CouponInfo> searchSerial(String serialNumber) {
		  Session session =  getSession();
		  Query query = session.createQuery( "from CouponInfo where serialNumber = :serialNumber" );
		  query.setString( "serialNumber", serialNumber );
		  List<CouponInfo> ci  = query.list();
		return ci ;
	}

	public Long getVisitorNum() {
		@SuppressWarnings("unchecked")
		List<CouponVisitor>  list =  getSession().createCriteria(CouponVisitor.class).list();
		CouponVisitor cv = list.get(0);
		return cv.getCoupon_visitor();
	}

	public Object saveVisitorNum(Long num) {
		@SuppressWarnings("unchecked")
		List<CouponVisitor>  list =  getSession().createCriteria(CouponVisitor.class).list();
		CouponVisitor cv = list.get(0);
		cv.setCoupon_visitor(num);
		Session session =  getSession();
		session.beginTransaction();
		session.update(cv);
		session.getTransaction().commit();
		session.flush();
		session.close();
		return null;
	}
}