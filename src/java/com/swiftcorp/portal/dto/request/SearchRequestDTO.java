/**
 * 
 */
package com.swiftcorp.portal.dto.request;

/**
 * @author asraful.haque
 * 
 */
public class SearchRequestDTO extends RequestDTO
{
	public SearchRequestDTO ( )
	{
		this.dtoType = DTOType.SEARCH_REQ_DTO;
	}
	
	// beneficiary type and userId
	// beneficiary type and userId and itemId
	private int beneficiaryType;
	private String userId;
	private String searchItemId;
	
	public int getBeneficiaryType ( )
	{
		return beneficiaryType;
	}
	
	public void setBeneficiaryType ( int beneficiaryType )
	{
		this.beneficiaryType = beneficiaryType;
	}
	
	public String getUserId ( )
	{
		return userId;
	}
	
	public void setUserId ( String userId )
	{
		this.userId = userId;
	}
	
	public String getSearchItemId ( )
	{
		return searchItemId;
	}
	
	public void setSearchItemId ( String searchItemId )
	{
		this.searchItemId = searchItemId;
	}
	
}
