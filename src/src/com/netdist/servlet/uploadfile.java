package com.netdist.servlet;

import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.io.IOException;
import java.util.List;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.netdist.driver.RemoveFile;
import com.netdist.driver.MoveFile;


/**
 * Servlet implementation class uploadfile
 */
@WebServlet("/uploadfile")
public class uploadfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final long MAX_SIZE = 30*1024*1024;
	
		final String[] allowedExt = new String[]{
				"jpg",
				"jpeg",
				"gif",
				"txt",
				"doc",
				"mp3",
				"wma",
				"mp4",
				"mkv"
		};
		MoveFile mvfile = new MoveFile();
		request.setAttribute("SavePath", new String("/test/"));
		String SavePath = (String) request.getAttribute("SavePath");
		SavePath = "/mnt/mfs/"+SavePath;
		response.setContentType("test/html");
		response.setCharacterEncoding("UTF-8");
		
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(4096);
		if(!new File(request.getRealPath("/")+"imagesUploadTemp").isDirectory())   
	        new File(request.getRealPath("/")+"imagesUploadTemp").mkdirs();
		dfif.setRepository(new File (request.getRealPath("/")+"imagesUploadTemp"));
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		sfu.setSizeMax(MAX_SIZE);
		PrintWriter out = response.getWriter();
		if(!ServletFileUpload.isMultipartContent(request)){
			return ;
		}
		List<FileItem>  filelist = null;
		try{
			filelist = sfu.parseRequest(request);
		}catch(FileUploadException ex){
			out.println("upload file error  "+ex.toString());
		}
		if (filelist ==null || filelist.size() ==0){
			out.println("could not find the file ");
			return;
		}
		Iterator fileItr = filelist.iterator();
		while(fileItr.hasNext()){
			FileItem fileItem = null;
			String path = null;
			long size  = 0;
			fileItem = (FileItem)fileItr.next();
			if(fileItem == null||fileItem.isFormField()){
				continue;
			}
			path = fileItem.getName();
			size = fileItem.getSize();
			if("".equals(path)||size == 0){
				out.println("Upload file error");
				return ;
			}
			String t_name = path.substring(path.lastIndexOf("\\") + 1);
			String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
			int allowFlag = 0;
			int allowedExtCount = allowedExt.length;
			for (; allowFlag < allowedExtCount; allowFlag++) {
				if (allowedExt[allowFlag].equals(t_ext))
					break;
			}
			if (allowFlag == allowedExtCount) {
				out.println("请上传以下类型的文件\n\n");
				for (allowFlag = 0; allowFlag < allowedExtCount; allowFlag++)
					out.println("*." + allowedExt[allowFlag]+ "&nbsp;&nbsp;&nbsp;");
			    return;
			   }

	
			String u_name = request.getRealPath("/") +"imagesUploaded/"+t_name;
			if(!new File(request.getRealPath("/")+"imagesUploaded").isDirectory())   
		        new File(request.getRealPath("/")+"imagesUploaded").mkdirs();
			try{
				fileItem.write(new File(u_name));
				out.print("upload file  " + t_name + "  OK size "+size );
			}
			catch (Exception ex)
			{
				out.println("upload file "+t_name +" Error  "+ex.toString());
			}
			try {
				mvfile.MvFile(u_name,SavePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		}
	public void destroy(){
		super.destroy();
	}

}
