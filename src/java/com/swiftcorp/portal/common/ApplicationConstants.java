package com.swiftcorp.portal.common;

import java.util.ArrayList;
import java.util.List;

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
	//public static IReportService REPORT_SERVICE = null;
	
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
	public static int ROLE_FUNCTION_INFO = 44;
	public static int ROLE_FUNCTION_AJAN = 45;
	
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
	
}

