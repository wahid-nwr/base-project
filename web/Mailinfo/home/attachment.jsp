<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.struts.upload.MultipartRequestWrapper"%>
<%@page import="org.apache.struts.upload.MultipartRequestHandler"%>
<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.io.File"%>

<%!

%>
<%
	String id = request.getParameter("id");
	String uId = session.getId();
	String user_id = (String)session.getAttribute("user_id");
	String user_name = (String)session.getAttribute("user_name");
	
	System.out.println("id::::"+id);
	if(id == null ||(id != null && id.equals(uId) == false))
		{
		//out.println("timeOut");
		//return;
		}	
%>

<%
/* PrintWriter outWriter = response.getWriter();

//set content type and header attributes
response.setContentType("text/html");
response.setHeader("Cache-control", "no-cache, no-store");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "-1");

DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();

//Set the size threshold, above which content will be stored on disk.
fileItemFactory.setSizeThreshold(10*1024*1024); //1 MB

//Set the temporary directory to store the uploaded files of size above threshold.
fileItemFactory.setRepository(new File(request.getRealPath("/")+"swift-erp"));

ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
JSONObject myObj = new JSONObject();

String fileName = null;
String fullName = null;
File file = null; */
/* 
try {

    //Parse the request
    Map params = request.getParameterMap();
    Set en = params.keySet();
    for(Object obj:en)
    {
    	System.out.println(request.getParameter("method")+" obj.toString()::"+obj.toString()+"params:::"+(params.get(obj.toString())).toString());
    }
    System.out.println(request.getParameter("newMail-form-file"));
    List items = uploadHandler.parseRequest((HttpServletRequest)request);
    System.out.println("items::"+items.size());
    Iterator iterator = items.iterator();
    while(iterator.hasNext()) {
        FileItem item = (FileItem) iterator.next();

        //Handle Form Fields
        if(item.isFormField()) {
            System.out.println("Field Name = " + item.getFieldName() + ", Value = " + item.getString());
            if(item.getFieldName().trim().equalsIgnoreCase("filename")){
                fileName = item.getString().trim();
            }
        }

        //Handle Uploaded files.
        else {
            System.out.println("Field Name = " + item.getFieldName()+
                    ", File Name = "+ item.getName()+
                    ", Content type = "+item.getContentType()+
                    ", File Size = "+item.getSize());
            fullName = item.getName().trim();
			String destinationDir = request.getRealPath("/")+"swift-erp"+File.separator+"attachments";
            //Write file to the ultimate location.
            file = new File(destinationDir,item.getName());
            item.write(file);
        }



    }

    int count = 0;
    
    String extension = FilenameUtils.getExtension(fullName);
     if(extension.trim().equalsIgnoreCase("xlsx")){
        count = processExcelFile(file);
    }
    else if(extension.trim().equalsIgnoreCase("xls")){
        //process your binary excel file
    }
    if(extension.trim().equalsIgnoreCase("csv")){
        //process your CSV file
    } 

    myObj.put("success", true);
    myObj.put("message", count + " item(s) were processed for file " + fileName);
    outWriter.println(myObj.toString());

}
catch(FileUploadException ex) {
    log("Error encountered while parsing the request",ex);
    myObj.put("success", false);
    outWriter.println(myObj.toString());
} catch(Exception ex) {
    log("Error encountered while uploading file",ex);
    myObj.put("success", false);
    outWriter.println(myObj.toString());
}

outWriter.close();


 
	System.out.println("In the Attachment Flow...");
	MultipartParser mp = new MultipartParser(request, 10*1024*1024); // 10MB
	Part part;
	fileName=null;
	String dirName = request.getRealPath("/")+"email_new"+File.separator+"attachments";
	System.out.println("dirName::"+dirName);
	String filePath;
	Calendar cal = Calendar.getInstance();
	cal.set(Calendar.YEAR,2008);
	cal.set(Calendar.MONTH,0);
	cal.set(Calendar.DAY_OF_MONTH,1);
	cal.set(Calendar.HOUR,0);
	cal.set(Calendar.MINUTE,0);
	cal.set(Calendar.SECOND,0);
	long timestamp = (System.currentTimeMillis() - cal.getTimeInMillis())/1000;
	String msg="<span style=\"color: green;font-weight: bold;\">Attachment uploaded successfully..</span>";
	try{
		while ((part = mp.readNextPart()) != null) {
				String name = part.getName();
				if (part.isParam()) {
				}else if (part.isFile()) {
					FilePart filePart = (FilePart) part;
					fileName = timestamp+"_"+filePart.getFileName();
					if (fileName != null)
					{
						long size = filePart.writeTo(new File(dirName+File.separator+fileName));
						filePath = filePart.getFilePath() ;
						
					}
					else{
						msg="<span style=\"color: green;font-weight: bold;\">Error: No file has been selected.</span>";
					}
				}
		}
	
	//System.out.println("In the Attachment Flow... New :");
	out.print("{success:true,file:'"+fileName+"',fileSize:"+timestamp+"}");	
	//System.out.printlnt("{success:true,file:'json_encode("+filePath+")',fileSize:'json_encode("+size+")'}");	
	}catch(Exception e){
		msg = "<span style=\"color: red;font-weight: bold;\">Error Occurred "+e.getMessage()+"</span>";
	}finally{
		if(msg.indexOf("Error")!=-1)
			fileName = "";
	}  */
	

	try {
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> items = upload.parseRequest(request);
        System.out.println("items:::"+items.size());
        for (FileItem item : items) {
            if (item.isFormField()) {
                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                String fieldname = item.getFieldName();
                String fieldvalue = item.getString();
                System.out.println("fieldname::"+fieldname);
                System.out.println("fieldvalue::"+fieldvalue);
                // ... (do your job here)
            } else {
                // Process form file field (input type="file").
                String fieldname = item.getFieldName();
                System.out.println("fieldname::"+fieldname);
                String filename = FilenameUtils.getName(item.getName());
                System.out.println("filename::"+filename);
                InputStream filecontent = item.getInputStream();
                System.out.println("filecontent::"+filecontent);
                // ... (do your job here)
            }
        }
    } catch (FileUploadException e) {
        throw new ServletException("Cannot parse multipart request.", e);
    }
	
	
%>