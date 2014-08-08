package com.boopon.schedule;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.boopon.bean.CouponCompany;
import com.boopon.constants.Constants;
import com.boopon.service.boopon_maininfoSvc;
import static com.boopon.constants.Constants.*;

@Component
public class BooJob {
	private static final Log log = LogFactory.getLog(BooJob.class);
	@Autowired
	ServletContext  context;
	@Autowired
    private boopon_maininfoSvc boopon_maininfoSvc;
	//1 day ms 86400000
	@Scheduled(fixedRate=86400000)
	public void recordVisitor(){
		Long num = null;
		if(context.getAttribute(ATTR_VISITOR_NUM)==null){
			context.setAttribute(ATTR_VISITOR_NUM, 0L);
			num=0L;
		}else{
			//TODO :  getvisitorNum and saveVIsitorNum
			num =boopon_maininfoSvc.getVisitorNum() ;
			context.setAttribute(ATTR_VISITOR_NUM, num);
			Long nowVisitor =(Long)context.getAttribute(ATTR_TODAY_VISITOR_NUM);
			if(nowVisitor!=null)
				num += nowVisitor;
			boopon_maininfoSvc.saveVisitorNum(num);
			context.setAttribute(ATTR_TODAY_VISITOR_NUM, 0L);
		}
		
		log.info("save Visitor in db:"+num);
	}
	@Scheduled(fixedRate=3600000)
	public void loadConstants(){
//		List <CouponCompany> comList = boopon_maininfoSvc.getCompanyList();
//		if(comList==null){
//			comList = new ArrayList();
//		}	
//		if(context.getAttribute("comList")==null)
//			context.setAttribute("comList",comList);
	}
	
	
	
}
