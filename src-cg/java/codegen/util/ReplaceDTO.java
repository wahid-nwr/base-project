package codegen.util;

public class ReplaceDTO 
{
	
	private String packageLowercase = null ;
	private String componentName = null ;
	
	private String sourceCapitalizedStr = null ;
	private String targetCapitalizedStr = null ;
	
	private String sourceAllLowerCase = null ;
	private String targetAllLowerCase = null ;
	
	private String sourceAllUpperCase = null ;
	private String targetAllUpperCase = null ;
	
	
	
	public String getSourceAllLowerCase()
	{
		return sourceAllLowerCase;
	}
	public void setSourceAllLowerCase(String sourceAllLowerCase)
	{
		this.sourceAllLowerCase = sourceAllLowerCase;
	}
	public String getSourceAllUpperCase()
	{
		return sourceAllUpperCase;
	}
	public void setSourceAllUpperCase(String sourceAllUpperCase)
	{
		this.sourceAllUpperCase = sourceAllUpperCase;
	}
	public String getSourceCapitalizedStr()
	{
		return sourceCapitalizedStr;
	}
	public void setSourceCapitalizedStr(String sourceCapitalizedStr)
	{
		this.sourceCapitalizedStr = sourceCapitalizedStr;
	}
	public String getTargetAllLowerCase()
	{
		return targetAllLowerCase;
	}
	public void setTargetAllLowerCase(String targetAllLowerCase)
	{
		this.targetAllLowerCase = targetAllLowerCase;
	}
	public String getTargetAllUpperCase()
	{
		return targetAllUpperCase;
	}
	public void setTargetAllUpperCase(String targetAllUpperCase)
	{
		this.targetAllUpperCase = targetAllUpperCase;
	}
	public String getTargetCapitalizedStr()
	{
		return targetCapitalizedStr;
	}
	public void setTargetCapitalizedStr(String targetCapitalizedStr)
	{
		this.targetCapitalizedStr = targetCapitalizedStr;
	}
	public String getComponentName()
	{
		return componentName;
	}
	
	public String getComponentNameLowercase()
	{
		return componentName.toLowerCase();
	}
	
	public void setComponentName(String componentName)
	{
		this.componentName = componentName;
	}
	public String getPackageLowercase()
	{
		return packageLowercase;
	}
	public void setPackageLowercase(String packageLowercase)
	{
		this.packageLowercase = packageLowercase;
	}
	


}
