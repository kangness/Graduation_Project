package com.netdist.driver;


import java.util.ArrayList;
import java.util.regex.*;
public class testclass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	listPath ll = new listPath();
	ArrayList list = null;
	NetDiskFile ndf = null;
	int i = 0;
	try {
		list = ll.ls("/tmp/");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e.toString());
	}
	for (i =0 ;i < list.size();i++){
	
		ndf = (NetDiskFile) list.get(i);
		System.out.println(ndf.getCreateDate());
		System.out.println(ndf.getSize());
	}
	}
}
