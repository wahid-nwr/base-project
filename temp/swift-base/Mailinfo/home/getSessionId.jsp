<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%
String id = request.getSession().getId();
out.println("session_id:"+id);
%>