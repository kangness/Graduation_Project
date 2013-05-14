public void removefiles(HttpServletRequest request, HttpServletResponse response) throws IOException{
    PrintWriter out = response.getWriter();
    RemoveFile remove = new RemoveFile();
    String RemovePath = (String) request.getAttribute("RemovePath");
    if (RemovePath == null){
        out.println("path is null");
        return ;
    }
    String temp = null;
    try {
        temp = "/mnt/mfs"+ RemovePath;
        remove.RmFile(temp);
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        out.println("remove file error"+RemovePath);
        System.out.println("remove file error" +temp);
    }
    out.println("OK to remove " + RemovePath);    
}
