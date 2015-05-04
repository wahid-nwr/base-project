/**
 * 
 */
package com.swiftcorp.portal.report.dto;

/**
 * @author asraful.haque
 * 
 */
public class DeathReportDTOConstants
{
	// beneficiary type
	public static final int MOTHER_BENEFICIARY_TYPE = 5;
	public static final int CHILD_BENEFICIARY_TYPE = 10;
	
	public static final int EIGHT_AM_4PM_LOWER_LIMIT = 480;
	public static final int EIGHT_AM_4PM_UPEER_LIMIT = 960;
	
	public static final int FOUR_PM_12AM_LOWER_LIMIT = 960;
	public static final int FOUR_PM_12AM_UPEER_LIMIT = 1439;
	
	public static final int TWELVE_AM_8AM_LOWER_LIMIT = 0;
	public static final int TWELVE_AM_8AM_UPEER_LIMIT = 480;
	
	public static final float CHILD_AGE_0_TO_24HR_LOWER_LIMIT = 0;
	public static final float CHILD_AGE_0_TO_24HR_UPEER_LIMIT = 1;
	
	public static final int JAN = 1;
	public static final int FEB = 2;
	public static final int MAR = 3;
	public static final int APR = 4;
	public static final int MAY = 5;
	public static final int JUN = 6;
	public static final int JUL = 7;
	public static final int AUG = 8;
	public static final int SEP = 9;
	public static final int OCT = 10;
	public static final int NOV = 11;
	public static final int DEC = 12;
	
	// CAUSE OF DEATH FOR CHILD
	public static String BIRTH_ASPHYXIA = "Birth Asphyxia";
	public static String NEONATAL_SEPSIS = "Neonatal Sepsis";
	public static String SEVER_PNEUMONIA = "Sever Pneumonia";
	public static String LBW_COMPLICATION = "LBW related complications";
	public static String PRE_MATURITY_COMPLICATION = "Pre maturity related complications";
	// public static String JAUNDICE = "Jaundice";
	// public static String OTHERS = "Others ";
	
	// CAUSE OF DEATHF FOR MOTHER
	public static String APH = "Ante partum Hemorrhage (APH)";
	public static String PPH = "Post partum Hemorrhage (PPH)";
	public static String RETAINED_PLACANT = "Retained Placenta";
	public static String ANAEMIA = "Anaemia";
	public static String PROLONGED_LABOUR = "Prolonged Labour";
	public static String PRE_ECLAMPSIA = "Pre Eclamptic Toximia/ Pre Eclampsia";
	public static String ECLAMPSIA = "Eclampsia";
	public static String SEPSIS = "Puerperal Sepsis";
	public static String ABORTION = "Abortion";
	public static String JAUNDICE = "Jaundice";
	public static String OTHERS = "Others ";
	
	// place
	public static String HOSPITAL_BRAC_DELIVERY_CENTER = "brac";
	public static String HOSPITAL_UPHCP = "uphcp";
	public static String HOSPITAL_GOVT_FACILITY = "govt";
	public static String HOSPITAL_PRIVATE_FACILITY = "private";
	public static String ON_WAY_TO_FACILITY = "fromHome";
	public static String FACILITY_TO_FACILITY = "fromHospital";
	
	// stage of mother
	public static String preDelivery = "preDelivery";
	public static String postDelivery = "postDelivery";
	public static String duringDelivery = "miscarriage";
	
	// economic condition
	public static String poor = "poor";
	public static String veryPoor = "very poor";
	public static String notPoor = "not poor";
	
}
