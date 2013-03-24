package com.netdist.driver;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class listPath {

	public ArrayList ls(String path){
		String command = "ls -l "+path;
		
		String User = null;
		String CreateDate = null;
		String Name= null;
		String Size = null;
		String Group = null;
		String Permission = null;
		String[] temp = null;
		boolean Dirctory = false;
		int i;
		ArrayList result = new ArrayList();
		try {
			
			Process p = Runtime.getRuntime().exec(command);// 启动另一个进程来执行命令  
         BufferedInputStream in = new BufferedInputStream(p.getInputStream());  
         
         BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
         
         String lineStr;  
         while ((lineStr = inBr.readLine()) != null)  {
        	   temp = lineStr.split("\\s{1,10}");
        	   
        	    if (temp.length >5){
        	    	Permission = temp[0];
        	    	if (Permission.charAt(0) =='d'){
        	    		Dirctory = true;
        	    	}else{
        	    		Dirctory = false;
        	    	}
        	    	Group = temp[2];
        	    	User = temp[3];
        	    	Size = temp[4];
        	    	CreateDate = temp[5]+" "+temp[6]+" "+temp[7];
        	    	Name = temp[8];
        	    	result.add(new NetDiskFile(Name,User,CreateDate,Size,Permission,Dirctory));
        	    }
         	}
         try {
			if (p.waitFor()!= 0 ) {  
			 
				 if (p.exitValue() == 1)  
					 System.out.println("命令执行失败!");  
			    }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         inBr.close();  
         in.close();   
         
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
