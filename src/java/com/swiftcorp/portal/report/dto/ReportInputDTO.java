package com.swiftcorp.portal.report.dto;

import java.io.Serializable;

public class ReportInputDTO implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private int catagory = 0;// CL, CIB,SBS
	private int reportId = 0;// CL1,CL2...SBS1
	private int month = 0;
	private int year = 0;
	
	public int getCatagory ( )
	{
		return catagory;
	}
	
	public void setCatagory ( int catagory )
	{
		this.catagory = catagory;
	}
	
	public int getMonth ( )
	{
		return month;
	}
	
	public void setMonth ( int month )
	{
		this.month = month;
	}
	
	public int getReportId ( )
	{
		return reportId;
	}
	
	public void setReportId ( int reportId )
	{
		this.reportId = reportId;
	}
	
	public int getYear ( )
	{
		return year;
	}
	
	public void setYear ( int year )
	{
		this.year = year;
	}
	
}
