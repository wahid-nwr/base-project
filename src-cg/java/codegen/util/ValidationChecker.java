package codegen.util;

import java.util.List;

import codegen.generators.dto.FieldDTO;

public class ValidationChecker
{
	public static  boolean existDuplicateField(List<FieldDTO> fieldNames)throws Exception
	{
		boolean bFlag = false ;
		for(FieldDTO field : fieldNames)
		{
	        if(    field.getName().equalsIgnoreCase("componentId") || field.getName().equalsIgnoreCase("uniqueCode") 
	        	|| field.getName().equalsIgnoreCase("description") || field.getName().equalsIgnoreCase("status") 
	        	|| field.getName().equalsIgnoreCase("version") || field.getName().equalsIgnoreCase("version"))
	        {
	        	bFlag = true ;
	        	break;
	        }// end if	        
		}// end for
		return bFlag;		
	}

}
