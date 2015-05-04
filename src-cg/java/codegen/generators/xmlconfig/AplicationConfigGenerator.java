package codegen.generators.xmlconfig;

import java.io.File;

import org.apache.commons.lang.WordUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import codegen.init.ComponentDetailCache;
import codegen.util.CodeGenConstants;
import codegen.util.ReplaceDTO;
import codegen.util.ReplaceDTOMaker;
import codegen.util.XMLReaderWriter;

public class AplicationConfigGenerator extends XMLReaderWriter
{
	public Element generate(ReplaceDTO replace,String location) throws Exception
	{
		String projectDir = File.separator + "home" + File.separator + "wahid" + File.separator + "workspace" + File.separator + "beximco";
		projectDir = ComponentDetailCache.projectLocation;
		String dirStr = projectDir + File.separator + CodeGenConstants.PROJECT_WEB_INF_DIR ;
		System.out.println("dirStr::"+dirStr);
		String fileName = "applicationContext.xml"; 
		try 
		{
			Document doc = super.readFile(dirStr,fileName);			
			Element root = doc.getRootElement() ;
			Node mappingNode = root.selectSingleNode("//beans/bean[@id='sessionFactory']/property/list"); 
			writeMappingResource(mappingNode,replace);
			writeBeanConfigs(root,replace);
			// output file
			File ouputFile = new File(dirStr,fileName);
			super.writeXML(doc, ouputFile);
		}
		catch (Exception e)
		{
			System.out.println("generate()- application-config.xml path  not found or cannot read the file: "+ e );
			throw e ;
		}
		return null;
	}
	
	/**
	 * write the form-bean tags with the existing one
	 * Exam: root.selectSingleNode("//beans/bean[@id='sessionFactory']/property[@name='mappingResources']/list");
	 */
	private void writeMappingResource(Node mappingNode, ReplaceDTO data)
	{
    		String dtoFullName = "com/"+ CodeGenConstants.COMPANY_NAME+"/" + data.getPackageLowercase() + "/"+ data.getComponentNameLowercase()+ "/dto/"+ WordUtils.capitalize(data.getComponentName())+ "DTO.hbm.xml" ;
    		
            if(mappingNode == null)
            {
            	  System.out.println("writeMappingResource(): element is found null");
                  return ;                          
            }    
           Element mappingElement = (Element) mappingNode;
           Element value = mappingElement.addElement("value");
           value.setText(dtoFullName);
 	}
	
	private void writeBeanConfigs(Element root,ReplaceDTO data)throws Exception
	{
    		try
			{
	    		String componentCapitalized = WordUtils.capitalize(data.getComponentName());
	    		String componentNonCapitalized = WordUtils.uncapitalize(data.getComponentName());
	    		String packageBase = "com." + CodeGenConstants.COMPANY_NAME + "."+ data.getPackageLowercase() + "."+ data.getComponentNameLowercase() ;
	    		
	    		String daoName= componentCapitalized + "DAOImpl";
	    		String daoClass=  packageBase + ".dao."+ WordUtils.capitalize(data.getComponentName())+ "HibernateDAOImpl" ;
	    		String serviceName=componentCapitalized + "Service";
	    		String serviceClass=packageBase + ".service."+ WordUtils.capitalize(data.getComponentName())+ "ServiceImpl" ;
	    		String actionName= "/"+data.getComponentNameLowercase()+ "Action"; 
	    		String actionValidationName= "/"+data.getComponentNameLowercase()+ "ActionWithValidation";
	    		String actionClass=packageBase + ".web."+ WordUtils.capitalize(data.getComponentName())+ "DispatchAction" ;

	    		root.addComment("\n\t=============================================================================\n " 
	    				        + "                       " + data.getComponentName().toUpperCase() 
	    				        + "\n\t=============================================================================\n\t");
	            
	    		// Add DAO Bean
	    		Element bean = root.addElement("bean");
	    		bean.addAttribute("id", daoName);
	    		bean.addAttribute("class", daoClass);           
	            Element property = bean.addElement("property");
	            property.addAttribute("name", "hibernateTemplate");
	            Element ref = property.addElement("ref");
	            ref.addAttribute("bean", "hibernateTemplate");
	            
	            //add service bean 
	    		bean = root.addElement("bean");
	    		bean.addAttribute("id", serviceName);
	    		bean.addAttribute("class", serviceClass);           
	            property = bean.addElement("property");
	            property.addAttribute("name", componentNonCapitalized+ "DAO");
	            ref = property.addElement("ref");
	            ref.addAttribute("bean", daoName);
	            property = bean.addElement("property");
	            property.addAttribute("name", "searcher");
	            ref = property.addElement("ref");
	            ref.addAttribute("bean", "GenericSearcher");
	            
	            //add action bean 
	    		bean = root.addElement("bean");
	    		bean.addAttribute("name", actionName);
	    		bean.addAttribute("class", actionClass);           
	            property = bean.addElement("property");
	            property.addAttribute("name", componentNonCapitalized+"Service");
	            ref = property.addElement("ref");
	            ref.addAttribute("bean", serviceName);
	            
	            //add action with validation bean 
	    		bean = root.addElement("bean");
	    		bean.addAttribute("name", actionValidationName);
	    		bean.addAttribute("class", actionClass);           
	            property = bean.addElement("property");
	            property.addAttribute("name",componentNonCapitalized+"Service");
	            ref = property.addElement("ref");
	            ref.addAttribute("bean", serviceName);
			
			}
    		catch (Exception e)
			{
				e.printStackTrace();
				throw e;
			}
            
     }
	


	
	public static void main(String[] args)
	{
		try
		{
			System.out.println("Starting application.....");
			ReplaceDTOMaker.prepareReplaceDTO("teleshop", "cas");
			//generator.generate("E:\\projects\\development\\workspace\\cas-crm\\src\\input\\conf", fileName,replace);
		}
		catch (Exception e)
		{
			System.out.println("error ::" +e);
		}
		System.out.println("Finished!!!...........!!!");

	}

}
