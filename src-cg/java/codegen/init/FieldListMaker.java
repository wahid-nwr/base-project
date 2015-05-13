package codegen.init;

import java.util.ArrayList;
import java.util.List;

import com.swiftcorp.portal.common.web.SESSION_KEYS;


import codegen.generators.dto.FieldDTO;
import codegen.init.main.Entry;
import codegen.init.main.EntryList;

public class FieldListMaker
{

	public static List<FieldDTO> getTestFieldList()
	{
		List<FieldDTO> fieldList = new ArrayList<FieldDTO>();
		fieldList.add(new FieldDTO("name", "String",FieldDTO.HTML_TEXT,true));
		fieldList.add(new FieldDTO("number", "double",FieldDTO.HTML_TEXT,true));
		return fieldList ;
	}
	
	public static List<FieldDTO> getXXXFieldList()
	{
		List<FieldDTO> fieldList = new ArrayList<FieldDTO>();
		//fieldList.add(new FieldDTO("lifecycleStatus", "String"));
		//fieldList.add(new FieldDTO("nextAction", "String"));
		//fieldList.add(new FieldDTO("nextActionDate", "Calendar"));
		//fieldList.add(new FieldDTO("closingDate", "Calendar"));
		
		//fieldList.add(new FieldDTO("customer", "CustomerDTO", true,"com.swiftcorp.portal.customer.dto",FieldDTO.MAPPING_MANY_TO_ONE, FieldDTO.HTML_COMBO,SESSION_KEYS.CUSTOMER_LIST));
		//fieldList.add(new FieldDTO("updatedBy", "UserDTO", true,"com.swiftcorp.portal.user.dto",FieldDTO.MAPPING_MANY_TO_ONE, FieldDTO.HTML_COMBO,SESSION_KEYS.USER_LIST));
		//fieldList.add(new FieldDTO("assignedTo", "UserDTO", true,"com.swiftcorp.portal.user.dto",FieldDTO.MAPPING_MANY_TO_ONE, FieldDTO.HTML_COMBO, true,SESSION_KEYS.USER_LIST));

		return fieldList ;
	}
	
	public static List<FieldDTO> getRoleFieldList()
	{
		List<FieldDTO> fieldList = new ArrayList<FieldDTO>();
		fieldList.add(new FieldDTO("accessLevel", "int",FieldDTO.HTML_COMBO,SESSION_KEYS.ACCESS_LEVEL_LIST,true));		
		return fieldList ;
	}
	
	public static List<FieldDTO> getGroupFieldList()
	{
		List<FieldDTO> fieldList = new ArrayList<FieldDTO>();
		fieldList.add(new FieldDTO("parentGroupId", "Long",FieldDTO.HTML_COMBO,SESSION_KEYS.ACCESS_LEVEL_LIST,true));		
		//fieldList.add(new FieldDTO("customer", "CustomerDTO", true,"com.swiftcorp.portal.customer.dto",FieldDTO.MAPPING_MANY_TO_ONE, FieldDTO.HTML_COMBO,SESSION_KEYS.CUSTOMER_LIST));
		//fieldList.add(new FieldDTO("updatedBy", "UserDTO", true,"com.swiftcorp.portal.user.dto",FieldDTO.MAPPING_MANY_TO_ONE, FieldDTO.HTML_COMBO,SESSION_KEYS.USER_LIST));
		fieldList.add(new FieldDTO("assignedTo", "UserDTO", true,"com.swiftcorp.portal.user.dto",FieldDTO.MAPPING_MANY_TO_ONE, FieldDTO.HTML_COMBO, true,SESSION_KEYS.USER_LIST));

		return fieldList ;
	}
	
	public static List<FieldDTO> getBizCodeList()
	{
		List<FieldDTO> fieldList = new ArrayList<FieldDTO>();
//		fieldList.add(new FieldDTO("catagory", "String"));
		fieldList.add(new FieldDTO("code", "String"));
		fieldList.add(new FieldDTO("name", "String"));
//		fieldList.add(new FieldDTO("systemCode", "String"));
//		fieldList.add(new FieldDTO("bdBankCode", "String"));
		
		return fieldList ;
	}
	
	public static List<FieldDTO> getQuestionPropertyList()
	{
		List<FieldDTO> fieldList = new ArrayList<FieldDTO>();

		fieldList.add(new FieldDTO("id", "String"));
		fieldList.add(new FieldDTO("name", "String"));

		
		return fieldList ;
	}

	public static List<FieldDTO> getDCRInfoList() {
		List<FieldDTO> fieldList = new ArrayList<FieldDTO>();

		fieldList.add(new FieldDTO("date", "String"));
		fieldList.add(new FieldDTO("session", "String"));
		fieldList.add(new FieldDTO("location", "String"));
		fieldList.add(new FieldDTO("doctorName", "String"));
		
		return fieldList ;
	}

	public static List<FieldDTO> getDCRProductInfoList() {
		List<FieldDTO> fieldList = new ArrayList<FieldDTO>();

		fieldList.add(new FieldDTO("productCode", "String"));
		fieldList.add(new FieldDTO("productType", "String"));
		fieldList.add(new FieldDTO("quantity", "int"));
		
		return fieldList ;
	}

	public static List<FieldDTO> getDCRReportList() 
	{
		List<FieldDTO> fieldList = new ArrayList<FieldDTO>();

		fieldList.add(new FieldDTO("month", "String"));
		fieldList.add(new FieldDTO("target", "float"));
		fieldList.add(new FieldDTO("achievement", "float"));
		
		return fieldList ;
	}
	
	public static List<FieldDTO> getComponentList(EntryList fieldDetailList) 
	{
		List<FieldDTO> fieldList = new ArrayList<FieldDTO>();
		List<Entry> entries = fieldDetailList.getEntries();
		String fieldName = "";
		String fieldType = "";
		for (Entry e : entries) {
			fieldName = e.getTextField().getText();
			fieldType = e.getComboBox().getSelectedItem().toString();
			fieldList.add(new FieldDTO(fieldName, fieldType));
        }
				
		return fieldList ;
	}

}
