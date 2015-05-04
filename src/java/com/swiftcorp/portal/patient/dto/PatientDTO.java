package com.swiftcorp.portal.patient.dto;
 
import java.util.Calendar;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class PatientDTO extends PersistentCapableDTO
{
 
 private String patientId = null ;
 private String name = null ;
 private Calendar birthDate = null ;
 private int age = 0 ;
 private String mobileNo = null ;
 private String email = null ;
 private String address = null ;
 
 public String getPatientId( )
 {
 	 return this.patientId;
 }
 public String getName( )
 {
 	 return this.name;
 }
 public Calendar getBirthDate( )
 {
 	 return this.birthDate;
 }
 public int getAge( )
 {
 	 return this.age;
 }
 public String getMobileNo( )
 {
 	 return this.mobileNo;
 }
 public String getEmail( )
 {
 	 return this.email;
 }
 public String getAddress( )
 {
 	 return this.address;
 }
 
 public void setPatientId(String patientId)
 {
 	 this.patientId = patientId ;
 }
 public void setName(String name)
 {
 	 this.name = name ;
 }
 public void setBirthDate(Calendar birthDate)
 {
 	 this.birthDate = birthDate ;
 }
 public void setAge(int age)
 {
 	 this.age = age ;
 }
 public void setMobileNo(String mobileNo)
 {
 	 this.mobileNo = mobileNo ;
 }
 public void setEmail(String email)
 {
 	 this.email = email ;
 }
 public void setAddress(String address)
 {
 	 this.address = address ;
 }
 
}
