package com.netdist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class copyfile
 */
@WebServlet("/copyfile")
public class copyfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public copyfile() {
        super();
        // TODO Auto-generated constructor stub
    }

   private void runCopyFiles(HttpServletRequest request,HttpServletResponse response)
   {
	   PrintWriter out = null;
	   String Source = null;
	   String Destination = null;
	   String org = "/mnt/mfs";
	   
	   try {
		   out = response.getWriter();
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		   System.out.println(e.toString());
		   return;
		
	   }
	   Source = (String) request.getAttribute("source_path");
	   Destination = (String) request.getAttribute("destination_path");
	   if (Source ==null || Destination ==null){
		   out.println("Source or Destination is Unuseful\n");
		   return ;
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
