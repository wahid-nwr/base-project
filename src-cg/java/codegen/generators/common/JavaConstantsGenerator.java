package codegen.generators.common;

import java.io.File;
import java.util.List;

import codegen.util.CodeGenConstants;
import codegen.util.CustomFileUtils;
import codegen.util.ReplaceDTO;
import codegen.util.ReplaceDTOMaker;

public class JavaConstantsGenerator
{
	
	public static File readFile(String dirStr, String fileName) throws Exception
	{
		System.out.println("readFile(): Enter");
		System.out.println("readFile(): path = "+ dirStr);
		System.out.println("readFile(): name = "+ fileName);
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
		System.out.println("readFile(): Exit");
		return file ;
	}
	
	
	
	
	public static void generateSessionKeys( ReplaceDTO replace)throws Exception 
	{
		try 
		{
            // read the input file
			String dir = CodeGenConstants.TEMPLATE_BASE_DIR + File.separator + "java";
			File sessionKeyInput = new File(dir, "SessionsKeys.cfg");
			List<String> newLines = CustomFileUtils.getReplacedLines(sessionKeyInput, replace);
		    
			// read the output file
			dir = CodeGenConstants.getJAVA_SRC_COMMON_DIR(replace.getPackageLowercase()) + File.separator + "web";
			File sessionOutputFile =  readFile(dir,"SESSION_KEYS.java") ; 
			List<String> totalLines = CustomFileUtils.getFileContensWithoutClosingBrace(sessionOutputFile);
			
			//add the new lines
			totalLines.addAll(newLines);
			totalLines.add("\n }");
			
		  // write the output file	
			CustomFileUtils.doCopyFile(totalLines,sessionOutputFile);
		}
		catch (Exception e)
		{
			System.out.println("generate()- xml path  not found or cannot read the file: "+ e );
			e.printStackTrace();
			throw e ;
		}
		
	}
	

	
	
	public static void generateForwardNames( ReplaceDTO replace)throws Exception 
	{
		try 
		{
            // read the input file
			String dir = CodeGenConstants.TEMPLATE_BASE_DIR + File.separator + "java";
			File sessionKeyInput = new File(dir, "ForwardNames.cfg");
			List<String> newLines = CustomFileUtils.getReplacedLines(sessionKeyInput, replace);
		    
			// read the output file
			dir = CodeGenConstants.getJAVA_SRC_COMMON_DIR(replace.getPackageLowercase())+ File.separator + "web";
			File sessionOutputFile =  readFile(dir,"ForwardNames.java") ; 
			List<String> totalLines = CustomFileUtils.getFileContensWithoutClosingBrace(sessionOutputFile);
			
			//add the new lines
			totalLines.addAll(newLines);
			totalLines.add("\n }");
			
		  // write the output file	
			CustomFileUtils.doCopyFile(totalLines,sessionOutputFile);
		}
		catch (Exception e)
		{
			System.out.println("generate()- xml path  not found or cannot read the file: "+ e );
			e.printStackTrace();
			throw e ;
		}
		
	}
	

	
	
	
	public static void main(String[] args)
	{
		try
		{
			ReplaceDTO data = ReplaceDTOMaker.prepareReplaceDTO("teleshop","cas");
			System.out.println("Starting application.....");
			JavaConstantsGenerator.generateForwardNames(data);
		}
		catch (Exception e)
		{
			System.out.println("error ::" +e);
		}
		System.out.println("Finished!!!...........!!!");
	}

}
