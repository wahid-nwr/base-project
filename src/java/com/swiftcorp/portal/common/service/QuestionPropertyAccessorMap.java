/**
 * 
 */
package com.swiftcorp.portal.common.service;

import java.util.Map;

/**
 * @author asraful.haque
 * 
 */
public class QuestionPropertyAccessorMap
{
	// qid property accessor map
	private Map<String, PropertyAccessor> qidPropertyAccessorMap;
	
	// private Map<String, List<PropertyAccessor>>
	// qidMultiplePropertyAccessorMap;
	
	public Map<String, PropertyAccessor> getQidPropertyAccessorMap ( )
	{
		return qidPropertyAccessorMap;
	}
	
	public void setQidPropertyAccessorMap ( Map<String, PropertyAccessor> qidPropertyAccessorMap )
	{
		this.qidPropertyAccessorMap = qidPropertyAccessorMap;
	}
	
}
