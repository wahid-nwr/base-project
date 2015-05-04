package com.swiftcorp.portal.dcrinfo.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.dcrinfo.dto.DcrProductInfoDTO;
import com.swiftcorp.portal.dcrinfo.dto.DcrinfoDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DcrInfoAddService
{
	private static final Log logger = LogFactory.getLog ( DcrInfoAddService.class );
	
	IDcrinfoService dcrinfoService;
	
	public void addDcrInfo ( DcrinfoDTO dcrinfoDTO  )
	{
		try
		{
			List<DcrProductInfoDTO> infoDTOListToAdd = new ArrayList<DcrProductInfoDTO> ();
			List<DcrProductInfoDTO> infoDTOList = dcrinfoDTO.getProductInfoList ();
			dcrinfoDTO.setDescription("des");
			dcrinfoDTO.setStatus(1);
			dcrinfoDTO.setUniqueCode("test");
			dcrinfoDTO.setUpdatedBy(2l);
			dcrinfoDTO.setUpdatedDate(Calendar.getInstance());
			for(DcrProductInfoDTO dcrProductInfoDTO:infoDTOList)
			{
				dcrProductInfoDTO.setDcrinfoDTO ( dcrinfoDTO );
				infoDTOListToAdd.add ( dcrProductInfoDTO );
			}
			dcrinfoDTO.setProductInfoList ( infoDTOListToAdd );
			dcrinfoService.add ( dcrinfoDTO );
		}
		catch (SystemException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (BusinessRuleViolationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDcrinfoService ( IDcrinfoService dcrinfoService )
	{
		this.dcrinfoService = dcrinfoService;
	}
}
