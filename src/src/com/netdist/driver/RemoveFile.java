package com.netdist.driver;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RemoveFile {

	public RemoveFile(){
		
	}
	 public boolean RmFile(String path) throws InterruptedException{
		String command = "rm -rf "+path;
		//String command = "ls -l";
		try {
			
			Process p = Runtime.getRuntime().exec(command);// 启动另一个进程来执行命令  
         BufferedInputStream in = new BufferedInputStream(p.getInputStream());  
         
         BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
         
         String lineStr;  
         
         System.out.println("rm file "+path);
         while ((lineStr = inBr.readLine()) != null)  {
        	 	System.out.println(lineStr);  
         	}
         if (p.waitFor()!= 0 ) {  
         
        	 if (p.exitValue() == 1)  
        		 System.out.println("命令执行失败!");  
            }  
         inBr.close();  
         in.close();   
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	 
	 
	 /*public static void main(String[] args) {
			// TODO Auto-generated method stub
		RemoveFile rrm = new RemoveFile();
		try {
			rrm.RmFile("Helloworld");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

}
