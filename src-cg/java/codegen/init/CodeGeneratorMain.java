package codegen.init;

import java.util.List;

import codegen.generators.common.JavaConstantsGenerator;
import codegen.generators.dto.DTOFileGenerator;
import codegen.generators.dto.FieldDTO;
import codegen.generators.hbm.HbmFileGenerator;
import codegen.generators.javascript.JavascriptFileGenerator;
import codegen.generators.jsp.JSPFileGenerator;
import codegen.generators.properties.PropertiesFileGenerator;
import codegen.generators.script.DBScriptGenerator;
import codegen.generators.xmlconfig.AplicationConfigGenerator;
import codegen.generators.xmlconfig.StrutsConfigGenerator;
import codegen.generators.xmlconfig.ValidationConfigGenerator;
import codegen.init.main.EntryList;
import codegen.util.CodeGenConstants;
import codegen.util.CustomFileUtils;
import codegen.util.ReplaceDTO;
import codegen.util.ReplaceDTOMaker;
import codegen.util.ValidationChecker;



public class CodeGeneratorMain 
{
	public static void main(String[] args)
	{
		System.out.println("<<Starting the code generator>>");
		
        /*
          Please read the instruction and generate the component
          1. For any component, define : (a) componentName and (b)projectName
          2. In the fieldList, following fields are added automatically. So don't add again the following
             a. uniqueCode - String type
             b. description - String type
             c. status - int type
             d. version -int type
             c. creationDate - date type
          
          3. After successfully generate, copy the following
              a. java component to project the src/java/com/mycompany/%PACKAGE% directory
              b. JSP component the project web directory
             
          4. If you get any Exception/Error, you are asked to remove the component specific entry from the following area
              a. component from the src
              b. component from the web (JSP)
              c. struts-config.xml
              d. application-context.xml
              e. Messages.properties (conf)
              f. Error.properties  (conf)
              g. SESSION_KEYS.java (com.swiftcorp.portal.common.web)
              h. FORWARD_NAMES.java (com.swiftcorp.portal.common.web)
            
            5. Check and synchronize in the following areas:
               a. search query in %COMPONENT%SearchUtil.java 
               b. Component hbm file
               c Compoent db script                 
		 */		
				
		
		try 
		{
			// This is the component name that we want to create as new component
			//ComponentDetailCache componentDetailCache = new ComponentDetailCache();
			String componentName = ComponentDetailCache.componentName;	
			String projectName = CodeGenConstants.PROJECT_NAME ;
			ReplaceDTO replace = ReplaceDTOMaker.prepareReplaceDTO(componentName,projectName);
			System.out.println("componentName:::::::::::"+componentName);
			// DTO attribute list
			//List<FieldDTO> fieldList = FieldListMaker.getBizCodeList();
			//List<FieldDTO> fieldList = FieldListMaker.getDCRInfoList();
			//List<FieldDTO> fieldList = FieldListMaker.getDCRProductInfoList();
			List<FieldDTO> fieldList = FieldListMaker.getComponentList(ComponentDetailCache.fieldList);
			
			// generate new followings of a component		
			generateCode(componentName, replace,fieldList, ComponentDetailCache.location);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("main method: Found error");
		}
		
		System.out.println("<<End of the process of code generation>>");

	}
	
	
	public static void generateCode(String componentName,ReplaceDTO replace , List<FieldDTO> fieldList,String location) throws Exception 
	{
		System.out.println("generateCode(): Enter");
		try 
		{			
			boolean flag = ValidationChecker.existDuplicateField(fieldList);
			if(flag)
			{
				System.err.println("ERROR::  Found duplicate field name. Check again that you did not add \n\t componentId, uniquecode, version, status or creationdate. \n\tThese fields are added automatically");
				return ;
			}
			
			// generate new followings of a component		
			// All the files are genetated in OUTPUT directory 	
						
			CustomFileUtils.generateNewComponent(fieldList,componentName,replace,location);
			System.out.println("[Task Completion]:: new component generation is complete");
			
			DTOFileGenerator.generate(fieldList, replace,location);
			System.out.println("[Task Completion]:: DTO generation is complete");
			
			HbmFileGenerator.generate(fieldList, replace,location);
			System.out.println("[Task Completion]:: HBM file generation is complete");
			
			JSPFileGenerator.generate(fieldList, componentName,location);
			System.out.println("[Task Completion]:: JSP file generation is complete");
			
			JavascriptFileGenerator.generate(fieldList, componentName, location);
			System.out.println("[Task Completion]:: Javascript file generation is complete");
			
//			// ----------- The file is genetated in web/WEB-INF directory -----------------
			AplicationConfigGenerator appConfigGenerator = new AplicationConfigGenerator(); 
			appConfigGenerator.generate(replace,location);
			System.out.println("[Task Completion]:: application-context.xml  file update is complete");
//
//
//			
			StrutsConfigGenerator strutsConfigGenerator = new StrutsConfigGenerator(); 
			strutsConfigGenerator.generate(replace);
			System.out.println(" [Task Completion]::struts-config.xml  file update is complete");
//			
			ValidationConfigGenerator validationConfigGenerator = new ValidationConfigGenerator(); 
			//validationConfigGenerator.generate(replace, fieldList);
			System.out.println(" [Task Completion]::validation.xml  file update is complete");			
//			
//			// ----------- The file is genetated in src/conf directory -----------------
			PropertiesFileGenerator.generateMessageProperties(fieldList,replace);
			PropertiesFileGenerator.generateErrosProperties(replace);
			System.out.println(" [Task Completion]::Error and Message properties file updates is complete");	
//			
//			// ----------- The file is genetated in src/java/com/mycompany/%package%/common directory -----------------
			JavaConstantsGenerator.generateForwardNames(replace);
			JavaConstantsGenerator.generateSessionKeys(replace);
			System.out.println(" [Task Completion]::SessionKeys and ForwardBanes java file updates are complete");	
//			// ----------- generate dbscript -----------------
			DBScriptGenerator.generate(fieldList, componentName,location);
//			System.out.println("[Task Completion]:: dbscript file is generated");
//			
//			// -----------generate the link in the home page left bar 
//			JSPFileGenerator.generateLeftbarComponentLink(replace);
//			System.out.println("[Task Completion]:: component link is generated");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Found error in generateCode() method");
			throw e ;
		}
		System.out.println("<<End the process>>");
	}
	

}
