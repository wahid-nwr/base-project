package com.swiftcorp.portal.geo.dto;

import java.util.List;

import com.swiftcorp.portal.user.dto.SSDTO;

public class SkDataDTO
{
	private String skId;
	private String skName;
	List<SSDTO> ssDTOList;
	public String getSkId ( )
	{
		return skId;
	}
	public void setSkId ( String skId )
	{
		this.skId = skId;
	}
	public String getSkName ( )
	{
		return skName;
	}
	public void setSkName ( String skName )
	{
		this.skName = skName;
	}
	public List<SSDTO> getSsDTOList ( )
	{
		return ssDTOList;
	}
	public void setSsDTOList ( List<SSDTO> ssDTOList )
	{
		this.ssDTOList = ssDTOList;
	}
	
}
