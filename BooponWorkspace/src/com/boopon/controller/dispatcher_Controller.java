package com.boopon.controller;

import static com.boopon.bean.json.CouponInfoRequest.THUMB_UP;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.boopon.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.boopon.bean.CouponCompany;
import com.boopon.bean.CouponInfo;
import com.boopon.bean.CouponMessage;
import com.boopon.bean.json.CouponInfoRequest;
import com.boopon.bean.json.CouponInfoResponse;
import com.boopon.constants.Constants;
import com.boopon.service.boopon_maininfoSvc;
import com.boopon.util.BooponUtils;

@Controller
@RequestMapping("/")
public class dispatcher_Controller {
	private static final Log log = LogFactory.getLog(dispatcher_Controller.class);
	@Autowired
	private HttpServletRequest context;

	@Autowired
    private boopon_maininfoSvc boopon_maininfoSvc;
public dispatcher_Controller() {


}
	private void calVisitor(){
		Long num = boopon_maininfoSvc.getVisitorNum();
		if(context.getServletContext().getAttribute(ATTR_VISITOR_NUM)==null){
			if(num==null)
				num=0L;
			context.getServletContext().setAttribute(ATTR_VISITOR_NUM,num);
		}else{
			//TODO : DAO get visitor number;
			num = (Long)	context.getServletContext().getAttribute(ATTR_VISITOR_NUM);
			context.getServletContext().setAttribute(ATTR_VISITOR_NUM,++num);
			boopon_maininfoSvc.saveVisitorNum(num);
			if(context.getServletContext().getAttribute(ATTR_TODAY_VISITOR_NUM)!=null){
				Long todayNum = (Long)context.getServletContext().getAttribute(ATTR_TODAY_VISITOR_NUM)	;
				context.getServletContext().setAttribute(ATTR_TODAY_VISITOR_NUM,++todayNum);
			}else{
				context.getServletContext().setAttribute(ATTR_TODAY_VISITOR_NUM,0L);
			}
			log.info("visitor:"+num);
		}
	}
	
	
	@RequestMapping(value = "/")
	public String mainpage(HttpServletRequest request) {
		calVisitor();
		List msgList = new ArrayList();
		List<CouponInfo> ls = boopon_maininfoSvc.getAllCoupon();
		for(CouponInfo ci:ls){
			ci.setRate(BooponUtils.getAvaliableRate(ci.getVoteUp(),ci.getVoteDown()));
			List <CouponMessage> msgTemp= boopon_maininfoSvc.searchMsg(ci.getSerialNumber());
			ci.setMessageNum(msgTemp.size());
			msgList.addAll(msgTemp);
		}
		request.setAttribute(ATTR_COUPON_LIST,  ls);
		request.setAttribute(ATTR_MSG_LIST, msgList);
		return "index";
	}
	
	/**
	 * �j�MCOUPON
	 * @param info
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/searchCoupon", method = RequestMethod.POST )
	public  String searchCoupon(@RequestParam(value = "searchText", required = true, defaultValue = "")  String searchText,
			HttpServletRequest request) throws UnsupportedEncodingException{
		CouponInfoResponse res = new CouponInfoResponse();

		//do search
		searchText = URLDecoder.decode(searchText,"UTF-8");
		List<CouponMessage> msgList = new ArrayList<CouponMessage>();
		
		List <CouponInfo> couponList  =boopon_maininfoSvc.searchCoupon(searchText);
		
		
		

		if(couponList!=null){
			for(CouponInfo ci:couponList){
				ci.setRate(BooponUtils.getAvaliableRate(ci.getVoteUp(),ci.getVoteDown()));
				List <CouponMessage> msgTemp= boopon_maininfoSvc.searchMsg(ci.getSerialNumber());
				ci.setMessageNum(msgTemp.size());
				msgList.addAll(msgTemp);
			}
		}else{
			couponList = new ArrayList();
		}

		
		request.setAttribute(ATTR_COUPON_LIST, couponList);
		request.setAttribute(ATTR_MSG_LIST, msgList);
		request.setAttribute(ATTR_SEARCH_TEXT, searchText);
		

		return "index";
	}
	
	/**
	 * �s������
	 * @param info
	 * @return
	 */
	@RequestMapping(value="/shareCoupon", method = RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody String shareCoupon(@RequestBody @Valid  CouponInfoRequest info){
		log.info("info.getSerialNum():"+info.getSerialNum());
		List <CouponInfo> couponList  =boopon_maininfoSvc.searchSerial(info.getSerialNum());
		if(couponList.size()>0){
			return "此優惠券序號已存在";
		}else{
			boopon_maininfoSvc.saveNewCoupon(info);
			return "分享成功! 感謝您無私的分享";
		}
	}
	
	@RequestMapping(value="/addMessage", method = RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody String addMessage(@RequestBody CouponInfoRequest info){
		boopon_maininfoSvc.addMessage(info);
		return "Success";
	}
	@RequestMapping(value="/reportUsed", method = RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody String reportUsed(@RequestBody CouponInfoRequest info){
		boopon_maininfoSvc.reportUsed(info.getSerialNum());
		return "Success";
	}
	
	
	/**
	 * �i�βv�벼
	 * @return
	 */
	@RequestMapping(value="/vote", method = RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody String vote(@RequestBody CouponInfoRequest info){
		StringBuilder msg = new StringBuilder("");
		String rate = "";
		if(THUMB_UP.equals(info.getUpOrDown())){	
			 rate = boopon_maininfoSvc.thumbUp(info.getSerialNum());
		}else{
			 rate = boopon_maininfoSvc.thumbDown(info.getSerialNum());	
		}
		return rate;
	}
	
}
