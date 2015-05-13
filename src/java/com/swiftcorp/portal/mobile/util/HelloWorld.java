package com.swiftcorp.portal.mobile.util;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileUpload;

// Extend HttpServlet class
public class HelloWorld extends HttpServlet {
 
  private String message;

  public void init() throws ServletException
  {
      // Do required initialization
      message = "Hello World";
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");
     Map params = request.getParameterMap();
     for(Object key :params.entrySet())
     {
    	 System.out.println("key:::"+key);
     }
      System.out.println("uploads:::::::::::::::"+request.getParameter("__UPLOADS"));
		/*List<FileUpload> uploads = (List<FileUpload>) request.getParameter("__UPLOADS");
		System.out.println(uploads.size());*/
      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      System.out.println("<h1>" + message + "</h1>");
      out.println("<h1>" + message + "</h1>");
  }
  
  public void doPost(HttpServletRequest request,
          HttpServletResponse response)  throws ServletException, IOException
	{
	// Set response content type
	response.setContentType("text/html");
	Map params = request.getParameterMap();
	for(Object key :params.entrySet())
	{
	System.out.println("key:::"+key);
	}
	System.out.println("uploads:::::::::::::::"+request.getParameter("__UPLOADS"));
	/*List<FileUpload> uploads = (List<FileUpload>) request.getParameter("__UPLOADS");
	System.out.println(uploads.size());*/
	// Actual logic goes here.
	PrintWriter out = response.getWriter();
	System.out.println("<h1>" + message + "</h1>");
	out.println("<h1>" + message + "</h1>");
	}
  
  public void destroy()
  {
	  System.out.println("<h1>POst:::" + message + "</h1>");
      // do nothing.
  }
}