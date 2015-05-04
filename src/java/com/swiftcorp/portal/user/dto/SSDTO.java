/**
 * 
 */
package com.swiftcorp.portal.user.dto;

import java.util.List;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
import com.swiftcorp.portal.geo.dto.GeoDTO;
import com.swiftcorp.portal.household.dto.HouseholdDTO;

/**
 * @author asraful.haque
 *
 */
//public class SSDTO extends UserDTO
public class SSDTO extends PersistentCapableDTO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ssid
	private String ssId;
	
	// sk id
	private String skId;
	
	// branch
	private GeoDTO branch;
	
	private String password;
	
	// household list
	private List<HouseholdDTO> householdList;

	
	public String getSsId ( )
	{
		return ssId;
	}

	public void setSsId ( String ssId )
	{
		this.ssId = ssId;
	}

	public String getSkId ( )
	{
		return skId;
	}

	public void setSkId ( String skId )
	{
		this.skId = skId;
	}

	public GeoDTO getBranch ( )
	{
		return branch;
	}

	public void setBranch ( GeoDTO branch )
	{
		this.branch = branch;
	}

	public List<HouseholdDTO> getHouseholdList ( )
	{
		return householdList;
	}

	public void setHouseholdList ( List<HouseholdDTO> householdList )
	{
		this.householdList = householdList;
	}
	
}
