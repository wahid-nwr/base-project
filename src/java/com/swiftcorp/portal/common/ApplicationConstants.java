package com.swiftcorp.portal.common;

import java.util.ArrayList;
import java.util.List;

import com.swiftcorp.portal.report.service.IReportService;

public class ApplicationConstants
{
	
	public static final String USER_ROOT = "user";
	public static final String COMMUNICATOR_ROOT = "communicator";
	public static final String AUTHENTICATED = "authenticated";
	public static final String ERROR_MSG = "errormessage";
	public static final String SERVICES = "services";
	public static final String SERVICE = "service";
	
	public static final String USER_LIST = "user_list";
	public static final String SELECTED_USER = "selected_user";
	public static final String RESULT = "result";
	public static final String USER_METHOD_NAME = "user_method_name";
	public static final String USER_CONTACT_METHOD_NAME = "user_contact_method_name";
	public static final String SUCCESS = "success";
	public static final String USER_SUCCESS = "user_success";
	public static final String SIP_USER_SUCCESS = "sip_user_success";
	public static final String LOGIN = "login";
	public static final String REGISTERED = "registered";
	
	public static final String IDENTIFICATION_SIGN = "#";
	public static final String ROLL = "Roll";
	public static final String NAME = "Name";
	public static final String ROLL_NO = "Roll No";
	
	public static String FILE_STORAGE_DIR = "c:/upload/";
	public static String FILE_STORAGE_UPLOAD_DIR = FILE_STORAGE_DIR + "upload/";
	public static String FILE_STORAGE_TEMPLATE_DIR = FILE_STORAGE_DIR + "templates/";
	public static String FILE_STORAGE_REPORT_DIR = FILE_STORAGE_DIR + "reports/";
	public static IReportService REPORT_SERVICE = null;
	
	public static final String RECORD_SOURCE_STATUS_NOT_UPLOADED = "Not Uploaded";
	public static final String RECORD_SOURCE_STATUS_UPLOADED = "Uploaded";
	public static final String RECORD_SOURCE_STATUS_IMPORTED = "Imported";
	public static final String RECORD_SOURCE_STATUS_PROCESSED = "Processed";
	
	public static final int MULTIPLE_CHOICE_QUESTION_TYPE = 1;
	
	public static List<String> getRecordSourceStatusList ( )
	{
		List<String> statusList = new ArrayList<String> ();
		statusList.add ( RECORD_SOURCE_STATUS_NOT_UPLOADED );
		statusList.add ( RECORD_SOURCE_STATUS_UPLOADED );
		statusList.add ( RECORD_SOURCE_STATUS_IMPORTED );
		statusList.add ( RECORD_SOURCE_STATUS_PROCESSED );
		return statusList;
	}
	
	public static int ROLE_FUNCTION_VIEW_LISTS = 1;
	public static int ROLE_FUNCTION_VIEW_BENEFICIARY = 2;
	public static int ROLE_FUNCTION_VIEW_HOUSEHOLD = 3;
	public static int ROLE_FUNCTION_VIEW_WORK_SCHEDULE = 4;
	public static int ROLE_FUNCTION_VIEW_VIEW_REPORTS = 5;
	public static int ROLE_FUNCTION_VIEW_ALERTS = 6;
	public static int ROLE_FUNCTION_VIEW_SEARCH = 7;
	public static int ROLE_FUNCTION_VIEW_LOGOUT = 8;
	public static int ROLE_FUNCTION_VIEW_QUESTIONNAIRE = 9;
	public static int ROLE_FUNCTION_VIEW_ALGORITHMS = 10;
	public static int ROLE_FUNCTION_VIEW_USERS = 12;
	public static int ROLE_FUNCTION_VIEW_BACKUP = 13;
	public static int ROLE_FUNCTION_VIEW_EXPORTDATA = 14;
	public static int ROLE_FUNCTION_VIEW_QUESTION = 15;
	public static int ROLE_FUNCTION_VIEW_ROLE_FUNCTION = 16;
	public static int ROLE_FUNCTION_REPORT_MOTHER = 20;
	public static int ROLE_FUNCTION_REPORT_CHILD = 21;
	public static int ROLE_FUNCTION_ALGORITHM = 22;
	public static int ROLE_FUNCTION_GEOGRAPHICAL_INFO = 25;
	public static int ROLE_FUNCTION_GEOGRAPHICAL_INFO_VIEW = 26;
	public static int ROLE_FUNCTION_REPORT_MPR = 27;
	public static int ROLE_FUNCTION_DCR_INFO = 35;
	public static int ROLE_FUNCTION_DCR_REPORT = 36;
	public static int ROLE_FUNCTION_IMPORT = 37;
	public static int ROLE_FUNCTION_TRAVEL_SCHEDULE = 38;
	public static int ROLE_FUNCTION_TRAVEL_VEHICLE = 39;
	public static int ROLE_FUNCTION_TRAVEL_ROUTE = 40;
	public static int ROLE_FUNCTION_TRAVEL_BUYER = 41;
	public static int ROLE_FUNCTION_TRAVEL_TRIP = 42;
	public static int ROLE_FUNCTION_TRAVEL_RESERVATION = 43;
	public static int ROLE_FUNCTION_ACCOUNT = 44;
	public static int ROLE_FUNCTION_TRANSACTION = 45;
	public static int ROLE_FUNCTION_STATION = 46;
	public static int ROLE_FUNCTION_BATCH = 48;
	public static int ROLE_FUNCTION_ITEM = 47;
	public static int ROLE_FUNCTION_STORE = 49;
	public static int ROLE_FUNCTION_COVERING = 50;
	public static int ROLE_FUNCTION_TRANSPORT = 51;
	public static int ROLE_FUNCTION_PAYMENT = 52;
	public static int ROLE_FUNCTION_CREDITTERM = 53;
	public static int ROLE_FUNCTION_UNIT = 54;
	public static int ROLE_FUNCTION_PRODSTEP = 55;
	public static int ROLE_FUNCTION_CURRENCY = 56;
	public static int ROLE_FUNCTION_BANK = 57;
	public static int ROLE_FUNCTION_CITY = 58;
	public static int ROLE_FUNCTION_COUNTRY = 59;
	public static int ROLE_FUNCTION_MODULE = 60;
	public static int ROLE_FUNCTION_HSCODE = 61;
	public static int ROLE_FUNCTION_MATERIALTYPE = 62;
	public static int ROLE_FUNCTION_MATERIALCATEGORY = 63;
	public static int ROLE_FUNCTION_WAREHOUSE = 64;
	public static int ROLE_FUNCTION_PRODUCTIONSECTION = 65;
	public static int ROLE_FUNCTION_INKTYPE = 66;
	public static int ROLE_FUNCTION_MACHINE = 67;
	public static int ROLE_FUNCTION_MACHINETYPE = 68;
	public static int ROLE_FUNCTION_BRAND = 69;
	public static int ROLE_FUNCTION_LEAVEMGNT = 70;
	public static int ROLE_FUNCTION_SALARY = 71;
	public static int ROLE_FUNCTION_ATTENDENCE = 72;
	public static int ROLE_FUNCTION_DEPARTMENT = 73;
	public static int ROLE_FUNCTION_DESIGNATION = 74;
	public static int ROLE_FUNCTION_LEAVEPACKAGE = 75;
	public static int ROLE_FUNCTION_HOLIDAY = 76;
	public static int ROLE_FUNCTION_EMPLOYEE = 77;
	public static int ROLE_FUNCTION_EMPLOYEEINFO = 78;
	public static int ROLE_FUNCTION_ACADEMICINFO = 79;
	public static int ROLE_FUNCTION_CAREERHISTORY = 80;
	public static int ROLE_FUNCTION_SUPPLIER = 81;
	public static int ROLE_FUNCTION_SUPPLIERBANKINFO = 82;
	public static int ROLE_FUNCTION_INVENTORYIN = 83;
	public static int ROLE_FUNCTION_INVENTORYOUT = 84;
	public static int ROLE_FUNCTION_MAILBOX = 85;
	public static int ROLE_FUNCTION_CUSTOMERINFO = 86;
	public static int ROLE_FUNCTION_EMAIL = 8005;
	public static int ROLE_FUNCTION_EMAILDTL = 8005;
	public static int ROLE_FUNCTION_EMAILGROUP = 8005;
	public static int ROLE_FUNCTION_EMAILREF = 8005;
	public static int ROLE_FUNCTION_EMAILRECIPIENT = 8005;
	public static int ROLE_FUNCTION_EMAILRECV = 8005;
	public static int ROLE_FUNCTION_SHIPPINGINFO = 8005;
	public static int ROLE_FUNCTION_BANKINFO = 8005;
	public static int ROLE_FUNCTION_INQUIRY = 87;
	public static int ROLE_FUNCTION_ITEMMASTER = 88;
	public static int ROLE_FUNCTION_MATERIALS = 8005;
	public static int ROLE_FUNCTION_PRICING = 8005;
	public static int ROLE_FUNCTION_PAYMENT_COLL = 89;
	public static int ROLE_FUNCTION_EMP_REPORT = 90;
	public static int ROLE_FUNCTION_SALE_REPORT = 91;
	public static int ROLE_FUNCTION_CUSTOMER_ORDER_REPORT = 92;
	public static int ROLE_FUNCTION_GENERAL_PROFILE = 93;
	public static int ROLE_FUNCTION_RESPONDENT_DETAILS = 94;
	public static int ROLE_FUNCTION_RESPONDENT_OTHER_INFO = 95;
	public static int ROLE_FUNCTION_UPAZILLA = 96;
	public static int ROLE_FUNCTION_CROP_DETAIL = 100;
	public static int ROLE_FUNCTION_MOST_IMP_VEG = 97;
	public static int ROLE_FUNCTION_VEGETABLE_RATING = 98;
	public static int ROLE_FUNCTION_VEGETABLE_SITUATION = 99;
	public static int ROLE_FUNCTION_TARGET_VEGETABLES = 101;
	public static int ROLE_FUNCTION_POST_HARVEST_STAGES = 102;
	public static int ROLE_FUNCTION_TRANSPORT_TO_MAIN_BUYER = 103;
	public static int ROLE_FUNCTION_COORDINATING_ORGANIZATION = 104;
	public static int ROLE_FUNCTION_EXPECTATION_OF_SUPPLY_CHAIN_ACTOR = 105;
	public static int ROLE_FUNCTION_EXPECTATION_OTHER_QUESTION = 106;
	public static int ROLE_FUNCTION_POST_HARVEST_CONSTRAINTS = 107;
	public static int ROLE_FUNCTION_RATING_OF_POST_HARVEST_PROBLEMS = 108;
	public static int ROLE_FUNCTION_PRICE_IN_TAKA_PER_KG = 109;
	public static int ROLE_FUNCTION_QUANTATIVE_LOSSES_IN_PH_SYSTEM = 110;
	public static int ROLE_FUNCTION_COMMONLY_GROWN_VEGETABLE = 111;
	public static int ROLE_FUNCTION_GENERAL_SETUP_REPORT = 112;
	//add rolefunction here
	
	public static long ROLE_SYSTEM_ADMIN = 1;
	public static long ROLE_DOCTOR = 8;
	
	public static String ECONOMIC_COND_GROUP_1 = "very poor";
	public static String ECONOMIC_COND_GROUP_2 = "poor";
	public static String ECONOMIC_COND_GROUP_3 = "not poor";
	
	public static String CHILD_GESTATIONAL_LIFE_POST = "Post term";
	public static String CHILD_GESTATIONAL_LIFE_PRE = "Pre term";
	
	public static int ANSSER_TYPE_DOUBLE_INPUT = 11;
	public static int ANSSER_TYPE_DATE_INPUT = 9;
	
	// ANC COMPLICATIONS
	public static String severeHeadache = "sh";
	public static String excessiveBleeding = "eb";
	public static String isfever = "fv";
	public static String odorousDischarge = "od";
	public static String tornPerineum = "tp";
	public static String problemOnBreast = "pb";
	public static String abdomenPain = "ap";
	// PNC COMPLICATIONS
	public static String bluredVision = "bv";
	public static String unusalFatigue = "uf";
	public static String varginalInfection = "vi";
	public static String feelIncreasedThirsty = "fi";
	public static String urinalBleeding = "ub";
	public static String extraUrinate = "eu";
	public static String isJaundice = "jd";
	public static String isConvulsion = "cv";
	public static String severeAnemia = "sa";
	public static String highFever = "hf";
	
	public static String SEARCH_VISIT_TYPE_ANC = "anc";
	public static String SEARCH_VISIT_TYPE_PNC = "pnc";
	
	// VISIT TYPE FOR MOTHER BENEFICIARY
	public static int VISIT_TYPE_MOTHER_REG = 5;
	public static int VISIT_TYPE_MOTHER_ANC_1ST_TRIMESTER = 10;
	public static int VISIT_TYPE_MOTHER_ANC_2ND_TRIMESTER = 15;
	public static int VISIT_TYPE_MOTHER_ANC_3RD_TRIMESTER = 20;
	public static int VISIT_TYPE_MOTHER_DELIVERY = 25;
	public static int VISIT_TYPE_MOTHER_PNC = 30;
	public static int VISIT_TYPE_MOTHER_MISCARRIAGE = 35;
	
	//VISIT TYPE FOR CHILD BENEFICIARY
	public static int VISIT_TYPE_CHILD_REG = 40;
	public static int VISIT_TYPE_CHILD_1ST_VISIT = 45;
	public static int VISIT_TYPE_CHILD_3RD_DAY_VISIT = 50;
	public static int VISIT_TYPE_CHILD_7TH_DAY_VISIT = 55;
	public static int VISIT_TYPE_CHILD_14TH_DAY_VISIT = 60;
	public static int VISIT_TYPE_CHILD_28TH_DAY_VISIT = 65;
	public static int VISIT_TYPE_CHILD_1YR_VISIT = 70;
	public static int VISIT_TYPE_CHILD_5YR_VISIT = 75;
	
	//VISIT TYPE FOR HOUSE HOLD
	public static int VISIT_TYPE_HH_REG = 80;
	public static int VISIT_TYPE_HH_VISIT = 85;
	
	//VISIT TYPE FOR DEATH RECORD
	public static int VISIT_TYPE_DEATH_RECORD = 90;
	public static int VISIT_TYPE_MOTHER_REFFER = 95;
	public static int VISIT_TYPE_CHILD_REFFER = 100;
	
	public final static int GEO_IMPORT_FILE_CITYCORPID_INDEX = 0;
	public final static int GEO_IMPORT_FILE_CITYCORPNAME_INDEX = 1;
	public final static int GEO_IMPORT_FILE_REGIONID_INDEX = 2;
	public final static int GEO_IMPORT_FILE_REGIONNAME_INDEX = 3;
	public final static int GEO_IMPORT_FILE_BRANCHID_INDEX = 4;
	public final static int GEO_IMPORT_FILE_BRANCHNAME_INDEX = 5;
	public final static int GEO_IMPORT_FILE_SKID_INDEX = 6;
	public final static int GEO_IMPORT_FILE_SKNAME_INDEX = 7;
	public final static int GEO_IMPORT_FILE_SSID_INDEX = 8;
	public final static int GEO_IMPORT_FILE_SSNAME_INDEX = 9;
	
	
	public final static int DEFAULT_GEO_LOCATION = 1;
	public final static long COMPANY_ACCOUNT_INCOME = 1;
	public final static long COMPANY_ACCOUNT_EXPENSE = 2;
	public final static long COMPANY_ACCOUNT_ASSET = 3;
	public final static long COMPANY_ACCOUNT_LIABILITY = 4;
	
	public final static long COMPANY_AREA_DEFAULT = 1;
	public final static long COMPANY_ITEM_TICKET = 1;
	public final static long COMPANY_ADMIN_ID = 1;
	
	public final static int COMPANY_TRANSACTION_DEBIT = -1;
	public final static int COMPANY_TRANSACTION_CREDIT = 1;
}

