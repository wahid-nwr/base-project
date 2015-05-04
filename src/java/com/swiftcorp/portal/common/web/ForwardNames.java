/*
 * @ (#) ForwardNames.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.web;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public interface ForwardNames
{
	String ADD_USER = "add_user_input";
	String EDIT_SIPUSER = "edit_sipuser_input";
	String VIEW_ALL_USER = "view_all_user";
	String VIEW_USER = "view_user";
	String EDIT_USER = "edit_user_input";
	String ERROR = "error";
	String SUCCESS = "success";
	String FAILURE = "failure";
	String USER_LOGIN = "user_login";
	String START = "start";
	String PROMT_LOGIN = "prompt_login";
	String USER_FOLE_FUNCTIONS = "user_role_functions";
	
	/************************ HOME ****************************/
	String SYSTEM_ADMIN_HOME = "system_admin_home"; // system admin home
	String GROUP_ADMIN_HOME = "group_admin_home"; // group admin home
	String GROUP_HOME_SYSTEM_ADMIN = "group_home_system_admin";// system admin
																// at group
																// level
	String USER_HOME = "user_home";
	
	/************************ USER ****************************/
	
	String USER_SEARCH_SYSTEM_LEVEL = "user_search_system_level";
	String USER_SEARCH_GROUP_LEVEL = "user_search_group_level";
	
	String PROMPT_ADD_USER = "prompt_add_user";
	String PROMPT_MODIFY_USER = "prompt_modify_user";
	String PROMPT_PASS_MODIFY_USER = "prompt_pass_modify_user";
	
	/************************ CONTACT ****************************/
	String CONTACT_ADD_FORM = "contactaddform";
	String CONTACT_MODIFY_FORM = "contactmodifyform";
	String CONTACT_SEARCH = "contactsearch";
	
	/************************ ROLE ****************************/
	String ROLE_HOME = "role_home";
	String ROLE_HOME_FROM_GROUP_LEVEL = "role_home_group_level";
	String ROLE_HOME_SYSTEM_LEVEL = "role_home_system_level";
	
	String ROLE_SEARCH_SYSTEM_LEVEL = "role_search_system_level";
	String ROLE_SEARCH_GROUP_LEVEL = "role_search_group_level";
	
	String PROMPT_ADD_ROLE = "prompt_add_role";
	String PROMPT_MODIFY_ROLE = "prompt_modify_role";
	String SHOW_FUNCTIONS = "show_functions";
	
	/************************ GROUP ****************************/
	String GROUP_SEARCH_SYSTEM_LEVEL = "group_search_system_level";
	String GROUP_SEARCH_GROUP_LEVEL = "group_search_group_level";
	
	String PROMPT_ADD_GROUP = "prompt_add_group";
	String PROMPT_MODIFY_GROUP = "prompt_modify_group";
	String PROMPT_MODIFY_GROUP_PROFILE = "prompt_modify_group_profile";
	String PROMPT_MODIFY_GROUP_ACCOUNT = "group_account_modify";
	
	/************************ ADMIN ****************************/
	String ADMIN_HOME_GROUP_LEVEL = "admin_home_group_level";
	String ADMIN_HOME_SYSTEM_LEVEL = "admin_home_system_level";
	String ADMIN_SEARCH_SYSTEM_LEVEL = "admin_search_system_level";
	String ADMIN_SEARCH_GROUP_LEVEL = "admin_search_group_level";
	
	String PROMPT_ADD_ADMIN = "prompt_add_admin";
	String PROMPT_MODIFY_ADMIN = "prompt_modify_admin";
	String PROMPT_PASS_MODIFY_ADMIN = "prompt_pass_modify_admin";
	
	/************************ DIMDIM ****************************/
	String DIMDIM_HOME = "dimdim_home";
	String DIMDIM_HOME_FROM_GROUP_LEVEL = "dimdim_home_group_level";
	String DIMDIM_HOME_SYSTEM_LEVEL = "dimdim_home_system_level";
	String DIMDIM_SEARCH_SYSTEM_LEVEL = "dimdim_search_system_level";
	String DIMDIM_SEARCH_GROUP_LEVEL = "dimdim_search_group_level";
	
	String PROMPT_ADD_DIMDIM = "prompt_add_dimdim";
	String PROMPT_MODIFY_DIMDIM = "prompt_modify_dimdim";
	
	/************************ TOMTOM ****************************/
	String TOMTOM_HOME = "tomtom_home";
	String TOMTOM_HOME_FROM_GROUP_LEVEL = "tomtom_home_group_level";
	String TOMTOM_HOME_SYSTEM_LEVEL = "tomtom_home_system_level";
	String TOMTOM_SEARCH_SYSTEM_LEVEL = "tomtom_search_system_level";
	String TOMTOM_SEARCH_GROUP_LEVEL = "tomtom_search_group_level";
	
	String PROMPT_ADD_TOMTOM = "prompt_add_tomtom";
	String PROMPT_MODIFY_TOMTOM = "prompt_modify_tomtom";
	
	/************************ QUESTION ****************************/
	String QUESTION_HOME = "question_home";
	String QUESTION_HOME_FROM_GROUP_LEVEL = "question_home_group_level";
	String QUESTION_HOME_SYSTEM_LEVEL = "question_home_system_level";
	String QUESTION_SEARCH_SYSTEM_LEVEL = "question_search_system_level";
	String QUESTION_SEARCH_GROUP_LEVEL = "question_search_group_level";
	String QUESTIONNAIRE_SEARCH_SYSTEM_LEVEL = "questionnaire_search_system_level";
	String QUESTIONNAIRE_QUESTION_SERIAL = "questionnaire_question_serial";
	String QUESTIONNAIRE_QUESTION_SERIAL_ADD = "questionnaire_question_serial_add";
	String QUESTIONNAIRE_SERIAL = "questionnaire_serial";
	
	String PROMPT_ADD_QUESTION = "prompt_add_question";
	String PROMPT_MODIFY_QUESTION = "prompt_modify_question";
	
	String PROMPT_ADD_QUESTIONNAIRE = "prompt_add_questionnaire";
	String PROMPT_MODIFY_QUESTIONNAIRE = "prompt_modify_questionnaire";
	
	/************************ BENEFICIARY ****************************/
	String BENEFICIARY_HOME = "beneficiary_home";
	String CHILD_BENEFICIARY_HOME = "child_beneficiary_home";
	String BENEFICIARY_HOME_FROM_GROUP_LEVEL = "beneficiary_home_group_level";
	String BENEFICIARY_HOME_SYSTEM_LEVEL = "beneficiary_home_system_level";
	String BENEFICIARY_SEARCH_SYSTEM_LEVEL = "beneficiary_search_system_level";
	String BENEFICIARY_SEARCH_GROUP_LEVEL = "beneficiary_search_group_level";
	
	String PROMPT_ADD_BENEFICIARY = "prompt_add_beneficiary";
	String PROMPT_MODIFY_BENEFICIARY = "prompt_modify_beneficiary";
	String PROMPT_SHOW_MOTHER_BENEFICIARY = "prompt_show_mother_beneficiary";
	String PROMPT_SHOW_CHILD_BENEFICIARY = "prompt_show_child_beneficiary";
	String PROMPT_MODIFY_CHILD_BENEFICIARY = "prompt_modify_child_beneficiary";
	
	/************************ ALGORITHM ****************************/
	String ALGORITHM_HOME = "algorithm_home";
	String ALGORITHM_HOME_FROM_GROUP_LEVEL = "algorithm_home_group_level";
	String ALGORITHM_HOME_SYSTEM_LEVEL = "algorithm_home_system_level";
	String ALGORITHM_SEARCH_SYSTEM_LEVEL = "algorithm_search_system_level";
	String ALGORITHM_SEARCH_GROUP_LEVEL = "algorithm_search_group_level";
	
	String PROMPT_ADD_ALGORITHM = "prompt_add_algorithm";
	String PROMPT_MODIFY_ALGORITHM = "prompt_modify_algorithm";
	String PROMPT_ADD_ALGORITHM_FIRST_ALG = "prompt_add_algorithm_first_alg";
	
	
	/************************ ALERT ****************************/
	String ALERT_HOME = "alert_home";
	String ALERT_HOME_FROM_GROUP_LEVEL = "alert_home_group_level";
	String ALERT_HOME_SYSTEM_LEVEL = "alert_home_system_level";
	String ALERT_SEARCH_SYSTEM_LEVEL = "alert_search_system_level";
	String ALERT_SEARCH_GROUP_LEVEL = "alert_search_group_level";
	String ALERT_SEARCH_BY_USER_POPUP = "alert_search_by_user_popup";
	String PROMPT_ADD_ALERT = "prompt_add_alert";
	String PROMPT_MODIFY_ALERT = "prompt_modify_alert";
	
	/************************ SCHEDULE ****************************/
	String SCHEDULE_HOME = "schedule_home";
	String SCHEDULE_HOME_FROM_GROUP_LEVEL = "schedule_home_group_level";
	String SCHEDULE_HOME_SYSTEM_LEVEL = "schedule_home_system_level";
	String SCHEDULE_SEARCH_SYSTEM_LEVEL = "schedule_search_system_level";
	String SCHEDULE_SEARCH_GROUP_LEVEL = "schedule_search_group_level";
	
	String PROMPT_ADD_SCHEDULE = "prompt_add_schedule";
	String PROMPT_MODIFY_SCHEDULE = "prompt_modify_schedule";
	
	/************************ HOUSEHOLD ****************************/
	String HOUSEHOLD_HOME = "household_home";
	String HOUSEHOLD_HOME_FROM_GROUP_LEVEL = "household_home_group_level";
	String HOUSEHOLD_HOME_SYSTEM_LEVEL = "household_home_system_level";
	String HOUSEHOLD_SEARCH_SYSTEM_LEVEL = "household_search_system_level";
	String HOUSEHOLD_SEARCH_GROUP_LEVEL = "household_search_group_level";
	String HOUSEHOLD_SEARCH_BY_USER_POPUP = "household_search_by_user_popup";
	String PROMPT_ADD_HOUSEHOLD = "prompt_add_household";
	String PROMPT_MODIFY_HOUSEHOLD = "prompt_modify_household";
	
	/************************ RISK ****************************/
	String RISK_HOME = "risk_home";
	String RISK_HOME_FROM_GROUP_LEVEL = "risk_home_group_level";
	String RISK_HOME_SYSTEM_LEVEL = "risk_home_system_level";
	String RISK_SEARCH_SYSTEM_LEVEL = "risk_search_system_level";
	String RISK_SEARCH_GROUP_LEVEL = "risk_search_group_level";
	
	String PROMPT_ADD_RISK = "prompt_add_risk";
	String PROMPT_MODIFY_RISK = "prompt_modify_risk";
	
	/************************GEO ****************************/
	String GEO_HOME = "geo_home"; 
	String GEO_HOME_FROM_GROUP_LEVEL = "geo_home_group_level"; 
	String GEO_HOME_SYSTEM_LEVEL = "geo_home_system_level";
	String GEO_SEARCH_SYSTEM_LEVEL = "geo_search_system_level";
	String GEO_SEARCH_GROUP_LEVEL = "geo_search_group_level";
	
	String PROMPT_ADD_GEO = "prompt_add_geo";	
	String PROMPT_MODIFY_GEO = "prompt_modify_geo";
	String PROMPT_IMPORT_GEO = "prompt_import_geo";	
	
	
	/************************DCRINFO ****************************/
	String DCRINFO_HOME = "dcrinfo_home"; 
	String DCRINFO_HOME_FROM_GROUP_LEVEL = "dcrinfo_home_group_level"; 
	String DCRINFO_HOME_SYSTEM_LEVEL = "dcrinfo_home_system_level";
	String DCRINFO_SEARCH_SYSTEM_LEVEL = "dcrinfo_search_system_level";
	String DCRINFO_SEARCH_GROUP_LEVEL = "dcrinfo_search_group_level";
	String EXT_DCRINFO_SEARCH_SYSTEM_LEVEL = "ext_dcrinfo_search_system_level";
	
	String PROMPT_ADD_DCRINFO = "prompt_add_dcrinfo";	
	String DCRINFO_ADD_SUCCESS = "dcrinfo_add_success";
	String PROMPT_MODIFY_DCRINFO = "prompt_modify_dcrinfo";	
	
	String PROMPT_IMPORT = "prompt_import";
	/************************DCRREPORT ****************************/
	String DCRREPORT_HOME = "dcrreport_home"; 
	String DCRREPORT_HOME_FROM_GROUP_LEVEL = "dcrreport_home_group_level"; 
	String DCRREPORT_HOME_SYSTEM_LEVEL = "dcrreport_home_system_level";
	String DCRREPORT_SEARCH_SYSTEM_LEVEL = "dcrreport_search_system_level";
	String DCRREPORT_SEARCH_GROUP_LEVEL = "dcrreport_search_group_level";
	String EXT_DCRREPORT_SEARCH_SYSTEM_LEVEL = "ext_dcrreport_search_system_level";
	
	String PROMPT_ADD_DCRREPORT = "prompt_add_dcrreport";	
	String PROMPT_MODIFY_DCRREPORT = "prompt_modify_dcrreport";	
	
	String EXT_FORM_SUBMIT_EXCEPTION = "ext_form_submit_exception";
	String EXT_FORM_ADD_SUCCESS = "ext_form_add_success";
	/************************ACCOUNT ****************************/
	String ACCOUNT_HOME = "account_home"; 
	String ACCOUNT_HOME_FROM_GROUP_LEVEL = "account_home_group_level"; 
	String ACCOUNT_HOME_SYSTEM_LEVEL = "account_home_system_level";
	String ACCOUNT_SEARCH_SYSTEM_LEVEL = "account_search_system_level";
	String EXT_ACCOUNT_SEARCH_SYSTEM_LEVEL = "ext_account_search_system_level";
	String ACCOUNT_SEARCH_GROUP_LEVEL = "account_search_group_level";
	
	String PROMPT_ADD_ACCOUNT = "prompt_add_account";	
	String PROMPT_MODIFY_ACCOUNT = "prompt_modify_account";	

	/************************PATIENT ****************************/
	String PATIENT_HOME = "patient_home"; 
	String PATIENT_HOME_FROM_GROUP_LEVEL = "patient_home_group_level"; 
	String PATIENT_HOME_SYSTEM_LEVEL = "patient_home_system_level";
	String PATIENT_SEARCH_SYSTEM_LEVEL = "patient_search_system_level";
	String EXT_PATIENT_SEARCH_SYSTEM_LEVEL = "ext_patient_search_system_level";
	String PATIENT_SEARCH_GROUP_LEVEL = "patient_search_group_level";
	
	String PROMPT_ADD_PATIENT = "prompt_add_patient";	
	String PROMPT_MODIFY_PATIENT = "prompt_modify_patient";	

 }
