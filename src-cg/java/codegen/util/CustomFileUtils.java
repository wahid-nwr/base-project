
package codegen.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import codegen.generators.dto.FieldDTO;


 
public class CustomFileUtils
{
   
    
    public static void generateNewComponent(List<FieldDTO> fieldNames, String componentName,ReplaceDTO replace,String location) throws Exception
    {     	
    	String output_web_location = "";
    	String output_src_location = "";
    	String output_conf_location = "";
    	String output_js_location = "";
       	try
		{
        	File java_src_dir = new File(CodeGenConstants.JAVA_SRC_COMPONENT_DIR,CodeGenConstants.JAVA_SRC_COMPONENT);
        	File web_src_component_dir = new File(CodeGenConstants.WEB_SRC_COMPONENT_DIR, CodeGenConstants.WEB_SRC_COMPONENT);
        	File javascript_src_component_dir = new File(CodeGenConstants.JAVASCRIPT_SRC_DIR);
        	File miscl_configuration_dir = new File(CodeGenConstants.MISC_CONF_DIR);
        	
        	output_web_location = CodeGenConstants.OUTPUT_WEB_DIR;
        	output_src_location = CodeGenConstants.OUTPUT_SRC_DIR;
        	output_conf_location = CodeGenConstants.OUTPUT_CONF_DIR;
        	output_js_location = CodeGenConstants.OUTPUT_JS_DIR;
        	
        	//now replace default scr-cg location to user defined location
        	output_web_location = output_web_location.replaceAll(CodeGenConstants.CODEGEN_BASE_DIR, location);
        	output_src_location = output_src_location.replaceAll(CodeGenConstants.CODEGEN_BASE_DIR, location);
        	output_conf_location = output_conf_location.replaceAll(CodeGenConstants.CODEGEN_BASE_DIR, location);
        	output_js_location = output_js_location.replaceAll(CodeGenConstants.CODEGEN_BASE_DIR, location);
        	
        	File output_dir_src = new File(output_src_location,replace.getTargetAllLowerCase());
        	File output_dir_web = new File(output_web_location,replace.getTargetCapitalizedStr());
        	File output_dir_js = new File(output_js_location);
        	//File output_dir_src = new File(CodeGenConstants.getJAVA_SRC_DIR(replace.getPackageLowercase()),replace.getTargetAllLowerCase());
        	//File output_dir_web = new File(CodeGenConstants.PROJECT_WEB_DIR,replace.getTargetCapitalizedStr());
        	
        	File output_dir_conf = new File(output_conf_location);
        	
        	// generate the web files
    		System.out.println("\nGenerating the web script(JS) component........");
    		copyDirectoryToDirectory(fieldNames,componentName,javascript_src_component_dir, output_dir_js, replace);
    		System.out.println("[Task Completion]::Java web script(JS) components  generation is completed successfully");
        	
    		// generate the web files
    		System.out.println("\nGenerating the web(JSP) component........");
    		copyDirectoryToDirectory(fieldNames,componentName,web_src_component_dir, output_dir_web, replace);
    		System.out.println("[Task Completion]::Java web(JSP) components  generation is completed successfully");

    		// generate the src files
    		System.out.println("\nGenerating the java source component........");
    		copyDirectoryToDirectory(fieldNames,componentName,java_src_dir, output_dir_src, replace);
    		System.out.println("[Task Completion]::Java source components generation is completed successfully");
    		
    		// generate the config files
    		System.out.println("\nGenerating all the configuratins ........");
    		copyDirectoryToDirectory(fieldNames,componentName,miscl_configuration_dir,output_dir_conf, replace);
    		System.out.println("[Task Completion]:: All configuratins generation is completed successfully");    				
		} 
    	catch (Exception e)
		{
    		 System.err.println("\nError found while processing in generateNewComponent() method");
    		 throw e ;
		}    	
    	

    	
    }
    

    /**
     * Copies a directory to within another directory preserving the file dates.
     */
    @SuppressWarnings("unchecked")
	public static void copyDirectoryToDirectory(List<FieldDTO> fieldNames,String componentName,File srcDir, File destDir, ReplaceDTO replaceDTO) 
    throws IOException 
    {
        if (srcDir == null)
        {
            throw new NullPointerException("Source must not be null");
        }
        if (srcDir.exists() && srcDir.isDirectory() == false)
        {
            throw new IllegalArgumentException("Source '" + destDir + "' is not a directory");
        }
        if (destDir == null) 
        {
            throw new NullPointerException("Destination must not be null");
        }
        if (destDir.exists() && destDir.isDirectory() == false)
        {
            throw new IllegalArgumentException("Destination '" + destDir + "' is not a directory");
        }
        
        //copyDirectory(srcDir, new File(destDir, srcDir.getName()), null, replaceDTO);
        List exclusionList = null;
        doCopyDirectory(fieldNames,componentName,srcDir, destDir, null, exclusionList, replaceDTO);
    }

   

 

    /**
     * Internal copy directory method.
     * 
     * @param srcDir  the validated source directory, must not be <code>null</code>
     * @param destDir  the validated destination directory, must not be <code>null</code>
     * @param filter  the filter to apply, null means copy all directories and files
     * @param preserveFileDate  whether to preserve the file date
     * @param exclusionList  List of files and directories to exclude from the copy, may be null
     * @throws IOException if an error occurs
     * @since Commons IO 1.1
     */
    @SuppressWarnings("unchecked")
	private static void doCopyDirectory(List<FieldDTO> fieldNames,String componentName,File srcDir, File destDir, FileFilter filter, List exclusionList , ReplaceDTO replaceDTO) throws IOException
    {
        if (destDir.exists())
        {
            if (destDir.isDirectory() == false) 
            {
                throw new IOException("Destination '" + destDir + "' exists but is not a directory");
            }
        }
        else
        {
            if (destDir.mkdirs() == false)
            {
                throw new IOException("Destination '" + destDir + "' directory cannot be created");
            }
        }
        if (destDir.canWrite() == false) 
        {
            throw new IOException("Destination '" + destDir + "' cannot be written to");
        }
        
        // create a pattern and match and replace 
        Pattern patternCapitalized = Pattern.compile(replaceDTO.getSourceCapitalizedStr());
        Pattern patternLowercase = Pattern.compile(replaceDTO.getSourceAllLowerCase());
        Pattern patternUppercase = Pattern.compile(replaceDTO.getSourceAllUpperCase());
        Matcher matcherCapitalized = null ;
        Matcher matcherLowercase = null ;
        Matcher matcherUppercase = null ;
        String updatedFileName = null ;
        
        Pattern svn_pattern = Pattern.compile(".svn");
        Matcher svn_matcher = null ;
        
        File[] srcFiles = filter == null ? srcDir.listFiles() : srcDir.listFiles(filter);
        if (srcFiles == null) 
        {  // null if security restricted
            throw new IOException("Failed to list contents of " + srcDir);
        }
        
        for (int i = 0; i < srcFiles.length; i++)
        {
        	System.out.println("srcFiles[i].getName()::"+srcFiles[i].getName());
         	// added by monoar to replace file name
         	matcherCapitalized = patternCapitalized.matcher(srcFiles[i].getName());
         	matcherLowercase = patternLowercase.matcher(srcFiles[i].getName());
        	matcherUppercase = patternUppercase.matcher(srcFiles[i].getName());
         	
        	
        	boolean isFound= false ;
        	if(matcherCapitalized.find())
         	{
         		updatedFileName = matcherCapitalized.replaceAll(replaceDTO.getTargetCapitalizedStr());
         		isFound = true ;
         	}         	
         	
        	if(matcherLowercase.find())
         	{
         		updatedFileName = matcherLowercase.replaceAll(replaceDTO.getTargetAllLowerCase());
         		isFound = true ;
         	}
         	if(matcherUppercase.find())
         	{
         		updatedFileName = matcherUppercase.replaceAll(replaceDTO.getTargetAllUpperCase());
         		isFound = true ;
         	} 
            
         	if(!isFound)
         	{
         		updatedFileName = srcFiles[i].getName();
         	}    
     
            File copiedFile = new File(destDir, updatedFileName);
            if (exclusionList == null || !exclusionList.contains(srcFiles[i].getCanonicalPath())) 
            {
                if (srcFiles[i].isDirectory()) 
                {
                    doCopyDirectory(fieldNames,componentName,srcFiles[i], copiedFile, filter, exclusionList, replaceDTO);
                }
                else 
                {
                	svn_matcher = svn_pattern.matcher(copiedFile.getPath());
                	if(!svn_matcher.find())
                 	{
                    	System.out.println("       creating file >> "+ copiedFile.getPath());                		
                		doCopyFile(fieldNames,componentName,srcFiles[i], copiedFile,replaceDTO);
                	}                	
                }
            }
        }
    }
    
    
    
    
    
    public static void doCopyFile(List<FieldDTO> fieldNames,String componentName,File srcFile, File destFile, ReplaceDTO replace) throws IOException 
    {
        if (destFile.exists() && destFile.isDirectory()) 
        {
            throw new IOException("Destination '" + destFile + "' exists but is a directory");
        }       
        
        //search and replace the file
        List<String> replacedLines = getReplacedLines(srcFile,replace);
        String fieldsToAdd = "";
        List<String> linesToAdd = new ArrayList<String>();
        String searchField = "";
        if(srcFile.getName().equals("SamplecomSearchUtils.java"))
        {
        	System.out.println("this is searchutil");
        	for(int i=0;i<replacedLines.size();i++)
        	{
        		String lines = replacedLines.get(i);
        		if(lines.indexOf("String projectSqlQuery = \" SELECT a.componentId\" +")>0)
        		{
        			linesToAdd = new ArrayList<String>();
        			replacedLines.remove(i);
        			System.out.println("lines::"+lines);
        			for(FieldDTO field:fieldNames)
        			{
        				if(fieldsToAdd!= null && !fieldsToAdd.equals("null") && fieldsToAdd.length()>0)
        				{
        					
        				}
        				else
        				{
        					searchField = "a."+field.getName().toLowerCase();
        				}
        				fieldsToAdd += ",a."+field.getName().toLowerCase();
        			}
        			lines = lines.replace("\" +", "");
        			lines = lines+fieldsToAdd+ "\" +" ;
        			linesToAdd.add(lines);
        			replacedLines.addAll(i, linesToAdd);
        			System.out.println("lines::"+lines);
        		}
        		if(lines.indexOf("ArrayList<String> columnHeader = new ArrayList<String>();")>0)
        		{
        			linesToAdd = new ArrayList<String>();
        			for(FieldDTO field:fieldNames)
        			{
        				linesToAdd.add("\n\t\tcolumnHeader.add(\"label."+componentName+"."+field.getName()+"\");");
        			}
        			replacedLines.addAll(i+1, linesToAdd);
        		}
        		if(lines.indexOf("projectSqlQuery += \" WHERE a.uniqueCode like '%\" + searchQueryInput + \"%'\";")>0)
        		{
        			linesToAdd = new ArrayList<String>();
        			linesToAdd.add("projectSqlQuery += \" WHERE "+searchField+ " like '%\" + searchQueryInput + \"%'\";");
        			replacedLines.remove(i);
        			replacedLines.addAll(i,linesToAdd);
        		}
        		if(lines.indexOf("projectSqlQuery += \" ORDER BY a.uniqueCode \" ;")>0)
        		{
        			linesToAdd = new ArrayList<String>();
        			linesToAdd.add("projectSqlQuery += \" ORDER BY "+searchField+ "\" ;");
        			replacedLines.remove(i);
        			replacedLines.addAll(i,linesToAdd);
        		}
        	}
        }
        FileOutputStream output = new FileOutputStream(destFile);           
        try 
        {
            IOUtils.writeLines(replacedLines,null, output);
        }
        finally
        {
            IOUtils.closeQuietly(output);
        }
    }
    
    
    
    public static void doCopyFile(List<String> srcLines, File destFile) throws IOException 
    {
        if (destFile.exists() && destFile.isDirectory()) 
        {
            throw new IOException("Destination '" + destFile + "' exists but is a directory");
        }       
        
        FileOutputStream output = new FileOutputStream(destFile);           
        try 
        {
            IOUtils.writeLines(srcLines,null, output);
        }
        finally
        {
            IOUtils.closeQuietly(output);
        }
    }
    
    /*
     * This method is mainly used to copy java class and to add some dynamic part
     * before closing the last '}' of the class
     */
    
    @SuppressWarnings("unchecked")
	public static List<String> getFileContensWithoutClosingBrace(File srcFile) throws IOException 
    {
         List<String> replacedLines = null ;
         
         try
		 {
        	 replacedLines = FileUtils.readLines(srcFile);
		 }
         catch (Exception e)
		 {
			e.printStackTrace();
		 }
         
         if(replacedLines == null)
         {
        	return replacedLines; 
         }
         
		 int size = replacedLines.size();
		 for(int i = size -1 ; i >=0 ; i--)
		 {
			String line = replacedLines.get(i);
			if(line != null && line.trim().contains("}"))
			{
				replacedLines.remove(i);
				break;
			}
		 }
        return replacedLines;
    }
 
    
    
	@SuppressWarnings("unchecked")
	public static List<String> getReplacedLines( File srcFile, ReplaceDTO replace)
	{
        List<String> contents = new ArrayList<String>();
		try 
		{
			contents = FileUtils.readLines(srcFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
        return getReplacedLines(contents, replace) ;
	}
	
	
	
	public static List<String> getReplacedLines( List<String> inputLines, ReplaceDTO replace)
	{
        List<String> rplacedList = new ArrayList<String>();
        try
        {            
            Pattern patternCapitalized = Pattern.compile(replace.getSourceCapitalizedStr());
            Pattern patternLowercase = Pattern.compile(replace.getSourceAllLowerCase());
            Pattern patternUppercase = Pattern.compile(replace.getSourceAllUpperCase());
            
            Matcher matcherCapitalized = null ;
            Matcher matcherLowercase = null ;
            Matcher matcherUppercase = null ;

            List<String> contents = inputLines ;
            for (String line : contents)
            {
            	if(line == null || line.length()==0)
            	{
            		continue;
            	}
            	matcherCapitalized = patternCapitalized.matcher(line);
            	if(matcherCapitalized.find())
             	{   
            		line = matcherCapitalized.replaceAll(replace.getTargetCapitalizedStr());
             	}  
            	
            	matcherLowercase = patternLowercase.matcher(line);
            	if(matcherLowercase.find())
             	{
            		 line = matcherLowercase.replaceAll(replace.getTargetAllLowerCase());         		
             	}
            	
            	matcherUppercase = patternUppercase.matcher(line);
             	if(matcherUppercase.find())
             	{
             		line = matcherUppercase.replaceAll(replace.getTargetAllUpperCase());             		
             	}             	
            	
            	rplacedList.add(line);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rplacedList ;
	}
	
   
}

