package codegen.generators.xmlconfig;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import codegen.generators.dto.FieldDTO;
import codegen.util.CodeGenConstants;
import codegen.util.ReplaceDTO;
import codegen.util.XMLReaderWriter;

public class ValidationConfigGenerator extends XMLReaderWriter
{
	
	public Element generate(ReplaceDTO replace, List<FieldDTO> fields) throws Exception
	{
		String dirStr = CodeGenConstants.PROJECT_WEB_INF_DIR ;
		String fileName = "validation.xml"; 
	
		try 
		{
			Document doc = super.readFile(dirStr, fileName);
			Element root = doc.getRootElement() ;
			
			Element formset = root.element("formset");
			writeComponentForm(formset,replace,fields);
			
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
	
	
	/*
		<form name="/userActionWithValidation">
			<field property="user.uniqueCode" depends="required">
		 		<arg position="0" key="label.user.userId"/>
		 	</field>
		 	
		 	<field property="contact.designationId" depends="required,mask">
				<arg position="0" key="label.contact.designation"/>		 			 		
				<var>		            
				  <var-name>mask</var-name>		            
				  <var-value>^[1-9]{1}[0-9]*$</var-value>		                    		
				</var>		 				 				 	
			</field>	
		 </form>	 
	 */
	
	private void writeComponentForm(Element actionmappings,ReplaceDTO data, List<FieldDTO> fields)
	{   		
   		    actionmappings.addComment("     " + data.getComponentName().toUpperCase()+"    "); 
            Element form = actionmappings.addElement("form");
 
            String actionStr = "/" + data.getComponentNameLowercase()+ "ActionWithValidation";            
            form.addAttribute("name", actionStr);
              
            for(FieldDTO field : fields)
            {
                if(field != null && field.isValidationReq())
                {
	            	Element fieldEle = form.addElement("field"); 
	            	String propertyKey = data.getComponentNameLowercase() + "." + field.getName() ;          
	            	fieldEle.addAttribute("property", propertyKey);
	            	if(field.getHtmlInputType()== FieldDTO.HTML_TEXT)
	            	{
	            		fieldEle.addAttribute("depends", "required");
	            	}
	            	else if(field.getHtmlInputType()== FieldDTO.HTML_COMBO)
	            	{
	            	  fieldEle.addAttribute("depends", "required,mask");
	            	}
	            	
	            	String labelKey = "label."+ data.getComponentNameLowercase() + "." + field.getName() ;          
	            	Element argEle = fieldEle.addElement("arg");
	            	argEle.addAttribute("position", "0");           	
	            	argEle.addAttribute("key", labelKey);
	            	
	            	if(field.getHtmlInputType()== FieldDTO.HTML_COMBO)
	            	{
		            	Element varEle = fieldEle.addElement("var");
		            	Element varName = varEle.addElement("var-name");
		            	Element varval = varEle.addElement("var-value");
		            	varName.setText("mask");
		            	varval.setText("^[1-9]{1}[0-9]*$");		            
	            	}	            	
	            	
                }// end if
                   
	         }// end loop          
     }
	

}