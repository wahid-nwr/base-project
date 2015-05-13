package com.swiftcorp.portal.covering.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class CoveringDTO extends PersistentCapableDTO
{
 
 private String coveringType = null ;

public String getCoveringType() {
	return coveringType;
}

public void setCoveringType(String coveringType) {
	this.coveringType = coveringType;
}
  
}
