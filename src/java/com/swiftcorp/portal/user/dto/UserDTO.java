/*
 * @ (#) UserDTO.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.user.dto;

import com.swiftcorp.portal.common.dto.DTOConstants;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
import com.swiftcorp.portal.common.util.StringMaker;
import com.swiftcorp.portal.geo.dto.GeoDTO;
import com.swiftcorp.portal.role.dto.RoleDTO;

/**
 * This DTO represents a User object. CMSUser object
 * is created on an Organization / ClientGroup Level. Clients of profileserver will 
 * use this object to determine the access level on a clientprofile.
 * AccessLevel will be fixed based on the accesslevel attribute of the dto.
 *
 */

/**
 * @author swift
 * @since mar 3, 2011
 */
public class UserDTO extends UserInfo
{
	public UserDTO ( )
	{
		super ( DTOConstants.USER_TYPE_SYSTEM );
	}
	private static final long serialVersionUID = 1L;
	
	private String lastName;
	private String firstName;
	private String password;
	private String confirmPassword;
	private Long groupId;
	private String email;
	private RoleDTO role = new RoleDTO ();
	
	// area type and area
	private int areaType;
	private GeoDTO userArea = new GeoDTO ();
	
	private long userAreaId;
	
	public void setUserAreaId ( long userAreaId )
	{
		this.userAreaId = userAreaId;
	}

	public long getUserAreaId ( )
	{
		return userAreaId;
	}

	public Long getGroupId ( )
	{
		return groupId;
	}
	
	public void setGroupId ( Long groupId )
	{
		this.groupId = groupId;
	}
	
	public String getFirstName ( )
	{
		return firstName;
	}
	
	public void setFirstName ( String firstName )
	{
		this.firstName = firstName;
	}
	
	public String getLastName ( )
	{
		return lastName;
	}
	
	public void setLastName ( String lastName )
	{
		this.lastName = lastName;
	}
	
	public String getPassword ( )
	{
		return password;
	}
	
	public void setPassword ( String password )
	{
		this.password = password;
	}
	
	public String getConfirmPassword ( )
	{
		return confirmPassword;
	}
	
	public void setConfirmPassword ( String confirmPassword )
	{
		this.confirmPassword = confirmPassword;
	}
	
	public String toString ( )
	{
		StringMaker stringMaker = StringMaker.getInstance ();
		
		stringMaker.addToBuffer ( super.toString () );
		stringMaker.addToBuffer ( " ,lastName = " );
		stringMaker.addToBuffer ( lastName );
		
		stringMaker.addToBuffer ( ", firstName = " );
		stringMaker.addToBuffer ( firstName );
		stringMaker.addToBuffer ( " ,password = " );
		stringMaker.addToBuffer ( password == null ? "null" : "****" );
		return stringMaker.getBufferValue ();
	}
	
	public String getEmail ( )
	{
		return email;
	}
	
	public void setEmail ( String email )
	{
		this.email = email;
	}
	
	public RoleDTO getRole ( )
	{
		return role;
	}
	
	public void setRole ( RoleDTO role )
	{
		this.role = role;
	}
	
	@Override
	public boolean equals ( Object object )
	{
		UserDTO userDTO = (UserDTO) object;
		if ( userDTO.getComponentId () == this.componentId )
		{
			return true;
		}
		return false;
	}

	public int getAreaType ( )
	{
		return areaType;
	}

	public void setAreaType ( int areaType )
	{
		this.areaType = areaType;
	}

	public GeoDTO getUserArea ( )
	{
		return userArea;
	}

	public void setUserArea ( GeoDTO userArea )
	{
		this.userArea = userArea;
	}
}
