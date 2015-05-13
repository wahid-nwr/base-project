package com.swiftcorp.portal.dcrreport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
/*import com.swiftcorp.portal.dcrinfo.dto.DcrProductInfoDTO;
import com.swiftcorp.portal.dcrinfo.dto.DcrinfoDTO;
import com.swiftcorp.portal.dcrinfo.service.IDcrinfoService;
import com.swiftcorp.portal.dcrreport.dto.DcrReportDTO;
import com.swiftcorp.portal.dcrreport.service.IDcrReportService;*/

public class DcrReportTest {

	/*
	private IDcrReportService dcrReportService;
	
	public void init(){
		
		if(dcrReportService != null){
			return;
		}
		
		// 
		String[] contextFileArray = new String[]
		                               		{
		                               				"/web/WEB-INF/applicationContext.xml",
		                               				"/web/WEB-INF/webservice.xml"
		                               		};
		                               		
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext ( contextFileArray );
		dcrReportService = (IDcrReportService) ctx.getBean ( "DcrReportService" );  
		
	}
	
	public void testAdd(){
		
		DcrReportDTO reportDTO = new DcrReportDTO();
		reportDTO.setTarget(12000.0f);
		reportDTO.setAchievement(9000.0f);
		
		try {
			dcrReportService.add(reportDTO);
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (BusinessRuleViolationException e) {
			e.printStackTrace();
		}
	}

	public void testGet(){
		
		try {
			
			DcrReportDTO reportDTO = (DcrReportDTO)dcrReportService.get();
			System.out.println("Achievement = " + reportDTO.getAchievement());
			System.out.println("Target = " + reportDTO.getTarget());
			System.out.println("percentage = " + reportDTO.getPercentage());
			
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (BusinessRuleViolationException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DcrReportTest dcrInfoTest = new DcrReportTest();
		dcrInfoTest.init();
		//dcrInfoTest.testAdd();
		dcrInfoTest.testGet();
	}*/
}
