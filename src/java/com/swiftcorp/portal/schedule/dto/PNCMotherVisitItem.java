/**
 * 
 */
package com.swiftcorp.portal.schedule.dto;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author asraf
 * 
 */
public class PNCMotherVisitItem extends MotherVisitItem
{
	// delivery date
	private Calendar deliveryDate;
	
	// now mother's associative child
	private ArrayList<NeonatalVisitItem> neonatalVisitItemList;
	
	public Calendar getDeliveryDate ( )
	{
		return deliveryDate;
	}
	
	public void setDeliveryDate ( Calendar deliveryDate )
	{
		this.deliveryDate = deliveryDate;
	}
	
	public ArrayList<NeonatalVisitItem> getNeonatalVisitItemList ( )
	{
		return neonatalVisitItemList;
	}
	
	public void setNeonatalVisitItemList ( ArrayList<NeonatalVisitItem> neonatalVisitItemList )
	{
		this.neonatalVisitItemList = neonatalVisitItemList;
	}
	
}
