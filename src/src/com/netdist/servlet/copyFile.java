package com.netdist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netdist.driver.User;
import com.netdist.driver.copyFile;

/**
 * Servlet implementation class copyfile
 */
@WebServlet("/copyFile")
public class copyFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public copyFile() {
        super();
        // TODO Auto-generated constructor stub
    }

   private void runCopyFiles(HttpServletRequest request,HttpServletResponse response)
   {
	   PrintWriter out = null;
	   String Source = null;
	   String Destination = null;
	   String UserPath = "";
	   String UserName = (String)request.getAttribute("UserName");
	   String UserKey = (String)request.getParameter("UserKey");
	   try {
		   out = response.getWriter();
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		   System.out.println(e.toString());
		   return;
	   }
	   if (UserKey == null){
		   out.println("UserKey not found!!");
		   return ;
	   }
	   if (UserName == null){
		   User usr = new User();
		   if (!usr.getUserInfoByUserKey(UserKey)){
			   out.println("UserKey not found");
			   return ;
		   }else{
			   UserName = usr.getUserName();
		   }
	   }
	   String org = "/mnt/mfs/"+UserName;
	   copyFile copy = new copyFile();
	   
	   request.setAttribute("SourcePath", "/testfile");
	   request.setAttribute("DestinationPath", "/testfiles");
	   Source = (String) request.getAttribute("SourcePath");
	   Destination = (String) request.getAttribute("DestinationPath");
	   if (Source ==null || Destination ==null){
		   out.println("Source or Destination is Unuseful\n");
		   return ;
	   }
	   try {
		copy.CpFile(org+Source, org+Destination);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		out.println("copy file error");
	}
	   out.println("copy file OK");
	   return;
	   
	   
	   
   }
	private void CpFile(String string, String string2) {
	// TODO Auto-generated method stub
	
}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		runCopyFiles( request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		runCopyFiles( request, response);
		return;
	}

}
