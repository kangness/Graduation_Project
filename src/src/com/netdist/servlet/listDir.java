package com.netdist.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

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
@WebServlet("/listDir.xml")
public class listDir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/xml; charset=UTF-8";
       
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
    	byte[] tempbyte = null;
    	InputStream in = null;
    	listPath ll = new listPath();
    	String Path =  null;
    	String result = "";
    	Random rand = new Random(1000);
    	response.setContentType(CONTENT_TYPE);
    	Path = "/tmp/result"+ rand.nextInt()+".xml";
    	File file = null;
    	Destination = "/mnt/mfs" + Destination;
    	List<NetDiskFile> list = null;
    	try {
			 out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
    	list = ll.ls(Destination);
    	CreateFileInfoXml.generateXml(Path, list);
    	try{
    		file = new File(Path);
    		in = new FileInputStream(file);
    		
    		BufferedReader br=new BufferedReader(new InputStreamReader(in));  
    		String line = null;  
    		while ((line = br.readLine()) != null) {  
    			tempbyte = line.getBytes();
    			result+=new String(tempbyte,"UTF-8");
    		}  
    		in.close();
    		
    	}
    	catch (Exception ex)
    	{
    		System.out.print(ex.toString());
    		out.println("Error");
    	}
    	finally{
    		file.delete();	
    	}
    	out.println(result);	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		listDirinfo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		listDirinfo(request,response);
	}

}
