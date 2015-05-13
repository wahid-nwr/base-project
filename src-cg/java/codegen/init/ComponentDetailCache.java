package codegen.init;

import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import codegen.init.main.EntryList;

public class ComponentDetailCache {
	
	public static String componentName;
	public static String location;
	public static String projectLocation;
	public static EntryList fieldList;
	public static Hashtable fieldMap;
	public static Hashtable fieldDataTypeForClassorHbm;
	public ComponentDetailCache()
	{
		fieldMap = new Hashtable();
		fieldDataTypeForClassorHbm = new Hashtable();
		fieldDataTypeForClassorHbm.put("dto", "String");
		fieldDataTypeForClassorHbm.put("hbm", "string");
		fieldDataTypeForClassorHbm.put("sql", "varchar");
		fieldDataTypeForClassorHbm.put("js", "textfield");
		fieldMap.put("string", fieldDataTypeForClassorHbm);
		
		fieldDataTypeForClassorHbm = new Hashtable();
		fieldDataTypeForClassorHbm.put("dto", "int");
		fieldDataTypeForClassorHbm.put("hbm", "int");
		fieldDataTypeForClassorHbm.put("sql", "int");
		fieldDataTypeForClassorHbm.put("js", "number");
		fieldMap.put("int", fieldDataTypeForClassorHbm);
		
		fieldDataTypeForClassorHbm = new Hashtable();
		fieldDataTypeForClassorHbm.put("dto", "double");
		fieldDataTypeForClassorHbm.put("hbm", "double");
		fieldDataTypeForClassorHbm.put("sql", "double");
		fieldDataTypeForClassorHbm.put("js", "number");
		fieldMap.put("double", fieldDataTypeForClassorHbm);
		
		fieldDataTypeForClassorHbm = new Hashtable();
		fieldDataTypeForClassorHbm.put("dto", "long");
		fieldDataTypeForClassorHbm.put("hbm", "long");
		fieldDataTypeForClassorHbm.put("sql", "bigint");
		fieldDataTypeForClassorHbm.put("js", "number");
		fieldMap.put("long", fieldDataTypeForClassorHbm);
		
		fieldDataTypeForClassorHbm = new Hashtable();
		fieldDataTypeForClassorHbm.put("dto", "float");
		fieldDataTypeForClassorHbm.put("hbm", "double");
		fieldDataTypeForClassorHbm.put("sql", "double");
		fieldDataTypeForClassorHbm.put("js", "number");
		fieldMap.put("float", fieldDataTypeForClassorHbm);
		
		fieldDataTypeForClassorHbm = new Hashtable();
		fieldDataTypeForClassorHbm.put("dto", "Calendar");
		fieldDataTypeForClassorHbm.put("hbm", "calendar");
		fieldDataTypeForClassorHbm.put("sql", "datetime");
		fieldDataTypeForClassorHbm.put("js", "datefield");
		fieldMap.put("calendar", fieldDataTypeForClassorHbm);
		
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public EntryList getFieldList() {
		return fieldList;
	}
	public void setFieldList(EntryList fieldList) {
		this.fieldList = fieldList;
	}
	public static String getProjectLocation() {
		return projectLocation;
	}
	public static void setProjectLocation(String projectLocation) {
		ComponentDetailCache.projectLocation = projectLocation;
	}
}
