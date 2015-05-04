package com.swiftcorp.portal.common.search;

import java.util.Calendar;

public class SearchVisitDTO
{
	private String bloodPressure;
	private String edima;
	private String anaemia;
	private String complications;
	// visit specific question, visit type for anc pnc
	private String visitType;
	private Calendar visitDate;
	
	public void setBloodPressure ( String bloodPressure )
	{
		this.bloodPressure = bloodPressure;
	}
	
	public String getBloodPressure ( )
	{
		return bloodPressure;
	}
	
	public void setEdima ( String edima )
	{
		this.edima = edima;
	}
	
	public String getEdima ( )
	{
		return edima;
	}
	
	public void setAnaemia ( String anaemia )
	{
		this.anaemia = anaemia;
	}
	
	public String getAnaemia ( )
	{
		return anaemia;
	}
	
	public void setComplications ( String complications )
	{
		this.complications = complications;
	}
	
	public String getComplications ( )
	{
		return complications;
	}
	
	public void setVisitType ( String visitType )
	{
		this.visitType = visitType;
	}
	
	public String getVisitType ( )
	{
		return visitType;
	}
	
	public void setVisitDate ( Calendar visitDate )
	{
		this.visitDate = visitDate;
	}
	
	public Calendar getVisitDate ( )
	{
		return visitDate;
	}
}
