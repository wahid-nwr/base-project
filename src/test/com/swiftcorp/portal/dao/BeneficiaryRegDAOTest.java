/**
 * 
 */
package com.swiftcorp.portal.dao;

import org.springframework.context.support.FileSystemXmlApplicationContext;
/*
import com.swiftcorp.portal.beneficiary.dto.BeneficiaryDTO;
import com.swiftcorp.portal.beneficiary.dto.BeneficiaryExamDTO;
import com.swiftcorp.portal.beneficiary.dto.BeneficiaryHistoryDTO;
import com.swiftcorp.portal.beneficiary.service.IBeneficiaryService;
*/
/**
 * @author asraful.haque
 *
 */
public class BeneficiaryRegDAOTest
{
	/*
	private static IBeneficiaryService beneficiaryService;
	
	*//**
	 * @param args
	 *//*
	public static void main ( String[] args )
	{
		// 
		String[] contextFileArray = new String[]
		                               		{
		                               				"/web/WEB-INF/applicationContext.xml",
		                               				"/web/WEB-INF/webservice.xml",
		                               				
		                               		};
		                               		
		//FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext ( contextFileArray );
		//beneficiaryService = (IBeneficiaryService) ctx.getBean ( "BeneficiaryService" );  
		
		try
		{
			//testBeneficiaryAdd();
			//testBeneficiaryFetch ();
			testBeneficiaryUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		
	}
	
	private static void testBeneficiaryAdd () throws Exception
	{
		// get the beneficiary
		BeneficiaryDTO beneficiaryDTO = new BeneficiaryDTO ();
		beneficiaryDTO.setBeneficiaryName ( "adfdfd" );
		
		// get ben history
		BeneficiaryHistoryDTO beneficiaryHistoryDTO = new BeneficiaryHistoryDTO ();
		beneficiaryHistoryDTO.setBreastMass ( 12 );
		
		beneficiaryDTO.getBeneficiaryHistoryDTOList ().add ( beneficiaryHistoryDTO );
		
		
		
		// now get beneficiary exam
		BeneficiaryExamDTO beneficiaryExamDTO = new BeneficiaryExamDTO ();
		beneficiaryExamDTO.setRightBreastMassSize ( 11 );
		beneficiaryDTO.getBeneficiaryExamDTOList ().add ( beneficiaryExamDTO );
		
		// now save the beneficiary
		beneficiaryService.add ( beneficiaryDTO );
		
	}
	
	private static void testBeneficiaryFetch () throws Exception
	{
		// get the beneficiary
		BeneficiaryDTO beneficiaryDTO = (BeneficiaryDTO) beneficiaryService.get ( 2l );
		
		System.out.println ("get the beneficiary with name " + beneficiaryDTO.getBeneficiaryName () );
		
	}
	
	private static void testBeneficiaryUpdate () throws Exception
	{
		// get the beneficiary
		BeneficiaryDTO beneficiaryDTO = (BeneficiaryDTO) beneficiaryService.get ( 2l );
		
		BeneficiaryHistoryDTO beneficiaryHistoryDTO = new BeneficiaryHistoryDTO ();
		beneficiaryHistoryDTO.setBreastMass ( 15 );
		
		beneficiaryDTO.getBeneficiaryHistoryDTOList ().add ( beneficiaryHistoryDTO );
		
		beneficiaryService.modify ( beneficiaryDTO );
		
		System.out.println ("get the beneficiary with name " + beneficiaryDTO.getBeneficiaryName () );
		
	}
	*/
}
