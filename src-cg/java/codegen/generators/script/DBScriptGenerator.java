package codegen.generators.script;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import codegen.generators.dto.FieldDTO;
import codegen.util.CodeGenConstants;

public class DBScriptGenerator
{


	public static  void generate(List<FieldDTO> fieldNames, String componentName, String location)throws Exception
	{
		List<String> fieldLines = new ArrayList<String>();
		for(FieldDTO field : fieldNames)
		{
			// skip List/ArrayList/Set/Map/HashMap
	        if(  field.getType().startsWith("List") || field.getType().startsWith("ArrayList")
	           ||field.getType().startsWith("Map") || field.getType().startsWith("HashMap")		
	        )
	        {
	        	continue ;
	        }
	        
			String typeStr = "VARCHAR(100)";
	        if(field.getType().equalsIgnoreCase("long"))
	        {
	        	typeStr = "BIGINT";
	        }
	        else if(field.getType().equalsIgnoreCase("int"))
	        {
	        	typeStr = "INT";
	        }
	        else if(field.getType().equalsIgnoreCase("double"))
	        {
	        	typeStr = "DOUBLE";
	        }
	        else if(field.getType().equalsIgnoreCase("boolean"))
	        {
	        	typeStr = "TINYINT";
	        }
	        else if(field.getType().equalsIgnoreCase("string"))
	        {
	        	typeStr = "VARCHAR (250)";
	        }
	        else if(field.getType().equalsIgnoreCase("long"))
	        {
	        	typeStr = "BIGINT";
	        }
	        else if(field.getType().equalsIgnoreCase("Calendar"))
	        {
	        	typeStr = "datetime";
	        }

			
	        fieldLines.add("\t " + field.getName().toLowerCase() +" " + typeStr + ",");
		}
		
		
		List<String> allLines = new ArrayList<String>();
        allLines.add("#--------------------------------------------------------#");
        allLines.add("### Copy this section into the defaultscript.sql file ###");
        allLines.add("#--------------------------------------------------------#");
        allLines.add("\n");
        allLines.add("drop table if exists " + componentName + " ;");
        allLines.add("create table " + componentName );
        allLines.add("(");
        allLines.add("\t componentId BIGINT not null,");
        allLines.add("\t uniqueCode VARCHAR(50),");
        
        allLines.addAll(fieldLines);
        
        allLines.add("\t status INT not null,");
        allLines.add("\t description VARCHAR(250),");        
        allLines.add("\t updateddate datetime default NULL,");
        allLines.add("\t updatedby BIGINT default null,");
        allLines.add("\t primary key (componentId)");
        allLines.add(");");
        
        
		FileOutputStream output = null ;   
        try 
        {
        	String outputDir = location + File.separator + CodeGenConstants.OUTPUT_CONF_DIR;
        	outputDir = outputDir.replaceAll(File.separator + CodeGenConstants.CODEGEN_BASE_DIR, "");
        	File destFile = new File(outputDir, (componentName +"-dbscript.sql"));   
            output = new FileOutputStream(destFile);     
            IOUtils.writeLines(allLines,null, output);
        }
        catch(Exception e)
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
	

}
