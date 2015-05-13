<%@page import="org.apache.struts.util.PropertyMessageResources"%>
<%@page import="org.apache.struts.util.MessageResources"%>
<%@page import="com.sun.xml.internal.ws.api.message.Message"%>
<%@page import="org.springframework.beans.BeanUtils"%>
<%@page import="java.lang.reflect.Method"%>
<%@ include file="/Common/Include/taglib.jsp"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.search.SearchOperationResult"%>
<%@ page import="com.swiftcorp.portal.common.search.SearchUtil"%>
<%@ page import="org.springframework.context.support.FileSystemXmlApplicationContext"%>
<%@ page import="org.springframework.context.MessageSourceAware"%>
<%@ page import="org.springframework.context.MessageSource"%>

<%@ page import="java.util.Locale"%>    
<%@ page import="javax.annotation.Resource"%>    
<%@ page import="org.springframework.beans.factory.annotation.Configurable"%>
<%@ page import="org.springframework.context.MessageSource"%>
<%@ page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%
	int SEARCH_RESULT_PER_PAGE = 20;
	

	SearchOperationResult searchOperationResult = null;
	ArrayList<ArrayList<String>> searchResult = null;
	int totalRow = 0;
	
	ArrayList<String> columnHeader = (ArrayList<String>)request.getAttribute(SESSION_KEYS.COLUMN_HEADER_LIST);
	String modifyURL = (String)request.getAttribute(SESSION_KEYS.MODIFYING_URL);
	
	String component = (String)request.getParameter("component");
	String search_result_per_page = request.getParameter("search_result_per_page");
	SEARCH_RESULT_PER_PAGE = 60;
	if(component!=null && !component.equals("null") && component.equals("reservation"))
	{
		SEARCH_RESULT_PER_PAGE = 60;
		System.out.println("SEARCH_RESULT_PER_PAGE::"+SEARCH_RESULT_PER_PAGE);
	}
	searchOperationResult = SearchUtil.getSearchResult(component, request);
	boolean isSearchResultShow = SearchUtil.isSearchResultShow(request);
	
	if(searchOperationResult != null)
	{
		searchResult = searchOperationResult.getSearchResult();
		totalRow = searchOperationResult.getTotalRowCount();
		System.out.println("totalrow:::"+totalRow);
	}
	
	String currentSortColumnNumber = (String)request.getAttribute(SESSION_KEYS.CURRENT_SORT_COLUMN_NUMBER);
	String isAscending = (String)request.getAttribute(SESSION_KEYS.IS_ASCENDING);
	String currentPageNumber = (String)request.getAttribute(SESSION_KEYS.CURRENT_PAGE_NUMBER);
	
	if(currentSortColumnNumber == null)
	{
		currentSortColumnNumber = "1";
	}
	if(isAscending == null)
	{
		isAscending = "true";
	}
	if(currentPageNumber == null)
	{
		currentPageNumber = "1";
	}
	
	int totalPage = 1;
	if(totalRow > SEARCH_RESULT_PER_PAGE)
	{
		totalPage = (int)Math.ceil((float)totalRow / SEARCH_RESULT_PER_PAGE);
	}
	System.out.println("totalPage::"+totalPage);
	List<String> columnNameList = new ArrayList<String>();
		

if(!isSearchResultShow)
{
	return;
}

String xml = "";
xml += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
xml += "<ItemSearchResponse xmlns=\"http://webservices.amazon.com/AWSECommerceService/2006-06-28\">";

	xml += "<Arguments>";	
	int columnNumber = 0;
	PropertyMessageResources p = (PropertyMessageResources) request.getAttribute(Globals.MESSAGES_KEY);
	String messageValue = null;
	if(columnHeader != null && columnHeader.size() > 0)
	{
		for(String header:columnHeader)
		{
			columnNumber++;
			
			//xml += "<Argument Name=\""+header+"\" Value=\""+header+"\"></Argument>";
			System.out.println("header:::"+header);			
			
			if (p != null) {
			  // Value for key errors.notempty
			  messageValue = p.getMessage(header);
			}
			messageValue = messageValue.trim();
			messageValue = messageValue.replaceAll(" ","");
			messageValue = messageValue.toLowerCase();	
			System.out.println("messageValue::"+messageValue);
			if(messageValue!=null)
			{
				columnNameList.add(messageValue);
			}
			xml += "<Argument Name=\""+messageValue+"\" Value=\""+messageValue+"\"></Argument>";
			messageValue = null;
		}
	}
	
		
	xml += "</Arguments>";

	xml += "<Items>";
	xml += "<Request>";
	xml += "<IsValid>True</IsValid>";
	xml += "<ItemSearchRequest>";
	xml += "<code>Sidney Sheldon</code>";
	xml += "<SearchIndex>Books</SearchIndex>";
	xml += "</ItemSearchRequest>";
	xml += "</Request>";
	if(searchResult!=null && searchResult.size()>0)
	{
		xml += "<TotalResults>"+totalRow+"</TotalResults>";
	}
	else
	{
		xml += "<TotalResults>"+0+"</TotalResults>";
	}
	xml += "<TotalPages>"+totalPage+"</TotalPages>";
	
	String items = "";
	if(searchResult != null && searchResult.size() > 0)
	{
		System.out.println("searchResult::"+searchResult.size());
		int rowNumber = 0;
		for(ArrayList<String> row:searchResult)
		{
			rowNumber++;			
			if( row != null && row.size() >=1 )
			{
				
				String componentId = row.get(0);
				xml += "\n<Item>";
				xml += "<componentId>"+componentId+"</componentId>";
				xml += "<DetailPageURL>";
				xml += "http://www.amazon.com/gp/redirect.html%3FASIN=0446355453%26tag=ws%26lcode=xm2%26cID=2025%26ccmID=165953%26location=/o/ASIN/0446355453%253FSubscriptionId=1A7XKHR5BYD0WPJVQEG2";
				xml += "</DetailPageURL>";
				xml += "<ItemAttributes>";
				int totalColumn = row.size();
				System.out.println("SEARCH_RESULT_PER_PAGE::::::"+SEARCH_RESULT_PER_PAGE);
				for(int index=1;index<totalColumn && index<SEARCH_RESULT_PER_PAGE;index++)
				{
					
					String column = row.get(index);
					String url = modifyURL + componentId;
					String tag = columnNameList.get(index-1);
					/*
					if(index==1)
					{
						xml += "<uniqueCode>"+column+"</uniqueCode>";
					}
					else if(index==2)
					{
						xml += "<description>"+column+"</description>";
					}
					else if(index==3)
					{
						xml += "<updatedby>"+column+"</updatedby>";
					}*/
					xml += "<"+tag+">"+column+"</"+tag+">";
				}
				
				xml += "</ItemAttributes>";
				xml += "</Item>";
			}		
		}
	}
	xml += "</Items>";
xml += "</ItemSearchResponse>";
response.setContentType("text/xml;charset=ISO-8859-1");
PrintWriter xmlout = response.getWriter();
System.out.println(xml);
//xmlout.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
xmlout.println(xml);
%>