/**
 * 
 */
package com.swiftcorp.portal.schedule.dto;

/**
 * @author asraful.haque
 * 
 */
public class SchedulingConstants
{
	// mother stage
	public static final String PRE_DELIVERY = "predelivery";
	public static final String POST_DELIVERY = "postdelivery";
	
	public static final String SCHEDULE_BY_SYSTEM = "System";
	
	// visit item type
	public static final int PREGNANT_MOTHER = 0;
	public static final int POSTDELIVERY_MOTHER = 5;
	public static final int NEONATAL_CHILD = 10;
	public static final int HH_CHILD = 15;
	public static final int HOUSEHOLD = 20;
	
	// schedule state
	public static final int NOT_VISITED = 0;
	public static final int VISITED = 1;
	
	// predelivery day interval
	public static final int PREDELIVERY_VISIT_INTERVAL = 30;
	public static final int PREDELIVERY_NUMBER_OF_VISIT = 9;
	
	// Post delivery day interval
	public static final int POST_DELIVERY_2ND_VISIT_DAY_INTERVAL = 3;
	public static final int POST_DELIVERY_3RD_VISIT_DAY_INTERVAL = 7;
	public static final int POST_DELIVERY_4TH_VISIT_DAY_INTERVAL = 28;
	public static final int POST_DELIVERY_5TH_VISIT_DAY_INTERVAL = 42;
	
	// if visit is missed for pregnant woman
	public static final int PRE_DELIVERY_MISS_VISIT_DAY_INTERVAL = 15;
	
	// post delivery miss visit
	public static final int POST_DELIVERY_MISS_VISIT_LOWERLIMIT = 45;
	public static final int POST_DELIVERY_MISS_VISIT_UPPERLIMIT = 45;
	
	// default visit hour min sec
	public static final int DEFAULT_VISIT_HOUR = 10;
	public static final int DEFAULT_VISIT_MIN = 0;
	public static final int DEFAULT_VISIT_SECOND = 0;
	
	// household scheduling constant
	public static final int HOUSEHOLD_VISIT_INTERVAL = 90;
}
