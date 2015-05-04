package com.swiftcorp.portal.alert.dto;

import java.util.Calendar;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
import com.swiftcorp.portal.user.dto.UserDTO;

public class AlertDTO extends PersistentCapableDTO
{
	// reciever's id
	private UserDTO reciever;
	// beneficiary id
	private String beneficiaryId;
	// message body
	private String body;
	// message header
	private String header;
	// sent from
	private String sendBy;
	// message name
	private String name;
	// alert id
	private String alertId;
	
	private int alertType;
	
	private Calendar alertDate;
	
	public Calendar getAlertDate ( )
	{
		return alertDate;
	}
	
	public void setAlertDate ( Calendar alertDate )
	{
		this.alertDate = alertDate;
	}
	
	public UserDTO getReciever ( )
	{
		return reciever;
	}
	
	public void setReciever ( UserDTO reciever )
	{
		this.reciever = reciever;
	}
	
	public String getBeneficiaryId ( )
	{
		return beneficiaryId;
	}
	
	public void setBeneficiaryId ( String beneficiaryId )
	{
		this.beneficiaryId = beneficiaryId;
	}
	
	public String getBody ( )
	{
		return body;
	}
	
	public void setBody ( String body )
	{
		this.body = body;
	}
	
	public String getHeader ( )
	{
		return header;
	}
	
	public void setHeader ( String header )
	{
		this.header = header;
	}
	
	public String getSendBy ( )
	{
		return sendBy;
	}
	
	public void setSendBy ( String sendBy )
	{
		this.sendBy = sendBy;
	}
	
	public String getName ( )
	{
		return name;
	}
	
	public void setName ( String name )
	{
		this.name = name;
	}
	
	public String getAlertId ( )
	{
		return alertId;
	}
	
	public void setAlertId ( String alertId )
	{
		this.alertId = alertId;
	}
	
	public int getAlertType ( )
	{
		return alertType;
	}
	
	public void setAlertType ( int alertType )
	{
		this.alertType = alertType;
	}
	
}
