<%@ include file="/Common/Include/taglib.jsp"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.search.SearchOperationResult"%>
<%@ page import="com.swiftcorp.portal.common.search.SearchUtil"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<link rel="stylesheet" type="text/css" href="./Common/Styles/common-styles.css">
<%
	final int SEARCH_RESULT_PER_PAGE = 20;

	SearchOperationResult searchOperationResult = null;
	ArrayList<ArrayList<String>> searchResult = null;
	int totalRow = 0;
	String userId = request.getParameter("userId");
	ArrayList<String> columnHeader = (ArrayList<String>)request.getAttribute(SESSION_KEYS.COLUMN_HEADER_LIST);
	String modifyURL = (String)request.getAttribute(SESSION_KEYS.MODIFYING_URL);

	String component = (String)request.getParameter("component");
	if(component == null)
	{
		//out.println("COMPONENT NAME IS FOUND NULL");
	}
	
	
	searchOperationResult = (SearchOperationResult)request.getAttribute(SESSION_KEYS.ALERT_SEARCH_RESULT);
	boolean isSearchResultShow = SearchUtil.isSearchResultShow(request);
	
	if(searchOperationResult != null)
	{
		searchResult = searchOperationResult.getSearchResult();
		totalRow = searchOperationResult.getTotalRowCount();	
	}
	
	String currentSortColumnNumber = (String)request.getAttribute(SESSION_KEYS.CURRENT_SORT_COLUMN_NUMBER);
	String isAscending = (String)request.getAttribute(SESSION_KEYS.IS_ASCENDING);
	String currentPageNumber = request.getParameter(SESSION_KEYS.CURRENT_PAGE_NUMBER);//(String)request.getAttribute(SESSION_KEYS.CURRENT_PAGE_NUMBER);
	
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
	System.out.println("totalRow::"+totalRow+" SEARCH_RESULT_PER_PAGE::"+SEARCH_RESULT_PER_PAGE);
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
	function submitToParent(url)
	{
		window.opener.location.href=url;
		self.close();
	}
	
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
	function prepareSubmitAction(actionName)
	{
      prepareAlertAction(actionName);
	} 
		
	function prepareAlertAction(actionName)
	{
		if(document.getElementById('alertForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('alertForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddAlert';
		}
		else if(actionName == 'search')
		{
			path += 'promptAlertSearchByUser&userId=<%=userId%>';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchAlert';
		}
		document.getElementById('alertForm').action = path;
		document.getElementById('alertForm').submit();
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
<form name="alertForm" id="alertForm" action="alertAction.csmp?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">

<div>
	<input type="hidden" name="currentSortColumnNumber" id="currentSortColumnNumber">
	<input type="hidden" name="isAscending" id="isAscending">
	<input type="hidden" name="currentPageNumber" id="currentPageNumber">
	<input type="hidden" name="sqlQuery" id="sqlQuery">
	<input type="hidden" name="searchInput" id="searchInput">		
	<input type="hidden" name="searchQuery" id="searchQuery">
</div>
</form>
<%--
<tr >
	<td class="searchpanel" colspan="100" align="center">
		Enter Search Item 
		<html:text styleId="searchQuery" property="searchQuery"></html:text>
		<input type="button" onclick="prepareSearch('<%= component %>', 0);" value="Search">
	</td>
</tr>
--%>

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
		                <td> <a href="<%=url%>" onclick="submitToParent(this.href)"><%=column%></a> </td>
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
	if(Integer.parseInt(currentPageNumber) > 1)
	{
%>
		<html:link href="javascript:prepareFirst();">First</html:link>
		&nbsp;&nbsp;
		<html:link href="javascript:preparePrevious();">Previous</html:link>
<%
	}
%>
		&nbsp;&nbsp;
		[Page <%= currentPageNumber %> of <%= totalPage %>]			
<%
	if(Integer.parseInt(currentPageNumber) < totalPage)
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