package com.shinhan.heehee.initalizer;

import java.util.List;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.shinhan.heehee.config.AppConfig;
import com.shinhan.heehee.config.SecurityConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { SecurityConfig.class, AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    } 
    
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[]{encodingFilter};
    }
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        
        // Set throwExceptionIfNoHandlerFound to true
        ServletRegistration.Dynamic dispatcher = (ServletRegistration.Dynamic) servletContext.getServletRegistration("dispatcher");
        if (dispatcher != null) {
            dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        }
    }
}
