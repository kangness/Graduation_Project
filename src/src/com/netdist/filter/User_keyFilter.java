package com.netdist.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class User_keyFilter
 */
//@WebFilter(description = "User key", urlPatterns = { "/NewFile.jsp"})
@WebFilter(
		urlPatterns = { 
				"/NewFile.jsp", 
				"/NewFile.html"
		})
public class User_keyFilter implements Filter {

    /**
     * Default constructor. 
     * @throws IOException 
     */
    public User_keyFilter() {
        // TODO Auto-generated constructor stub 
    	
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		chain.doFilter(request, response);
		String User_key = (String)request.getAttribute("User_Key");
    	PrintWriter out = null;
    	try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(User_key == null){
    		out.println("Could not find User_key");
    	}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
