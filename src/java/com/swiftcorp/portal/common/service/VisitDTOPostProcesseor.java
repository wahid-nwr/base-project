/**
 * 
 */
package com.swiftcorp.portal.common.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.util.CalendarUtils;
import com.swiftcorp.portal.user.dto.UserDTO;
import com.swiftcorp.portal.user.service.UserServiceImpl;

/**
 * @author asraful.haque
 * 
 */
public class VisitDTOPostProcesseor extends DTOPostProcessor
{
	UserServiceImpl userService;
	private static final Log logger = LogFactory.getLog ( VisitDTOPostProcesseor.class );
	
	public void setUserService ( UserServiceImpl userService )
	{
		this.userService = userService;
	}
	
	@Override
	public GenericDTO process ( GenericDTO genericDTO )
	{
		/*
		VisitDTO visitDTO = (VisitDTO) genericDTO;
		String userId = visitDTO.getUserId ();
		UserDTO userDTO = null;
		try
		{
			userDTO = (UserDTO) userService.get ( userId );
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		visitDTO.setUserDTO ( userDTO );
		long endTimeStamp = visitDTO.getEndTimeStamp ().getTimeInMillis ();
		long startTimeStamp = visitDTO.getStartTimeStamp ().getTimeInMillis ();
		
		long durationInMillis = (endTimeStamp - startTimeStamp);
		float visitDurationInMin = (float) durationInMillis / (60 * 1000);
		visitDTO.setVisitDate ( visitDTO.getStartTimeStamp () );
		visitDTO.setDataArrivingTime ( CalendarUtils.getCurrentCalendar () );
		logger.info ( "startTimeStamp::" + startTimeStamp + " endTimeStamp::" + endTimeStamp );
		
		visitDTO.setVisitDuration ( visitDurationInMin );
		long differanceBenweenTimes = visitDTO.getDataArrivingTime ().getTimeInMillis () - visitDTO.getEndTimeStamp ().getTimeInMillis ();
		
		long hours = 0;
		long minutes = 0;
		long days = 0;
		long dateFactor = (24 * 60 * 60 * 1000);
		long hourFactor = (60 * 60 * 1000);
		long minFactor = (60 * 1000);
		days = differanceBenweenTimes / dateFactor;
		long hoursInMillis = differanceBenweenTimes % dateFactor;
		hours = hoursInMillis / hourFactor;
		long minuteInMilis = differanceBenweenTimes % hourFactor;
		minutes = minuteInMilis / minFactor;
		
		String differanceBetweenTime = days + ":" + hours + ":" + minutes;
		long visitPicTimeStamp = 0;
		long diffBetweenvisitPic = 0;
		long diffBetweenvisitPicAndEndQ = 0;
		String diffBetweenvisitPicTime = "";
		if ( visitDTO.getVisitPicTimeStamp () != null )
		{
			visitPicTimeStamp = visitDTO.getVisitPicTimeStamp ().getTimeInMillis ();
			diffBetweenvisitPic = visitPicTimeStamp - startTimeStamp;
			days = diffBetweenvisitPic / dateFactor;
			hoursInMillis = diffBetweenvisitPic % dateFactor;
			hours = hoursInMillis / hourFactor;
			minuteInMilis = diffBetweenvisitPic % hourFactor;
			minutes = minuteInMilis / minFactor;
			diffBetweenvisitPicTime = days + ":" + hours + ":" + minutes;
			visitDTO.setDiffBetweenvisitPicTime ( diffBetweenvisitPicTime );
			
			diffBetweenvisitPic = endTimeStamp - visitPicTimeStamp;
			days = diffBetweenvisitPic / dateFactor ;
			hoursInMillis = diffBetweenvisitPic % dateFactor;
			hours = hoursInMillis / hourFactor;
			minuteInMilis = diffBetweenvisitPic % hourFactor;
			minutes = minuteInMilis / minFactor;
			diffBetweenvisitPicTime = days + ":" + hours + ":" + minutes;
			visitDTO.setDiffBetweenvisitPicAndQEndTime(diffBetweenvisitPicTime);
		}
		System.out.println ( "differanceBetweenTime:::::::::::" + differanceBetweenTime );
		visitDTO.setDifferanceBetweenTime ( differanceBetweenTime );
		
		// now process the visit dto
		System.out.println ( "in visit dto post processor" );
		// now return the dto
		return visitDTO;*/
		return null;
	}
	
}
