package com.swiftcorp.portal.contact.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class ContactDTO extends PersistentCapableDTO
{
	
	private static final long serialVersionUID = 1L;
	private String contactName = null;
	private String phoneNumber = null;
	private String email = null;
	private String yahooId = null;
	private String skypeId = null;
	private String msnId = null;
	
	public String getContactName ( )
	{
		return contactName;
	}
	
	public void setContactName ( String contactName )
	{
		this.contactName = contactName;
	}
	
	public String getEmail ( )
	{
		return email;
	}
	
	public void setEmail ( String email )
	{
		this.email = email;
	}
	
	public String getMsnId ( )
	{
		return msnId;
	}
	
	public void setMsnId ( String msnId )
	{
		this.msnId = msnId;
	}
	
	public String getPhoneNumber ( )
	{
		return phoneNumber;
	}
	
	public void setPhoneNumber ( String phoneNumber )
	{
		this.phoneNumber = phoneNumber;
	}
	
	public String getSkypeId ( )
	{
		return skypeId;
	}
	
	public void setSkypeId ( String skypeId )
	{
		this.skypeId = skypeId;
	}
	
	public String getYahooId ( )
	{
		return yahooId;
	}
	
	public void setYahooId ( String yahooId )
	{
		this.yahooId = yahooId;
	}
	
}
