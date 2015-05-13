package com.swiftcorp.portal.common.feature;

import javax.servlet.http.HttpServletRequest;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.role.dto.RoleDTO;
import com.swiftcorp.portal.user.dto.UserDTO;

public class FeatureAccessUtils
{
	
	public static boolean hasAccess ( int featureId, HttpServletRequest request )
	{
		boolean hasAccess = false;
		UserDTO user = (UserDTO) request.getSession ().getAttribute ( SESSION_KEYS.USER );
		if ( user == null )
		{
			return false;
		}
		// RoleDTO role = user.getRole();
		if ( featureId == FeatureConstants.USER )
		{
			hasAccess = true;
		}
		return hasAccess;
	}
	
	public static int authorizationLevel ( HttpServletRequest request )
	{
		int authCode = GlobalConstants.ACCESS_VIEW_ONLY;
		String featureId = (String) request.getAttribute ( "featureId" );
		UserDTO user = (UserDTO) request.getSession ().getAttribute ( SESSION_KEYS.USER );
		if ( user == null )
		{
			return authCode;
		}
		RoleDTO role = user.getRole ();
		
		if ( "101".equalsIgnoreCase ( featureId ) )
		{
			authCode = GlobalConstants.ACCESS_ADD_MODIFY_DELETE;
		}
		return authCode;
	}
	
}
