package com.swiftcorp.portal.common.web;

public interface SESSION_KEYS
{
	String OPERATION_TYPE = "operation_type";
	String CURRENT_SORT_COLUMN_NUMBER = "currentSortColumnNumber";
	String IS_ASCENDING = "isAscending";
	String CURRENT_PAGE_NUMBER = "currentPageNumber";
	String LOCALE_LIST = "localeList";
	String DOMAIN_NAME = "domainName";
	String LOGIN_INFO = "logininfo";
	String IS_SESSION_EXPIRE = "is_session_expire";
	String MODIFYING_URL = "modifying_url";
	String MODIFYING_MOTHER_URL = "modifying_mother_url";
	String MODIFYING_CHILD_URL = "modifying_child_url";
	String COLUMN_HEADER_LIST = "column_headers";
	String SUB_OPERATION_TYPE = "sub_operation_type";
	
	String IS_SEARCH_RESULT_SHOW = "issearchresultshow";
	String WORKING_LEVEL = "user_working_level";
	String SEARCH_RESULT_SUFFIX = "_search_result";
	
	String USER = "user";
	String USER_SEARCH_RESULT = "user_search_result";
	String USER_LIST = "user_list";
	
	String CONTACT = "contact";
	String CONTACT_SEARCH_RESULT = "contact_search_result";
	
	/************************ ROLE ****************************/
	String ROLE = "role";
	String ROLE_LIST = "role_list";
	String ROLE_SEARCH_RESULT = "role_search_result";
	String ACCESS_LEVEL_LIST = "access_level_list";
	
	/************************ GROUP ****************************/
	String GROUP = "group";
	String GROUP_LIST = "group_list";
	String GROUP_SEARCH_RESULT = "group_search_result";
	
	/************************ MAIL ****************************/
	String MAIL = "mail";
	String MAIL_LIST = "mail_list";
	String MAIL_SEARCH_RESULT = "mail_search_result";
	
	/************************ ADMIN ****************************/
	String ADMIN = "admin";
	String ADMIN_LIST = "admin_list";
	String ADMIN_SEARCH_RESULT = "admin_search_result";
	
	/************************ CODE ****************************/
	String CODE = "code";
	String CODE_LIST = "code_list";
	String CODE_SEARCH_RESULT = "code_search_result";
	
	/************************ DATAPROCESSOR ****************************/
	String DATAPROCESSOR = "dataprocessor";
	String DATAPROCESSOR_LIST = "dataprocessor_list";
	String DATAPROCESSOR_SEARCH_RESULT = "dataprocessor_search_result";
	
	/************************ DATAIMPORT ****************************/
	String DATAIMPORT = "dataimport";
	String DATAIMPORT_LIST = "dataimport_list";
	String DATAIMPORT_SEARCH_RESULT = "dataimport_search_result";
	String REPORT_ID_LIST = "reportid_list";
	String MONTH_LIST = "month_list";
	String YEAR_LIST = "year_list";
	
	/************************ FILE UPLOAD ****************************/
	String ABSENT_COLUMN_NAME_LIST = "absent_column_name_list";
	String MISSING_COLUMN_NAME_LIST = "missing_column_name_list";
	
	String PROCESSED_DATA_SEARCH_RESULT = "processed_data_search_result";
	
	/************************ DIMDIM ****************************/
	String DIMDIM = "dimdim";
	String DIMDIM_LIST = "dimdim_list";
	String DIMDIM_SEARCH_RESULT = "dimdim_search_result";
	
	/************************ TOMTOM ****************************/
	String TOMTOM = "tomtom";
	String TOMTOM_LIST = "tomtom_list";
	String TOMTOM_SEARCH_RESULT = "tomtom_search_result";
	
	/************************ QUESTION ****************************/
	String QUESTION = "question";
	String QUESTION_LIST = "question_list";
	String QUESTION_TO_MODIFY = "question_to_modify";
	String QUESTION_SEARCH_RESULT = "question_search_result";
	String ANSWERTYPE_LIST = "answer_type_list";
	String CATEGORY_LIST = "category_list";
	String QUESTIONNAIRE_LIST = "questionnaire_list";
	String QUESTIONNAIRE_SEARCH_RESULT = "questionnaire_search_result";
	String QUESTIONNAIRE_TO_MODIFY = "questionnaire_to_modify";
	String QUESTIONNAIRE_STATUS_LIST = "questionnaire_status_list";
	
	/************************ Role function ****************************/
	// function dto list
	String FUNCTIONDTO_LIST = "functionDTOList";
	String FUNCTIONDTO_SET = "functionDTOSet";
	
	/************************ BENEFICIARY ****************************/
	String BENEFICIARY = "beneficiary";
	String BENEFICIARY_LIST = "beneficiary_list";
	String BENEFICIARY_SEARCH_RESULT = "beneficiary_search_result";
	String BENEFICIARY_TO_MODIFY = "beneficiary_to_modify";
	String HHDTO_OF_MEMBER = "hh_dto_of_member";
	String SK_ID = "sk_id";
	String HHMEMBERDTO_OF_MEMBER = "hh_member_dto_of_member";
	String CHILDREN_OF_MOTHER = "children_of_mother";
	String HHMOTHERDTO_OF_CHILD = "hh_motherdto_of_child";
	String RISK_OF_BENEFICIARY = "risk_of_beneficiary";
	
	/************************ ALGORITHM ****************************/
	String ALGORITHM = "algorithm";
	String ALGORITHM_LIST = "algorithm_list";
	String ALGORITHM_SEARCH_RESULT = "algorithm_search_result";
	String ALGORITHM_DTO_TO_ADD = "algorithm_dto_to_add";
	String PREV_ALG_QUESTION = "prev_alg_question";
	
	/************************ ALERT ****************************/
	String ALERT = "alert";
	String ALERT_LIST = "alert_list";
	String ALERT_SEARCH_RESULT = "alert_search_result";
	
	/************************ SCHEDULE ****************************/
	String SCHEDULE = "schedule";
	String SCHEDULE_LIST = "schedule_list";
	String SCHEDULE_SEARCH_RESULT = "schedule_search_result";
	
	/************************ HOUSEHOLD ****************************/
	String HOUSEHOLD = "household";
	String HOUSEHOLD_LIST = "household_list";
	String HOUSEHOLD_SEARCH_RESULT = "household_search_result";
	String HOUSEHOLD_TO_MODIFY = "household_to_modify";
	
	/************************ USER ****************************/
	String USER_TO_MODIFY = "user_to_modify";
	/************************ RISK ****************************/
	String RISK = "risk";
	String RISK_LIST = "risk_list";
	String RISK_SEARCH_RESULT = "risk_search_result";
	
	/************************GEO ****************************/
	String GEO = "geo";
	String GEO_LIST_CC = "geo_list_CC";
	String GEO_LIST_BRANCH = "geo_list_branch";
	String GEO_LIST_REGION = "geo_list_region";
	String GEO_SEARCH_RESULT = "geo_search_result";
	String GEO_PARENT_LIST = "geo_parent_list";
	String GEO_TYPE_LIST = "geo_type_list";
	
	String USER_AREA_TO_ADD = "user_area_to_add";
	String LOGIN_USER_AREA = "login_user_area";
	String LOGIN_USER_CHILD_AREA = "login_user_child_area";
	
	/************************DCRINFO ****************************/
	String DCRINFO = "dcrinfo";
	String DCRINFO_LIST = "dcrinfo_list";
	String DCRINFO_SEARCH_RESULT = "dcrinfo_search_result";

	/************************DCRREPORT ****************************/
	String DCRREPORT = "dcrreport";
	String DCRREPORT_LIST = "dcrreport_list";
	String DCRREPORT_SEARCH_RESULT = "dcrreport_search_result";
	/************************ACCOUNT ****************************/
	String ACCOUNT = "account";
	String ACCOUNT_LIST = "account_list";
	String ACCOUNT_SEARCH_RESULT = "account_search_result";

	/************************PATIENT ****************************/
	String PATIENT = "patient";
	String PATIENT_LIST = "patient_list";
	String PATIENT_SEARCH_RESULT = "patient_search_result";

	/************************HELLO ****************************/
	String HELLO = "hello";
	String HELLO_LIST = "hello_list";
	String HELLO_SEARCH_RESULT = "hello_search_result";

	/************************HOME ****************************/
	String HOME = "home";
	String HOME_LIST = "home_list";
	String HOME_SEARCH_RESULT = "home_search_result";

	/************************INFO ****************************/
	String INFO = "info";
	String INFO_LIST = "info_list";
	String INFO_SEARCH_RESULT = "info_search_result";

	/************************PAITENPROFILE ****************************/
	String PAITENPROFILE = "paitenprofile";
	String PAITENPROFILE_LIST = "paitenprofile_list";
	String PAITENPROFILE_SEARCH_RESULT = "paitenprofile_search_result";

	/************************AJAN ****************************/
	String AJAN = "ajan";
	String AJAN_LIST = "ajan_list";
	String AJAN_SEARCH_RESULT = "ajan_search_result";

 }
