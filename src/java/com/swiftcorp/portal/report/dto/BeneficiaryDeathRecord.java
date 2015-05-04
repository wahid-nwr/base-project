/**
 * 
 */
package com.swiftcorp.portal.report.dto;

/**
 * @author asraf
 * 
 */
public class BeneficiaryDeathRecord
{
	// column name in the excel file
	private String columnName;
	
	private MonthwiseCountRecord monthwiseCountRecord;

	


	public BeneficiaryDeathRecord ( String columnName )
	{
		this.columnName = columnName;
	}
	
	public String getColumnName ( )
	{
		return columnName;
	}
	
	public void setColumnName ( String columnName )
	{
		this.columnName = columnName;
	}
	
	public MonthwiseCountRecord getMonthwiseCountRecord ( )
	{
		return monthwiseCountRecord;
	}
	
	public void setMonthwiseCountRecord ( MonthwiseCountRecord monthwiseCountRecord )
	{
		this.monthwiseCountRecord = monthwiseCountRecord;
	}
	
}
