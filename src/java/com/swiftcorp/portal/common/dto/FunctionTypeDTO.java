package com.swiftcorp.portal.common.dto;

public class FunctionTypeDTO extends PersistentCapableDTO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeName = null;
	private int typeId = 0;
	
	public String getTypeName ( )
	{
		return typeName;
	}
	
	public void setTypeName ( String typeName )
	{
		this.typeName = typeName;
	}
	
	public int getTypeId ( )
	{
		return typeId;
	}
	
	public void setTypeId ( int typeId )
	{
		this.typeId = typeId;
	}
}
