package com.swiftcorp.portal.alert;

public class AlertConstants
{
	// alert type
	public static final int ALERT_TYPE_EDD = 1;
	public static final int ALERT_TYPE_TT_FIRST = 2;
	public static final int ALERT_TYPE_TT_SECOND = 3;
	public static final int ALERT_TYPE_MISSING = 4;
	
	// alert header and body
	public static final String ALERT_HEADER_EDD = "EDD alert";
	public static final String ALERT_BODY_EDD = "EDD is after 7 days";
	public static final String ALERT_HEADER_TT1 = "TT 1st alert";
	public static final String ALERT_BODY_TT1 = "First tt dose date over";
	public static final String ALERT_HEADER_TT2 = "TT 2nd alert";
	public static final String ALERT_BODY_TT2 = "TT 2nd alert";
	public static final String ALERT_BODY_MISSING = "Visit missed";
	public static final String ALERT_HEADER_MISSING = "Your scheduled visit has been missed";
	
	public static final int ALERT_FIRST_TT_START = 150;
	public static final int ALERT_FIRST_TT_END = 240;
	public static final int ALERT_STATE_NOT_VISITED = 0;
	public static final String ALERT_START_TIME = "00:00:00";
	public static final String ALERT_END_TIME = "23:59:59";
	public static final int ALERT_EDD_DAY_AFTER = 7;
	
	// Alert for risk FOR MOTHER
	public static final int ALERT_TYPE_RISK_MOTHER_EMERGENCY = 10;
	public static final int ALERT_TYPE_RISK_MOTHER_SEVERE = 20;
	public static final int ALERT_TYPE_RISK_MOTHER_MODERATE = 30;
	public static final int ALERT_TYPE_RISK_MOTHER_MILD = 40;
	
	// Alert type for risk for child
	public static final int ALERT_TYPE_RISK_CHILD_EMERGENCY = 50;
	public static final int ALERT_TYPE_RISK_CHILD_SEVERE = 60;
	public static final int ALERT_TYPE_RISK_CHILD_MODERATE = 70;
	public static final int ALERT_TYPE_RISK_CHILD_MILD = 80;
	
	// Alert for risk FOR MOTHER
	public static final String ALERT_HEADER_RISK_MOTHER_EMERGENCY = "Risk Emergency";
	public static final String ALERT_HEADER_RISK_MOTHER_SEVERE = "Severe Risk";
	public static final String ALERT_HEADER_RISK_MOTHER_MODERATE = "Moderate Risk";
	public static final String ALERT_HEADER_RISK_MOTHER_MILD = "Mild Risk";
	
	// Alert HEADER for risk for child
	public static final String ALERT_HEADER_RISK_CHILD_EMERGENCY = "Risk Emergency";
	public static final String ALERT_HEADER_RISK_CHILD_SEVERE = "Severe Risk";
	public static final String ALERT_HEADER_RISK_CHILD_MODERATE = "Moderate Risk";
	public static final String ALERT_HEADER_RISK_CHILD_MILD = "Mild Risk";
	
	// Alert for risk FOR MOTHER
	public static final String ALERT_BODY_RISK_MOTHER_EMERGENCY = "Take the patient to Hospital";
	public static final String ALERT_BODY_RISK_MOTHER_SEVERE =  "Take the patient to Hospital";
	public static final String ALERT_BODY_RISK_MOTHER_MODERATE = "Take additional Care";
	public static final String ALERT_BODY_RISK_MOTHER_MILD = "Take additional Care";
	
	// Alert BODY for risk for child
	public static final String ALERT_BODY_RISK_CHILD_EMERGENCY =  "Take the patient to Hospital";
	public static final String ALERT_BODY_RISK_CHILD_SEVERE =  "Take the patient to Hospital";
	public static final String ALERT_BODY_RISK_CHILD_MODERATE = "Take additional Care";
	public static final String ALERT_BODY_RISK_CHILD_MILD = "Take additional Care";
	
	public static final int ALERT_TYPE_HH_MEMBER = 110;
	//public static final int ALERT_TYPE_HH_CHILD = 120;
	
	// Alert for risk FOR MOTHER
	public static final String ALERT_HEADER_HH_MOTHER = "Mother ID";
	public static final String ALERT_HEADER_HH_CHILD = "Child ID";	
	
	// Alert HEADER for risk for child
	public static final String ALERT_BODY_HH_MOTHER = "Mother ID";
	public static final String ALERT_BODY_HH_CHILD = "Child ID";	
		
	// alert generated by system
	public static final String DEFAULT_ALERT_BY = "System";
}