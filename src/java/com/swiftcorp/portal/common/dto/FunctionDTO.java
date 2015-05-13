package com.swiftcorp.portal.common.dto;

import java.util.HashSet;
import java.util.Set;

import com.swiftcorp.portal.module.dto.ModuleDTO;

public class FunctionDTO extends PersistentCapableDTO
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String functionName = null;
	private int functionId = 0;
	private FunctionTypeDTO typeId = new FunctionTypeDTO ();
	private ModuleDTO moduleId = new ModuleDTO();
	public ModuleDTO getModuleId() {
		return moduleId;
	}

	public void setModuleId(ModuleDTO moduleId) {
		this.moduleId = moduleId;
	}

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
