package com.netdist.servlet;

import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netdist.driver.listPath;

/**
 * Servlet implementation class listfile
 */
@WebServlet("/listFile")
public class listFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listFile() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void  listFileInfo(HttpServletRequest request, HttpServletResponse response){
    	request.setAttribute("Destination","/1.jpg");
    	String Destination = (String)request.getAttribute("Destination");
    	PrintWriter out = null;
    	String result = "";
    	String Path = "";
    	Path = "/mnt/mfs/test";
    	listPath ll ;
    	try{
    		out = response.getWriter();
    	}catch (Exception ex){
    		System.out.println(ex.toString());
    		return;
    	}
    	try{
    		ll = new listPath();
    		
    	}
    	
    	
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
