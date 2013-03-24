package com.netdist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netdist.driver.User;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void Login(HttpServletRequest request, HttpServletResponse response){
    	PrintWriter out = null;
    	
    	String UserName = request.getParameter("UserName");
    	String Password = request.getParameter("Password");
    	try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return ;
		}
    	if(UserName==null ||Password ==null){
    		return ;
    	}
    	User usr = new User();
    	usr.setUserName(UserName);
    	usr.setPassword(Password);
    	if(!usr.getUserInfo()){
    		out.println("UserName or Password not Matching");
    		return;
    	}
    	request.setAttribute("UserName", usr.getUserName());
    	request.setAttribute("UserKey", usr.getUserKey());
    	out.println(usr.getUserKey());
    	return;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Login(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Login(request,response);
	}

}
