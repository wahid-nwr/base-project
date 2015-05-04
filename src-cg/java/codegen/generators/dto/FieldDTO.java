package codegen.generators.dto;

public class FieldDTO 
{
 public static final int HTML_TEXT = 1;
 public static final int HTML_COMBO = 2;
 public static final int HTML_CHECKBOX = 3;
 public static final int HTML_RADIO = 4;
 
 public static final String MAPPING_MANY_TO_ONE = "many-to-one";
 public static final String MAPPING_MANY_TO_MANY = "many-to-many";
 public static final String MAPPING_ONE_TO_ONE = "one-to-one";
 public static final String MAPPING_ONE_TO_MANY = "one-to-many";
 
  
 private String name = null ;
 private String dataType = null ;
 private boolean isUserDefinedDTO = false ;
 private String packageStr = null ;
 private String mappingType = null ;
 private int htmlInputType = HTML_TEXT;
 private String htmlComboboxDataList = null ;
 private boolean validationReq = false ;
 
 public String getHtmlComboboxDataList()
{
	return htmlComboboxDataList;
}

public void setHtmlComboboxDataList(String htmlComboboxDataList)
{
	this.htmlComboboxDataList = htmlComboboxDataList;
}

public FieldDTO()
 {
	// empty constructor 
 }
 
 public FieldDTO(String name, String type)
 {
	this.name = name ;
	this.dataType = type ;
	this.htmlInputType = HTML_TEXT;
 }
 
 public FieldDTO(String name, String type,int htmlInputType, boolean isvalidation)
 {
	this.name = name ;
	this.dataType = type ;
	this.htmlInputType = htmlInputType;
	this.validationReq = isvalidation ;
 }
 
 public FieldDTO(String name, String type,int htmlInputType, String dataList,boolean isvalidation)
 {
	this.name = name ;
	this.dataType = type ;
	this.htmlInputType = htmlInputType;
	this.htmlComboboxDataList = dataList ;	
	this.validationReq = isvalidation ;
 }
  
 /**
  * For DTO type field
  */
 public FieldDTO(String name, String type, boolean isDTO, String packageStr, String mappingType, int htmlInputType, String dataList)
 {
	this.name = name ;
	this.dataType = type ;
	this.htmlInputType = HTML_TEXT;
	this.isUserDefinedDTO = isDTO ;
	this.packageStr = packageStr ;
	this.mappingType = mappingType ;
	this.htmlInputType = htmlInputType;
	this.htmlComboboxDataList = dataList ;
	
 }
 
 public FieldDTO(String name, String type, boolean isDTO, String packageStr, String mappingType, int htmlInputType, boolean isvalidation, String dataList)
 {
	this.name = name ;
	this.dataType = type ;
	this.htmlInputType = HTML_TEXT;
	this.isUserDefinedDTO = isDTO ;
	this.packageStr = packageStr ;
	this.mappingType = mappingType ;
	this.htmlInputType = htmlInputType;
	this.htmlComboboxDataList = dataList ;
	this.validationReq = isvalidation ;
	
 }
 
 public FieldDTO(String name, String type, int htmlInputType)
 {
	this.name = name ;
	this.dataType = type ;
	this.htmlInputType = htmlInputType;
 }

public String getName()
{
	return name;
}

public void setName(String name)
{
	this.name = name;
}

public String getType() 
{
	return dataType;
}

public void setType(String type)
{
	this.dataType = type;
}

public int getHtmlInputType()
{
	return htmlInputType;
}

public void setHtmlInputType(int htmlInputType)
{
	this.htmlInputType = htmlInputType;
}

public String getDataType()
{
	return dataType;
}
public String getFullQualifiedDataType()
{
	if(isUserDefinedDTO && packageStr != null)
	{
		dataType = packageStr + "." + dataType ;	
	}
	return dataType;
}

public void setDataType(String dataType)
{
	this.dataType = dataType;
}

public boolean isUserDefinedDTO()
{
	return isUserDefinedDTO;
}

public void setUserDefinedDTO(boolean isUserDefinedDTO)
{
	this.isUserDefinedDTO = isUserDefinedDTO;
}

public String getMappingType()
{
	return mappingType;
}

public void setMappingType(String mappingType)
{
	this.mappingType = mappingType;
}

public String getPackageStr()
{
	return packageStr;
}

public void setPackageStr(String packageStr)
{
	this.packageStr = packageStr;
}

public boolean isValidationReq()
{
	return validationReq;
}

public void setValidationReq(boolean validationReq)
{
	this.validationReq = validationReq;
}
 
}
