/**
 * 
 */
package com.swiftcorp.portal.common.dao;

import java.util.ArrayList;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author asraf
 * 
 */
public class CommonDAOUtil extends HibernateDaoSupport
{
	public long getMaxComponentId ( String dtoName )
	{
		long componentId = 0;
		try
		{
			String queryStr = "select max(componentId) from " + dtoName;
			System.out.println ( "queryStr::" + queryStr );
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( queryStr );
			if ( list != null && list.size () > 0 )
			{
				componentId = Long.parseLong ( "" + list.get ( 0 ) );
				System.out.println ( "max componet id " + list.get ( 0 ) );
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			// throw new SystemException ( e );
		}
		
		return componentId;
	}
	
	public long getHHIdFromMotherBenId ( String motherId )
	{
		long householdId = 0;
		try
		{
			String queryStr = "select householdId from HouseholdMemberDTO householdMemberDTO where householdMemberDTO.beneficiaryId = " + motherId;
			System.out.println ( "queryStr::" + queryStr );
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( queryStr );
			if ( list != null && list.size () > 0 )
			{
				householdId = Long.parseLong ( "" + list.get ( 0 ) );
				System.out.println ( "householdId " + list.get ( 0 ) );
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			// throw new SystemException ( e );
		}
		
		return householdId;
	}
}
