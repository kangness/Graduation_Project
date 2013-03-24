package com.netdist.servlet;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import com.netdist.driver.NetDiskFile;
public class CreateFileInfoXml {
public static void generateXml(String outputPath ,List list) {
  try {
	  Document doc = generateXml(list);//生成XML文件
   
	  outputXml(doc, outputPath);//将文件输出到指定的路径
  
  } catch (Exception e) {
  
	  System.err.println("出现异常"+e.toString());
  }
}
/**
  * 将XML文件输出到指定的路径
  * @param doc
  * @param fileName
  * @throws Exception
  */
private static void outputXml (Document doc, String fileName) throws Exception{
  TransformerFactory tf = TransformerFactory.newInstance();
  Transformer transformer = tf.newTransformer();
  DOMSource source = new DOMSource(doc);
  transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
  transformer.setOutputProperty(OutputKeys.INDENT, "yes");//设置文档的换行与缩进
  PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
  StreamResult result = new StreamResult(pw);
  transformer.transform(source, result);
  System.out.println("生成XML文件成功!");
}
/**
  * 生成XML文件
  * @param list
  * @return
  */
public static Document generateXml(List<NetDiskFile> list){
  Document doc = null;
  Element root = null;
  try {
   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
   DocumentBuilder builder = factory.newDocumentBuilder();
   doc = builder.newDocument();
   root = doc.createElement("NetDiskFileInfo");
   doc.appendChild(root);
  } catch (Exception e) {
   e.printStackTrace();
   return null;//如果出现异常，则不再往下执行
  }
  
  int len = list.size() ;
  Element element ;
  for (int i = 0; i < len; i++) {
	  NetDiskFile NetDiskFile = list.get(i);
	  element = doc.createElement("FileInfo"+(i+1));
	  element.setAttribute("fileName", ""+NetDiskFile.getName());
	  element.setAttribute("User", NetDiskFile.getUser());
	  element.setAttribute("Group", NetDiskFile.getGroup());
	  element.setAttribute("Permission", NetDiskFile.getPermission());
	  element.setAttribute("Size", String.valueOf(NetDiskFile.getSize()));
	  element.setAttribute("CreateDate", NetDiskFile.getCreateDate());
	  element.setAttribute("Dirctory", String.valueOf(NetDiskFile.getDirctory()));
	  root.appendChild(element);
  }
  return doc;
}
}