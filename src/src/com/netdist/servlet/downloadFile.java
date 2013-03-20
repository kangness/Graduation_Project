package com.netdist.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class downloadfile
 */
@WebServlet("/downloadFile")
public class downloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;       
	private static final String CONTENT_TYPE = "text/html; charset=GBK";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public downloadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void download(HttpServletRequest request, HttpServletResponse response){
    	response.setContentType(CONTENT_TYPE);
    	String Source = (String) request.getAttribute("SourcePath");
    	String Destination = (String) request.getAttribute("DestinationPath");
    	Source = "/mnt/mfs/test" +Source;
    	File file = new File (Source);
    	response.setContentType("application/x-msdownload");
    	response.setContentLength((int)file.length());
    	try {
			FileInputStream fls = new FileInputStream(file);
			BufferedInputStream buff=new BufferedInputStream(fls);
			byte [] b=new byte[1024];//相当于我们的缓存
	        long k=0;//该值用于计算当前实际下载了多少字节
	        //从response对象中得到输出流,准备下载
	        OutputStream myout=response.getOutputStream();
	        //开始循环下载
	        while(k<file.length()){
	            int j=buff.read(b,0,1024);
	            k+=j;
	            //将b中的数据写到客户端的内存
	            myout.write(b,0,j);
	        }
	        //将写入到客户端的内存的数据,刷新到磁盘
	        myout.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error" + e.toString());
			e.printStackTrace();
		}
    	return;
    }
    /*public void download(File f,String imgUrl){
    	
    	byte[] buffer = new byte[8*1024];
    	URL u;
    	URLConnection connection = null;
    	try{
    		u = new URL(imgUrl);
    		connection = u.openConnection();
    	}catch(Exception e){
    		System.out.println(e.toString() + imgUrl);
    		return ;
    	}
    	connection.setReadTimeout(1000);
    	InputStream is = null;
    	FileOutputStream fos = null;
    	try{
    		f.createNewFile();
    		is = connection.getInputStream();
    		fos = new FileOutputStream(f);
    		int len = 0;
    		while ((len = is.read(buffer))!=-1){
    			fos.write(buffer,0,len);
    		}
    	}catch(Exception ex){
    		System.out.println(ex.toString());
    		f.delete();
    		return ;
    	}finally{
    		if (fos!=null){
    			try{
    				fos.close();
    			}catch (Exception ex){
    				System.out.println(ex.toString());
    			}
    		}
    	}
    	buffer = null;
    	
    }*/
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("DestinationPath", "/tmp/1.jpg");
		request.setAttribute("SourcePath", "/1.jpg");
		String Destination = (String) request.getAttribute("DestinationPath");
		String Source = (String) request.getAttribute("SourcePath");
		File  a,b;
		if (Destination == null){
			System.out.println("Destination is not found");
			return ;
		}
		if (Source == null){
			System.out.println("Source is not found");
			return ;
		}
		Source = "/mnt/mfs/test" + Source;
		a = new File(Source);
		if (!a.isFile()){
			System.out.println("Path is not found" + Source);
			return ;
		}
		b = new File(Destination);
		if(b.exists()){
			if(b.createNewFile()){
				System.out.println("Create "+Destination + " error");
				return ;
			}
		}
		download(request,response);
		return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
