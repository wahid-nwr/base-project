/**
 * 
 */
package com.swiftcorp.portal.schedule.dto;

import java.util.Calendar;

/**
 * @author asraful.haque
 * 
 */
public class MotherSchedulerInfo extends ScheduleMakerInfo
{
	// stage of the patient
	private String patientStage;
	
	// pregnancy identification date
	private Calendar pregIdentificationDate;
	
	// delivery date
	private Calendar deliveryDate;
	
	public String getPatientStage ( )
	{
		return patientStage;
	}
	
	public void setPatientStage ( String patientStage )
	{
		this.patientStage = patientStage;
	}
	
	public Calendar getPregIdentificationDate ( )
	{
		return pregIdentificationDate;
	}
	
	public void setPregIdentificationDate ( Calendar pregIdentificationDate )
	{
		this.pregIdentificationDate = pregIdentificationDate;
	}
	
	public Calendar getDeliveryDate ( )
	{
		return deliveryDate;
	}
	
	public void setDeliveryDate ( Calendar deliveryDate )
	{
		this.deliveryDate = deliveryDate;
	}
}
