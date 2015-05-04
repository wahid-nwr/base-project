package com.swiftcorp.portal.common.search;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;

public interface ISearcher
{
	public SearchOperationResult search ( String query )
			throws SystemException, InvalidSQLSyntaxException;
	
	public SearchOperationResult searchQuestionnaire ( String query )
			throws SystemException, InvalidSQLSyntaxException;
	
}
