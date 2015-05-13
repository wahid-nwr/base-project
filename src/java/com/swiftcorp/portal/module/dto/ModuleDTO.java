package com.swiftcorp.portal.module.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class ModuleDTO extends PersistentCapableDTO
{
 
 private String moduleCode = null ;
 private String moduleName = null ;
 
 public String getModuleCode( )
 {
 	 return this.moduleCode;
 }
 public String getModuleName( )
 {
 	 return this.moduleName;
 }
 
 public void setModuleCode(String moduleCode)
 {
 	 this.moduleCode = moduleCode ;
 }
 public void setModuleName(String moduleName)
 {
 	 this.moduleName = moduleName ;
 }
 
}
