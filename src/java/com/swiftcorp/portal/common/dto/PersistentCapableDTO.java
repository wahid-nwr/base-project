/*
 * @ (#) PersistentCapableDTO.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */

package com.swiftcorp.portal.common.dto;

import java.util.Calendar;
import java.util.Date;

import com.swiftcorp.portal.common.util.StringMaker;

/**
 * Common class for all dtos. All the value classes which will ultimately go to the database 
 * must extend this class. The attribute componentId is treated as the primary key. 
 * The version attribute is used to handle the optimistic locking policy programatically. 
 * Only EntityBeans are supposed to set it. Any client must not set the version explicitly. Otherwise
 * unexpected error can be generated.
 * 
 */

/**
 * @author monoar
 * @since Sep 4, 2009
 */
public class PersistentCapableDTO extends GenericDTO
{
	private static final long serialVersionUID = 1L;
	// status 0 means no status fixed
	public static final int STATUS_INACTIVE = 0;
	public static final int STATUS_ACTIVE = 1;
	
	protected Long componentId;
	protected String uniqueCode;
	protected Calendar updatedDate;
	protected String description;
	protected int status = STATUS_ACTIVE;
	protected Long updatedBy;
	
	public Long getUpdatedBy ( )
	{
		return updatedBy;
	}
	
	public void setUpdatedBy ( Long updatedBy )
	{
		this.updatedBy = updatedBy;
	}
	
	/* to be added later */
	// private String componentName;
	public Long getComponentId ( )
	{
		return componentId;
	}
	
	public void setComponentId ( Long componentId )
	{
		this.componentId = componentId;
	}
	
	public String getDescription ( )
	{
		return description;
	}
	
	public void setDescription ( String description )
	{
		this.description = description;
	}
	
	public int getStatus ( )
	{
		return status;
	}
	
	public void setStatus ( int status )
	{
		this.status = status;
	}
	
	public String getUniqueCode ( )
	{
		return uniqueCode;
	}
	
	public void setUniqueCode ( String uniqueCode )
	{
		this.uniqueCode = uniqueCode;
	}
	
	public Calendar getUpdatedDate ( )
	{
		return updatedDate;
	}
	
	public void setUpdatedDate ( Calendar updatedDate )
	{
		this.updatedDate = updatedDate;
	}
	
	@Override
	public String toString ( )
	{
		StringMaker stringMaker = StringMaker.getInstance ();
		stringMaker.addToBuffer ( "componentId = " );
		stringMaker.addToBuffer ( componentId );
		stringMaker.addToBuffer ( " ,uniqueCode = " );
		stringMaker.addToBuffer ( uniqueCode );
		stringMaker.addToBuffer ( " ,updatedDate = " );
		
		/*
		 * We don't want to use long date information that comes from
		 * calendar.toString. so converting it to Date which have much better
		 * toString implementation
		 */
		Date cd = null;
		if ( updatedDate != null )
		{
			cd = updatedDate.getTime ();
		}
		
		stringMaker.addToBuffer ( cd );
		stringMaker.addToBuffer ( " ,description = " );
		stringMaker.addToBuffer ( description );
		stringMaker.addToBuffer ( " ,status = " );
		stringMaker.addToBuffer ( status );
		
		return stringMaker.getBufferValue ();
	}
	
	/**
	 * All the subclass will have this equals. However, It is our policy that
	 * only the componentId field will determine the equality of a
	 * PersistentCapableDTO or it's subclasses.
	 * 
	 * NOTE: there is a utility library in apache commons for equality. consider
	 * using it.
	 */
	@Override
	public boolean equals ( Object o )
	{
		if ( o == null )
			return false;
		if ( !(o instanceof PersistentCapableDTO) )
		{
			return false;
		}
		
		PersistentCapableDTO that = (PersistentCapableDTO) o;
		// return (this.getComponentId () == null) ? (that.getComponentId () ==
		// null)
		if ( (this.getComponentId () != null) && (that.getComponentId () != null) )
		{
			return (this.getComponentId ().equals ( that.getComponentId () ));
		}
		
		return false;
		
	}
	
	/**
	 * All the subclass will have this hashcode. But this is inefficient. we
	 * might write a more efficient code if necessary.
	 * 
	 * NOTE: why didn't we used the id?? think, is the id immutable?? can we
	 * write an efficient hashcode without an immutable object?? may be we could
	 * use the class name, thats the best we could have done.
	 * 
	 * SEE: equals & hashcode contract of jvm.
	 */
	@Override
	public int hashCode ( )
	{
		return 7; // this is a valid hashcode. All the objects of it's subclass
					// will have this hash which is communication server
					// specific
	}
}
