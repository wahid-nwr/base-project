package codegen.generators.hbm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import codegen.generators.dto.FieldDTO;
import codegen.init.ComponentDetailCache;
import codegen.util.CodeGenConstants;
import codegen.util.CustomFileUtils;
import codegen.util.ReplaceDTO;

public class HbmFileGenerator 
{

	static File start_dir = new File(CodeGenConstants.HBM_TEMPLATE_DIR,"hbmStart.xml");
	static File end_dir = new File(CodeGenConstants.HBM_TEMPLATE_DIR, "hbmEnd.xml");


	@SuppressWarnings("unchecked")
	public static  void generate(List<FieldDTO> fieldNames, ReplaceDTO replace,String location)throws Exception
	{

		List<String> startHbmLines = new ArrayList<String>();
		List<String> endHbmLines = new ArrayList<String>();
		List<String> bodyHbmLines = new ArrayList<String>();
		//List<String> allBodyJspLines = new ArrayList<String>();
		List<String> allLines = new ArrayList<String>();
		
		try 
		{
			startHbmLines = FileUtils.readLines(start_dir);
			//bodyJspLines = FileUtils.readLines(body_dir);
			endHbmLines = FileUtils.readLines(end_dir);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
	       	System.err.println("\nError found while processing in gHBMFileGenerator.generate() method");
		    throw e ;			
		}
		
    	// prepare the replace DTO
		String componentName = replace.getComponentName() ;
		String capitalizedCompname =WordUtils.capitalize(componentName);

		//ComponentDetailCache componentDetailCache = new ComponentDetailCache();
		Hashtable fieldMap = ComponentDetailCache.fieldMap;
		Hashtable fieldDtl = null;
		
		String fieldType = "";
		String fieldName = "";
		String dtoFieldType = "";
		// only for body replcement


		for(FieldDTO fieldDTO : fieldNames)
		{	
			fieldName = fieldDTO.getName();
			fieldType = fieldDTO.getType();
			
			fieldDtl = (Hashtable)fieldMap.get(fieldType);
			dtoFieldType = (String) fieldDtl.get("hbm");
			 String line = null ;
			if(fieldDTO.isUserDefinedDTO())
			{
				line = "\t<" + fieldDTO.getMappingType() + " name=\"" + fieldName + "\" class=\"" + fieldDTO.getFullQualifiedDataType()+ "\" lazy=\"false\" column=\"" +fieldName.toLowerCase() + "\" />"; 	
			}
			else
			{
				line = "\t<property name=\"" + fieldName + "\" type=\""+ dtoFieldType.toLowerCase() + "\" column=\"" + fieldName.toLowerCase() +"\" /> " ;
			}
			bodyHbmLines.add(line);
		}
		
		startHbmLines =  CustomFileUtils.getReplacedLines(startHbmLines,replace);					
		endHbmLines = CustomFileUtils.getReplacedLines(endHbmLines,replace);			
	
		allLines.addAll(startHbmLines);
		allLines.addAll(bodyHbmLines);
		allLines.addAll(endHbmLines);
		
		FileOutputStream output = null ;   
        try 
        {
        	String baseDir = CodeGenConstants.OUTPUT_BASE_DIR;
        	baseDir = baseDir.replaceAll(CodeGenConstants.CODEGEN_BASE_DIR, "");
        	String OUTPUT_DIR = location + baseDir + File.separator+"src" + File.separator+ componentName  + File.separator+ "dto";
        	File destFile = new File(OUTPUT_DIR,capitalizedCompname+ "DTO.hbm.xml");   
            output = new FileOutputStream(destFile);     
            IOUtils.writeLines(allLines,null, output);
        }
        catch(IOException e)
        {
   		    e.printStackTrace(); 
        	System.err.println("\nError found while processing in gHBMFileGenerator.generate() method");
		    throw e ;
        }
        finally
        {
            IOUtils.closeQuietly(output);
        }
		
	}
	

	
	public static void main(String[] args)
	{
		System.out.println("<<Starting the DTO code generator:>>");
		List<FieldDTO> fieldNames = new ArrayList<FieldDTO>();
		FieldDTO fieldDTO = new FieldDTO("name", "String" );
		fieldNames.add(fieldDTO);
		fieldDTO = new FieldDTO("parent", "String" );
		fieldNames.add(fieldDTO);
		
		String propertyName = "name" ;
		String propertyType = "string" ;
		
		String xxx= "<property name=\"" + propertyName + "\" type=\""+ propertyType + "\" column=\"" + propertyName +"\"  /> " ;
		
		try 
		{            			
			//generate(fieldNames, "employee");
			System.out.println("output = "+ xxx);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("<<End the process>>");

	}

}
