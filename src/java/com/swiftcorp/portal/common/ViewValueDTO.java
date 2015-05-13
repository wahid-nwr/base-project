package com.swiftcorp.portal.common;

import java.io.Serializable;

public class ViewValueDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int value;
	private Long valueLongObj;
	private String valueStr;
	private String view;
	
	public ViewValueDTO ( String view, String valStr )
	{
		this.view = view;
		this.valueStr = valStr;
	}
	
	public ViewValueDTO ( String view, int val )
	{
		this.view = view;
		this.value = val;
	}
	
	public ViewValueDTO ( String view, Long valLO )
	{
		this.view = view;
		this.valueLongObj = valLO;
	}
	
	public int getValue ( )
	{
		return value;
	}
	
	public void setValue ( int value )
	{
		this.value = value;
	}
	
	public String getValueStr ( )
	{
		return valueStr;
	}
	
	public void setValueStr ( String valueStr )
	{
		this.valueStr = valueStr;
	}
	
	public String getView ( )
	{
		return view;
	}
	
	public void setView ( String view )
	{
		this.view = view;
	}
	
	public Long getValueLongObj ( )
	{
		return valueLongObj;
	}
	
	public void setValueLongObj ( Long valueLongObj )
	{
		this.valueLongObj = valueLongObj;
	}
	
}
