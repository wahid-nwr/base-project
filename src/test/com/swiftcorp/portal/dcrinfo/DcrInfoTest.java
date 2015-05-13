package com.swiftcorp.portal.dcrinfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
/*import com.swiftcorp.portal.dcrinfo.dto.DcrProductInfoDTO;
import com.swiftcorp.portal.dcrinfo.dto.DcrinfoDTO;
import com.swiftcorp.portal.dcrinfo.service.IDcrinfoService;*/

public class DcrInfoTest {
	/*
	private IDcrinfoService dcrinfoService;
	
	public void init(){
		
		if(dcrinfoService != null){
			return;
		}
		
		// 
		String[] contextFileArray = new String[]
		                               		{
		                               				"/web/WEB-INF/applicationContext.xml",
		                               				"/web/WEB-INF/webservice.xml"
		                               		};
		                               		
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext ( contextFileArray );
		dcrinfoService = (IDcrinfoService) ctx.getBean ( "DcrInfoService" );  
		
	}
	
	public void testAdd(){
		
		DcrinfoDTO dto = new DcrinfoDTO();
		dto.setDate("12-10-2011");
		dto.setDoctorName("Dr. Nazmul Hossain");
		dto.setLocation("Dhanmondhi - Unit - 01");
		dto.setSession("Evening");
		
		List<DcrProductInfoDTO> list = new ArrayList<DcrProductInfoDTO>();
		dto.setProductInfoList(list);
		
		DcrProductInfoDTO productInfoDTO1 = new DcrProductInfoDTO();
		productInfoDTO1.setDcrinfoDTO(dto);
		productInfoDTO1.setProductCode("Prod. A");
		productInfoDTO1.setProductType("Prod. Type - 01");
		productInfoDTO1.setQuantity(5);
		list.add(productInfoDTO1);
		
		DcrProductInfoDTO productInfoDTO2 = new DcrProductInfoDTO();
		productInfoDTO2.setDcrinfoDTO(dto);
		productInfoDTO2.setProductCode("Prod. B");
		productInfoDTO2.setProductType("Prod. Type - 02");
		productInfoDTO2.setQuantity(6);
		list.add(productInfoDTO2);
		

		DcrProductInfoDTO productInfoDTO3 = new DcrProductInfoDTO();
		productInfoDTO3.setDcrinfoDTO(dto);
		productInfoDTO3.setProductCode("Prod. C");
		productInfoDTO3.setProductType("Prod. Type - 02");
		productInfoDTO3.setQuantity(7);
		list.add(productInfoDTO3);
		
		try {
			dcrinfoService.add(dto);
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (BusinessRuleViolationException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DcrInfoTest dcrInfoTest = new DcrInfoTest();
		dcrInfoTest.init();
		dcrInfoTest.testAdd();
	}*/
}
