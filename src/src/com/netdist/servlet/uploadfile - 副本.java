﻿protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    final long MAX_SIZE = 30*1024*1024;/*设置文件最大的上传大小*/
    /*设置文件上传的类型*/
    final String[] allowedExt = new String[]{"jpg","jpeg","gif","txt","doc","mp3","wma","mp4","mkv"};
    MoveFile mvfile = new MoveFile();
    String SavePath = (String) request.getAttribute("SavePath");
    SavePath = "/mnt/mfs/"+SavePath;
    response.setContentType("test/html");/*设置文件response的类型*/
    response.setCharacterEncoding("UTF-8");/*设置响应体的编码格式*/
    DiskFileItemFactory dfif = new DiskFileItemFactory();       
    dfif.setSizeThreshold(4096);/*设置缓冲区域大小*/
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
            e.printStackTrace();
        }
    }    
}
