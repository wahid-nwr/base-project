package com.swiftcorp.portal.question.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class QuestionnaireStatusDTO extends PersistentCapableDTO
{
	private static final long serialVersionUID = 1L;
	
	private String statusName;
	private int statusId;
	
	public String getStatusName ( )
	{
		return statusName;
	}
	
	public void setStatusName ( String statusName )
	{
		this.statusName = statusName;
	}
	
	public int getStatusId ( )
	{
		return statusId;
	}
	
	public void setStatusId ( int statusId )
	{
		this.statusId = statusId;
	}
}
