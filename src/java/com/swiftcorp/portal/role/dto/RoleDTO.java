package com.swiftcorp.portal.role.dto;

import java.util.HashSet;
import java.util.Set;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class RoleDTO extends PersistentCapableDTO
{
	private static final long serialVersionUID = 1L;
	
	// function dto
	private Set functions = new HashSet ( 0 );
	private int accessLevel = 0;
	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Set getFunctions ( )
	{
		return functions;
	}
	
	public void setFunctions ( Set functions )
	{
		this.functions = functions;
	}
	
}
