package codegen.generators.dto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import codegen.init.ComponentDetailCache;
import codegen.util.CodeGenConstants;
import codegen.util.ReplaceDTO;

public class DTOFileGenerator 
{

	public static  void generate(List<FieldDTO> fieldNames, ReplaceDTO replace,String location)throws Exception
	{
		try
		{
			List<String> variableNames = new ArrayList<String>();
			List<String> setters = new ArrayList<String>();
			List<String> getters = new ArrayList<String>();
			List<String> allLines = new ArrayList<String>();
			
			String componentName = replace.getComponentName();
			String projectName = replace.getPackageLowercase();
			String fieldType = "";
			String fieldName = "";
			String dtoFieldType = "";
			//ComponentDetailCache componentDetailCache = new ComponentDetailCache();
			Hashtable fieldMap = ComponentDetailCache.fieldMap;
			Hashtable fieldDtl = null;
			
			for(FieldDTO fieldDTO : fieldNames)
			{	
				fieldName = fieldDTO.getName();
				fieldType = fieldDTO.getType();
				
				fieldDtl = (Hashtable)fieldMap.get(fieldType);
				dtoFieldType = (String) fieldDtl.get("dto"); 
				String capitalizedWord = WordUtils.capitalize(fieldName);
				
				if("int".equalsIgnoreCase(fieldType))
				{
					
					variableNames.add(" private " + dtoFieldType+ " " + fieldName + " = 0 ;");		
				}
				if("double".equalsIgnoreCase(fieldType))
				{
					variableNames.add(" private " + dtoFieldType+ " " + fieldName + " = 0 ;");		
				}
				if("long".equalsIgnoreCase(fieldType))
				{
					variableNames.add(" private " + dtoFieldType+ " " + fieldName + " = 0 ;");		
				}
				if("float".equalsIgnoreCase(fieldType))
				{
					variableNames.add(" private " + dtoFieldType+ " " + fieldName + " = 0 ;");		
				}
				if("string".equalsIgnoreCase(fieldType))
				{
					variableNames.add(" private " + dtoFieldType+ " " + fieldName + " = null ;");		
				}				
				if("calendar".equalsIgnoreCase(fieldType))
				{
					variableNames.add(" private " + dtoFieldType+ " " + fieldName + " = null ;");		
				}
				else if(fieldDTO.isUserDefinedDTO())
				{
					variableNames.add(" private " + fieldDTO.getType() + " " + fieldDTO.getName() + " = new "+ fieldDTO.getDataType()+ "();");		
				}			
				
				// setter
				setters.add(" public void set" + capitalizedWord + "(" +dtoFieldType + " " + fieldName+ ")");
				setters.add(" {");
				setters.add(" \t this." + fieldName + " = " + fieldName + " ;");
				setters.add(" }");
				
				// getter
				getters.add(" public " + dtoFieldType + " get" + capitalizedWord + "( )");
				getters.add(" {");
				getters.add(" \t return this." + fieldName + ";");
				getters.add(" }");						
			}
			
			allLines.add("package com." + CodeGenConstants.COMPANY_NAME + "." + projectName + "."+ componentName.toLowerCase()+ ".dto;");
			allLines.add(" ");
			allLines.add("import java.util.Calendar;");
			allLines.add("import com." + CodeGenConstants.COMPANY_NAME + "." + projectName +".common.dto.PersistentCapableDTO;");
		
			allLines.add("public class " + WordUtils.capitalize(componentName) + "DTO extends PersistentCapableDTO");
			allLines.add("{");
			allLines.add(" ");
			allLines.addAll(variableNames);
			allLines.add(" ");
			allLines.addAll(getters);
			allLines.add(" ");
			allLines.addAll(setters);
			allLines.add(" ");
			allLines.add("}");
			
			FileOutputStream output = null ;   
	        try 
	        {
	        	String baseDir = CodeGenConstants.OUTPUT_BASE_DIR;
	        	baseDir = baseDir.replaceAll(CodeGenConstants.CODEGEN_BASE_DIR, "");
	        	String DTO_OUTPUT_DIR = location + baseDir + File.separator + "src" + File.separator + componentName  + File.separator+ "dto";
	        	File destFile = new File(DTO_OUTPUT_DIR,WordUtils.capitalize(componentName)+ "DTO.java");   
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
		catch (Exception e)
		{
	  		 System.err.println("\nError found while processing in DTOFileGenerator.generate() method");
			 throw e ;	
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
		fieldDTO = new FieldDTO("age", "double" );
		fieldNames.add(fieldDTO);
		fieldDTO = new FieldDTO("status", "int" );
		fieldNames.add(fieldDTO);
		fieldDTO = new FieldDTO("sex", "String" );
		fieldNames.add(fieldDTO);

					
		try 
		{            			
			//generate(fieldNames, "Xyz", "avrs");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("<<End the process>>");

	}

}
