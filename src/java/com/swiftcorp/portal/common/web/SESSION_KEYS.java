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
	String MODULEDTO_LIST = "moduleDTOList";
	
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
	String ACCOUNT_TREE_SEARCH_RESULT = "account_tree_search_result";

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

	/************************ITEM ****************************/
	String ITEM = "item";
	String ITEM_LIST = "item_list";
	String ITEM_SEARCH_RESULT = "item_search_result";

	/************************BATCH ****************************/
	String BATCH = "batch";
	String BATCH_LIST = "batch_list";
	String BATCH_SEARCH_RESULT = "batch_search_result";

	/************************TRANSACTION ****************************/
	String TRANSACTION = "transaction";
	String TRANSACTION_LIST = "transaction_list";
	String TRANSACTION_SEARCH_RESULT = "transaction_search_result";

	String TRANSACTION_DETAIL_SEARCH_RESULT = "transactiondetail_search_result";
	/************************TRAVELSCHEDULE ****************************/
	String TRAVELSCHEDULE = "travelschedule";
	String TRAVELSCHEDULE_LIST = "travelschedule_list";
	String TRAVELSCHEDULE_SEARCH_RESULT = "travelschedule_search_result";

	/************************VEHICLE ****************************/
	String VEHICLE = "vehicle";
	String VEHICLE_LIST = "vehicle_list";
	String VEHICLE_SEARCH_RESULT = "vehicle_search_result";

	/************************ROUTE ****************************/
	String ROUTE = "route";
	String ROUTE_LIST = "route_list";
	String ROUTE_SEARCH_RESULT = "route_search_result";

	/************************BUYER ****************************/
	String BUYER = "buyer";
	String BUYER_LIST = "buyer_list";
	String BUYER_SEARCH_RESULT = "buyer_search_result";

	/************************RESERVATION ****************************/
	String RESERVATION = "reservation";
	String RESERVATION_LIST = "reservation_list";
	String RESERVATION_SEARCH_RESULT = "reservation_search_result";

	/************************TRIP ****************************/
	String TRIP = "trip";
	String TRIP_LIST = "trip_list";
	String TRIP_SEARCH_RESULT = "trip_search_result";

	/************************STATION ****************************/
	String STATION = "station";
	String STATION_LIST = "station_list";
	String STATION_SEARCH_RESULT = "station_search_result";
	
	/************************SUBROUTE ****************************/
	String SUBROUTE = "subroute";
	String SUBROUTE_LIST = "subroute_list";
	String SUBROUTE_SEARCH_RESULT = "subroute_search_result";

	/************************STORE ****************************/
	String STORE = "store";
	String STORE_LIST = "store_list";
	String STORE_SEARCH_RESULT = "store_search_result";

	/************************COVERING ****************************/
	String COVERING = "covering";
	String COVERING_LIST = "covering_list";
	String COVERING_SEARCH_RESULT = "covering_search_result";

	/************************TRANSPORTATION ****************************/
	String TRANSPORTATION = "transportation";
	String TRANSPORTATION_LIST = "transportation_list";
	String TRANSPORTATION_SEARCH_RESULT = "transportation_search_result";

	/************************PAYMENT ****************************/
	String PAYMENT = "payment";
	String PAYMENT_LIST = "payment_list";
	String PAYMENT_SEARCH_RESULT = "payment_search_result";

	/************************CREDITTERM ****************************/
	String CREDITTERM = "creditterm";
	String CREDITTERM_LIST = "creditterm_list";
	String CREDITTERM_SEARCH_RESULT = "creditterm_search_result";

	/************************UNIT ****************************/
	String UNIT = "unit";
	String UNIT_LIST = "unit_list";
	String UNIT_SEARCH_RESULT = "unit_search_result";

	/************************PRODUCTIONSTEP ****************************/
	String PRODUCTIONSTEP = "productionstep";
	String PRODUCTIONSTEP_LIST = "productionstep_list";
	String PRODUCTIONSTEP_SEARCH_RESULT = "productionstep_search_result";

	/************************CURRENCY ****************************/
	String CURRENCY = "currency";
	String CURRENCY_LIST = "currency_list";
	String CURRENCY_SEARCH_RESULT = "currency_search_result";

	/************************BANK ****************************/
	String BANK = "bank";
	String BANK_LIST = "bank_list";
	String BANK_SEARCH_RESULT = "bank_search_result";

	/************************CITY ****************************/
	String CITY = "city";
	String CITY_LIST = "city_list";
	String CITY_SEARCH_RESULT = "city_search_result";

	/************************COUNTRY ****************************/
	String COUNTRY = "country";
	String COUNTRY_LIST = "country_list";
	String COUNTRY_SEARCH_RESULT = "country_search_result";

	/************************MODULE ****************************/
	String MODULE = "module";
	String MODULE_LIST = "module_list";
	String MODULE_SEARCH_RESULT = "module_search_result";

	/************************HSCODE ****************************/
	String HSCODE = "hscode";
	String HSCODE_LIST = "hscode_list";
	String HSCODE_SEARCH_RESULT = "hscode_search_result";

	/************************MATERIALTYPE ****************************/
	String MATERIALTYPE = "materialtype";
	String MATERIALTYPE_LIST = "materialtype_list";
	String MATERIALTYPE_SEARCH_RESULT = "materialtype_search_result";

	/************************MATERIALCATEGORY ****************************/
	String MATERIALCATEGORY = "materialcategory";
	String MATERIALCATEGORY_LIST = "materialcategory_list";
	String MATERIALCATEGORY_SEARCH_RESULT = "materialcategory_search_result";

	/************************WAREHOUSE ****************************/
	String WAREHOUSE = "warehouse";
	String WAREHOUSE_LIST = "warehouse_list";
	String WAREHOUSE_SEARCH_RESULT = "warehouse_search_result";

	/************************PRODUCTIONSECTION ****************************/
	String PRODUCTIONSECTION = "productionsection";
	String PRODUCTIONSECTION_LIST = "productionsection_list";
	String PRODUCTIONSECTION_SEARCH_RESULT = "productionsection_search_result";

	/************************INKTYPE ****************************/
	String INKTYPE = "inktype";
	String INKTYPE_LIST = "inktype_list";
	String INKTYPE_SEARCH_RESULT = "inktype_search_result";

	/************************MACHINE ****************************/
	String MACHINE = "machine";
	String MACHINE_LIST = "machine_list";
	String MACHINE_SEARCH_RESULT = "machine_search_result";

	/************************MACHINETYPE ****************************/
	String MACHINETYPE = "machinetype";
	String MACHINETYPE_LIST = "machinetype_list";
	String MACHINETYPE_SEARCH_RESULT = "machinetype_search_result";

	/************************BRAND ****************************/
	String BRAND = "brand";
	String BRAND_LIST = "brand_list";
	String BRAND_SEARCH_RESULT = "brand_search_result";

	/************************LEAVEMANAGEMENT ****************************/
	String LEAVEMANAGEMENT = "leavemanagement";
	String LEAVEMANAGEMENT_LIST = "leavemanagement_list";
	String LEAVEMANAGEMENT_SEARCH_RESULT = "leavemanagement_search_result";

	/************************SALARY ****************************/
	String SALARY = "salary";
	String SALARY_LIST = "salary_list";
	String SALARY_SEARCH_RESULT = "salary_search_result";

	/************************ATTENDENCE ****************************/
	String ATTENDENCE = "attendence";
	String ATTENDENCE_LIST = "attendence_list";
	String ATTENDENCE_SEARCH_RESULT = "attendence_search_result";

	/************************DEPARTMENT ****************************/
	String DEPARTMENT = "department";
	String DEPARTMENT_LIST = "department_list";
	String DEPARTMENT_SEARCH_RESULT = "department_search_result";

	/************************DESIGNATION ****************************/
	String DESIGNATION = "designation";
	String DESIGNATION_LIST = "designation_list";
	String DESIGNATION_SEARCH_RESULT = "designation_search_result";

	/************************LEAVEPACKAGE ****************************/
	String LEAVEPACKAGE = "leavepackage";
	String LEAVEPACKAGE_LIST = "leavepackage_list";
	String LEAVEPACKAGE_SEARCH_RESULT = "leavepackage_search_result";

	/************************HOLIDAY ****************************/
	String HOLIDAY = "holiday";
	String HOLIDAY_LIST = "holiday_list";
	String HOLIDAY_SEARCH_RESULT = "holiday_search_result";

	/************************EMPLOYEE ****************************/
	String EMPLOYEE = "employee";
	String EMPLOYEE_LIST = "employee_list";
	String EMPLOYEE_SEARCH_RESULT = "employee_search_result";

	/************************EMPLOYEEPERSONALINFO ****************************/
	String EMPLOYEEPERSONALINFO = "employeepersonalinfo";
	String EMPLOYEEPERSONALINFO_LIST = "employeepersonalinfo_list";
	String EMPLOYEEPERSONALINFO_SEARCH_RESULT = "employeepersonalinfo_search_result";

	/************************ACADEMICINFO ****************************/
	String ACADEMICINFO = "academicinfo";
	String ACADEMICINFO_LIST = "academicinfo_list";
	String ACADEMICINFO_SEARCH_RESULT = "academicinfo_search_result";

	/************************CAREERHISTORY ****************************/
	String CAREERHISTORY = "careerhistory";
	String CAREERHISTORY_LIST = "careerhistory_list";
	String CAREERHISTORY_SEARCH_RESULT = "careerhistory_search_result";

	/************************SUPPLIER ****************************/
	String SUPPLIER = "supplier";
	String SUPPLIER_LIST = "supplier_list";
	String SUPPLIER_SEARCH_RESULT = "supplier_search_result";

	/************************SUPPLIERBANKINFO ****************************/
	String SUPPLIERBANKINFO = "supplierbankinfo";
	String SUPPLIERBANKINFO_LIST = "supplierbankinfo_list";
	String SUPPLIERBANKINFO_SEARCH_RESULT = "supplierbankinfo_search_result";

	/************************INVENTORYIN ****************************/
	String INVENTORYIN = "inventoryin";
	String INVENTORYIN_LIST = "inventoryin_list";
	String INVENTORYIN_SEARCH_RESULT = "inventoryin_search_result";

	/************************INVENTORYOUT ****************************/
	String INVENTORYOUT = "inventoryout";
	String INVENTORYOUT_LIST = "inventoryout_list";
	String INVENTORYOUT_SEARCH_RESULT = "inventoryout_search_result";

	/************************MAILINFO ****************************/
	String MAILINFO = "mailinfo";
	String MAILINFO_LIST = "mailinfo_list";
	String MAILINFO_SEARCH_RESULT = "mailinfo_search_result";
	String MAILINFO_INITIAL_LOAD = "mailinfo_initial_load";

	/************************EMAIL ****************************/
	String EMAIL = "email";
	String EMAIL_LIST = "email_list";
	String EMAIL_SEARCH_RESULT = "email_search_result";

	/************************EMAILDTL ****************************/
	String EMAILDTL = "emaildtl";
	String EMAILDTL_LIST = "emaildtl_list";
	String EMAILDTL_SEARCH_RESULT = "emaildtl_search_result";

	/************************EMAILGROUP ****************************/
	String EMAILGROUP = "emailgroup";
	String EMAILGROUP_LIST = "emailgroup_list";
	String EMAILGROUP_SEARCH_RESULT = "emailgroup_search_result";

	/************************EMAILREFERANCE ****************************/
	String EMAILREFERANCE = "emailreferance";
	String EMAILREFERANCE_LIST = "emailreferance_list";
	String EMAILREFERANCE_SEARCH_RESULT = "emailreferance_search_result";

	/************************EMAILRECIPIENTS ****************************/
	String EMAILRECIPIENTS = "emailrecipients";
	String EMAILRECIPIENTS_LIST = "emailrecipients_list";
	String EMAILRECIPIENTS_SEARCH_RESULT = "emailrecipients_search_result";

	/************************EMAILRECV ****************************/
	String EMAILRECV = "emailrecv";
	String EMAILRECV_LIST = "emailrecv_list";
	String EMAILRECV_SEARCH_RESULT = "emailrecv_search_result";
	
	/************************EMAILRECV ****************************/
	String CUSTOMERINFO = "customerinfo";
	String CUSTOMERINFO_LIST = "customerinfo_list";
	String CUSTOMERINFO_SEARCH_RESULT = "customerinfo_search_result";

	/************************SHIPPINGINFO ****************************/
	String SHIPPINGINFO = "shippinginfo";
	String SHIPPINGINFO_LIST = "shippinginfo_list";
	String SHIPPINGINFO_SEARCH_RESULT = "shippinginfo_search_result";

	/************************BANKINFO ****************************/
	String BANKINFO = "bankinfo";
	String BANKINFO_LIST = "bankinfo_list";
	String BANKINFO_SEARCH_RESULT = "bankinfo_search_result";

	/************************INQUIRY ****************************/
	String INQUIRY = "inquiry";
	String INQUIRY_LIST = "inquiry_list";
	String INQUIRY_SEARCH_RESULT = "inquiry_search_result";

	/************************ITEMMASTER ****************************/
	String ITEMMASTER = "itemmaster";
	String ITEMMASTER_LIST = "itemmaster_list";
	String ITEMMASTER_SEARCH_RESULT = "itemmaster_search_result";

	/************************MATERIALS ****************************/
	String MATERIALS = "materials";
	String MATERIALS_LIST = "materials_list";
	String MATERIALS_SEARCH_RESULT = "materials_search_result";

	/************************PRICING ****************************/
	String PRICING = "pricing";
	String PRICING_LIST = "pricing_list";
	String PRICING_SEARCH_RESULT = "pricing_search_result";

	/************************PAYMENTCOLLECTION ****************************/
	String PAYMENTCOLLECTION = "paymentcollection";
	String PAYMENTCOLLECTION_LIST = "paymentcollection_list";
	String PAYMENTCOLLECTION_SEARCH_RESULT = "paymentcollection_search_result";

	/************************PAYMENTCOLLDOCS ****************************/
	String PAYMENTCOLLDOCS = "paymentcolldocs";
	String PAYMENTCOLLDOCS_LIST = "paymentcolldocs_list";
	String PAYMENTCOLLDOCS_SEARCH_RESULT = "paymentcolldocs_search_result";

	/************************GENERALPROFILE ****************************/
	String GENERALPROFILE = "generalprofile";
	String GENERALPROFILE_LIST = "generalprofile_list";
	String GENERALPROFILE_SEARCH_RESULT = "generalprofile_search_result";

	/************************RESPONDENTADDRESS ****************************/
	String RESPONDENTADDRESS = "respondentaddress";
	String RESPONDENTADDRESS_LIST = "respondentaddress_list";
	String RESPONDENTADDRESS_SEARCH_RESULT = "respondentaddress_search_result";

	/************************UPAZILLA ****************************/
	String UPAZILLA = "upazilla";
	String UPAZILLA_LIST = "upazilla_list";
	String UPAZILLA_SEARCH_RESULT = "upazilla_search_result";

	/************************RESPONDENTOTHERINFO ****************************/
	String RESPONDENTOTHERINFO = "respondentotherinfo";
	String RESPONDENTOTHERINFO_LIST = "respondentotherinfo_list";
	String RESPONDENTOTHERINFO_SEARCH_RESULT = "respondentotherinfo_search_result";

	/************************CROPDETAIL ****************************/
	String CROPDETAIL = "cropdetail";
	String CROPDETAIL_LIST = "cropdetail_list";
	String CROPDETAIL_SEARCH_RESULT = "cropdetail_search_result";

	/************************MOSTIMPVEGETABLE ****************************/
	String MOSTIMPVEGETABLE = "mostimpvegetable";
	String MOSTIMPVEGETABLE_LIST = "mostimpvegetable_list";
	String MOSTIMPVEGETABLE_SEARCH_RESULT = "mostimpvegetable_search_result";

	/************************VEGETABLERATING ****************************/
	String VEGETABLERATING = "vegetablerating";
	String VEGETABLERATING_LIST = "vegetablerating_list";
	String VEGETABLERATING_SEARCH_RESULT = "vegetablerating_search_result";

	/************************VEGETABLESITUATION ****************************/
	String VEGETABLESITUATION = "vegetablesituation";
	String VEGETABLESITUATION_LIST = "vegetablesituation_list";
	String VEGETABLESITUATION_SEARCH_RESULT = "vegetablesituation_search_result";

	/************************TARGETVEGETABLE ****************************/
	String TARGETVEGETABLE = "targetvegetable";
	String TARGETVEGETABLE_LIST = "targetvegetable_list";
	String TARGETVEGETABLE_SEARCH_RESULT = "targetvegetable_search_result";

	/************************POSTHARVESTSTAGES ****************************/
	String POSTHARVESTSTAGES = "postharveststages";
	String POSTHARVESTSTAGES_LIST = "postharveststages_list";
	String POSTHARVESTSTAGES_SEARCH_RESULT = "postharveststages_search_result";

	/************************TRANSPORTTOMAINBUYER ****************************/
	String TRANSPORTTOMAINBUYER = "transporttomainbuyer";
	String TRANSPORTTOMAINBUYER_LIST = "transporttomainbuyer_list";
	String TRANSPORTTOMAINBUYER_SEARCH_RESULT = "transporttomainbuyer_search_result";

	/************************POSTHARVESTPROBLEMRATING ****************************/
	String POSTHARVESTPROBLEMRATING = "postharvestproblemrating";
	String POSTHARVESTPROBLEMRATING_LIST = "postharvestproblemrating_list";
	String POSTHARVESTPROBLEMRATING_SEARCH_RESULT = "postharvestproblemrating_search_result";

	/************************QUANTATIVELOSSESINPHSYSTEM ****************************/
	String QUANTATIVELOSSESINPHSYSTEM = "quantativelossesinphsystem";
	String QUANTATIVELOSSESINPHSYSTEM_LIST = "quantativelossesinphsystem_list";
	String QUANTATIVELOSSESINPHSYSTEM_SEARCH_RESULT = "quantativelossesinphsystem_search_result";

	/************************PRICEINTAKAPERKG ****************************/
	String PRICEINTAKAPERKG = "priceintakaperkg";
	String PRICEINTAKAPERKG_LIST = "priceintakaperkg_list";
	String PRICEINTAKAPERKG_SEARCH_RESULT = "priceintakaperkg_search_result";

	/************************POSTHARVESTCONSTRAINTS ****************************/
	String POSTHARVESTCONSTRAINTS = "postharvestconstraints";
	String POSTHARVESTCONSTRAINTS_LIST = "postharvestconstraints_list";
	String POSTHARVESTCONSTRAINTS_SEARCH_RESULT = "postharvestconstraints_search_result";

	/************************EXPECTATIONOFSUPPLYCHAINACTOR ****************************/
	String EXPECTATIONOFSUPPLYCHAINACTOR = "expectationofsupplychainactor";
	String EXPECTATIONOFSUPPLYCHAINACTOR_LIST = "expectationofsupplychainactor_list";
	String EXPECTATIONOFSUPPLYCHAINACTOR_SEARCH_RESULT = "expectationofsupplychainactor_search_result";

	/************************COORDINATINGORGANIZATION ****************************/
	String COORDINATINGORGANIZATION = "coordinatingorganization";
	String COORDINATINGORGANIZATION_LIST = "coordinatingorganization_list";
	String COORDINATINGORGANIZATION_SEARCH_RESULT = "coordinatingorganization_search_result";

	/************************EXPECTATIONOTHERQUESTIONS ****************************/
	String EXPECTATIONOTHERQUESTIONS = "expectationotherquestions";
	String EXPECTATIONOTHERQUESTIONS_LIST = "expectationotherquestions_list";
	String EXPECTATIONOTHERQUESTIONS_SEARCH_RESULT = "expectationotherquestions_search_result";

	/************************COMMONLYGROWNVEGETABLE ****************************/
	String COMMONLYGROWNVEGETABLE = "commonlygrownvegetable";
	String COMMONLYGROWNVEGETABLE_LIST = "commonlygrownvegetable_list";
	String COMMONLYGROWNVEGETABLE_SEARCH_RESULT = "commonlygrownvegetable_search_result";

 }
