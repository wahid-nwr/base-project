/*
 * @ (#) GroupDTO.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.group.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
import com.swiftcorp.portal.common.util.StringMaker;

/**
 * The Value object of group. This object will be passed around through all the tier.
 * The all the operations on a group will be performed by passing around this dto.
 * This dto actually represents the real entity for group. 
 * The properties of this dto must be serializable.
 * 
 * The Nested DTOs (contact,domain,ldapinfo,address) are treated as  aggregated dtos of this DTO.
 * Deletion of the parent dtos will delete the entities for this dto. 
 * 
 * parentGroupId is used to mantain the paren child relation between groups. 
 */

/**
 * @author swift
 * @since mar 3, 2011
 */

public class GroupDTO extends PersistentCapableDTO
{
	private String name;
	private Long parentGroupId;
	
	public Long getParentGroupId ( )
	{
		return parentGroupId;
	}
	
	public void setParentGroupId ( Long parentGroupId )
	{
		this.parentGroupId = parentGroupId;
	}
	
	public String toString ( )
	{
		StringMaker stringMaker = StringMaker.getInstance ();
		stringMaker.addToBuffer ( super.toString () );
		stringMaker.addToBuffer ( ", parentGroupId = " ).addToBuffer ( parentGroupId );
		return stringMaker.getBufferValue ();
	}
	
}
