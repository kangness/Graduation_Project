Public static Document generateXml(List<NetDiskFile>list){
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
	NetDiskFile  NetDiskFile=list.get(i);
	element=doc.createElement("FileInfo"+(i+1));
	element.setAttribute("fileName",""+NetDiskFile.getName());
	element.setAttribute("User",NetDiskFile.getUser());
	element.setAttribute("Group",NetDiskFile.getGroup());
	element.setAttribute("Permission",NetDiskFile.getPermission());
	element.setAttribute("Size",String.valueOf(NetDiskFile.getSize()));
	element.setAttribute("CreateDate",NetDiskFile.getCreateDate());
	element.setAttribute("Dirctory",String.valueOf(NetDiskFile.getDirctory()));
	root.appendChild(element);
    }
    return doc;
}
