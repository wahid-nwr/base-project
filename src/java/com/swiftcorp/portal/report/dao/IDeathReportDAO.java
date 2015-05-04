/**
 * 
 */
package com.swiftcorp.portal.report.dao;

import java.util.ArrayList;

import com.swiftcorp.portal.common.exception.SystemException;

/**
 * @author asraful.haque
 * 
 */
public interface IDeathReportDAO
{
	public ArrayList getDeathTimeRecord ( int beneficiaryType, int year, int timeLowerLimit, int timeUpperLimit )
			throws SystemException;
	
	public ArrayList getDeathRecordByPregStage ( int year, String pregStage )
			throws SystemException;
	
	public ArrayList getDeathRecordByDeathPlace ( int beneficiaryType, int year, String deathPlace )
			throws SystemException;
	
	public ArrayList getDeathRecordByDeathCause ( int beneficiaryType, int year, String deathCause )
			throws SystemException;
	
	public ArrayList getDeathRecordByMotherGravid ( int beneficiaryType, int year, int motherGravid )
			throws SystemException;
	
	public ArrayList getDeathRecordForUpperGravid ( int beneficiaryType, int year, int motherGravidUpperLimit )
			throws SystemException;
	
	public ArrayList getDeathRecordByMotherAge ( int beneficiaryType, int year, int motherAgeUpperLimit, int motherAgeLowerLimit )
			throws SystemException;
	
	public ArrayList getDeathRecordByEconomicCondition ( int beneficiaryType, int year, String economicCondition )
			throws SystemException;
}
