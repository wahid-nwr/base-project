package codegen.generators.jsp;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;

import codegen.generators.dto.FieldDTO;
import codegen.util.CodeGenConstants;
import codegen.util.CustomFileUtils;
import codegen.util.ReplaceDTO;

public class JSPFileGenerator 
{
	
	static File jsp_start_dir = new File(CodeGenConstants.JSP_TEMPLATE_DIR,"JspStart.jsp");
	static File jsp_body_text_dir = new File(CodeGenConstants.JSP_TEMPLATE_DIR, "JspTextBody.jsp");
	static File jsp_body_combo_dir = new File(CodeGenConstants.JSP_TEMPLATE_DIR, "JspComboboxBody.jsp");
	static File jsp_end_dir = new File(CodeGenConstants.JSP_TEMPLATE_DIR, "JspEnd.jsp");
	
	static File jsp_component_link_dir = new File(CodeGenConstants.JSP_TEMPLATE_DIR, "LeftBarComponentLink.jsp");
	static File jsp_ext_component_link_dir = new File(CodeGenConstants.JSP_TEMPLATE_DIR, "ExtUserLeftBarComponentLink.jsp");
	static String leftBarpath = CodeGenConstants.PROJECT_WEB_DIR + File.separator + "Common" + File.separator + "PageLayout";
	static String extUserLeftBarPath = CodeGenConstants.PROJECT_WEB_DIR + File.separator +"Home"+File.separator+"Leftbar";
	static File jsp_project_web_leftbar_dir = new File(leftBarpath, "LeftBar.jsp");
	
	static File jsp_project_web_user_ext_leftbar_dir = new File(extUserLeftBarPath, "ExtUserLeftBar.jsp");

	@SuppressWarnings("unchecked")
	public static  void generate(List<FieldDTO> fieldNames, String componentName,String location)throws Exception
	{
		List<String> startJspLines = new ArrayList<String>();
		List<String> endJspLines = new ArrayList<String>();
		List<String> allBodyJspLines = new ArrayList<String>();
		List<String> allLines = new ArrayList<String>();
		
		List<String> textBodyJspLines = new ArrayList<String>();
		List<String> comboBodyJspLines = new ArrayList<String>();
		
		
		try 
		{
			startJspLines = FileUtils.readLines(jsp_start_dir);
			endJspLines = FileUtils.readLines(jsp_end_dir);
			
			// body
			textBodyJspLines = FileUtils.readLines(jsp_body_text_dir);
			comboBodyJspLines = FileUtils.readLines(jsp_body_combo_dir);
		}
		catch (Exception e) 
		{
			 e.printStackTrace();
	   		 System.err.println("\nError found while processing in JSPFileGenerator.generate() method");
    		 throw e ;			
		}
		
    	// prepare the replace DTO
		// Assuming that we dont need all UPPER case here and use teh UPPER case field as field property
		ReplaceDTO replace = new ReplaceDTO();		
		replace.setSourceCapitalizedStr(WordUtils.capitalize(CodeGenConstants.REFERENCE_COMPONENT));
		replace.setSourceAllLowerCase(CodeGenConstants.REFERENCE_COMPONENT.toLowerCase());
		replace.setSourceAllUpperCase("sampleproperty");

		// NO target UPPER CASE
		String componentNameCapitalized =WordUtils.capitalize(componentName);
		replace.setTargetCapitalizedStr(componentNameCapitalized);		
		replace.setTargetAllLowerCase(componentName.toLowerCase());
		
		startJspLines =  CustomFileUtils.getReplacedLines(startJspLines,replace);					
		endJspLines = CustomFileUtils.getReplacedLines(endJspLines,replace);			

		System.out.println("Start and end jsp is replaced complete");
		
		for(FieldDTO fieldName : fieldNames)
		{
			replace.setTargetAllUpperCase(fieldName.getName());
			List<String> jspLines = null ;
			if(fieldName.getHtmlInputType()== FieldDTO.HTML_TEXT)
			{
				jspLines = CustomFileUtils.getReplacedLines(textBodyJspLines,replace);	
			}
			else if(fieldName.getHtmlInputType()== FieldDTO.HTML_COMBO)
			{
				jspLines = CustomFileUtils.getReplacedLines(comboBodyJspLines,replace);
			}			
			allBodyJspLines.add("\n");
			allBodyJspLines.addAll(jspLines);
		}
	
		allLines.addAll(startJspLines);
		allLines.addAll(allBodyJspLines);
		allLines.addAll(endJspLines);
		
		FileOutputStream output = null ;   
        try 
        {
        	String baseDir = CodeGenConstants.OUTPUT_BASE_DIR;
        	baseDir = baseDir.replaceAll(CodeGenConstants.CODEGEN_BASE_DIR, "");
           	String OUTPUT_DIR = location + baseDir + File.separator+"web" + File.separator+ WordUtils.capitalize(componentName)  + File.separator+ "Modify";
        	File destFile = new File(OUTPUT_DIR,WordUtils.capitalize(componentName)+ "Entry.jsp");             
            output = new FileOutputStream(destFile);     
            IOUtils.writeLines(allLines,null, output);
            System.out.println("\nGenerating ext leftbar link");
            generateExtUserLeftbarComponentLink(replace);
        }
        catch(Exception e)
        {
	   		 System.err.println("\nError found while processing in JSPFileGenerator.generate() method");
    		 e.printStackTrace();
      		 throw e ;
        }
        finally
        {
            IOUtils.closeQuietly(output);
        }
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static  void generateLeftbarComponentLink(ReplaceDTO replace)throws Exception
	{
		System.out.println("<<generateLeftbarComponentLink()Enter:>>");
		FileOutputStream output = null ;  
		try 
		{
			List<String> leftbarJspLines = new ArrayList<String>();
			List<String> componentLinkJspLines = new ArrayList<String>();
			
			leftbarJspLines = FileUtils.readLines(jsp_project_web_leftbar_dir);
			componentLinkJspLines = CustomFileUtils.getReplacedLines(jsp_component_link_dir,replace);
			leftbarJspLines.addAll(componentLinkJspLines);

			String OUTPUT_DIR = leftBarpath ;
        	File destFile = new File(OUTPUT_DIR, "LeftBar.jsp"); 
        	if(destFile.exists())
        	{
        		destFile.delete();
        	}
        	
        	destFile = new File(OUTPUT_DIR, "LeftBar.jsp"); 
            output = new FileOutputStream(destFile);     
            IOUtils.writeLines(leftbarJspLines,null, output);
        }
        catch(Exception e)
        {
	   		 System.err.println("\nError found while processing in JSPFileGenerator.generateComponentLink() method");
    		 e.printStackTrace();
      		 throw e ;
        }
        finally
        {
            IOUtils.closeQuietly(output);
        }
		System.out.println("<<generateLeftbarComponentLink()Exit:>>");
	}
	
	@SuppressWarnings("unchecked")
	public static  void generateExtUserLeftbarComponentLink(ReplaceDTO replace)throws Exception
	{
		System.out.println("<<generateExtUserLeftbarComponentLink()Enter:>>");
		FileOutputStream output = null ;  
		try 
		{
			boolean isAddedLink = false;
			List<String> leftbarJspLines = new ArrayList<String>();
			List<String> componentLinkJspLines = new ArrayList<String>();
						
			leftbarJspLines = FileUtils.readLines(jsp_project_web_user_ext_leftbar_dir);
			componentLinkJspLines = CustomFileUtils.getReplacedLines(jsp_ext_component_link_dir,replace);
			for(int i=0;leftbarJspLines!=null && i<leftbarJspLines.size();i++)
			{
				String line = leftbarJspLines.get(i);
				System.out.println(line);
				//leftbarJspLines.addAll(componentLinkJspLines);
				if(line.indexOf("//add new functions here")>-1) 
				{
					leftbarJspLines.addAll(i, componentLinkJspLines);
					break;
				}
			}
			String OUTPUT_DIR = extUserLeftBarPath ;
        	File destFile = new File(OUTPUT_DIR, "ExtUserLeftBar.jsp"); 
        	if(destFile.exists())
        	{
        		destFile.delete();
        	}
        	
        	
        	destFile = new File(OUTPUT_DIR, "ExtUserLeftBar.jsp"); 
            output = new FileOutputStream(destFile);     
            IOUtils.writeLines(leftbarJspLines,null, output);
        }
        catch(Exception e)
        {
	   		 System.err.println("\nError found while processing in JSPFileGenerator.generateExtUserLeftbarComponentLink() method");
    		 e.printStackTrace();
      		 throw e ;
        }
        finally
        {
            IOUtils.closeQuietly(output);
        }
		System.out.println("<<generateExtUserLeftbarComponentLink()Exit:>>");
	}
	
	public static void main(String[] args)
	{
		System.out.println("<<Starting the DTO code generator:>>");
		List<FieldDTO> fieldNames = new ArrayList<FieldDTO>();
		FieldDTO fieldDTO = new FieldDTO("name", "String" );
		fieldNames.add(fieldDTO);
		fieldDTO = new FieldDTO("parent", "String" );
		fieldNames.add(fieldDTO);
					
		try 
		{            			
			//generate(fieldNames, "role","src-cg");
			generateExtUserLeftbarComponentLink(getReplaceDTO("test"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("<<End the process>>");

	}
	public static ReplaceDTO getReplaceDTO(String componentName)
	{
		ReplaceDTO replace = new ReplaceDTO();		
		replace.setSourceCapitalizedStr(WordUtils.capitalize(CodeGenConstants.REFERENCE_COMPONENT));
		replace.setSourceAllLowerCase(CodeGenConstants.REFERENCE_COMPONENT.toLowerCase());
		replace.setSourceAllUpperCase("sampleproperty");

		// NO target UPPER CASE
		String componentNameCapitalized =WordUtils.capitalize(componentName);
		replace.setTargetCapitalizedStr(componentNameCapitalized);		
		replace.setTargetAllLowerCase(componentName.toLowerCase());
		return replace;
	}
	
	public static List<FieldDTO> getCommonFieldList()
	{
		List<FieldDTO> fieldList = new ArrayList<FieldDTO>();
		fieldList.add(new FieldDTO("uniqueCode", "String"));
		fieldList.add(new FieldDTO("description", "String"));
		return fieldList ;

	}

}
