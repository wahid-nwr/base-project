package com.swiftcorp.portal.dcrinfo.dto;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class DcrinfoDTO extends PersistentCapableDTO
{
 
 /**
	 * 
	 */
	private static final long serialVersionUID = 6276221687209298332L;
 private String date = null ;
 private String session = null ;
 private String location = null ;
 private String doctorName = null ;
 private List<DcrProductInfoDTO> productInfoList = new ArrayList<DcrProductInfoDTO>();
 
public DcrinfoDTO(){
	 Date d = new Date();
	 String uniqueCode = Long.toString(d.getTime());
	 setUniqueCode(uniqueCode);
	 setStatus(PersistentCapableDTO.STATUS_ACTIVE);
 }
 
 public String getDate( )
 {
 	 return this.date;
 }
 public String getSession( )
 {
 	 return this.session;
 }
 public String getLocation( )
 {
 	 return this.location;
 }
 public String getDoctorName( )
 {
 	 return this.doctorName;
 }
 
 public void setDate(String date)
 {
 	 this.date = date ;
 }
 public void setSession(String session)
 {
 	 this.session = session ;
 }
 public void setLocation(String location)
 {
 	 this.location = location ;
 }
 public void setDoctorName(String doctorName)
 {
 	 this.doctorName = doctorName ;
 }
 
 public List<DcrProductInfoDTO> getProductInfoList() {
		return productInfoList;
	}

	public void setProductInfoList(List<DcrProductInfoDTO> productInfoList) {
		this.productInfoList = productInfoList;
	}

}
