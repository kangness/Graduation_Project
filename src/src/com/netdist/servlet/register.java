package com.netdist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netdist.driver.CreateDir;
import com.netdist.driver.User;

/**
 * Servlet implementation class register
 */
@WebServlet("/Register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void registerUser(HttpServletRequest request, HttpServletResponse response){
    	 PrintWriter out ;
    	 String UserName = request.getParameter("UserName");
    	 String Password =  request.getParameter("Password");
    	 CreateDir cdr = new CreateDir();
    	 System.out.println(UserName  + "    "+Password);
    	 
    	 User usr = new User(UserName,Password);
    	 usr.setSex((String)request.getAttribute("Sex"));
    	 if(!usr.addUserinfo()){
    		 return ;
    	 }
    	 try{
    		 out = response.getWriter();
    	 }catch(Exception ex)
    	 {
    		 System.out.println(ex.toString());
    		 return;
    	 }
    	 
    	 
    	 try {
			cdr.mkDir("/mnt/mfs/"+UserName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println("create home dirctory error");
		}
    	out.println("OK");
    	return;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		registerUser(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		registerUser(request,response);
	}

}
