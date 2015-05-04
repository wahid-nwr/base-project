/**
 * 
 */
package com.swiftcorp.portal.common.util;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.dto.FunctionDTO;
import com.swiftcorp.portal.role.dto.RoleDTO;
import com.swiftcorp.portal.role.service.IRoleService;

/**
 * @author asraful.haque
 * 
 */
public class FunctionAuthenticationUtil
{
	// get the log
	private static final Log theLogger = LogFactory.getLog ( FunctionAuthenticationUtil.class );
	// role dao to get the role dto
	private IRoleService roleService;
	
	/**
	 * 
	 * @param roleId
	 * @param functionName
	 * @return
	 */
	public boolean isFunctionAccessibleForRole ( long roleComponentId, String functionName )
	{
		// get the role from the database
		try
		{
			RoleDTO roleDTO = (RoleDTO) roleService.get ( roleComponentId );
			
			// now get the functions against the role
			Set<FunctionDTO> functionDTOSet = roleDTO.getFunctions ();
			
			// now iterate through the functionDTO set
			if ( functionDTOSet != null )
			{
				for ( FunctionDTO functionDTO : functionDTOSet )
				{
					// get the name from the function dto
					String functionNameFromDTo = functionDTO.getFunctionName ();
					
					// now if matches then return true
					if ( functionName.equalsIgnoreCase ( functionNameFromDTo ) )
					{
						return true;
					}
				}
			}
		}
		catch (Exception e)
		{
			theLogger.error ( "Error occured while Fetching the role from Database." );
		}
		// return false
		return false;
	}
	
	public IRoleService getRoleService ( )
	{
		return roleService;
	}
	
	public void setRoleService ( IRoleService roleService )
	{
		this.roleService = roleService;
	}
}
