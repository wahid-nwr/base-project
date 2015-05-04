package com.swiftcorp.portal.dcrinfo.dto;
 
import java.util.Date;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class DcrProductInfoDTO extends PersistentCapableDTO
{
 
 /**
	 * 
	 */
 private static final long serialVersionUID = -8803195056835001357L;
 
 private DcrinfoDTO dcrinfoDTO;
 private String productCode = null ;
 private String productType = null ;
 private int quantity = 0 ;
 
 public DcrProductInfoDTO(){
	 Date d = new Date();
	 String uniqueCode = Long.toString(d.getTime());
	 setUniqueCode(uniqueCode);
	 setStatus(PersistentCapableDTO.STATUS_ACTIVE);
 }
 
 public String getProductCode( )
 {
 	 return this.productCode;
 }
 public String getProductType( )
 {
 	 return this.productType;
 }
 public int getQuantity( )
 {
 	 return this.quantity;
 }
 
 public void setProductCode(String productCode)
 {
 	 this.productCode = productCode ;
 }
 public void setProductType(String productType)
 {
 	 this.productType = productType ;
 }
 public void setQuantity(int quantity)
 {
 	 this.quantity = quantity ;
 }

 public DcrinfoDTO getDcrinfoDTO() {
		return dcrinfoDTO;
	}

	public void setDcrinfoDTO(DcrinfoDTO dcrinfoDTO) {
		this.dcrinfoDTO = dcrinfoDTO;
	}
	
@Override
public String toString()
{
	String productInfo = "";
	productInfo += " Product Id : "+productCode+",";
	productInfo += " Product Type : "+productType+",";
	productInfo += " Quantity : "+quantity;
	return productInfo;
	
}

}
