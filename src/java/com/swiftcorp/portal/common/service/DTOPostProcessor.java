/**
 * 
 */
package com.swiftcorp.portal.common.service;

import com.swiftcorp.portal.common.dto.GenericDTO;

/**
 * @author asraful.haque
 * 
 */
public abstract class DTOPostProcessor
{
	public abstract GenericDTO process ( GenericDTO genericDTO );
	
}
