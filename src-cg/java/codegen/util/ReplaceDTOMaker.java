package codegen.util;

import org.apache.commons.lang.WordUtils;

public class ReplaceDTOMaker
{
	public static  ReplaceDTO prepareReplaceDTO( String componentName, String pckageName) throws Exception
	{
		
		// prepare the replace DTO
		ReplaceDTO replace = new ReplaceDTO();		
		if(componentName == null || componentName.equals(""))
		{
		     throw new Exception("Component name is not found or empty");
		}
		if(pckageName == null || pckageName.equals(""))
		{
		     throw new Exception("pckage name is not found or empty");
		}
		
		replace.setPackageLowercase(pckageName.toLowerCase());
		replace.setComponentName(componentName);
		
		String uppercase = componentName.toUpperCase();
		String lowercase= componentName.toLowerCase();		
		String capitalizedStr = WordUtils.capitalize(componentName);
		
		replace.setSourceCapitalizedStr(WordUtils.capitalize(CodeGenConstants.REFERENCE_COMPONENT));
		replace.setSourceAllLowerCase(CodeGenConstants.REFERENCE_COMPONENT.toLowerCase());
		replace.setSourceAllUpperCase(CodeGenConstants.REFERENCE_COMPONENT.toUpperCase());
		
		replace.setTargetCapitalizedStr(capitalizedStr);		
		replace.setTargetAllLowerCase(lowercase);
		replace.setTargetAllUpperCase(uppercase);
		
		 return replace;
	}
    

}
