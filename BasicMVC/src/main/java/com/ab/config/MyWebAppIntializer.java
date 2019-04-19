package com.ab.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebAppIntializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext sc) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext rootCtx=null,webCtx=null;
		ContextLoaderListener listner=null;
		DispatcherServlet servlet=null;
		ServletRegistration.Dynamic registration=null;
		
		//Create Application Context Container
		
		rootCtx=new AnnotationConfigApplicationContext();
		rootCtx.register(RootAppConfig.class);
		
		webCtx=new AnnotationConfigApplicationContext();
		webCtx.register(WebAppConfig.class);
		
		servlet=new DispatcherServlet((WebApplicationContext) webCtx);
		
		
		listner=new ContextLoaderListener((WebApplicationContext) rootCtx);
		sc.addListener(listner);
		
		registration=sc.addServlet("dispatcher", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
	}

}
