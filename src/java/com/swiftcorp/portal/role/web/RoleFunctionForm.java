/**
 * 
 */
package com.swiftcorp.portal.role.web;

import org.apache.struts.action.ActionForm;

/**
 * @author asraful.haque
 * 
 */
@Deprecated
public class RoleFunctionForm extends ActionForm
{
	private String[] selectedItems =
	{};
	/*
	 * This variable will also need getter and setter methods in order for this
	 * example to work. In the end you could actually do it a different way, to
	 * be explained later.; for now, let's pretend that there are getters and
	 * setters in place.
	 */
	private String[] items =
	{
			"UPS", "FedEx", "Airborne"
	};
	
	public String[] getSelectedItems ( )
	{
		return this.selectedItems;
	}
	
	public void setSelectedItems ( String[] selectedItems )
	{
		this.selectedItems = selectedItems;
	}
	
	public String[] getItems ( )
	{
		return items;
	}
	
	public void setItems ( String[] items )
	{
		this.items = items;
	}
	
}
