package com.netdist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netdist.driver.CreateFile;

/**
 * Servlet implementation class createfile
 */
@WebServlet("/createFile")
public class createFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createFile() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void create(HttpServletRequest request, HttpServletResponse response){
    	request.setAttribute("DestinationPath", "/helloworld");
    	String Destination = (String) request.getAttribute("DestinationPath");
    	Destination = "/mnt/mfs/test" + Destination ;
    	CreateFile cfile = new CreateFile();
    	PrintWriter out = null;
    	try {
			 out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if (Destination == null){
    		return ;
    	}
    	try {
			cfile.createFile(Destination);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println("error");
			System.out.println(e.toString());
			e.printStackTrace();
		}
    	out.print("OK");
    	return;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		create(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		create(request, response);
	}

}
