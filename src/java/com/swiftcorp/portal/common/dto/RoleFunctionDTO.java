/**
 * 
 */
package com.swiftcorp.portal.common.dto;

import java.util.Set;

import com.swiftcorp.portal.role.dto.RoleDTO;

/**
 * @author click 1
 * 
 */
public class RoleFunctionDTO extends PersistentCapableDTO
{
	private static final long serialVersionUID = 1L;
	
	// role dto
	private RoleDTO role;
	
	// function dto
	private Set<FunctionDTO> functions;
	
	public Set<FunctionDTO> getFunctions ( )
	{
		return functions;
	}
	
	public void setFunctions ( Set<FunctionDTO> functions )
	{
		this.functions = functions;
	}
	
	public RoleDTO getRole ( )
	{
		return role;
	}
	
	public void setRole ( RoleDTO role )
	{
		this.role = role;
	}
	
}
