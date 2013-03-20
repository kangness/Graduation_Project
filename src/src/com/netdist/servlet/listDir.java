package com.netdist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netdist.driver.NetDiskFile;
import com.netdist.driver.listPath;

/**
 * Servlet implementation class listDir
 */
@WebServlet("/listDir")
public class listDir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listDir() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void listDirinfo(HttpServletRequest request, HttpServletResponse response){
    	request.setAttribute("Destination","/");
    	String Destination = (String)request.getAttribute("Destination");
    	PrintWriter out = null;
    	listPath ll = new listPath();
    	Destination = "/mnt/mfs" + Destination;
    	List<NetDiskFile> list = null;
    	try {
			 out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	list = ll.ls(Destination);
    	CreateFileInfoXml.generateXml("/tmp/result.xml", list);
    	out.println("OK");
    	
    	
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
