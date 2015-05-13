package com.swiftcorp.portal.common.mail.dto;

import java.util.ArrayList;
import java.util.List;

public class MailMessage
{
	private String from;
	private List<String> to = new ArrayList<String> ();
	private List<String> bcc = new ArrayList<String> ();;
	private List<String> cc = new ArrayList<String> ();;
	private String mailBody;
	private String mailSubject;
	
	public String getFrom ( )
	{
		return from;
	}
	
	public void setFrom ( String from )
	{
		this.from = from;
	}
	
	public String getMailBody ( )
	{
		return mailBody;
	}
	
	public void setMailBody ( String mailBody )
	{
		this.mailBody = mailBody;
	}
	
	public String getMailSubject ( )
	{
		return mailSubject;
	}
	
	public void setMailSubject ( String mailSubject )
	{
		this.mailSubject = mailSubject;
	}
	
	public List<String> getTo ( )
	{
		return to;
	}
	
	public void setTo ( List<String> to )
	{
		this.to = to;
	}
	
	public List<String> getBcc ( )
	{
		return bcc;
	}
	
	public void setBcc ( List<String> bcc )
	{
		this.bcc = bcc;
	}
	
	public List<String> getCc ( )
	{
		return cc;
	}
	
	public void setCc ( List<String> cc )
	{
		this.cc = cc;
	}
}
