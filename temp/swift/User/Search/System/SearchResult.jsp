<%@ include file="/Common/Include/taglib.jsp"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.search.SearchOperationResult"%>
<%@ page import="com.swiftcorp.portal.common.search.SearchUtil"%>

<%
	final int SEARCH_RESULT_PER_PAGE = 20;

	SearchOperationResult searchOperationResult = null;
	ArrayList<ArrayList<String>> searchResult = null;
	int totalRow = 0;
	
	ArrayList<String> columnHeader = (ArrayList<String>)request.getAttribute(SESSION_KEYS.COLUMN_HEADER_LIST);
	String modifyURL = (String)request.getAttribute(SESSION_KEYS.MODIFYING_URL);

	String component = (String)request.getParameter("component");
	searchOperationResult = SearchUtil.getSearchResult(component, request);
	boolean isSearchResultShow = SearchUtil.isSearchResultShow(request);
	
	if(searchOperationResult != null)
	{
		searchResult = searchOperationResult.getSearchResult();
		totalRow = searchOperationResult.getTotalRowCount();	
	}
	
	String currentSortColumnNumber = (String)request.getAttribute(SESSION_KEYS.CURRENT_SORT_COLUMN_NUMBER);
	String isAscending = (String)request.getAttribute(SESSION_KEYS.IS_ASCENDING);
	String currentPageNumber = (String)request.getAttribute(SESSION_KEYS.CURRENT_PAGE_NUMBER);
	System.out.println("currentPageNumber in list result::"+currentPageNumber);
	if(currentSortColumnNumber == null || currentSortColumnNumber.equals("null") || currentSortColumnNumber .length()==0)
	{
		currentSortColumnNumber = "1";
	}
	if(isAscending == null || isAscending.equals("null") || isAscending .length()==0)
	{
		isAscending = "true";
	}
	if(currentPageNumber == null || currentPageNumber.equals("null") || currentPageNumber.length()==0)
	{
		currentPageNumber = "1";
	}
	
	int totalPage = 1;
	if(totalRow > SEARCH_RESULT_PER_PAGE)
	{
		totalPage = (int)Math.ceil((float)totalRow / SEARCH_RESULT_PER_PAGE);
	}
		
%>

<script type="text/javascript">

	var SEARCH_RESULT_PER_PAGE = <%= SEARCH_RESULT_PER_PAGE %>;
	
	var currentSortColumnNumber = <%= currentSortColumnNumber %>;
	var isAscending = <%= isAscending %>;
	
	var currentPageNumber = <%= currentPageNumber %>;
	
	var componentName = '<%= component %>';
	var totalRow = '<%= totalRow %>';
	
	var totalPage = <%= totalPage %>;
	
	function prepareSearch(component, columnNumber, pageNumber)
	{
	
		var sortColumnNumber = 1;
		if(typeof columnNumber != 'undefined')
			sortColumnNumber = columnNumber;
			
		if( !(typeof columnNumber != 'undefined' && columnNumber == -1))
		{
			if(sortColumnNumber == currentSortColumnNumber)
			{
				isAscending = ! isAscending;
			}
			else
			{
				currentSortColumnNumber = sortColumnNumber;
				isAscending = true;
			}
		}
		
		if(typeof pageNumber != 'undefined')
		{
				currentPageNumber = pageNumber;
		}		
		if( typeof columnNumber != 'undefined' && columnNumber == 0)	
		{
			currentSortColumnNumber = 1;
			isAscending = true;
			currentPageNumber = 1;
		}
		
		var searchQueryElement = document.getElementById('searchQuery');
		var searchQuery = addSQLEscape(searchQueryElement.value);

		var sqlQueryElement = document.getElementById('sqlQuery');		
		var sqlQuery;
		
				
		//Store current value
		document.getElementById('currentSortColumnNumber').value = currentSortColumnNumber;
		document.getElementById('isAscending').value = isAscending;
		document.getElementById('currentPageNumber').value = currentPageNumber;
		document.getElementById('searchInput').value = searchQuery;
		
		// submit request
		prepareSubmitAction('search');

	}
	
	function prepareFirst()
	{
		if(currentPageNumber > 1)
			prepareSearch(componentName, -1, 1);
	}
	
	function preparePrevious()
	{
		if(currentPageNumber > 1)
			prepareSearch(componentName, -1, currentPageNumber - 1);
	}
	
	function prepareNext()
	{
		if(currentPageNumber < totalPage)
			prepareSearch(componentName, -1, currentPageNumber + 1);
	}
	
	function prepareLast()
	{
		if(currentPageNumber < totalPage)
			prepareSearch(componentName, -1, totalPage);
	}
	
	function addSQLEscape(param)
	{
		s = new String(param);
		 	 
		//replacing all \ by \\
		s = s.replace(/\\/g,"\\\\");
			 
		//replacing all _ by \_
		s = s.replace(/_/g,"\\_");
		   
		//replacing all ' by ''
		s = s.replace(/'/g,"\'\'");     
		        
		//replacing all % by \%
		s = s.replace(/%/g,"\\%");
		   
		   
		// replaces all " with ""
		//A ?'? inside a string quoted with ?"? needs no special treatment and need not be doubled or escaped. 
		//In the same way, ?"? inside a string quoted with ?'? needs no special treatment.
		//So not needed.

		return s;
	}
</script>

<div>
	<input type="hidden" name="currentSortColumnNumber" id="currentSortColumnNumber">
	<input type="hidden" name="isAscending" id="isAscending">
	<input type="hidden" name="currentPageNumber" id="currentPageNumber">
	<input type="hidden" name="sqlQuery" id="sqlQuery">
	<input type="hidden" name="searchInput" id="searchInput">		
</div>

<tr>
	<td class="searchpanel" colspan="100" align="center">	 

		&nbsp;&nbsp;&nbsp;Enter User ID
		<html:text styleId="searchQuery" property="searchQuery"></html:text>
		<input type="button" onclick="prepareSearch('<%= component %>', 0);" value="Search">

	 </td>
</tr>


<%

if(!isSearchResultShow)
{
	return;
}

if(columnHeader != null && columnHeader.size() > 0)
{
%>
<tr><td colspan="100"><table style="width: 100%;"  cellspacing='0' cellpadding='0' class="SearchBody"><tbody>

	<tr class="listheader">
	
<%
	int columnNumber = 0;
	%><td>Delete</td><%
	for(String header:columnHeader)
	{
		columnNumber++;
%>
		<td valign='middle' height='23'>
			
			 <%--<a href="javascript:prepareSearch('<%= component %>' , <%= columnNumber %>)"> <bean:message key="<%=header%>"/></a> --%>
			 <a href="#"> <bean:message key="<%=header%>"/></a> 
		</td>
<%
	}
%>		
	</tr>


<%
	if(searchResult != null && searchResult.size() > 0)
	{
		int rowNumber = 0;
		for(ArrayList<String> row:searchResult)
		{
			rowNumber++;			
			
%>
	<tr class="SearchResultRow<%= rowNumber % 2%>">
<%
			if( row != null && row.size() >=2 )
			{
				String componentId = row.get(0);
				int totalColumn = row.size();
				%><td><input type="checkbox" name="deleteCheck" value="<%=row.get(0)%>"></td><%
				for(int index=1;index<totalColumn;index++)
				{
					String column = row.get(index);
					if(modifyURL == null)
					{
%>
		             <td><%=column%></td>
<%
					}
					else
					{
						String url = modifyURL + componentId;
%>
		                <td> <a href="<%=url%>"><%=column%></a> </td>
<%
					
					}
				}
			}
%>		
	</tr>
<%
		}
%>							

<%		
	}
	else
	{
%>
		<tr><td colspan="100" class='normal_txt'> No Entries Present.</td></tr>
<%
	}
%>
</tbody></table></td></tr>
<%
}
%>

<%-- TODO :: IMPLEMENT PAGER HERE --%>
<tr class="pager">
	<td colspan="100" align='center' valign='middle' height='23'>
<%
	int currentPage = 1;
	if(currentPageNumber!=null && !currentPageNumber.equals("null") && currentPageNumber.length()>0)
	{
		currentPage = Integer.parseInt(currentPageNumber);
	}
	if(currentPage > 1)
	{
%>
		<html:link href="javascript:prepareFirst();">First</html:link>
		&nbsp;&nbsp;
		<html:link href="javascript:preparePrevious();">Previous</html:link>
<%
	}
%>
		&nbsp;&nbsp;
		[Page <%= currentPage %> of <%= totalPage %>]			
<%
	if(currentPage < totalPage)
	{
%>
		&nbsp;&nbsp;
		<html:link href="javascript:prepareNext();">Next</html:link>
		&nbsp;&nbsp;
		<html:link href="javascript:prepareLast();">Last</html:link>
<%
	}
%>
	</td>
</tr>