/**
 * 
 */
package com.swiftcorp.portal.common.util;

/**
 * @author habiba
 * 
 */
public class IDGeneratorUtil
{
	public static long getQuestionId ( )
	{
		// get current time from system
		long currentTimeMillis = System.currentTimeMillis ();
		
		// return current time millis
		return currentTimeMillis;
	}
	
	public static String generateMotherBeneficiaryId ( long componentId )
	{
		// increment the id and return
		++componentId;
		String beneficiaryId = "" + componentId;
		
		return beneficiaryId;
	}
	
	public static String generateChildBeneficiaryId ( long componentId )
	{
		// increment the id and return
		++componentId;
		String beneficiaryId = "" + componentId;
		
		return beneficiaryId;
	}
	
	public static String generateOtherHHMemberId ( long componentId )
	{
		// increment the id and return
		++componentId;
		String memberId = "" + componentId;
		
		return memberId;
	}
	
}
