package com.swiftcorp.portal.generalprofile.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class GeneralprofileDTO extends PersistentCapableDTO
{
 
private String generalProfileCode = null; 
 private String supplyChainActor = null ;
 private Calendar dateOfFGD = null ;
 
 public String getGeneralProfileCode() {
	return generalProfileCode;
}
public void setGeneralProfileCode(String generalProfileCode) {
	this.generalProfileCode = generalProfileCode;
}
public String getSupplyChainActor( )
 {
 	 return this.supplyChainActor;
 }
 public Calendar getDateOfFGD( )
 {
 	 return this.dateOfFGD;
 }
 
 public void setSupplyChainActor(String supplyChainActor)
 {
 	 this.supplyChainActor = supplyChainActor ;
 }
 public void setDateOfFGD(Calendar dateOfFGD)
 {
 	 this.dateOfFGD = dateOfFGD ;
 }
 
}
