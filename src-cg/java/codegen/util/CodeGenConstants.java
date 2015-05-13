package codegen.util;

import java.io.File;

import codegen.init.ComponentDetailCache;

public class CodeGenConstants 
{
	
	public static final String PROJECT_WEB_DIR = "web";
	public static final String PROJECT_SRC_DIR = "src"+ File.separator+"java";		
	public static final String PROJECT_CONF_DIR = "src"+ File.separator+"conf";
	public static final String PROJECT_WEB_INF_DIR = PROJECT_WEB_DIR + File.separator +"WEB-INF";

	public static final String CODEGEN_BASE_DIR = "src-cg";
	// reference component name
	public static final String COMPONENT_NAME = "dcrReport";
	
	public static final String PROJECT_NAME = "portal";
	// Company name
	public static final String COMPANY_NAME = "swiftcorp";
	
	public static final String JAVA_SRC_COMPONENT = "samplecom";
	public static final String WEB_SRC_COMPONENT = "Samplecom";
	public static final String REFERENCE_COMPONENT = "Samplecom";
	
	// directory		
	public static final String INPUT_BASE_DIR = CODEGEN_BASE_DIR + File.separator+"input";	
	public static final String JAVA_SRC_COMPONENT_DIR = INPUT_BASE_DIR + File.separator + "classes";
	public static final String WEB_SRC_COMPONENT_DIR = INPUT_BASE_DIR+ File.separator+"jsp";
	public static final String MISC_CONF_DIR = INPUT_BASE_DIR+ File.separator+"conf";
	public static final String JAVASCRIPT_SRC_DIR = INPUT_BASE_DIR+ File.separator+"js";
	
	public static final String TEMPLATE_BASE_DIR = CODEGEN_BASE_DIR + File.separator+"input"+ File.separator+"template";
	public static final String JSP_TEMPLATE_DIR = TEMPLATE_BASE_DIR + File.separator+"jsp" ;
	public static final String HBM_TEMPLATE_DIR = TEMPLATE_BASE_DIR + File.separator + "hbm" ;
	public static final String JAVA_TEMPLATE_DIR = TEMPLATE_BASE_DIR + File.separator + "java" ;
	public static final String JAVASCRIPT_TEMPLATE_DIR = TEMPLATE_BASE_DIR + File.separator+"js" ;
	
	//test output dir	
	public static final String OUTPUT_BASE_DIR = CODEGEN_BASE_DIR + File.separator+"output";
	public static final String OUTPUT_SRC_DIR = OUTPUT_BASE_DIR + File.separator+"src";
	public static final String OUTPUT_CONF_DIR = OUTPUT_BASE_DIR+ File.separator+"conf";
	public static final String OUTPUT_WEB_DIR = OUTPUT_BASE_DIR + File.separator+"web" ;
	public static final String OUTPUT_JS_DIR = OUTPUT_BASE_DIR + File.separator+"js" ;

	
	public static final int FAILED=0 ;
	public static final int SUCCESS=1 ;
	
	public static String getJAVA_SRC_COMMON_DIR(String packageName)
	{
		 String dir = projectDir()+PROJECT_SRC_DIR + File.separator+"com" + File.separator+CodeGenConstants.COMPANY_NAME+ File.separator + packageName + File.separator+"common";	
         return dir ;
	}
	
	public static String getJAVA_SRC_DIR(String packageName)
	{
		 String dir = PROJECT_SRC_DIR + File.separator+"com" + File.separator+ CodeGenConstants.COMPANY_NAME + File.separator + packageName ;	
         return dir ;
	}
	
	public static String projectDir()
	{
		String dir = ComponentDetailCache.projectLocation+File.separator;
		
		return dir;
	}

}
