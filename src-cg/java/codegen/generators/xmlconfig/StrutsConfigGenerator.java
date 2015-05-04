package codegen.generators.xmlconfig;

import java.io.File;

import org.apache.commons.lang.WordUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import codegen.init.ComponentDetailCache;
import codegen.util.CodeGenConstants;
import codegen.util.ReplaceDTO;
import codegen.util.ReplaceDTOMaker;
import codegen.util.XMLReaderWriter;

public class StrutsConfigGenerator extends XMLReaderWriter
{
	
	public Element generate(ReplaceDTO replace) throws Exception
	{
		String projectDir = ComponentDetailCache.projectLocation;
		String dirStr = projectDir + File.separator + CodeGenConstants.PROJECT_WEB_INF_DIR ;
		System.out.println("dirStr::"+dirStr);
		String fileName = "struts-config.xml"; 
	
		try 
		{
			Document doc = super.readFile(dirStr, fileName);
			Element root = doc.getRootElement() ;
			
			Element formbeans = root.element("form-beans");
			writeComponentBean(formbeans,replace);
			
			// without validation
			Element action = root.element("action-mappings");
			writeComponentAction(action,replace, false);
			
			// with validation
			action = root.element("action-mappings");
			writeComponentAction(action,replace, true);
			// output file
			File ouputFile = new File(dirStr,fileName);
			super.writeXML(doc, ouputFile);
		}
		catch (Exception e)
		{
			System.out.println("generate()- xml path  not found or cannot read the file: "+ e );
			throw e;
		}
		return null;
	}
	
	/**
	 * write the form-bean tags with the existing one
	 */

	private void writeComponentBean(Element formbeans, ReplaceDTO data)
	{
    		String formName = data.getComponentNameLowercase()+ "Form";
    		String dtoFullName = "com." + CodeGenConstants.COMPANY_NAME + "."+ data.getPackageLowercase() + "."+ data.getComponentNameLowercase()+ ".dto."+ WordUtils.capitalize(data.getComponentName())+ "DTO" ;
    			
    		formbeans.addComment("Bean defination of "+ data.getComponentName().toUpperCase());
            Element formbean = formbeans.addElement("form-bean");
            formbean.addAttribute("name", formName);
            formbean.addAttribute("type", "org.apache.struts.validator.DynaValidatorActionForm");
            Element element = formbean.addElement("form-property"); 
            element.addAttribute("name", data.getComponentNameLowercase());
            element.addAttribute("type", dtoFullName);
            element = formbean.addElement("form-property");
            element.addAttribute("name", "searchQuery");
            element.addAttribute("type", "java.lang.String");
 	}
	
	private void writeComponentAction(Element actionmappings,ReplaceDTO data, boolean isValidation)
	{
       		String formName = data.getComponentNameLowercase()+ "Form";    		
    		String componentCapitalized = WordUtils.capitalize(data.getComponentName());
    		
    		if(!isValidation )
    		{
    		   actionmappings.addComment("\n\t=============================================================================\n " 
    				                 + "                       " + data.getComponentName().toUpperCase() 
    				                 + "\n\t=============================================================================\n\t");
    		}
    		
            Element action = actionmappings.addElement("action");
    		String actionStr = "/"+data.getComponentNameLowercase()+ "Action"; 
            if(isValidation)
            actionStr = "/"+data.getComponentNameLowercase()+ "ActionWithValidation"; 
            
            action.addAttribute("path", actionStr);
            action.addAttribute("type", "org.springframework.web.struts.DelegatingActionProxy");
            action.addAttribute("parameter", "method");
            action.addAttribute("name", formName);
            if(isValidation)
            action.addAttribute("validate", "true");
            if(isValidation)
            action.addAttribute("input", "/" + componentCapitalized+ "/Modify/index.jsp");
            
            action.addAttribute("scope", "session");
            
            Element element = action.addElement("exception"); 
            element.addAttribute("type", "com."+ CodeGenConstants.COMPANY_NAME + "."+ data.getPackageLowercase()+".common.exception.BusinessRuleViolationException");
            element.addAttribute("scope", "request");
            element.addAttribute("key", "already.exist.same.code");
            element.addAttribute("path", "/" + componentCapitalized+ "/Modify/index.jsp");
            
            element = action.addElement("forward"); 
            element.addAttribute("name", data.getComponentNameLowercase()+ "_search_system_level");
            element.addAttribute("path", "/" + componentCapitalized + "/Search/System/index.jsp");
            
            element = action.addElement("forward"); 
            element.addAttribute("name", "ext_"+data.getComponentNameLowercase()+ "_search_system_level");
            element.addAttribute("path", "/Common/Search/ExtSearchResult.jsp?component="+data.getComponentName());
            
            element = action.addElement("forward");
            element.addAttribute("name", data.getComponentNameLowercase()+ "_search_group_level");
            element.addAttribute("path", "/" + componentCapitalized + "/Search/Group/index.jsp"); 
            
            element = action.addElement("forward");
            element.addAttribute("name",  "prompt_add_"+ data.getComponentNameLowercase());
            element.addAttribute("path", "/" + componentCapitalized + "/Modify/index.jsp"); 

            element = action.addElement("forward");
            element.addAttribute("name",  "prompt_modify_"+ data.getComponentNameLowercase());
            element.addAttribute("path", "/" + componentCapitalized + "/Modify/index.jsp");             
            
     }
	


	
	public static void main(String[] args)
	{
		try
		{
			System.out.println("Starting application.....");
			ReplaceDTOMaker.prepareReplaceDTO("teleshop", "cas");
			//generator.generate(CodeGenConstants.MISC_CONF_DIR, fileName,replace);
		}
		catch (Exception e)
		{
			System.out.println("error ::" +e);
		}
		System.out.println("Finished!!!...........!!!");

	}

}
