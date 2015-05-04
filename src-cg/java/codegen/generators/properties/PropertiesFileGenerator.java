package codegen.generators.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;

import codegen.generators.dto.FieldDTO;
import codegen.util.CodeGenConstants;
import codegen.util.CustomFileUtils;
import codegen.util.ReplaceDTO;
import codegen.util.ReplaceDTOMaker;

public class PropertiesFileGenerator 
{
	@SuppressWarnings("unchecked")
	public static void generateMessageProperties( List<FieldDTO> fieldNames, ReplaceDTO replace) throws Exception
	{
		try 
		{
            // read the lines from input file
			String dir = CodeGenConstants.TEMPLATE_BASE_DIR + File.separator + "conf";
			File input = new File(dir, "Messages.cfg");
			List<String> newLines = CustomFileUtils.getReplacedLines(input, replace);
		    
            // read the lines from field list
			for(FieldDTO field : fieldNames)
			{
				if(field != null && field.getName() != null)
				{
				   String line = "label." + replace.getComponentNameLowercase() + "."+ field.getName() + " = " + WordUtils.capitalize(field.getName());
				   newLines.add(line);
				}
			}			
			
			// read the existing lines of the output file
			dir = CodeGenConstants.projectDir()+CodeGenConstants.PROJECT_CONF_DIR ;
			File outputFile =  readFile(dir,"Messages.properties") ; 
			List<String> totalLines = null ;
	         try
			 {
	        	 totalLines = FileUtils.readLines(outputFile);
			 }
	         catch (Exception e)
			 {
				e.printStackTrace();
			 }
			
			//add the new lines
			totalLines.addAll(newLines);
			
		  // write the output file	
			CustomFileUtils.doCopyFile(totalLines,outputFile);
		}
		catch (Exception e)
		{
			System.out.println("generate()- xml path  not found or cannot read the file: "+ e );
			e.printStackTrace();
			throw e;
		}		
	}
	
	
	@SuppressWarnings("unchecked")
	public static void generateErrosProperties( ReplaceDTO replace)throws Exception 
	{
		try 
		{
            // read the input file
			String dir = CodeGenConstants.TEMPLATE_BASE_DIR + File.separator + "conf";
			File input = new File(dir, "Errors.cfg");
			List<String> newLines = CustomFileUtils.getReplacedLines(input, replace);
		    
			// read the output file
			dir = CodeGenConstants.projectDir()+CodeGenConstants.PROJECT_CONF_DIR ;
			File outputFile =  readFile(dir,"Errors.properties") ; 
			List<String> totalLines = null ;
	         try
			 {
	        	 totalLines = FileUtils.readLines(outputFile);
			 }
	         catch (Exception e)
			 {
				e.printStackTrace();
			 }
			
			//add the new lines
			totalLines.addAll(newLines);
			
		  // write the output file	
			CustomFileUtils.doCopyFile(totalLines,outputFile);
		}
		catch (Exception e)
		{
			System.out.println("generate()- xml path  not found or cannot read the file: "+ e );
			e.printStackTrace();
			throw e ;
		}
		
	}
	
	
	public static  void dumpAllConfigs(List<FieldDTO> fieldNames, ReplaceDTO replace)
	{
		File miscl_configuration_dir = new File(CodeGenConstants.MISC_CONF_DIR);
		File output_dir_conf = new File(CodeGenConstants.OUTPUT_CONF_DIR);	

		
		List<String> confLines = new ArrayList<String>();
		List<String> bodyLines = new ArrayList<String>();
		List<String> allLines = new ArrayList<String>();

		try 
		{
			File confFile = new File(miscl_configuration_dir, "AllConfigurations.cfg");
			confLines = CustomFileUtils.getReplacedLines(confFile ,replace);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		for(FieldDTO fieldName : fieldNames)
		{
			String lineStr = "label." + replace.getComponentName().toLowerCase()+ "."+ fieldName.getName() + " = " + WordUtils.capitalize(fieldName.getName());
			bodyLines.add(lineStr);
		}
			
		allLines.add("");
		allLines.add("### Copy this section into the Messages.properties file ### ");
		allLines.add("###-----------------------------------------------------###");
		allLines.add("");		
		allLines.add("######################## "  + " ##########################################");
		allLines.addAll(bodyLines);
		allLines.addAll(confLines);
		
		FileOutputStream output = null ;   
        try 
        {           
        	File destFile = new File(output_dir_conf, "AllConfigurations.cfg");             
            output = new FileOutputStream(destFile);     
            IOUtils.writeLines(allLines,null, output);
        }
        catch(IOException e)
        {
          e.printStackTrace();	
        }
        finally
        {
            IOUtils.closeQuietly(output);
        }
		
	}
	
	
	public static File readFile(String dirStr, String fileName) throws Exception
	{
		//System.out.println("readFile(): Enter");
		//System.out.println("readFile(): path = "+ dirStr);
		//System.out.println("readFile(): name = "+ fileName);
		File file = null ;
		try 
		{
			file = new File(dirStr,fileName);
		}
		catch (Exception e)
		{			
			e.printStackTrace();
			throw e;
		}
		//System.out.println("readFile(): Exit");
		return file ;
	}
	
	
	
	public static void main(String[] args)
	{
		System.out.println("<<Starting the DTO code generator:>>");
		try
		{
			ReplaceDTO data = ReplaceDTOMaker.prepareReplaceDTO("teleshop","cas");
			System.out.println("Starting application.....");
			PropertiesFileGenerator.generateErrosProperties(data);
		}
		catch (Exception e)
		{
			System.out.println("error ::" +e);
		}
		System.out.println("Finished!!!...........!!!");

	}
}
