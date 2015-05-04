package com.swiftcorp.portal.geo.dto;

import java.util.List;

public class GeoImportHHRegDTO
{
	private String cityCorpId;
	private String cityCorpName;
	private String regionId;
	private String regionName;
	private String branchId;
	private String branchName;
	List<SkDataDTO> skDataDTOList;
	
	public String getCityCorpId ( )
	{
		return cityCorpId;
	}
	
	public void setCityCorpId ( String cityCorpId )
	{
		this.cityCorpId = cityCorpId;
	}
	
	public String getCityCorpName ( )
	{
		return cityCorpName;
	}
	
	public void setCityCorpName ( String cityCorpName )
	{
		this.cityCorpName = cityCorpName;
	}
	
	public String getRegionId ( )
	{
		return regionId;
	}
	
	public void setRegionId ( String regionId )
	{
		this.regionId = regionId;
	}
	
	public String getRegionName ( )
	{
		return regionName;
	}
	
	public void setRegionName ( String regionName )
	{
		this.regionName = regionName;
	}
	
	public String getBranchId ( )
	{
		return branchId;
	}
	
	public void setBranchId ( String branchId )
	{
		this.branchId = branchId;
	}
	
	public String getBranchName ( )
	{
		return branchName;
	}
	
	public void setBranchName ( String branchName )
	{
		this.branchName = branchName;
	}
	
	public List<SkDataDTO> getSkDataDTOList ( )
	{
		return skDataDTOList;
	}
	
	public void setSkDataDTOList ( List<SkDataDTO> skDataDTOList )
	{
		this.skDataDTOList = skDataDTOList;
	}
	@Override
	public String toString()
	{
		String str = "";
		str += this.branchId+","+this.branchName+","+this.cityCorpId+","+this.cityCorpName+","+this.regionId+","+this.regionName;
		return str;
	}
	
}
