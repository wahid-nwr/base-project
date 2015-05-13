package codegen.generators.javascript;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;

import codegen.generators.dto.FieldDTO;
import codegen.generators.jsp.JSPFileGenerator;
import codegen.init.ComponentDetailCache;
import codegen.util.CodeGenConstants;
import codegen.util.CustomFileUtils;
import codegen.util.ReplaceDTO;

public class JavascriptFileGenerator {

	//static File jsp_start_dir = new File(CodeGenConstants.JSP_TEMPLATE_DIR,"JspStart.jsp");
	//static File jsp_body_text_dir = new File(CodeGenConstants.JSP_TEMPLATE_DIR, "JspTextBody.jsp");
	//static File jsp_body_combo_dir = new File(CodeGenConstants.JSP_TEMPLATE_DIR, "JspComboboxBody.jsp");
	//static File jsp_end_dir = new File(CodeGenConstants.JSP_TEMPLATE_DIR, "JspEnd.jsp");
	
	static File componentLoadScriptTemplateDir = new File(CodeGenConstants.JAVASCRIPT_TEMPLATE_DIR, "samplecomLoad.js");
	static String loadScriptPath = CodeGenConstants.PROJECT_WEB_DIR + File.separator + "Common" + File.separator + "JavaScript"+File.separator+"config-js";	                    
	static File projectLoadScriptPath = new File(loadScriptPath, "load.js");

	static File mainSampleJSFileDir = new File(CodeGenConstants.JAVASCRIPT_TEMPLATE_DIR,"samplecomMain.js");
	static File sampleFormJSFileDir = new File(CodeGenConstants.JAVASCRIPT_TEMPLATE_DIR,"samplecomForm.js");
	static File sampleFormFieldsJSFileDir = new File(CodeGenConstants.JAVASCRIPT_TEMPLATE_DIR,"samplecomFormFields.js");
	static File sampleGridJSFileDir = new File(CodeGenConstants.JAVASCRIPT_TEMPLATE_DIR,"samplecomGrid.js");
	static File sampleSearchJSFileDir = new File(CodeGenConstants.JAVASCRIPT_TEMPLATE_DIR,"samplecomSearchField.js");
	static File sampleStoreJSFileDir = new File(CodeGenConstants.JAVASCRIPT_TEMPLATE_DIR,"samplecomStore.js");
	static File sampleGridColumnsJSFileDir = new File(CodeGenConstants.JAVASCRIPT_TEMPLATE_DIR,"samplecomGetColumns.js");
	static File sampleFormLoadJSFileDir = new File(CodeGenConstants.JAVASCRIPT_TEMPLATE_DIR,"samplecomLoadColumns.js");
	
	@SuppressWarnings("unchecked")
	public static  void generate(List<FieldDTO> fieldNames, String componentName,String location)throws Exception
	{
		List<String> startJspLines = new ArrayList<String>();
		List<String> endJspLines = new ArrayList<String>();
		List<String> allBodyJspLines = new ArrayList<String>();
		List<String> allLines = new ArrayList<String>();
		
		List<String> textBodyJspLines = new ArrayList<String>();
		List<String> comboBodyJspLines = new ArrayList<String>();
		
		List<String> mainSampleJSFile = new ArrayList<String>();
		List<String> sampleFormJSFile = new ArrayList<String>();
		List<String> sampleFormFieldsJSFile = new ArrayList<String>();
		List<String> sampleGridJSFile = new ArrayList<String>();
		List<String> sampleSearchJSFile = new ArrayList<String>();
		List<String> sampleStoreJSFile = new ArrayList<String>();
		List<String> sampleGridColumnsJSFile = new ArrayList<String>();
		List<String> sampleFormLoadColumnsJSFile = new ArrayList<String>();
		
		try 
		{
			//startJspLines = FileUtils.readLines(jsp_start_dir);
			//endJspLines = FileUtils.readLines(jsp_end_dir);
			
			// body
			//textBodyJspLines = FileUtils.readLines(jsp_body_text_dir);
			//comboBodyJspLines = FileUtils.readLines(jsp_body_combo_dir);
			
			mainSampleJSFile = FileUtils.readLines(mainSampleJSFileDir);
			sampleFormJSFile = FileUtils.readLines(sampleFormJSFileDir);
			sampleFormFieldsJSFile = FileUtils.readLines(sampleFormFieldsJSFileDir);
			sampleGridJSFile = FileUtils.readLines(sampleGridJSFileDir);
			sampleSearchJSFile = FileUtils.readLines(sampleSearchJSFileDir);
			sampleStoreJSFile = FileUtils.readLines(sampleStoreJSFileDir);
			sampleGridColumnsJSFile = FileUtils.readLines(sampleGridColumnsJSFileDir);
			sampleFormLoadColumnsJSFile = FileUtils.readLines(sampleFormLoadJSFileDir);
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
		
		mainSampleJSFile = CustomFileUtils.getReplacedLines(mainSampleJSFile,replace);
		sampleFormJSFile = CustomFileUtils.getReplacedLines(sampleFormJSFile,replace);
		sampleFormFieldsJSFile = CustomFileUtils.getReplacedLines(sampleFormFieldsJSFile,replace);
		sampleGridColumnsJSFile = CustomFileUtils.getReplacedLines(sampleGridColumnsJSFile,replace);
		sampleGridJSFile = CustomFileUtils.getReplacedLines(sampleGridJSFile,replace);
		sampleSearchJSFile = CustomFileUtils.getReplacedLines(sampleSearchJSFile,replace);
		sampleStoreJSFile = CustomFileUtils.getReplacedLines(sampleStoreJSFile,replace);
		sampleFormLoadColumnsJSFile = CustomFileUtils.getReplacedLines(sampleFormLoadColumnsJSFile,replace);
		
		List<String> formFields = new ArrayList<String>();
		List<String> gridColumns = new ArrayList<String>();
		List<String> formLoadColumns = new ArrayList<String>();
		List<String> storeItems = new ArrayList<String>();
		String formField = "";
		for(int i=0;sampleFormFieldsJSFile!=null && i<sampleFormFieldsJSFile.size();i++)
		{
			String line = sampleFormFieldsJSFile.get(i);
			if(line.indexOf("fieldSet")>0)
			{		
				sampleFormFieldsJSFile.remove(i);
				formFields.add("\n\t\t{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }");
				//System.out.println(line+line.length());
				for(FieldDTO fieldDTO:fieldNames)
				{
					formField = generateField(fieldDTO.getName(),fieldDTO.getType());
					if(formFields.size()>0)
					{
						formField = "\n\t\t\t"+formField;
					}
					formFields.add(formField);
				}
				line = line.substring(0, line.indexOf("["))+formFields.toString()+line.substring(line.indexOf("]")+1,line.length());
				sampleFormFieldsJSFile.add(i, line);
				break;
			}
		}
		String gridColumn= "";
		for(int i=0;sampleGridColumnsJSFile!=null && i<sampleGridColumnsJSFile.size();i++)
		{
			String line = sampleGridColumnsJSFile.get(i);
			if(line.indexOf("columns")>0)
			{		
				sampleGridColumnsJSFile.remove(i);
				//System.out.println(line+line.length());				
				for(FieldDTO fieldDTO:fieldNames)
				{
					gridColumn = getGridColumn(fieldDTO.getName(),fieldDTO.getType());
					if(gridColumns.size()>0)
					{
						gridColumn = "\n\t\t\t"+gridColumn;
					}
					gridColumns.add(gridColumn);
				}
				//gridColumns.add("\n\t\t\t"+"itemDeleter");
				line = line.substring(0, line.indexOf("["))+gridColumns.toString()+line.substring(line.indexOf("]")+1,line.length());
				sampleGridColumnsJSFile.add(i, line);
				break;
			}
		}
		String formLoadColumn = "";
		for(int i=0;sampleFormLoadColumnsJSFile!=null && i<sampleFormLoadColumnsJSFile.size();i++)
		{
			String line = sampleFormLoadColumnsJSFile.get(i);
			if(line.indexOf("{")>-1)
			{		
				//sampleGridColumnsJSFile.remove(i);
				//System.out.println(line+line.length());
				formLoadColumns.add("form.findField('componentId').setValue(row.data.componentId);");
				for(FieldDTO fieldDTO:fieldNames)
				{
					formLoadColumn = getFormLoadOfColumn(fieldDTO.getName(),fieldDTO.getType());
					formLoadColumn = "\t"+formLoadColumn;					
					formLoadColumns.add(formLoadColumn);
				}
				//line = line.substring(0, line.indexOf("["))+formLoadColumns.toString()+line.substring(line.indexOf("]")+1,line.length());
				sampleFormLoadColumnsJSFile.addAll(i+1, formLoadColumns);
				break;
			}
		}
		
		String storeLines= "";
		for(int i=0;sampleStoreJSFile!=null && i<sampleStoreJSFile.size();i++)
		{
			String line = sampleStoreJSFile.get(i);
			if(line.indexOf("fields: ['componentId']")>-1)
			{		
				//sampleGridColumnsJSFile.remove(i);
				//System.out.println(line+line.length());
				sampleStoreJSFile.remove(i);
				line = line.replace("]", "");
				storeItems.add(line);
				for(FieldDTO fieldDTO:fieldNames)
				{
					formLoadColumn = fieldDTO.getName().toLowerCase();
					formLoadColumn = ",'"+formLoadColumn+"'";					
					storeItems.add(formLoadColumn);
				}
				storeItems.add("]");
				//line = line.substring(0, line.indexOf("["))+formLoadColumns.toString()+line.substring(line.indexOf("]")+1,line.length());
				sampleStoreJSFile.addAll(i, storeItems);
				break;
			}
		}
		
		
		allLines.addAll(mainSampleJSFile);
		allLines.addAll(sampleFormJSFile);
		allLines.addAll(sampleFormFieldsJSFile);
		allLines.addAll(sampleGridJSFile);
		allLines.addAll(sampleGridColumnsJSFile);
		allLines.addAll(sampleFormLoadColumnsJSFile);
		allLines.addAll(sampleSearchJSFile);
		allLines.addAll(sampleStoreJSFile);
		System.out.println("Start and end jsp is replaced complete");
		/*
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
		 */
		/*for(String line : allLines)
		{
			System.out.println(line);
		}*/
		//allLines.addAll(startJspLines);
		//allLines.addAll(allBodyJspLines);
		//allLines.addAll(endJspLines);
		
		FileOutputStream output = null ;   
        try 
        {
           	//String OUTPUT_DIR = CodeGenConstants.OUTPUT_BASE_DIR + File.separator+"web" + File.separator+ WordUtils.capitalize(componentName)  + File.separator+ "Modify";
        	String OUTPUT_DIR = location + File.separator + "output" + File.separator + "js";
        	File destFile = new File(OUTPUT_DIR,WordUtils.capitalize(componentName)+ ".js");             
            output = new FileOutputStream(destFile);     
            IOUtils.writeLines(allLines,null, output);
            System.out.println("\nGenerating load script");
            generateLoadScriptComponentLink(replace);
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
	public static String generateField(String fieldName,String dataType)throws Exception
	{
		String fieldDescription = "";
		//{ xtype: 'textfield',name:'code', fieldLabel: 'Code' },
		if(dataType.equals("string"))
		{
			fieldDescription = "{ xtype: 'textfield',name:'"+fieldName+"', fieldLabel: '"+fieldName+"' }";
		}
		else if(dataType.equals("calendar"))
		{
			fieldDescription = "{ xtype: 'datefield',name:'"+fieldName+"',fieldLabel: '"+fieldName+"',emptyText:'Enter Date',format:'Y-n-d H:i',readOnly:true}";
		}
		else if(dataType.equals("textarea"))
		{
			fieldDescription = "{ xtype: 'textarea',name:'"+fieldName+"', fieldLabel: '"+fieldName+"',width:100 }";
		}
		else if(dataType.equals("long")||dataType.equals("int")||dataType.equals("double")||dataType.equals("float"))
		{
			fieldDescription = "{ xtype: 'numberfield',name:'"+fieldName+"',fieldLabel: '"+fieldName+"',emptyText:'0'}";
		}
		return fieldDescription;
	}
	
	@SuppressWarnings("unchecked")
	public static String getGridColumn(String fieldName,String dataType)throws Exception
	{
		String columnDescription = "";
		//{ xtype: 'textfield',name:'code', fieldLabel: 'Code' },
		columnDescription = "{header: '"+fieldName+"', width: 120, dataIndex: '"+fieldName.toLowerCase()+"', sortable: true}";
		
		return columnDescription;
	}
	
	@SuppressWarnings("unchecked")
	public static String getFormLoadOfColumn(String fieldName,String dataType)throws Exception
	{
		String columnDescription = "";		
		//{ xtype: 'textfield',name:'code', fieldLabel: 'Code' },
		columnDescription = "form.findField('"+fieldName+"').setValue(row.data."+fieldName.toLowerCase()+");";
		
		return columnDescription;
	}
	
	@SuppressWarnings("unchecked")
	public static  void generateLoadScriptComponentLink(ReplaceDTO replace)throws Exception
	{
		System.out.println("<<generateLeftbarComponentLink()Enter:>>");
		FileOutputStream output = null ;  
		try 
		{
			List<String> projectLoadScriptLInes = new ArrayList<String>();
			List<String> componentLoadScriptLines = new ArrayList<String>();
			
			projectLoadScriptLInes = FileUtils.readLines(projectLoadScriptPath);
			componentLoadScriptLines = CustomFileUtils.getReplacedLines(componentLoadScriptTemplateDir,replace);
			
			for(String line:componentLoadScriptLines)
			{
				System.out.println(line);
			}
			
			for(int i=0;projectLoadScriptLInes!=null && i<projectLoadScriptLInes.size();i++)
			{
				String line = projectLoadScriptLInes.get(i);
				System.out.println(line);
				//leftbarJspLines.addAll(componentLinkJspLines);
				if(line.indexOf("//add load script here")>-1) 
				{
					projectLoadScriptLInes.addAll(i, componentLoadScriptLines);
					break;
				}
			}

			String OUTPUT_DIR = loadScriptPath ;
        	File destFile = new File(OUTPUT_DIR, "load.js"); 
        	if(destFile.exists())
        	{
        		destFile.delete();
        	}
        	
        	destFile = new File(OUTPUT_DIR, "load.js"); 
            output = new FileOutputStream(destFile);     
            IOUtils.writeLines(projectLoadScriptLInes,null, output);
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
	
	
	
	public static void main(String[] args)
	{
		System.out.println("<<Starting the DTO code generator:>>");
		List<FieldDTO> fieldNames = new ArrayList<FieldDTO>();
		FieldDTO fieldDTO = new FieldDTO("lastName", "string" );
		fieldNames.add(fieldDTO);
		fieldDTO = new FieldDTO("firstName", "string" );
		fieldNames.add(fieldDTO);
		fieldDTO = new FieldDTO("password", "string" );
		fieldNames.add(fieldDTO);
		fieldDTO = new FieldDTO("confirmPassword", "string" );
		fieldNames.add(fieldDTO);
		fieldDTO = new FieldDTO("groupId", "long" );
		fieldDTO = new FieldDTO("email", "string" );
		fieldDTO = new FieldDTO("uniqueCode", "string" );
		fieldNames.add(fieldDTO);
					
		try 
		{     
			//generateLoadScriptComponentLink(JSPFileGenerator.getReplaceDTO("test"));
			generate(fieldNames, "user", "/home/wahid/generated");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("<<End the process>>");

	}
	
	public static List<FieldDTO> getCommonFieldList()
	{
		List<FieldDTO> fieldList = new ArrayList<FieldDTO>();
		fieldList.add(new FieldDTO("uniqueCode", "String"));
		fieldList.add(new FieldDTO("description", "String"));
		return fieldList ;

	}
}
