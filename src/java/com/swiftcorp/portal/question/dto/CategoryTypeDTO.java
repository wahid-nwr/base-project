package com.swiftcorp.portal.question.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class CategoryTypeDTO extends PersistentCapableDTO
{
	private static final long serialVersionUID = 1L;
	private String categoryName;
	private int categoryId;
	
	public String getCategoryName ( )
	{
		return categoryName;
	}
	
	public void setCategoryName ( String categoryName )
	{
		this.categoryName = categoryName;
	}
	
	public int getCategoryId ( )
	{
		return categoryId;
	}
	
	public void setCategoryId ( int categoryId )
	{
		this.categoryId = categoryId;
	}
	
}
