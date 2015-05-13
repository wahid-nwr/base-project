/**
 * 
 */
package com.swiftcorp.portal.common.dto;

/**
 * @author asraful.haque
 * 
 */
public class DTOConstants
{
	public static final int USER_TYPE_SYSTEM = 1;
	public static final int USER_TYPE_BUYER = 2;
	public static final int USER_TYPE_SUPPLIER = 3;
	// QUESTION type
	public static final int QUESTION_TYPE = 110;
	
	// questionnaire type
	public static final int QUESTIONNAIRE_TYPE = 120;
	
	// questionnaire used independently
	public static final int QUESTIONNAIRE_SINGLE = 0;
	
	// questionnaire used only with algoritm
	public static final int QUESTIONNAIRE_IN_ALGORITHM_ONLY = 20;
	
	// QUESTIONNAIRE used in both
	public static final int QUESTIONNAIRE_SINGLE_AND_INALG = 10;
	
	public static final String HOUSEHOLD_OTHER_MEMBER = "other";
	public static final String HOUSEHOLD_MOTHER_MEMBER = "ELCOWoman";
	
	// IN XML
	public static final int MOTHER_MEMBERTYPE = 5;
	public static final int CHILD_MEMBERTYPE = 10;
	
	public static final String HOUSEHOLD_MOTHER_VISITTYPE = "mother";
	public static final String HOUSEHOLD_CHILD_VISITTYPE = "child";
	public static final int INACTIVE_ALGORITHM_STATUS = 0;

	public static final String extraBleeding = "extraBleeding";
	public static final String delayedDelivery = "delayedDelivery";
	
	// userid for all web and all mobile users
	public static final String ALL_WEB_USERID = "allWeb";
	public static final String ALL_MOBILE_USERID = "allMobile";
	public static final String ALL_SK_USERID = "allSK";
	
	// dto constants for geo hierarchy
	public static final int GEO_TYPE_CITY_CORPORATION = 5;
	public static final int GEO_TYPE_REGION = 10;
	public static final int GEO_TYPE_BRANCH = 15;	
	
	public static final String HHID_SEPARATOR = "/";
	public static final String ALERT_BENEFICIARYID_SEPARATOR = ";";	
	public static final String ALERT_HHMEMBER_SEPARATOR = "|";

}
