package com.swiftcorp.portal.dcrreport.dto;
 
import java.util.Date;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class DcrReportDTO extends PersistentCapableDTO
{
 
 /**
	 * 
	 */
	private static final long serialVersionUID = -2136275687502357144L;
 private String month = null ;
 private float target = 0.0f;
 private float achievement = 0.0f;
 
  public DcrReportDTO(){
	 Date d = new Date();
	 String uniqueCode = Long.toString(d.getTime());
	 setUniqueCode(uniqueCode);
	 setStatus(PersistentCapableDTO.STATUS_ACTIVE);
  }
 public String getMonth( )
 {
 	 return this.month;
 }
 public float getTarget( )
 {
 	 return this.target;
 }
 public float getAchievement( )
 {
 	 return this.achievement;
 }
 
 public void setMonth(String month)
 {
 	 this.month = month ;
 }
 public void setTarget(float target)
 {
 	 this.target = target ;
 }
 public void setAchievement(float achievement)
 {
 	 this.achievement = achievement ;
 }
 
 public float getPercentage(){
	 if(achievement > 0){
		 return (achievement * 100) / target;
	 }else{
		 return 0.0f;
	 }
 }
}
