package com.swiftcorp.portal.common.service;

import com.swiftcorp.portal.common.parser.MhealthDTODataProcessor;

public class PropertyAccessor
{
	// name of the property
	private String propertyName;
	
	// converter to convert from string to other property
	private MhealthDTODataProcessor mhealthDTODataProcessor;
	
	public String getPropertyName ( )
	{
		return propertyName;
	}
	
	public void setPropertyName ( String propertyName )
	{
		this.propertyName = propertyName;
	}
	
	public MhealthDTODataProcessor getMhealthDTODataProcessor ( )
	{
		return mhealthDTODataProcessor;
	}
	
	public void setMhealthDTODataProcessor ( MhealthDTODataProcessor mhealthDTODataProcessor )
	{
		this.mhealthDTODataProcessor = mhealthDTODataProcessor;
	}
	
}
