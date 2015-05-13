package com.swiftcorp.portal.transaction;

import java.util.Date;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.file.dao.FileDAOImpl;
import com.swiftcorp.portal.common.file.dto.File;
/*import com.swiftcorp.portal.transaction.dto.TransactionDTO;
import com.swiftcorp.portal.transaction.service.ITransactionService;*/

public class SaleTransactionTest
{
	//private ITransactionService transactionService;
	private FileDAOImpl fileDAO;
	public void init(){
		
		/*if(transactionService != null){
			return;
		}*/
		
		String[] contextFileArray = new String[]
			                               		{
			                               				"/web/WEB-INF/applicationContext.xml"
			                               		};
			                               		
			FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext ( contextFileArray );
			fileDAO =(FileDAOImpl)ctx.getBean ( "FileDAOImpl" );
			//transactionService = (ITransactionService) ctx.getBean ( "TransactionService" );  
			
		}
	public void getSaleTransaction(Long componentId) throws SystemException, BusinessRuleViolationException
	{
		/*TransactionDTO transactionDTO = (TransactionDTO) transactionService.get ( componentId );
		System.out.println ("Transaction is null::"+(transactionDTO==null));*/
	}
	public static void main(String[] args) throws SystemException, BusinessRuleViolationException {
		SaleTransactionTest saleTransactionTest = new SaleTransactionTest();
		saleTransactionTest.init();
		Long componentId = (long) 13;
		//saleTransactionTest.getSaleTransaction(componentId);
		saleTransactionTest.testSaveBlob();
	}
	public void testSaveBlob() {
		File file = new File();
        file.setName("TestMe");
        file.setType("txt");
        file.setCreationTimeStamp(new Date());
        file.setDescription("idc");

        byte[] testBytes = new byte[1024];
        byte byteValue = 1;
        for (int i = 0; i < testBytes.length; i++) {
            testBytes[i] = byteValue;
        }
        file.setLength(new Long(testBytes.length));
        file.setContent(testBytes);
        fileDAO.save(file);

        File dbFile = fileDAO.load(file.getComponentId());
        if(dbFile!=null)
        {
        	System.out.println("dbfile::"+dbFile.getName());
        }
        //assertNotNull(file);
    }
}
