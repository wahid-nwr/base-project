/**
 * 
 */
package com.swiftcorp.portal.schedule.dto;

import java.util.Calendar;

/**
 * @author asraf
 * 
 */
public class PregMotherVisitItem extends MotherVisitItem
{
	private Calendar firstTTCalendar;
	private Calendar secondTTCalendar;
	private int trimester;
	
	public Calendar getFirstTTCalendar ( )
	{
		return firstTTCalendar;
	}
	
	public void setFirstTTCalendar ( Calendar firstTTCalendar )
	{
		this.firstTTCalendar = firstTTCalendar;
	}
	
	public Calendar getSecondTTCalendar ( )
	{
		return secondTTCalendar;
	}
	
	public void setSecondTTCalendar ( Calendar secondTTCalendar )
	{
		this.secondTTCalendar = secondTTCalendar;
	}
	
	public int getTrimester ( )
	{
		return trimester;
	}
	
	public void setTrimester ( int trimester )
	{
		this.trimester = trimester;
	}
}
