/**
 * 
 */
package com.swiftcorp.portal.beneficiary.dto;

import java.util.Calendar;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

/**
 * @author asraful.haque
 * 
 */
public class AbstractVisitRecord extends PersistentCapableDTO implements Comparable<AbstractVisitRecord>
{
	// visit dto
	protected VisitDTO visitDTO;
	
	public VisitDTO getVisitDTO ( )
	{
		return visitDTO;
	}
	
	public void setVisitDTO ( VisitDTO visitDTO )
	{
		this.visitDTO = visitDTO;
	}
	
	@Deprecated
	public int compare ( AbstractVisitRecord abstractVisitRecord1, AbstractVisitRecord abstractVisitRecord2 )
	{
		try
		{
			// get visit date
			Calendar visitDate1 = abstractVisitRecord1.getVisitDTO ().getVisitDate ();
			Calendar visitDate2 = abstractVisitRecord2.getVisitDTO ().getVisitDate ();
			
			return visitDate1.compareTo ( visitDate2 );
		}
		
		catch (Exception ex)
		{
			ex.printStackTrace ();
		}
		
		return 0;
	}
	
	@Override
	public int compareTo ( AbstractVisitRecord abstractVisitRecord )
	{
		try
		{
			// get visit date
			Calendar visitDate1 = this.getVisitDTO ().getVisitDate ();
			Calendar visitDate2 = abstractVisitRecord.getVisitDTO ().getVisitDate ();
			
			return visitDate1.compareTo ( visitDate2 );
		}
		
		catch (Exception ex)
		{
			ex.printStackTrace ();
		}
		
		return 0;
	}
	
}
