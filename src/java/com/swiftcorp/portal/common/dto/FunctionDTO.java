package com.swiftcorp.portal.common.dto;

import java.util.HashSet;
import java.util.Set;

public class FunctionDTO extends PersistentCapableDTO
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String functionName = null;
	private int functionId = 0;
	private FunctionTypeDTO typeId = new FunctionTypeDTO ();
	private String displayName = null;
	
	// rolse
	private Set roles = new HashSet ( 0 );
	
	public FunctionTypeDTO getTypeId ( )
	{
		return typeId;
	}
	
	public void setTypeId ( FunctionTypeDTO typeId )
	{
		this.typeId = typeId;
	}
	
	public int getFunctionId ( )
	{
		return functionId;
	}
	
	public void setFunctionId ( int functionId )
	{
		this.functionId = functionId;
	}
	
	public String getDisplayName ( )
	{
		return displayName;
	}
	
	public void setDisplayName ( String displayName )
	{
		this.displayName = displayName;
	}
	
	public String getFunctionName ( )
	{
		return functionName;
	}
	
	public void setFunctionName ( String functionName )
	{
		this.functionName = functionName;
	}
	
	public Set getRoles ( )
	{
		return roles;
	}
	
	public void setRoles ( Set roles )
	{
		this.roles = roles;
	}
	
}
