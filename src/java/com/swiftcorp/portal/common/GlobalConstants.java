package com.swiftcorp.portal.common;

public interface GlobalConstants
{
	
	String ADD_OPERATION = "add_operation";
	String MODIFY_OPERATION = "modify_operation";
	String SEARCH_OPERATION = "search_operation";
	String REMOVE_OPERATION = "remove_operation";
	String IMPORT_OPERATION = "import_operation";
	String PROCESS_OPERATION = "process_operation";
	String VIEW_OPERATION = "view_operation";
	String PASSCHANGE_OPERATION = "passchange_operation";
	String FUND_DEPOSIT_OPERATION = "fund_deposit";
	String FUND_ADJUSTMENT_OPERATION = "fund_adjustment";
	
	public static final int SYSTEM_LEVEL = 1;
	public static final int GROUP_LEVEL = 2;
	public static final int END_USR_LEVEL = 3;
	
	public static final int SEARCH_RESULT_PER_PAGE = 20;
	
	public static final int STATUS_ENABLED = 1;
	public static final int STATUS_DISABLED = 0;
	
	public static final int ACCESS_VIEW_ONLY = 1;
	public static final int ACCESS_ADD_MODIFY = 2;
	public static final int ACCESS_ADD_MODIFY_DELETE = 3; // view+ add + modify
															// + remove
	
	public static final int LOAN_TYPE_CONTINIOUS = 1;
	public static final int LOAN_TYPE_DEMAND = 2;
	public static final int LOAN_TYPE_FIXED_TERM_BELOW_5YEARS = 3;
	public static final int LOAN_TYPE_FIXED_TERM_ABOVE_5YEARS = 4;
	public static final int LOAN_TYPE_SHORT_TERM = 5;
	
}
