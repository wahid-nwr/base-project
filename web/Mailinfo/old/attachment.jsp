<%@page import="java.util.Calendar"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.FilePart"%>
<%@page import="com.oreilly.servlet.multipart.Part"%>
<%@page import="com.oreilly.servlet.multipart.MultipartParser"%>
<%
	String id = request.getParameter("id");
	String uId = session.getId();
	String user_id = (String)session.getAttribute("user_id");
	String user_name = (String)session.getAttribute("user_name");
	
	System.out.println("id::::"+id);
	if(id == null ||(id != null && id.equals(uId) == false))
		{
		out.println("timeOut");
		return;
		}	
%>

<%
	//System.out.println("In the Attachment Flow...");
	MultipartParser mp = new MultipartParser(request, 10*1024*1024); // 10MB
	Part part;
	String fileName=null;
	String dirName = request.getRealPath("/")+"email_new"+File.separator+"attachments";
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
	}
%>