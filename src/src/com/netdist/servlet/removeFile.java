package com.netdist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netdist.driver.RemoveFile;

/**
 * Servlet implementation class removefile
 */
@WebServlet("/removeFile")
public class removeFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeFile() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void removefiles(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	PrintWriter out = response.getWriter();
    	RemoveFile remove = new RemoveFile();
    	String RemovePath = (String) request.getAttribute("RemovePath");
    	if (RemovePath == null){
    		out.println("path is null");
    		return ;
    	}
    	String temp = null;
    	try {
    		temp = "/mnt/mfs"+ RemovePath;
			remove.RmFile(temp);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			out.println("remove file error"+RemovePath);
			System.out.println("remove file error" +temp);
		}
    	out.println("OK to remove " + RemovePath);
    	
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		removefiles(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		removefiles(request,response);
	}

}
