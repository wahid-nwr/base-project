package com.swiftcorp.portal.beneficiary.dto;

import java.util.Calendar;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
import com.swiftcorp.portal.user.dto.UserDTO;

public class VisitDTO extends PersistentCapableDTO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// visit by skId
	private UserDTO userDTO;
	// visit date
	private Calendar visitDate;
	// start time of interview
	private Calendar startTimeStamp;
	// end time of interview
	private Calendar endTimeStamp;
	// pic of this interview
	private String visitPic;
	
	private int visitType;
	
	private String userId;
	
	private String differanceBetweenTime;
	
	private Calendar dataArrivingTime;
	
	private float visitDuration;
	
	private Calendar visitPicTimeStamp;
	
	private String diffBetweenvisitPicTime;
	
	private String diffBetweenvisitPicAndQEndTime;	

	public String getDiffBetweenvisitPicAndQEndTime() {
		return diffBetweenvisitPicAndQEndTime;
	}

	public void setDiffBetweenvisitPicAndQEndTime(
			String diffBetweenvisitPicAndQEndTime) {
		this.diffBetweenvisitPicAndQEndTime = diffBetweenvisitPicAndQEndTime;
	}

	public Calendar getVisitPicTimeStamp()
	{
		return visitPicTimeStamp;
	}
	
	public void setVisitPicTimeStamp ( Calendar visitPicTimeStamp )
	{
		this.visitPicTimeStamp = visitPicTimeStamp;
	}
	
	public String getDiffBetweenvisitPicTime ( )
	{
		return diffBetweenvisitPicTime;
	}
	
	public void setDiffBetweenvisitPicTime ( String diffBetweenvisitPicTime )
	{
		this.diffBetweenvisitPicTime = diffBetweenvisitPicTime;
	}
	
	public String getDifferanceBetweenTime ( )
	{
		return differanceBetweenTime;
	}
	
	public void setDifferanceBetweenTime ( String differanceBetweenTime )
	{
		this.differanceBetweenTime = differanceBetweenTime;
	}
	
	public Calendar getDataArrivingTime ( )
	{
		return dataArrivingTime;
	}
	
	public void setDataArrivingTime ( Calendar dataArrivingTime )
	{
		this.dataArrivingTime = dataArrivingTime;
	}
	
	public float getVisitDuration ( )
	{
		return visitDuration;
	}
	
	public void setVisitDuration ( float visitDuration )
	{
		this.visitDuration = visitDuration;
	}
	
	public String getUserId ( )
	{
		return userId;
	}
	
	public void setUserId ( String userId )
	{
		this.userId = userId;
	}
	
	public int getVisitType ( )
	{
		return visitType;
	}
	
	public void setVisitType ( int visitType )
	{
		this.visitType = visitType;
	}
	
	public Calendar getVisitDate ( )
	{
		return visitDate;
	}
	
	public void setVisitDate ( Calendar visitDate )
	{
		this.visitDate = visitDate;
	}
	
	public Calendar getStartTimeStamp ( )
	{
		return startTimeStamp;
	}
	
	public void setStartTimeStamp ( Calendar startTimeStamp )
	{
		this.startTimeStamp = startTimeStamp;
	}
	
	public Calendar getEndTimeStamp ( )
	{
		return endTimeStamp;
	}
	
	public void setEndTimeStamp ( Calendar endTimeStamp )
	{
		this.endTimeStamp = endTimeStamp;
	}
	
	public String getVisitPic ( )
	{
		return visitPic;
	}
	
	public void setVisitPic ( String visitPic )
	{
		this.visitPic = visitPic;
	}
	
	public UserDTO getUserDTO ( )
	{
		return userDTO;
	}
	
	public void setUserDTO ( UserDTO userDTO )
	{
		this.userDTO = userDTO;
	}
	
}
