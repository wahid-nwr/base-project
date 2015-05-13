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
 * @author swift
 * @since mar 3, 2011
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
	String LOGIN_SUCCESS = "login_success";
	String USER_FOLE_FUNCTIONS = "user_role_functions";
	
	String EXT_TEST = "ext_test";
	
	/************************ HOME ****************************/
	String SYSTEM_ADMIN_HOME = "system_admin_home"; // system admin home
	String GROUP_ADMIN_HOME = "group_admin_home"; // group admin home
	String GROUP_HOME_SYSTEM_ADMIN = "group_home_system_admin";// system admin
																// at group
																// level
	String USER_HOME = "user_home";
	
	/************************ USER ****************************/
	
	String USER_SEARCH_SYSTEM_LEVEL = "user_search_system_level";
	String EXT_USER_SEARCH_SYSTEM_LEVEL = "ext_user_search_system_level";
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
	String GET_ROLE_LIST = "get_role_list";
	String SHOW_FUNCTIONS = "show_functions";
	String GET_ROLE_FUNCTION_LIST = "get_role_function_list";
	String GET_ROLE_FUNCTIONS_LIST = "get_role_functions_list";
	
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
	String EXT_ACCOUNT_TREE_SEARCH_SYSTEM_LEVEL = "ext_account_tree_search_system_level";
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

	/************************HELLO ****************************/
	String HELLO_HOME = "hello_home"; 
	String HELLO_HOME_FROM_GROUP_LEVEL = "hello_home_group_level"; 
	String HELLO_HOME_SYSTEM_LEVEL = "hello_home_system_level";
	String HELLO_SEARCH_SYSTEM_LEVEL = "hello_search_system_level";
	String EXT_HELLO_SEARCH_SYSTEM_LEVEL = "ext_hello_search_system_level";
	String HELLO_SEARCH_GROUP_LEVEL = "hello_search_group_level";
	
	String PROMPT_ADD_HELLO = "prompt_add_hello";	
	String PROMPT_MODIFY_HELLO = "prompt_modify_hello";	

	/************************HOME ****************************/
	String HOME_HOME = "home_home"; 
	String HOME_HOME_FROM_GROUP_LEVEL = "home_home_group_level"; 
	String HOME_HOME_SYSTEM_LEVEL = "home_home_system_level";
	String HOME_SEARCH_SYSTEM_LEVEL = "home_search_system_level";
	String EXT_HOME_SEARCH_SYSTEM_LEVEL = "ext_home_search_system_level";
	String HOME_SEARCH_GROUP_LEVEL = "home_search_group_level";
	
	String PROMPT_ADD_HOME = "prompt_add_home";	
	String PROMPT_MODIFY_HOME = "prompt_modify_home";	

	/************************INFO ****************************/
	String INFO_HOME = "info_home"; 
	String INFO_HOME_FROM_GROUP_LEVEL = "info_home_group_level"; 
	String INFO_HOME_SYSTEM_LEVEL = "info_home_system_level";
	String INFO_SEARCH_SYSTEM_LEVEL = "info_search_system_level";
	String EXT_INFO_SEARCH_SYSTEM_LEVEL = "ext_info_search_system_level";
	String INFO_SEARCH_GROUP_LEVEL = "info_search_group_level";
	
	String PROMPT_ADD_INFO = "prompt_add_info";	
	String PROMPT_MODIFY_INFO = "prompt_modify_info";	

	/************************ITEM ****************************/
	String ITEM_HOME = "item_home"; 
	String ITEM_HOME_FROM_GROUP_LEVEL = "item_home_group_level"; 
	String ITEM_HOME_SYSTEM_LEVEL = "item_home_system_level";
	String ITEM_SEARCH_SYSTEM_LEVEL = "item_search_system_level";
	String EXT_ITEM_SEARCH_SYSTEM_LEVEL = "ext_item_search_system_level";
	String ITEM_SEARCH_GROUP_LEVEL = "item_search_group_level";
	
	String PROMPT_ADD_ITEM = "prompt_add_item";	
	String PROMPT_MODIFY_ITEM = "prompt_modify_item";	

	/************************BATCH ****************************/
	String BATCH_HOME = "batch_home"; 
	String BATCH_HOME_FROM_GROUP_LEVEL = "batch_home_group_level"; 
	String BATCH_HOME_SYSTEM_LEVEL = "batch_home_system_level";
	String BATCH_SEARCH_SYSTEM_LEVEL = "batch_search_system_level";
	String EXT_BATCH_SEARCH_SYSTEM_LEVEL = "ext_batch_search_system_level";
	String BATCH_SEARCH_GROUP_LEVEL = "batch_search_group_level";
	
	String PROMPT_ADD_BATCH = "prompt_add_batch";	
	String PROMPT_MODIFY_BATCH = "prompt_modify_batch";	

	/************************TRANSACTION ****************************/
	String TRANSACTION_HOME = "transaction_home"; 
	String TRANSACTION_HOME_FROM_GROUP_LEVEL = "transaction_home_group_level"; 
	String TRANSACTION_HOME_SYSTEM_LEVEL = "transaction_home_system_level";
	String TRANSACTION_SEARCH_SYSTEM_LEVEL = "transaction_search_system_level";
	String EXT_TRANSACTION_SEARCH_SYSTEM_LEVEL = "ext_transaction_search_system_level";
	String EXT_TRANSACTION_DETAIL_SEARCH_SYSTEM_LEVEL = "ext_transaction_detail_search_system_level";
	String TRANSACTION_SEARCH_GROUP_LEVEL = "transaction_search_group_level";
	
	String PROMPT_ADD_TRANSACTION = "prompt_add_transaction";	
	String PROMPT_MODIFY_TRANSACTION = "prompt_modify_transaction";	

	/************************TRAVELSCHEDULE ****************************/
	String TRAVELSCHEDULE_HOME = "travelschedule_home"; 
	String TRAVELSCHEDULE_HOME_FROM_GROUP_LEVEL = "travelschedule_home_group_level"; 
	String TRAVELSCHEDULE_HOME_SYSTEM_LEVEL = "travelschedule_home_system_level";
	String TRAVELSCHEDULE_SEARCH_SYSTEM_LEVEL = "travelschedule_search_system_level";
	String EXT_TRAVELSCHEDULE_SEARCH_SYSTEM_LEVEL = "ext_travelschedule_search_system_level";
	String TRAVELSCHEDULE_SEARCH_GROUP_LEVEL = "travelschedule_search_group_level";
	
	String PROMPT_ADD_TRAVELSCHEDULE = "prompt_add_travelschedule";	
	String PROMPT_MODIFY_TRAVELSCHEDULE = "prompt_modify_travelschedule";	

	/************************VEHICLE ****************************/
	String VEHICLE_HOME = "vehicle_home"; 
	String VEHICLE_HOME_FROM_GROUP_LEVEL = "vehicle_home_group_level"; 
	String VEHICLE_HOME_SYSTEM_LEVEL = "vehicle_home_system_level";
	String VEHICLE_SEARCH_SYSTEM_LEVEL = "vehicle_search_system_level";
	String EXT_VEHICLE_SEARCH_SYSTEM_LEVEL = "ext_vehicle_search_system_level";
	String VEHICLE_SEARCH_GROUP_LEVEL = "vehicle_search_group_level";
	
	String PROMPT_ADD_VEHICLE = "prompt_add_vehicle";	
	String PROMPT_MODIFY_VEHICLE = "prompt_modify_vehicle";	

	/************************ROUTE ****************************/
	String ROUTE_HOME = "route_home"; 
	String ROUTE_HOME_FROM_GROUP_LEVEL = "route_home_group_level"; 
	String ROUTE_HOME_SYSTEM_LEVEL = "route_home_system_level";
	String ROUTE_SEARCH_SYSTEM_LEVEL = "route_search_system_level";
	String EXT_ROUTE_SEARCH_SYSTEM_LEVEL = "ext_route_search_system_level";
	String ROUTE_SEARCH_GROUP_LEVEL = "route_search_group_level";
	
	String PROMPT_ADD_ROUTE = "prompt_add_route";	
	String PROMPT_MODIFY_ROUTE = "prompt_modify_route";	

	/************************BUYER ****************************/
	String BUYER_HOME = "buyer_home"; 
	String BUYER_HOME_FROM_GROUP_LEVEL = "buyer_home_group_level"; 
	String BUYER_HOME_SYSTEM_LEVEL = "buyer_home_system_level";
	String BUYER_SEARCH_SYSTEM_LEVEL = "buyer_search_system_level";
	String EXT_BUYER_SEARCH_SYSTEM_LEVEL = "ext_buyer_search_system_level";
	String BUYER_SEARCH_GROUP_LEVEL = "buyer_search_group_level";
	
	String PROMPT_ADD_BUYER = "prompt_add_buyer";	
	String PROMPT_MODIFY_BUYER = "prompt_modify_buyer";	

	/************************RESERVATION ****************************/
	String RESERVATION_HOME = "reservation_home"; 
	String RESERVATION_HOME_FROM_GROUP_LEVEL = "reservation_home_group_level"; 
	String RESERVATION_HOME_SYSTEM_LEVEL = "reservation_home_system_level";
	String RESERVATION_SEARCH_SYSTEM_LEVEL = "reservation_search_system_level";
	String EXT_RESERVATION_SEARCH_SYSTEM_LEVEL = "ext_reservation_search_system_level";
	String RESERVATION_SEARCH_GROUP_LEVEL = "reservation_search_group_level";
	
	String PROMPT_ADD_RESERVATION = "prompt_add_reservation";	
	String PROMPT_MODIFY_RESERVATION = "prompt_modify_reservation";	

	/************************TRIP ****************************/
	String TRIP_HOME = "trip_home"; 
	String TRIP_HOME_FROM_GROUP_LEVEL = "trip_home_group_level"; 
	String TRIP_HOME_SYSTEM_LEVEL = "trip_home_system_level";
	String TRIP_SEARCH_SYSTEM_LEVEL = "trip_search_system_level";
	String EXT_TRIP_SEARCH_SYSTEM_LEVEL = "ext_trip_search_system_level";
	String TRIP_SEARCH_GROUP_LEVEL = "trip_search_group_level";
	
	String PROMPT_ADD_TRIP = "prompt_add_trip";	
	String PROMPT_MODIFY_TRIP = "prompt_modify_trip";	

	/************************STATION ****************************/
	String STATION_HOME = "station_home"; 
	String STATION_HOME_FROM_GROUP_LEVEL = "station_home_group_level"; 
	String STATION_HOME_SYSTEM_LEVEL = "station_home_system_level";
	String STATION_SEARCH_SYSTEM_LEVEL = "station_search_system_level";
	String EXT_STATION_SEARCH_SYSTEM_LEVEL = "ext_station_search_system_level";
	String STATION_SEARCH_GROUP_LEVEL = "station_search_group_level";
	
	String PROMPT_ADD_STATION = "prompt_add_station";	
	String PROMPT_MODIFY_STATION = "prompt_modify_station";	
	
	/************************SUBROUTE ****************************/
	String SUBROUTE_HOME = "subroute_home"; 
	String SUBROUTE_HOME_FROM_GROUP_LEVEL = "subroute_home_group_level"; 
	String SUBROUTE_HOME_SYSTEM_LEVEL = "subroute_home_system_level";
	String SUBROUTE_SEARCH_SYSTEM_LEVEL = "subroute_search_system_level";
	String EXT_SUBROUTE_SEARCH_SYSTEM_LEVEL = "ext_subroute_search_system_level";
	String SUBROUTE_SEARCH_GROUP_LEVEL = "subroute_search_group_level";
	
	String PROMPT_ADD_SUBROUTE = "prompt_add_subroute";	
	String PROMPT_MODIFY_SUBROUTE = "prompt_modify_subroute";	

	/************************STORE ****************************/
	String STORE_HOME = "store_home"; 
	String STORE_HOME_FROM_GROUP_LEVEL = "store_home_group_level"; 
	String STORE_HOME_SYSTEM_LEVEL = "store_home_system_level";
	String STORE_SEARCH_SYSTEM_LEVEL = "store_search_system_level";
	String EXT_STORE_SEARCH_SYSTEM_LEVEL = "ext_store_search_system_level";
	String STORE_SEARCH_GROUP_LEVEL = "store_search_group_level";
	
	String PROMPT_ADD_STORE = "prompt_add_store";	
	String PROMPT_MODIFY_STORE = "prompt_modify_store";	

	/************************COVERING ****************************/
	String COVERING_HOME = "covering_home"; 
	String COVERING_HOME_FROM_GROUP_LEVEL = "covering_home_group_level"; 
	String COVERING_HOME_SYSTEM_LEVEL = "covering_home_system_level";
	String COVERING_SEARCH_SYSTEM_LEVEL = "covering_search_system_level";
	String EXT_COVERING_SEARCH_SYSTEM_LEVEL = "ext_covering_search_system_level";
	String COVERING_SEARCH_GROUP_LEVEL = "covering_search_group_level";
	
	String PROMPT_ADD_COVERING = "prompt_add_covering";	
	String PROMPT_MODIFY_COVERING = "prompt_modify_covering";	

	/************************TRANSPORTATION ****************************/
	String TRANSPORTATION_HOME = "transportation_home"; 
	String TRANSPORTATION_HOME_FROM_GROUP_LEVEL = "transportation_home_group_level"; 
	String TRANSPORTATION_HOME_SYSTEM_LEVEL = "transportation_home_system_level";
	String TRANSPORTATION_SEARCH_SYSTEM_LEVEL = "transportation_search_system_level";
	String EXT_TRANSPORTATION_SEARCH_SYSTEM_LEVEL = "ext_transportation_search_system_level";
	String TRANSPORTATION_SEARCH_GROUP_LEVEL = "transportation_search_group_level";
	
	String PROMPT_ADD_TRANSPORTATION = "prompt_add_transportation";	
	String PROMPT_MODIFY_TRANSPORTATION = "prompt_modify_transportation";	

	/************************PAYMENT ****************************/
	String PAYMENT_HOME = "payment_home"; 
	String PAYMENT_HOME_FROM_GROUP_LEVEL = "payment_home_group_level"; 
	String PAYMENT_HOME_SYSTEM_LEVEL = "payment_home_system_level";
	String PAYMENT_SEARCH_SYSTEM_LEVEL = "payment_search_system_level";
	String EXT_PAYMENT_SEARCH_SYSTEM_LEVEL = "ext_payment_search_system_level";
	String PAYMENT_SEARCH_GROUP_LEVEL = "payment_search_group_level";
	
	String PROMPT_ADD_PAYMENT = "prompt_add_payment";	
	String PROMPT_MODIFY_PAYMENT = "prompt_modify_payment";	

	/************************CREDITTERM ****************************/
	String CREDITTERM_HOME = "creditterm_home"; 
	String CREDITTERM_HOME_FROM_GROUP_LEVEL = "creditterm_home_group_level"; 
	String CREDITTERM_HOME_SYSTEM_LEVEL = "creditterm_home_system_level";
	String CREDITTERM_SEARCH_SYSTEM_LEVEL = "creditterm_search_system_level";
	String EXT_CREDITTERM_SEARCH_SYSTEM_LEVEL = "ext_creditterm_search_system_level";
	String CREDITTERM_SEARCH_GROUP_LEVEL = "creditterm_search_group_level";
	
	String PROMPT_ADD_CREDITTERM = "prompt_add_creditterm";	
	String PROMPT_MODIFY_CREDITTERM = "prompt_modify_creditterm";	

	/************************UNIT ****************************/
	String UNIT_HOME = "unit_home"; 
	String UNIT_HOME_FROM_GROUP_LEVEL = "unit_home_group_level"; 
	String UNIT_HOME_SYSTEM_LEVEL = "unit_home_system_level";
	String UNIT_SEARCH_SYSTEM_LEVEL = "unit_search_system_level";
	String EXT_UNIT_SEARCH_SYSTEM_LEVEL = "ext_unit_search_system_level";
	String UNIT_SEARCH_GROUP_LEVEL = "unit_search_group_level";
	
	String PROMPT_ADD_UNIT = "prompt_add_unit";	
	String PROMPT_MODIFY_UNIT = "prompt_modify_unit";	

	/************************PRODUCTIONSTEP ****************************/
	String PRODUCTIONSTEP_HOME = "productionstep_home"; 
	String PRODUCTIONSTEP_HOME_FROM_GROUP_LEVEL = "productionstep_home_group_level"; 
	String PRODUCTIONSTEP_HOME_SYSTEM_LEVEL = "productionstep_home_system_level";
	String PRODUCTIONSTEP_SEARCH_SYSTEM_LEVEL = "productionstep_search_system_level";
	String EXT_PRODUCTIONSTEP_SEARCH_SYSTEM_LEVEL = "ext_productionstep_search_system_level";
	String PRODUCTIONSTEP_SEARCH_GROUP_LEVEL = "productionstep_search_group_level";
	
	String PROMPT_ADD_PRODUCTIONSTEP = "prompt_add_productionstep";	
	String PROMPT_MODIFY_PRODUCTIONSTEP = "prompt_modify_productionstep";	

	/************************CURRENCY ****************************/
	String CURRENCY_HOME = "currency_home"; 
	String CURRENCY_HOME_FROM_GROUP_LEVEL = "currency_home_group_level"; 
	String CURRENCY_HOME_SYSTEM_LEVEL = "currency_home_system_level";
	String CURRENCY_SEARCH_SYSTEM_LEVEL = "currency_search_system_level";
	String EXT_CURRENCY_SEARCH_SYSTEM_LEVEL = "ext_currency_search_system_level";
	String CURRENCY_SEARCH_GROUP_LEVEL = "currency_search_group_level";
	
	String PROMPT_ADD_CURRENCY = "prompt_add_currency";	
	String PROMPT_MODIFY_CURRENCY = "prompt_modify_currency";	

	/************************BANK ****************************/
	String BANK_HOME = "bank_home"; 
	String BANK_HOME_FROM_GROUP_LEVEL = "bank_home_group_level"; 
	String BANK_HOME_SYSTEM_LEVEL = "bank_home_system_level";
	String BANK_SEARCH_SYSTEM_LEVEL = "bank_search_system_level";
	String EXT_BANK_SEARCH_SYSTEM_LEVEL = "ext_bank_search_system_level";
	String BANK_SEARCH_GROUP_LEVEL = "bank_search_group_level";
	
	String PROMPT_ADD_BANK = "prompt_add_bank";	
	String PROMPT_MODIFY_BANK = "prompt_modify_bank";	

	/************************CITY ****************************/
	String CITY_HOME = "city_home"; 
	String CITY_HOME_FROM_GROUP_LEVEL = "city_home_group_level"; 
	String CITY_HOME_SYSTEM_LEVEL = "city_home_system_level";
	String CITY_SEARCH_SYSTEM_LEVEL = "city_search_system_level";
	String EXT_CITY_SEARCH_SYSTEM_LEVEL = "ext_city_search_system_level";
	String CITY_SEARCH_GROUP_LEVEL = "city_search_group_level";
	
	String PROMPT_ADD_CITY = "prompt_add_city";	
	String PROMPT_MODIFY_CITY = "prompt_modify_city";	

	/************************COUNTRY ****************************/
	String COUNTRY_HOME = "country_home"; 
	String COUNTRY_HOME_FROM_GROUP_LEVEL = "country_home_group_level"; 
	String COUNTRY_HOME_SYSTEM_LEVEL = "country_home_system_level";
	String COUNTRY_SEARCH_SYSTEM_LEVEL = "country_search_system_level";
	String EXT_COUNTRY_SEARCH_SYSTEM_LEVEL = "ext_country_search_system_level";
	String COUNTRY_SEARCH_GROUP_LEVEL = "country_search_group_level";
	
	String PROMPT_ADD_COUNTRY = "prompt_add_country";	
	String PROMPT_MODIFY_COUNTRY = "prompt_modify_country";	

	/************************MODULE ****************************/
	String MODULE_HOME = "module_home"; 
	String MODULE_HOME_FROM_GROUP_LEVEL = "module_home_group_level"; 
	String MODULE_HOME_SYSTEM_LEVEL = "module_home_system_level";
	String MODULE_SEARCH_SYSTEM_LEVEL = "module_search_system_level";
	String EXT_MODULE_SEARCH_SYSTEM_LEVEL = "ext_module_search_system_level";
	String MODULE_SEARCH_GROUP_LEVEL = "module_search_group_level";
	
	String PROMPT_ADD_MODULE = "prompt_add_module";	
	String PROMPT_MODIFY_MODULE = "prompt_modify_module";	

	/************************HSCODE ****************************/
	String HSCODE_HOME = "hscode_home"; 
	String HSCODE_HOME_FROM_GROUP_LEVEL = "hscode_home_group_level"; 
	String HSCODE_HOME_SYSTEM_LEVEL = "hscode_home_system_level";
	String HSCODE_SEARCH_SYSTEM_LEVEL = "hscode_search_system_level";
	String EXT_HSCODE_SEARCH_SYSTEM_LEVEL = "ext_hscode_search_system_level";
	String HSCODE_SEARCH_GROUP_LEVEL = "hscode_search_group_level";
	
	String PROMPT_ADD_HSCODE = "prompt_add_hscode";	
	String PROMPT_MODIFY_HSCODE = "prompt_modify_hscode";	

	/************************MATERIALTYPE ****************************/
	String MATERIALTYPE_HOME = "materialtype_home"; 
	String MATERIALTYPE_HOME_FROM_GROUP_LEVEL = "materialtype_home_group_level"; 
	String MATERIALTYPE_HOME_SYSTEM_LEVEL = "materialtype_home_system_level";
	String MATERIALTYPE_SEARCH_SYSTEM_LEVEL = "materialtype_search_system_level";
	String EXT_MATERIALTYPE_SEARCH_SYSTEM_LEVEL = "ext_materialtype_search_system_level";
	String MATERIALTYPE_SEARCH_GROUP_LEVEL = "materialtype_search_group_level";
	
	String PROMPT_ADD_MATERIALTYPE = "prompt_add_materialtype";	
	String PROMPT_MODIFY_MATERIALTYPE = "prompt_modify_materialtype";	

	/************************MATERIALCATEGORY ****************************/
	String MATERIALCATEGORY_HOME = "materialcategory_home"; 
	String MATERIALCATEGORY_HOME_FROM_GROUP_LEVEL = "materialcategory_home_group_level"; 
	String MATERIALCATEGORY_HOME_SYSTEM_LEVEL = "materialcategory_home_system_level";
	String MATERIALCATEGORY_SEARCH_SYSTEM_LEVEL = "materialcategory_search_system_level";
	String EXT_MATERIALCATEGORY_SEARCH_SYSTEM_LEVEL = "ext_materialcategory_search_system_level";
	String MATERIALCATEGORY_SEARCH_GROUP_LEVEL = "materialcategory_search_group_level";
	
	String PROMPT_ADD_MATERIALCATEGORY = "prompt_add_materialcategory";	
	String PROMPT_MODIFY_MATERIALCATEGORY = "prompt_modify_materialcategory";	

	/************************WAREHOUSE ****************************/
	String WAREHOUSE_HOME = "warehouse_home"; 
	String WAREHOUSE_HOME_FROM_GROUP_LEVEL = "warehouse_home_group_level"; 
	String WAREHOUSE_HOME_SYSTEM_LEVEL = "warehouse_home_system_level";
	String WAREHOUSE_SEARCH_SYSTEM_LEVEL = "warehouse_search_system_level";
	String EXT_WAREHOUSE_SEARCH_SYSTEM_LEVEL = "ext_warehouse_search_system_level";
	String WAREHOUSE_SEARCH_GROUP_LEVEL = "warehouse_search_group_level";
	
	String PROMPT_ADD_WAREHOUSE = "prompt_add_warehouse";	
	String PROMPT_MODIFY_WAREHOUSE = "prompt_modify_warehouse";	

	/************************PRODUCTIONSECTION ****************************/
	String PRODUCTIONSECTION_HOME = "productionsection_home"; 
	String PRODUCTIONSECTION_HOME_FROM_GROUP_LEVEL = "productionsection_home_group_level"; 
	String PRODUCTIONSECTION_HOME_SYSTEM_LEVEL = "productionsection_home_system_level";
	String PRODUCTIONSECTION_SEARCH_SYSTEM_LEVEL = "productionsection_search_system_level";
	String EXT_PRODUCTIONSECTION_SEARCH_SYSTEM_LEVEL = "ext_productionsection_search_system_level";
	String PRODUCTIONSECTION_SEARCH_GROUP_LEVEL = "productionsection_search_group_level";
	
	String PROMPT_ADD_PRODUCTIONSECTION = "prompt_add_productionsection";	
	String PROMPT_MODIFY_PRODUCTIONSECTION = "prompt_modify_productionsection";	

	/************************INKTYPE ****************************/
	String INKTYPE_HOME = "inktype_home"; 
	String INKTYPE_HOME_FROM_GROUP_LEVEL = "inktype_home_group_level"; 
	String INKTYPE_HOME_SYSTEM_LEVEL = "inktype_home_system_level";
	String INKTYPE_SEARCH_SYSTEM_LEVEL = "inktype_search_system_level";
	String EXT_INKTYPE_SEARCH_SYSTEM_LEVEL = "ext_inktype_search_system_level";
	String INKTYPE_SEARCH_GROUP_LEVEL = "inktype_search_group_level";
	
	String PROMPT_ADD_INKTYPE = "prompt_add_inktype";	
	String PROMPT_MODIFY_INKTYPE = "prompt_modify_inktype";	

	/************************MACHINE ****************************/
	String MACHINE_HOME = "machine_home"; 
	String MACHINE_HOME_FROM_GROUP_LEVEL = "machine_home_group_level"; 
	String MACHINE_HOME_SYSTEM_LEVEL = "machine_home_system_level";
	String MACHINE_SEARCH_SYSTEM_LEVEL = "machine_search_system_level";
	String EXT_MACHINE_SEARCH_SYSTEM_LEVEL = "ext_machine_search_system_level";
	String MACHINE_SEARCH_GROUP_LEVEL = "machine_search_group_level";
	
	String PROMPT_ADD_MACHINE = "prompt_add_machine";	
	String PROMPT_MODIFY_MACHINE = "prompt_modify_machine";	

	/************************MACHINETYPE ****************************/
	String MACHINETYPE_HOME = "machinetype_home"; 
	String MACHINETYPE_HOME_FROM_GROUP_LEVEL = "machinetype_home_group_level"; 
	String MACHINETYPE_HOME_SYSTEM_LEVEL = "machinetype_home_system_level";
	String MACHINETYPE_SEARCH_SYSTEM_LEVEL = "machinetype_search_system_level";
	String EXT_MACHINETYPE_SEARCH_SYSTEM_LEVEL = "ext_machinetype_search_system_level";
	String MACHINETYPE_SEARCH_GROUP_LEVEL = "machinetype_search_group_level";
	
	String PROMPT_ADD_MACHINETYPE = "prompt_add_machinetype";	
	String PROMPT_MODIFY_MACHINETYPE = "prompt_modify_machinetype";	

	/************************BRAND ****************************/
	String BRAND_HOME = "brand_home"; 
	String BRAND_HOME_FROM_GROUP_LEVEL = "brand_home_group_level"; 
	String BRAND_HOME_SYSTEM_LEVEL = "brand_home_system_level";
	String BRAND_SEARCH_SYSTEM_LEVEL = "brand_search_system_level";
	String EXT_BRAND_SEARCH_SYSTEM_LEVEL = "ext_brand_search_system_level";
	String BRAND_SEARCH_GROUP_LEVEL = "brand_search_group_level";
	
	String PROMPT_ADD_BRAND = "prompt_add_brand";	
	String PROMPT_MODIFY_BRAND = "prompt_modify_brand";	

	/************************LEAVEMANAGEMENT ****************************/
	String LEAVEMANAGEMENT_HOME = "leavemanagement_home"; 
	String LEAVEMANAGEMENT_HOME_FROM_GROUP_LEVEL = "leavemanagement_home_group_level"; 
	String LEAVEMANAGEMENT_HOME_SYSTEM_LEVEL = "leavemanagement_home_system_level";
	String LEAVEMANAGEMENT_SEARCH_SYSTEM_LEVEL = "leavemanagement_search_system_level";
	String EXT_LEAVEMANAGEMENT_SEARCH_SYSTEM_LEVEL = "ext_leavemanagement_search_system_level";
	String LEAVEMANAGEMENT_SEARCH_GROUP_LEVEL = "leavemanagement_search_group_level";
	
	String PROMPT_ADD_LEAVEMANAGEMENT = "prompt_add_leavemanagement";	
	String PROMPT_MODIFY_LEAVEMANAGEMENT = "prompt_modify_leavemanagement";	

	/************************SALARY ****************************/
	String SALARY_HOME = "salary_home"; 
	String SALARY_HOME_FROM_GROUP_LEVEL = "salary_home_group_level"; 
	String SALARY_HOME_SYSTEM_LEVEL = "salary_home_system_level";
	String SALARY_SEARCH_SYSTEM_LEVEL = "salary_search_system_level";
	String EXT_SALARY_SEARCH_SYSTEM_LEVEL = "ext_salary_search_system_level";
	String SALARY_SEARCH_GROUP_LEVEL = "salary_search_group_level";
	
	String PROMPT_ADD_SALARY = "prompt_add_salary";	
	String PROMPT_MODIFY_SALARY = "prompt_modify_salary";	

	/************************ATTENDENCE ****************************/
	String ATTENDENCE_HOME = "attendence_home"; 
	String ATTENDENCE_HOME_FROM_GROUP_LEVEL = "attendence_home_group_level"; 
	String ATTENDENCE_HOME_SYSTEM_LEVEL = "attendence_home_system_level";
	String ATTENDENCE_SEARCH_SYSTEM_LEVEL = "attendence_search_system_level";
	String EXT_ATTENDENCE_SEARCH_SYSTEM_LEVEL = "ext_attendence_search_system_level";
	String ATTENDENCE_SEARCH_GROUP_LEVEL = "attendence_search_group_level";
	
	String PROMPT_ADD_ATTENDENCE = "prompt_add_attendence";	
	String PROMPT_MODIFY_ATTENDENCE = "prompt_modify_attendence";	

	/************************DEPARTMENT ****************************/
	String DEPARTMENT_HOME = "department_home"; 
	String DEPARTMENT_HOME_FROM_GROUP_LEVEL = "department_home_group_level"; 
	String DEPARTMENT_HOME_SYSTEM_LEVEL = "department_home_system_level";
	String DEPARTMENT_SEARCH_SYSTEM_LEVEL = "department_search_system_level";
	String EXT_DEPARTMENT_SEARCH_SYSTEM_LEVEL = "ext_department_search_system_level";
	String DEPARTMENT_SEARCH_GROUP_LEVEL = "department_search_group_level";
	
	String PROMPT_ADD_DEPARTMENT = "prompt_add_department";	
	String PROMPT_MODIFY_DEPARTMENT = "prompt_modify_department";	

	/************************DESIGNATION ****************************/
	String DESIGNATION_HOME = "designation_home"; 
	String DESIGNATION_HOME_FROM_GROUP_LEVEL = "designation_home_group_level"; 
	String DESIGNATION_HOME_SYSTEM_LEVEL = "designation_home_system_level";
	String DESIGNATION_SEARCH_SYSTEM_LEVEL = "designation_search_system_level";
	String EXT_DESIGNATION_SEARCH_SYSTEM_LEVEL = "ext_designation_search_system_level";
	String DESIGNATION_SEARCH_GROUP_LEVEL = "designation_search_group_level";
	
	String PROMPT_ADD_DESIGNATION = "prompt_add_designation";	
	String PROMPT_MODIFY_DESIGNATION = "prompt_modify_designation";	

	/************************LEAVEPACKAGE ****************************/
	String LEAVEPACKAGE_HOME = "leavepackage_home"; 
	String LEAVEPACKAGE_HOME_FROM_GROUP_LEVEL = "leavepackage_home_group_level"; 
	String LEAVEPACKAGE_HOME_SYSTEM_LEVEL = "leavepackage_home_system_level";
	String LEAVEPACKAGE_SEARCH_SYSTEM_LEVEL = "leavepackage_search_system_level";
	String EXT_LEAVEPACKAGE_SEARCH_SYSTEM_LEVEL = "ext_leavepackage_search_system_level";
	String LEAVEPACKAGE_SEARCH_GROUP_LEVEL = "leavepackage_search_group_level";
	
	String PROMPT_ADD_LEAVEPACKAGE = "prompt_add_leavepackage";	
	String PROMPT_MODIFY_LEAVEPACKAGE = "prompt_modify_leavepackage";	

	/************************HOLIDAY ****************************/
	String HOLIDAY_HOME = "holiday_home"; 
	String HOLIDAY_HOME_FROM_GROUP_LEVEL = "holiday_home_group_level"; 
	String HOLIDAY_HOME_SYSTEM_LEVEL = "holiday_home_system_level";
	String HOLIDAY_SEARCH_SYSTEM_LEVEL = "holiday_search_system_level";
	String EXT_HOLIDAY_SEARCH_SYSTEM_LEVEL = "ext_holiday_search_system_level";
	String HOLIDAY_SEARCH_GROUP_LEVEL = "holiday_search_group_level";
	
	String PROMPT_ADD_HOLIDAY = "prompt_add_holiday";	
	String PROMPT_MODIFY_HOLIDAY = "prompt_modify_holiday";	

	/************************EMPLOYEE ****************************/
	String EMPLOYEE_HOME = "employee_home"; 
	String EMPLOYEE_HOME_FROM_GROUP_LEVEL = "employee_home_group_level"; 
	String EMPLOYEE_HOME_SYSTEM_LEVEL = "employee_home_system_level";
	String EMPLOYEE_SEARCH_SYSTEM_LEVEL = "employee_search_system_level";
	String EXT_EMPLOYEE_SEARCH_SYSTEM_LEVEL = "ext_employee_search_system_level";
	String EMPLOYEE_SEARCH_GROUP_LEVEL = "employee_search_group_level";
	
	String PROMPT_ADD_EMPLOYEE = "prompt_add_employee";	
	String PROMPT_MODIFY_EMPLOYEE = "prompt_modify_employee";	

	/************************EMPLOYEEPERSONALINFO ****************************/
	String EMPLOYEEPERSONALINFO_HOME = "employeepersonalinfo_home"; 
	String EMPLOYEEPERSONALINFO_HOME_FROM_GROUP_LEVEL = "employeepersonalinfo_home_group_level"; 
	String EMPLOYEEPERSONALINFO_HOME_SYSTEM_LEVEL = "employeepersonalinfo_home_system_level";
	String EMPLOYEEPERSONALINFO_SEARCH_SYSTEM_LEVEL = "employeepersonalinfo_search_system_level";
	String EXT_EMPLOYEEPERSONALINFO_SEARCH_SYSTEM_LEVEL = "ext_employeepersonalinfo_search_system_level";
	String EMPLOYEEPERSONALINFO_SEARCH_GROUP_LEVEL = "employeepersonalinfo_search_group_level";
	
	String PROMPT_ADD_EMPLOYEEPERSONALINFO = "prompt_add_employeepersonalinfo";	
	String PROMPT_MODIFY_EMPLOYEEPERSONALINFO = "prompt_modify_employeepersonalinfo";	

	/************************ACADEMICINFO ****************************/
	String ACADEMICINFO_HOME = "academicinfo_home"; 
	String ACADEMICINFO_HOME_FROM_GROUP_LEVEL = "academicinfo_home_group_level"; 
	String ACADEMICINFO_HOME_SYSTEM_LEVEL = "academicinfo_home_system_level";
	String ACADEMICINFO_SEARCH_SYSTEM_LEVEL = "academicinfo_search_system_level";
	String EXT_ACADEMICINFO_SEARCH_SYSTEM_LEVEL = "ext_academicinfo_search_system_level";
	String ACADEMICINFO_SEARCH_GROUP_LEVEL = "academicinfo_search_group_level";
	
	String PROMPT_ADD_ACADEMICINFO = "prompt_add_academicinfo";	
	String PROMPT_MODIFY_ACADEMICINFO = "prompt_modify_academicinfo";	

	/************************CAREERHISTORY ****************************/
	String CAREERHISTORY_HOME = "careerhistory_home"; 
	String CAREERHISTORY_HOME_FROM_GROUP_LEVEL = "careerhistory_home_group_level"; 
	String CAREERHISTORY_HOME_SYSTEM_LEVEL = "careerhistory_home_system_level";
	String CAREERHISTORY_SEARCH_SYSTEM_LEVEL = "careerhistory_search_system_level";
	String EXT_CAREERHISTORY_SEARCH_SYSTEM_LEVEL = "ext_careerhistory_search_system_level";
	String CAREERHISTORY_SEARCH_GROUP_LEVEL = "careerhistory_search_group_level";
	
	String PROMPT_ADD_CAREERHISTORY = "prompt_add_careerhistory";	
	String PROMPT_MODIFY_CAREERHISTORY = "prompt_modify_careerhistory";	

	/************************SUPPLIER ****************************/
	String SUPPLIER_HOME = "supplier_home"; 
	String SUPPLIER_HOME_FROM_GROUP_LEVEL = "supplier_home_group_level"; 
	String SUPPLIER_HOME_SYSTEM_LEVEL = "supplier_home_system_level";
	String SUPPLIER_SEARCH_SYSTEM_LEVEL = "supplier_search_system_level";
	String EXT_SUPPLIER_SEARCH_SYSTEM_LEVEL = "ext_supplier_search_system_level";
	String SUPPLIER_SEARCH_GROUP_LEVEL = "supplier_search_group_level";
	
	String PROMPT_ADD_SUPPLIER = "prompt_add_supplier";	
	String PROMPT_MODIFY_SUPPLIER = "prompt_modify_supplier";	

	/************************SUPPLIERBANKINFO ****************************/
	String SUPPLIERBANKINFO_HOME = "supplierbankinfo_home"; 
	String SUPPLIERBANKINFO_HOME_FROM_GROUP_LEVEL = "supplierbankinfo_home_group_level"; 
	String SUPPLIERBANKINFO_HOME_SYSTEM_LEVEL = "supplierbankinfo_home_system_level";
	String SUPPLIERBANKINFO_SEARCH_SYSTEM_LEVEL = "supplierbankinfo_search_system_level";
	String EXT_SUPPLIERBANKINFO_SEARCH_SYSTEM_LEVEL = "ext_supplierbankinfo_search_system_level";
	String SUPPLIERBANKINFO_SEARCH_GROUP_LEVEL = "supplierbankinfo_search_group_level";
	
	String PROMPT_ADD_SUPPLIERBANKINFO = "prompt_add_supplierbankinfo";	
	String PROMPT_MODIFY_SUPPLIERBANKINFO = "prompt_modify_supplierbankinfo";	

	/************************INVENTORYIN ****************************/
	String INVENTORYIN_HOME = "inventoryin_home"; 
	String INVENTORYIN_HOME_FROM_GROUP_LEVEL = "inventoryin_home_group_level"; 
	String INVENTORYIN_HOME_SYSTEM_LEVEL = "inventoryin_home_system_level";
	String INVENTORYIN_SEARCH_SYSTEM_LEVEL = "inventoryin_search_system_level";
	String EXT_INVENTORYIN_SEARCH_SYSTEM_LEVEL = "ext_inventoryin_search_system_level";
	String INVENTORYIN_SEARCH_GROUP_LEVEL = "inventoryin_search_group_level";
	
	String PROMPT_ADD_INVENTORYIN = "prompt_add_inventoryin";	
	String PROMPT_MODIFY_INVENTORYIN = "prompt_modify_inventoryin";	

	/************************INVENTORYOUT ****************************/
	String INVENTORYOUT_HOME = "inventoryout_home"; 
	String INVENTORYOUT_HOME_FROM_GROUP_LEVEL = "inventoryout_home_group_level"; 
	String INVENTORYOUT_HOME_SYSTEM_LEVEL = "inventoryout_home_system_level";
	String INVENTORYOUT_SEARCH_SYSTEM_LEVEL = "inventoryout_search_system_level";
	String EXT_INVENTORYOUT_SEARCH_SYSTEM_LEVEL = "ext_inventoryout_search_system_level";
	String INVENTORYOUT_SEARCH_GROUP_LEVEL = "inventoryout_search_group_level";
	
	String PROMPT_ADD_INVENTORYOUT = "prompt_add_inventoryout";	
	String PROMPT_MODIFY_INVENTORYOUT = "prompt_modify_inventoryout";	

	/************************MAILINFO ****************************/
	String MAILINFO_HOME = "mailinfo_home"; 
	String MAILINFO_HOME_FROM_GROUP_LEVEL = "mailinfo_home_group_level"; 
	String MAILINFO_HOME_SYSTEM_LEVEL = "mailinfo_home_system_level";
	String MAILINFO_SEARCH_SYSTEM_LEVEL = "mailinfo_search_system_level";
	String EXT_MAILINFO_SEARCH_SYSTEM_LEVEL = "ext_mailinfo_search_system_level";
	String MAILINFO_SEARCH_GROUP_LEVEL = "mailinfo_search_group_level";
	
	String PROMPT_ADD_MAILINFO = "prompt_add_mailinfo";	
	String PROMPT_MODIFY_MAILINFO = "prompt_modify_mailinfo";
	String MAILINFO_INITIAL_LOAD = "mailinfo_initial_load";
	String MAILINFO_INBOX = "mailinfo_inbox";
	String MAILINFO_CHECK_MAIL = "mailinfo_check_mail";
	String MAILINFO_COUNT_INBOX = "mailinfo_count_inbox";
	String MAILINFO_COMPANY_INBOX = "mailinfo_company_inbox";
	String MAILINFO_MAIL_DTL = "mailinfo_mail_dtl";
	String MAILINFO_SESSION_CHECK = "mailinfo_session_check";
	String MAILINFO_MARK_AS_READ = "mailinfo_mark_as_read";
	String MAILINFO_MARK_AS_UNREAD = "mailinfo_mark_as_unread";
	String MAILINFO_SEND_MAIL = "mailinfo_send_mail";
	String MAILINFO_SAVE_AS_DRAFT = "mailinfo_save_as_draft";
	String MAILINFO_ATTACHMENTS = "mailinfo_attachments";
	String MAILINFO_EMAIL_SETTINGS = "mailinfo_email_settings";
	String MAILINFO_EMAIL_DOWNLOAD_ATTACHMENTS = "mailinfo_email_download_attachments";
	String MAILINFO_EMAIL_SEARCH = "mailinfo_email_search";
	String MAILINFO_EMAIL_SHARE = "mailinfo_email_share";
	/************************EMAIL ****************************/
	String EMAIL_HOME = "email_home"; 
	String EMAIL_HOME_FROM_GROUP_LEVEL = "email_home_group_level"; 
	String EMAIL_HOME_SYSTEM_LEVEL = "email_home_system_level";
	String EMAIL_SEARCH_SYSTEM_LEVEL = "email_search_system_level";
	String EXT_EMAIL_SEARCH_SYSTEM_LEVEL = "ext_email_search_system_level";
	String EMAIL_SEARCH_GROUP_LEVEL = "email_search_group_level";
	
	String PROMPT_ADD_EMAIL = "prompt_add_email";	
	String PROMPT_MODIFY_EMAIL = "prompt_modify_email";	

	/************************EMAILDTL ****************************/
	String EMAILDTL_HOME = "emaildtl_home"; 
	String EMAILDTL_HOME_FROM_GROUP_LEVEL = "emaildtl_home_group_level"; 
	String EMAILDTL_HOME_SYSTEM_LEVEL = "emaildtl_home_system_level";
	String EMAILDTL_SEARCH_SYSTEM_LEVEL = "emaildtl_search_system_level";
	String EXT_EMAILDTL_SEARCH_SYSTEM_LEVEL = "ext_emaildtl_search_system_level";
	String EMAILDTL_SEARCH_GROUP_LEVEL = "emaildtl_search_group_level";
	
	String PROMPT_ADD_EMAILDTL = "prompt_add_emaildtl";	
	String PROMPT_MODIFY_EMAILDTL = "prompt_modify_emaildtl";	

	/************************EMAILGROUP ****************************/
	String EMAILGROUP_HOME = "emailgroup_home"; 
	String EMAILGROUP_HOME_FROM_GROUP_LEVEL = "emailgroup_home_group_level"; 
	String EMAILGROUP_HOME_SYSTEM_LEVEL = "emailgroup_home_system_level";
	String EMAILGROUP_SEARCH_SYSTEM_LEVEL = "emailgroup_search_system_level";
	String EXT_EMAILGROUP_SEARCH_SYSTEM_LEVEL = "ext_emailgroup_search_system_level";
	String EMAILGROUP_SEARCH_GROUP_LEVEL = "emailgroup_search_group_level";
	
	String PROMPT_ADD_EMAILGROUP = "prompt_add_emailgroup";	
	String PROMPT_MODIFY_EMAILGROUP = "prompt_modify_emailgroup";	

	/************************EMAILREFERANCE ****************************/
	String EMAILREFERANCE_HOME = "emailreferance_home"; 
	String EMAILREFERANCE_HOME_FROM_GROUP_LEVEL = "emailreferance_home_group_level"; 
	String EMAILREFERANCE_HOME_SYSTEM_LEVEL = "emailreferance_home_system_level";
	String EMAILREFERANCE_SEARCH_SYSTEM_LEVEL = "emailreferance_search_system_level";
	String EXT_EMAILREFERANCE_SEARCH_SYSTEM_LEVEL = "ext_emailreferance_search_system_level";
	String EMAILREFERANCE_SEARCH_GROUP_LEVEL = "emailreferance_search_group_level";
	
	String PROMPT_ADD_EMAILREFERANCE = "prompt_add_emailreferance";	
	String PROMPT_MODIFY_EMAILREFERANCE = "prompt_modify_emailreferance";	

	/************************EMAILRECIPIENTS ****************************/
	String EMAILRECIPIENTS_HOME = "emailrecipients_home"; 
	String EMAILRECIPIENTS_HOME_FROM_GROUP_LEVEL = "emailrecipients_home_group_level"; 
	String EMAILRECIPIENTS_HOME_SYSTEM_LEVEL = "emailrecipients_home_system_level";
	String EMAILRECIPIENTS_SEARCH_SYSTEM_LEVEL = "emailrecipients_search_system_level";
	String EXT_EMAILRECIPIENTS_SEARCH_SYSTEM_LEVEL = "ext_emailrecipients_search_system_level";
	String EMAILRECIPIENTS_SEARCH_GROUP_LEVEL = "emailrecipients_search_group_level";
	
	String PROMPT_ADD_EMAILRECIPIENTS = "prompt_add_emailrecipients";	
	String PROMPT_MODIFY_EMAILRECIPIENTS = "prompt_modify_emailrecipients";	

	/************************EMAILRECV ****************************/
	String EMAILRECV_HOME = "emailrecv_home"; 
	String EMAILRECV_HOME_FROM_GROUP_LEVEL = "emailrecv_home_group_level"; 
	String EMAILRECV_HOME_SYSTEM_LEVEL = "emailrecv_home_system_level";
	String EMAILRECV_SEARCH_SYSTEM_LEVEL = "emailrecv_search_system_level";
	String EXT_EMAILRECV_SEARCH_SYSTEM_LEVEL = "ext_emailrecv_search_system_level";
	String EMAILRECV_SEARCH_GROUP_LEVEL = "emailrecv_search_group_level";
	
	String PROMPT_ADD_EMAILRECV = "prompt_add_emailrecv";	
	String PROMPT_MODIFY_EMAILRECV = "prompt_modify_emailrecv";	

	/************************CUSTOMERINFO ****************************/
	String CUSTOMERINFO_HOME = "customerinfo_home"; 
	String CUSTOMERINFO_HOME_FROM_GROUP_LEVEL = "customerinfo_home_group_level"; 
	String CUSTOMERINFO_HOME_SYSTEM_LEVEL = "customerinfo_home_system_level";
	String CUSTOMERINFO_SEARCH_SYSTEM_LEVEL = "customerinfo_search_system_level";
	String EXT_CUSTOMERINFO_SEARCH_SYSTEM_LEVEL = "ext_customerinfo_search_system_level";
	String CUSTOMERINFO_SEARCH_GROUP_LEVEL = "customerinfo_search_group_level";
	
	String PROMPT_ADD_CUSTOMERINFO = "prompt_add_customerinfo";	
	String PROMPT_MODIFY_CUSTOMERINFO = "prompt_modify_customerinfo";	
	/************************SHIPPINGINFO ****************************/
	String SHIPPINGINFO_HOME = "shippinginfo_home"; 
	String SHIPPINGINFO_HOME_FROM_GROUP_LEVEL = "shippinginfo_home_group_level"; 
	String SHIPPINGINFO_HOME_SYSTEM_LEVEL = "shippinginfo_home_system_level";
	String SHIPPINGINFO_SEARCH_SYSTEM_LEVEL = "shippinginfo_search_system_level";
	String EXT_SHIPPINGINFO_SEARCH_SYSTEM_LEVEL = "ext_shippinginfo_search_system_level";
	String SHIPPINGINFO_SEARCH_GROUP_LEVEL = "shippinginfo_search_group_level";
	
	String PROMPT_ADD_SHIPPINGINFO = "prompt_add_shippinginfo";	
	String PROMPT_MODIFY_SHIPPINGINFO = "prompt_modify_shippinginfo";	

	/************************BANKINFO ****************************/
	String BANKINFO_HOME = "bankinfo_home"; 
	String BANKINFO_HOME_FROM_GROUP_LEVEL = "bankinfo_home_group_level"; 
	String BANKINFO_HOME_SYSTEM_LEVEL = "bankinfo_home_system_level";
	String BANKINFO_SEARCH_SYSTEM_LEVEL = "bankinfo_search_system_level";
	String EXT_BANKINFO_SEARCH_SYSTEM_LEVEL = "ext_bankinfo_search_system_level";
	String BANKINFO_SEARCH_GROUP_LEVEL = "bankinfo_search_group_level";
	
	String PROMPT_ADD_BANKINFO = "prompt_add_bankinfo";	
	String PROMPT_MODIFY_BANKINFO = "prompt_modify_bankinfo";	

	/************************INQUIRY ****************************/
	String INQUIRY_HOME = "inquiry_home"; 
	String INQUIRY_HOME_FROM_GROUP_LEVEL = "inquiry_home_group_level"; 
	String INQUIRY_HOME_SYSTEM_LEVEL = "inquiry_home_system_level";
	String INQUIRY_SEARCH_SYSTEM_LEVEL = "inquiry_search_system_level";
	String EXT_INQUIRY_SEARCH_SYSTEM_LEVEL = "ext_inquiry_search_system_level";
	String INQUIRY_SEARCH_GROUP_LEVEL = "inquiry_search_group_level";
	
	String PROMPT_ADD_INQUIRY = "prompt_add_inquiry";	
	String PROMPT_MODIFY_INQUIRY = "prompt_modify_inquiry";	

	/************************ITEMMASTER ****************************/
	String ITEMMASTER_HOME = "itemmaster_home"; 
	String ITEMMASTER_HOME_FROM_GROUP_LEVEL = "itemmaster_home_group_level"; 
	String ITEMMASTER_HOME_SYSTEM_LEVEL = "itemmaster_home_system_level";
	String ITEMMASTER_SEARCH_SYSTEM_LEVEL = "itemmaster_search_system_level";
	String EXT_ITEMMASTER_SEARCH_SYSTEM_LEVEL = "ext_itemmaster_search_system_level";
	String ITEMMASTER_SEARCH_GROUP_LEVEL = "itemmaster_search_group_level";
	
	String PROMPT_ADD_ITEMMASTER = "prompt_add_itemmaster";	
	String PROMPT_MODIFY_ITEMMASTER = "prompt_modify_itemmaster";	

	/************************MATERIALS ****************************/
	String MATERIALS_HOME = "materials_home"; 
	String MATERIALS_HOME_FROM_GROUP_LEVEL = "materials_home_group_level"; 
	String MATERIALS_HOME_SYSTEM_LEVEL = "materials_home_system_level";
	String MATERIALS_SEARCH_SYSTEM_LEVEL = "materials_search_system_level";
	String EXT_MATERIALS_SEARCH_SYSTEM_LEVEL = "ext_materials_search_system_level";
	String MATERIALS_SEARCH_GROUP_LEVEL = "materials_search_group_level";
	
	String PROMPT_ADD_MATERIALS = "prompt_add_materials";	
	String PROMPT_MODIFY_MATERIALS = "prompt_modify_materials";	

	/************************PRICING ****************************/
	String PRICING_HOME = "pricing_home"; 
	String PRICING_HOME_FROM_GROUP_LEVEL = "pricing_home_group_level"; 
	String PRICING_HOME_SYSTEM_LEVEL = "pricing_home_system_level";
	String PRICING_SEARCH_SYSTEM_LEVEL = "pricing_search_system_level";
	String EXT_PRICING_SEARCH_SYSTEM_LEVEL = "ext_pricing_search_system_level";
	String PRICING_SEARCH_GROUP_LEVEL = "pricing_search_group_level";
	
	String PROMPT_ADD_PRICING = "prompt_add_pricing";	
	String PROMPT_MODIFY_PRICING = "prompt_modify_pricing";	

	/************************PAYMENTCOLLECTION ****************************/
	String PAYMENTCOLLECTION_HOME = "paymentcollection_home"; 
	String PAYMENTCOLLECTION_HOME_FROM_GROUP_LEVEL = "paymentcollection_home_group_level"; 
	String PAYMENTCOLLECTION_HOME_SYSTEM_LEVEL = "paymentcollection_home_system_level";
	String PAYMENTCOLLECTION_SEARCH_SYSTEM_LEVEL = "paymentcollection_search_system_level";
	String EXT_PAYMENTCOLLECTION_SEARCH_SYSTEM_LEVEL = "ext_paymentcollection_search_system_level";
	String PAYMENTCOLLECTION_SEARCH_GROUP_LEVEL = "paymentcollection_search_group_level";
	
	String PROMPT_ADD_PAYMENTCOLLECTION = "prompt_add_paymentcollection";	
	String PROMPT_MODIFY_PAYMENTCOLLECTION = "prompt_modify_paymentcollection";	

	/************************PAYMENTCOLLDOCS ****************************/
	String PAYMENTCOLLDOCS_HOME = "paymentcolldocs_home"; 
	String PAYMENTCOLLDOCS_HOME_FROM_GROUP_LEVEL = "paymentcolldocs_home_group_level"; 
	String PAYMENTCOLLDOCS_HOME_SYSTEM_LEVEL = "paymentcolldocs_home_system_level";
	String PAYMENTCOLLDOCS_SEARCH_SYSTEM_LEVEL = "paymentcolldocs_search_system_level";
	String EXT_PAYMENTCOLLDOCS_SEARCH_SYSTEM_LEVEL = "ext_paymentcolldocs_search_system_level";
	String PAYMENTCOLLDOCS_SEARCH_GROUP_LEVEL = "paymentcolldocs_search_group_level";
	
	String PROMPT_ADD_PAYMENTCOLLDOCS = "prompt_add_paymentcolldocs";	
	String PROMPT_MODIFY_PAYMENTCOLLDOCS = "prompt_modify_paymentcolldocs";	

	/************************GENERALPROFILE ****************************/
	String GENERALPROFILE_HOME = "generalprofile_home"; 
	String GENERALPROFILE_HOME_FROM_GROUP_LEVEL = "generalprofile_home_group_level"; 
	String GENERALPROFILE_HOME_SYSTEM_LEVEL = "generalprofile_home_system_level";
	String GENERALPROFILE_SEARCH_SYSTEM_LEVEL = "generalprofile_search_system_level";
	String EXT_GENERALPROFILE_SEARCH_SYSTEM_LEVEL = "ext_generalprofile_search_system_level";
	String GENERALPROFILE_SEARCH_GROUP_LEVEL = "generalprofile_search_group_level";
	
	String PROMPT_ADD_GENERALPROFILE = "prompt_add_generalprofile";	
	String PROMPT_MODIFY_GENERALPROFILE = "prompt_modify_generalprofile";	

	/************************RESPONDENTADDRESS ****************************/
	String RESPONDENTADDRESS_HOME = "respondentaddress_home"; 
	String RESPONDENTADDRESS_HOME_FROM_GROUP_LEVEL = "respondentaddress_home_group_level"; 
	String RESPONDENTADDRESS_HOME_SYSTEM_LEVEL = "respondentaddress_home_system_level";
	String RESPONDENTADDRESS_SEARCH_SYSTEM_LEVEL = "respondentaddress_search_system_level";
	String EXT_RESPONDENTADDRESS_SEARCH_SYSTEM_LEVEL = "ext_respondentaddress_search_system_level";
	String RESPONDENTADDRESS_SEARCH_GROUP_LEVEL = "respondentaddress_search_group_level";
	
	String PROMPT_ADD_RESPONDENTADDRESS = "prompt_add_respondentaddress";	
	String PROMPT_MODIFY_RESPONDENTADDRESS = "prompt_modify_respondentaddress";	

	/************************UPAZILLA ****************************/
	String UPAZILLA_HOME = "upazilla_home"; 
	String UPAZILLA_HOME_FROM_GROUP_LEVEL = "upazilla_home_group_level"; 
	String UPAZILLA_HOME_SYSTEM_LEVEL = "upazilla_home_system_level";
	String UPAZILLA_SEARCH_SYSTEM_LEVEL = "upazilla_search_system_level";
	String EXT_UPAZILLA_SEARCH_SYSTEM_LEVEL = "ext_upazilla_search_system_level";
	String UPAZILLA_SEARCH_GROUP_LEVEL = "upazilla_search_group_level";
	
	String PROMPT_ADD_UPAZILLA = "prompt_add_upazilla";	
	String PROMPT_MODIFY_UPAZILLA = "prompt_modify_upazilla";	

	/************************RESPONDENTOTHERINFO ****************************/
	String RESPONDENTOTHERINFO_HOME = "respondentotherinfo_home"; 
	String RESPONDENTOTHERINFO_HOME_FROM_GROUP_LEVEL = "respondentotherinfo_home_group_level"; 
	String RESPONDENTOTHERINFO_HOME_SYSTEM_LEVEL = "respondentotherinfo_home_system_level";
	String RESPONDENTOTHERINFO_SEARCH_SYSTEM_LEVEL = "respondentotherinfo_search_system_level";
	String EXT_RESPONDENTOTHERINFO_SEARCH_SYSTEM_LEVEL = "ext_respondentotherinfo_search_system_level";
	String RESPONDENTOTHERINFO_SEARCH_GROUP_LEVEL = "respondentotherinfo_search_group_level";
	
	String PROMPT_ADD_RESPONDENTOTHERINFO = "prompt_add_respondentotherinfo";	
	String PROMPT_MODIFY_RESPONDENTOTHERINFO = "prompt_modify_respondentotherinfo";	

	/************************CROPDETAIL ****************************/
	String CROPDETAIL_HOME = "cropdetail_home"; 
	String CROPDETAIL_HOME_FROM_GROUP_LEVEL = "cropdetail_home_group_level"; 
	String CROPDETAIL_HOME_SYSTEM_LEVEL = "cropdetail_home_system_level";
	String CROPDETAIL_SEARCH_SYSTEM_LEVEL = "cropdetail_search_system_level";
	String EXT_CROPDETAIL_SEARCH_SYSTEM_LEVEL = "ext_cropdetail_search_system_level";
	String CROPDETAIL_SEARCH_GROUP_LEVEL = "cropdetail_search_group_level";
	
	String PROMPT_ADD_CROPDETAIL = "prompt_add_cropdetail";	
	String PROMPT_MODIFY_CROPDETAIL = "prompt_modify_cropdetail";	

	/************************MOSTIMPVEGETABLE ****************************/
	String MOSTIMPVEGETABLE_HOME = "mostimpvegetable_home"; 
	String MOSTIMPVEGETABLE_HOME_FROM_GROUP_LEVEL = "mostimpvegetable_home_group_level"; 
	String MOSTIMPVEGETABLE_HOME_SYSTEM_LEVEL = "mostimpvegetable_home_system_level";
	String MOSTIMPVEGETABLE_SEARCH_SYSTEM_LEVEL = "mostimpvegetable_search_system_level";
	String EXT_MOSTIMPVEGETABLE_SEARCH_SYSTEM_LEVEL = "ext_mostimpvegetable_search_system_level";
	String MOSTIMPVEGETABLE_SEARCH_GROUP_LEVEL = "mostimpvegetable_search_group_level";
	
	String PROMPT_ADD_MOSTIMPVEGETABLE = "prompt_add_mostimpvegetable";	
	String PROMPT_MODIFY_MOSTIMPVEGETABLE = "prompt_modify_mostimpvegetable";	

	/************************VEGETABLERATING ****************************/
	String VEGETABLERATING_HOME = "vegetablerating_home"; 
	String VEGETABLERATING_HOME_FROM_GROUP_LEVEL = "vegetablerating_home_group_level"; 
	String VEGETABLERATING_HOME_SYSTEM_LEVEL = "vegetablerating_home_system_level";
	String VEGETABLERATING_SEARCH_SYSTEM_LEVEL = "vegetablerating_search_system_level";
	String EXT_VEGETABLERATING_SEARCH_SYSTEM_LEVEL = "ext_vegetablerating_search_system_level";
	String VEGETABLERATING_SEARCH_GROUP_LEVEL = "vegetablerating_search_group_level";
	
	String PROMPT_ADD_VEGETABLERATING = "prompt_add_vegetablerating";	
	String PROMPT_MODIFY_VEGETABLERATING = "prompt_modify_vegetablerating";	

	/************************VEGETABLESITUATION ****************************/
	String VEGETABLESITUATION_HOME = "vegetablesituation_home"; 
	String VEGETABLESITUATION_HOME_FROM_GROUP_LEVEL = "vegetablesituation_home_group_level"; 
	String VEGETABLESITUATION_HOME_SYSTEM_LEVEL = "vegetablesituation_home_system_level";
	String VEGETABLESITUATION_SEARCH_SYSTEM_LEVEL = "vegetablesituation_search_system_level";
	String EXT_VEGETABLESITUATION_SEARCH_SYSTEM_LEVEL = "ext_vegetablesituation_search_system_level";
	String VEGETABLESITUATION_SEARCH_GROUP_LEVEL = "vegetablesituation_search_group_level";
	
	String PROMPT_ADD_VEGETABLESITUATION = "prompt_add_vegetablesituation";	
	String PROMPT_MODIFY_VEGETABLESITUATION = "prompt_modify_vegetablesituation";	

	/************************TARGETVEGETABLE ****************************/
	String TARGETVEGETABLE_HOME = "targetvegetable_home"; 
	String TARGETVEGETABLE_HOME_FROM_GROUP_LEVEL = "targetvegetable_home_group_level"; 
	String TARGETVEGETABLE_HOME_SYSTEM_LEVEL = "targetvegetable_home_system_level";
	String TARGETVEGETABLE_SEARCH_SYSTEM_LEVEL = "targetvegetable_search_system_level";
	String EXT_TARGETVEGETABLE_SEARCH_SYSTEM_LEVEL = "ext_targetvegetable_search_system_level";
	String TARGETVEGETABLE_SEARCH_GROUP_LEVEL = "targetvegetable_search_group_level";
	
	String PROMPT_ADD_TARGETVEGETABLE = "prompt_add_targetvegetable";	
	String PROMPT_MODIFY_TARGETVEGETABLE = "prompt_modify_targetvegetable";	

	/************************POSTHARVESTSTAGES ****************************/
	String POSTHARVESTSTAGES_HOME = "postharveststages_home"; 
	String POSTHARVESTSTAGES_HOME_FROM_GROUP_LEVEL = "postharveststages_home_group_level"; 
	String POSTHARVESTSTAGES_HOME_SYSTEM_LEVEL = "postharveststages_home_system_level";
	String POSTHARVESTSTAGES_SEARCH_SYSTEM_LEVEL = "postharveststages_search_system_level";
	String EXT_POSTHARVESTSTAGES_SEARCH_SYSTEM_LEVEL = "ext_postharveststages_search_system_level";
	String POSTHARVESTSTAGES_SEARCH_GROUP_LEVEL = "postharveststages_search_group_level";
	
	String PROMPT_ADD_POSTHARVESTSTAGES = "prompt_add_postharveststages";	
	String PROMPT_MODIFY_POSTHARVESTSTAGES = "prompt_modify_postharveststages";	

	/************************TRANSPORTTOMAINBUYER ****************************/
	String TRANSPORTTOMAINBUYER_HOME = "transporttomainbuyer_home"; 
	String TRANSPORTTOMAINBUYER_HOME_FROM_GROUP_LEVEL = "transporttomainbuyer_home_group_level"; 
	String TRANSPORTTOMAINBUYER_HOME_SYSTEM_LEVEL = "transporttomainbuyer_home_system_level";
	String TRANSPORTTOMAINBUYER_SEARCH_SYSTEM_LEVEL = "transporttomainbuyer_search_system_level";
	String EXT_TRANSPORTTOMAINBUYER_SEARCH_SYSTEM_LEVEL = "ext_transporttomainbuyer_search_system_level";
	String TRANSPORTTOMAINBUYER_SEARCH_GROUP_LEVEL = "transporttomainbuyer_search_group_level";
	
	String PROMPT_ADD_TRANSPORTTOMAINBUYER = "prompt_add_transporttomainbuyer";	
	String PROMPT_MODIFY_TRANSPORTTOMAINBUYER = "prompt_modify_transporttomainbuyer";	

	/************************POSTHARVESTPROBLEMRATING ****************************/
	String POSTHARVESTPROBLEMRATING_HOME = "postharvestproblemrating_home"; 
	String POSTHARVESTPROBLEMRATING_HOME_FROM_GROUP_LEVEL = "postharvestproblemrating_home_group_level"; 
	String POSTHARVESTPROBLEMRATING_HOME_SYSTEM_LEVEL = "postharvestproblemrating_home_system_level";
	String POSTHARVESTPROBLEMRATING_SEARCH_SYSTEM_LEVEL = "postharvestproblemrating_search_system_level";
	String EXT_POSTHARVESTPROBLEMRATING_SEARCH_SYSTEM_LEVEL = "ext_postharvestproblemrating_search_system_level";
	String POSTHARVESTPROBLEMRATING_SEARCH_GROUP_LEVEL = "postharvestproblemrating_search_group_level";
	
	String PROMPT_ADD_POSTHARVESTPROBLEMRATING = "prompt_add_postharvestproblemrating";	
	String PROMPT_MODIFY_POSTHARVESTPROBLEMRATING = "prompt_modify_postharvestproblemrating";	

	/************************QUANTATIVELOSSESINPHSYSTEM ****************************/
	String QUANTATIVELOSSESINPHSYSTEM_HOME = "quantativelossesinphsystem_home"; 
	String QUANTATIVELOSSESINPHSYSTEM_HOME_FROM_GROUP_LEVEL = "quantativelossesinphsystem_home_group_level"; 
	String QUANTATIVELOSSESINPHSYSTEM_HOME_SYSTEM_LEVEL = "quantativelossesinphsystem_home_system_level";
	String QUANTATIVELOSSESINPHSYSTEM_SEARCH_SYSTEM_LEVEL = "quantativelossesinphsystem_search_system_level";
	String EXT_QUANTATIVELOSSESINPHSYSTEM_SEARCH_SYSTEM_LEVEL = "ext_quantativelossesinphsystem_search_system_level";
	String QUANTATIVELOSSESINPHSYSTEM_SEARCH_GROUP_LEVEL = "quantativelossesinphsystem_search_group_level";
	
	String PROMPT_ADD_QUANTATIVELOSSESINPHSYSTEM = "prompt_add_quantativelossesinphsystem";	
	String PROMPT_MODIFY_QUANTATIVELOSSESINPHSYSTEM = "prompt_modify_quantativelossesinphsystem";	

	/************************PRICEINTAKAPERKG ****************************/
	String PRICEINTAKAPERKG_HOME = "priceintakaperkg_home"; 
	String PRICEINTAKAPERKG_HOME_FROM_GROUP_LEVEL = "priceintakaperkg_home_group_level"; 
	String PRICEINTAKAPERKG_HOME_SYSTEM_LEVEL = "priceintakaperkg_home_system_level";
	String PRICEINTAKAPERKG_SEARCH_SYSTEM_LEVEL = "priceintakaperkg_search_system_level";
	String EXT_PRICEINTAKAPERKG_SEARCH_SYSTEM_LEVEL = "ext_priceintakaperkg_search_system_level";
	String PRICEINTAKAPERKG_SEARCH_GROUP_LEVEL = "priceintakaperkg_search_group_level";
	
	String PROMPT_ADD_PRICEINTAKAPERKG = "prompt_add_priceintakaperkg";	
	String PROMPT_MODIFY_PRICEINTAKAPERKG = "prompt_modify_priceintakaperkg";	

	/************************POSTHARVESTCONSTRAINTS ****************************/
	String POSTHARVESTCONSTRAINTS_HOME = "postharvestconstraints_home"; 
	String POSTHARVESTCONSTRAINTS_HOME_FROM_GROUP_LEVEL = "postharvestconstraints_home_group_level"; 
	String POSTHARVESTCONSTRAINTS_HOME_SYSTEM_LEVEL = "postharvestconstraints_home_system_level";
	String POSTHARVESTCONSTRAINTS_SEARCH_SYSTEM_LEVEL = "postharvestconstraints_search_system_level";
	String EXT_POSTHARVESTCONSTRAINTS_SEARCH_SYSTEM_LEVEL = "ext_postharvestconstraints_search_system_level";
	String POSTHARVESTCONSTRAINTS_SEARCH_GROUP_LEVEL = "postharvestconstraints_search_group_level";
	
	String PROMPT_ADD_POSTHARVESTCONSTRAINTS = "prompt_add_postharvestconstraints";	
	String PROMPT_MODIFY_POSTHARVESTCONSTRAINTS = "prompt_modify_postharvestconstraints";	

	/************************EXPECTATIONOFSUPPLYCHAINACTOR ****************************/
	String EXPECTATIONOFSUPPLYCHAINACTOR_HOME = "expectationofsupplychainactor_home"; 
	String EXPECTATIONOFSUPPLYCHAINACTOR_HOME_FROM_GROUP_LEVEL = "expectationofsupplychainactor_home_group_level"; 
	String EXPECTATIONOFSUPPLYCHAINACTOR_HOME_SYSTEM_LEVEL = "expectationofsupplychainactor_home_system_level";
	String EXPECTATIONOFSUPPLYCHAINACTOR_SEARCH_SYSTEM_LEVEL = "expectationofsupplychainactor_search_system_level";
	String EXT_EXPECTATIONOFSUPPLYCHAINACTOR_SEARCH_SYSTEM_LEVEL = "ext_expectationofsupplychainactor_search_system_level";
	String EXPECTATIONOFSUPPLYCHAINACTOR_SEARCH_GROUP_LEVEL = "expectationofsupplychainactor_search_group_level";
	
	String PROMPT_ADD_EXPECTATIONOFSUPPLYCHAINACTOR = "prompt_add_expectationofsupplychainactor";	
	String PROMPT_MODIFY_EXPECTATIONOFSUPPLYCHAINACTOR = "prompt_modify_expectationofsupplychainactor";	

	/************************COORDINATINGORGANIZATION ****************************/
	String COORDINATINGORGANIZATION_HOME = "coordinatingorganization_home"; 
	String COORDINATINGORGANIZATION_HOME_FROM_GROUP_LEVEL = "coordinatingorganization_home_group_level"; 
	String COORDINATINGORGANIZATION_HOME_SYSTEM_LEVEL = "coordinatingorganization_home_system_level";
	String COORDINATINGORGANIZATION_SEARCH_SYSTEM_LEVEL = "coordinatingorganization_search_system_level";
	String EXT_COORDINATINGORGANIZATION_SEARCH_SYSTEM_LEVEL = "ext_coordinatingorganization_search_system_level";
	String COORDINATINGORGANIZATION_SEARCH_GROUP_LEVEL = "coordinatingorganization_search_group_level";
	
	String PROMPT_ADD_COORDINATINGORGANIZATION = "prompt_add_coordinatingorganization";	
	String PROMPT_MODIFY_COORDINATINGORGANIZATION = "prompt_modify_coordinatingorganization";	

	/************************EXPECTATIONOTHERQUESTIONS ****************************/
	String EXPECTATIONOTHERQUESTIONS_HOME = "expectationotherquestions_home"; 
	String EXPECTATIONOTHERQUESTIONS_HOME_FROM_GROUP_LEVEL = "expectationotherquestions_home_group_level"; 
	String EXPECTATIONOTHERQUESTIONS_HOME_SYSTEM_LEVEL = "expectationotherquestions_home_system_level";
	String EXPECTATIONOTHERQUESTIONS_SEARCH_SYSTEM_LEVEL = "expectationotherquestions_search_system_level";
	String EXT_EXPECTATIONOTHERQUESTIONS_SEARCH_SYSTEM_LEVEL = "ext_expectationotherquestions_search_system_level";
	String EXPECTATIONOTHERQUESTIONS_SEARCH_GROUP_LEVEL = "expectationotherquestions_search_group_level";
	
	String PROMPT_ADD_EXPECTATIONOTHERQUESTIONS = "prompt_add_expectationotherquestions";	
	String PROMPT_MODIFY_EXPECTATIONOTHERQUESTIONS = "prompt_modify_expectationotherquestions";	

	/************************COMMONLYGROWNVEGETABLE ****************************/
	String COMMONLYGROWNVEGETABLE_HOME = "commonlygrownvegetable_home"; 
	String COMMONLYGROWNVEGETABLE_HOME_FROM_GROUP_LEVEL = "commonlygrownvegetable_home_group_level"; 
	String COMMONLYGROWNVEGETABLE_HOME_SYSTEM_LEVEL = "commonlygrownvegetable_home_system_level";
	String COMMONLYGROWNVEGETABLE_SEARCH_SYSTEM_LEVEL = "commonlygrownvegetable_search_system_level";
	String EXT_COMMONLYGROWNVEGETABLE_SEARCH_SYSTEM_LEVEL = "ext_commonlygrownvegetable_search_system_level";
	String COMMONLYGROWNVEGETABLE_SEARCH_GROUP_LEVEL = "commonlygrownvegetable_search_group_level";
	
	String PROMPT_ADD_COMMONLYGROWNVEGETABLE = "prompt_add_commonlygrownvegetable";	
	String PROMPT_MODIFY_COMMONLYGROWNVEGETABLE = "prompt_modify_commonlygrownvegetable";	

 }
