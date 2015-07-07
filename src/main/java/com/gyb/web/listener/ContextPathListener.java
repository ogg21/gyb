package com.gyb.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web缓存Context Path相关信息
 * @author Shunzhong.Huang
 *
 */
public class ContextPathListener implements ServletContextListener {
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	public void contextInitialized(ServletContextEvent contextEvent) {
		ServletContext ctx = contextEvent.getServletContext();
		ctx.setAttribute("home", ctx.getContextPath());
	}
}
