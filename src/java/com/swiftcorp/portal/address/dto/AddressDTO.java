/*
 * @ (#) AddressDTO.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.address.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

/**
 * @author mosa
 * @since Sep 4, 2008
 */
public class AddressDTO extends PersistentCapableDTO
{
	private String addressLine;
	private String city;
	private String stateOrProvince;
	private String zipOrPostalCode;
	private String country;
	
	public String getAddressLine ( )
	{
		return addressLine;
	}
	
	public void setAddressLine ( String addressLine )
	{
		this.addressLine = addressLine;
	}
	
	public String getCity ( )
	{
		return city;
	}
	
	public void setCity ( String city )
	{
		this.city = city;
	}
	
	public String getCountry ( )
	{
		return country;
	}
	
	public void setCountry ( String country )
	{
		this.country = country;
	}
	
	public String getStateOrProvince ( )
	{
		return stateOrProvince;
	}
	
	public void setStateOrProvince ( String stateOrProvince )
	{
		this.stateOrProvince = stateOrProvince;
	}
	
	public String getZipOrPostalCode ( )
	{
		return zipOrPostalCode;
	}
	
	public void setZipOrPostalCode ( String zipOrPostalCode )
	{
		this.zipOrPostalCode = zipOrPostalCode;
	}
}
