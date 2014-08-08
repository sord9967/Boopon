package com.boopon.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.boopon.bean.CouponCompany;
import com.boopon.service.boopon_maininfoSvc;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
@WebListener
public class InitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
    	ApplicationContext cxt = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());

    	
    	boopon_maininfoSvc dao = (boopon_maininfoSvc) cxt.getBean("mainInfoSvc");

    	ServletContext context = event.getServletContext();
    	//TODO : DAO getcompanyList
    	if(context.getAttribute("comList")==null){
    		List <CouponCompany> comList = dao.getCompanyList();
    		context.setAttribute("comList",comList);

    	}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {

    
    }
	
}
