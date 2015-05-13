<%@page import="java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%
	String filename = request.getParameter("file_name");
	try
	{
		/* response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", "attachment; filename="+file_name); */
		File                f        = new File(request.getRealPath("/attachments")+"/"+filename);
		int                 length   = 0;
		ServletOutputStream op       = response.getOutputStream();
		ServletContext      context  = getServletConfig().getServletContext();
		String              mimetype = context.getMimeType( filename );
		response.setContentType( (mimetype != null) ? mimetype : "application/octet-stream" );
		response.setContentLength( (int)f.length() );
		response.setHeader( "Content-Disposition", "attachment; filename=\"" + filename + "\"" );
		
		byte[] bbuf = new byte[4096];
		DataInputStream in = new DataInputStream(new FileInputStream(f));
		
		while ((in != null) && ((length = in.read(bbuf)) != -1))
		{
    	    op.write(bbuf,0,length);
	    }
		in.close();
		op.flush();
		op.close();
	
	}
	catch(Exception e)
	{
		%><p>&nbsp;</p><p><font color=red><b><%= e.getMessage() %></b></font> <%
		e.printStackTrace();
	}
%>