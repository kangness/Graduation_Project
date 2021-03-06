package com.netdist.driver;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateFile {
	public boolean createFile(String Source) throws Exception{
		String command = "touch "+Source;
		try {
			
			Process p = Runtime.getRuntime().exec(command);// 启动另一个进程来执行命令  
         BufferedInputStream in = new BufferedInputStream(p.getInputStream());  
         BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
         String lineStr; 
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
			System.out.println(e.toString() +"CreateFile error");
		}
		return true;
	}

}
