package com.swiftcorp.portal.household.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.household.FamilyPlanningInfoSuccessResult;
import com.swiftcorp.portal.household.HouseholdMemberSuccessResult;
import com.swiftcorp.portal.household.HouseholdMemberVisitSuccessResult;
import com.swiftcorp.portal.household.HouseholdSuccessResult;
import com.swiftcorp.portal.household.dto.FamilyPlanningInfoDTO;
import com.swiftcorp.portal.household.dto.HouseholdDTO;
import com.swiftcorp.portal.household.dto.HouseholdMemberDTO;
import com.swiftcorp.portal.household.dto.HouseholdMemberVisitDTO;

public class HouseholdHibernateDAOImpl extends HibernateDaoSupport implements IHouseholdDAO
{
	protected static final Log log = LogFactory.getLog ( HouseholdHibernateDAOImpl.class );
	
	public HouseholdDTO get ( Long componentId ) throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		HouseholdDTO householdDTO = null;
		try
		{
			householdDTO = (HouseholdDTO) getHibernateTemplate ().get ( HouseholdDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return householdDTO;
	}
	
	public HouseholdMemberDTO getHouseholdMemberByMemberId ( String beneficiaryId )
			throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + beneficiaryId );
		HouseholdMemberDTO householdMemberDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from HouseholdMemberDTO householdMemberDTO " + " where householdMemberDTO.beneficiaryId=?", "" + beneficiaryId );
			if ( list.size () > 0 )
			{
				householdMemberDTO = (HouseholdMemberDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return householdMemberDTO;
	}
	
	public HouseholdDTO get ( String householdId ) throws SystemException
	{
		log.info ( "get(code): Enter" );
		log.info ( "get(code): code = " + householdId );
		HouseholdDTO householdDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from HouseholdDTO householdDTO where householdDTO.houseNo=?", householdId );
			if ( list.size () > 0 )
			{
				householdDTO = (HouseholdDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.error ( "get(String uniqueCode): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(code): Exit" );
		return householdDTO;
	}
	
	public HouseholdDTO getHouseholdByHouseholdId ( String householdId )
			throws SystemException
	{
		log.info ( "get(householdId): Enter" );
		
		HouseholdDTO householdDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from HouseholdDTO householdDTO where householdDTO.houseNo=?", householdId );
			if ( list.size () > 0 )
			{
				householdDTO = (HouseholdDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.error ( "get(householdId): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(householdId): Exit" );
		return householdDTO;
	}
	
	public ArrayList<HouseholdDTO> getList ( ) throws SystemException
	{
		return getList ( null, HouseholdSortBy.uniqueCode );
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<HouseholdDTO> getList ( Long groupId, HouseholdSortBy sortby )
			throws SystemException
	{
		log.info ( "getList: Enter" );
		log.info ( "getList: sortby = " + sortby );
		
		ArrayList<HouseholdDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT householdDTO FROM HouseholdDTO householdDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE householdDTO.groupId=" + groupId );
		}
		queryStr.append ( " ORDER BY " );
		queryStr.append ( getSortByStr ( sortby ) );
		try
		{
			result = (ArrayList) getHibernateTemplate ().find ( queryStr.toString () );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		log.info ( "getList: Exit" );
		return result;
	}
	
	public HouseholdSuccessResult add ( HouseholdDTO householdDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		HouseholdSuccessResult successResult = new HouseholdSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( householdDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( householdDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(HouseholdDTO householdDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public HouseholdSuccessResult modify ( HouseholdDTO householdDTO )
			throws SystemException
	{
		log.info ( "modify(): Enter" );
		HouseholdSuccessResult successResult = new HouseholdSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( householdDTO );
			successResult.setMessage ( "Modified Successfully." );
			successResult.setOperationResult ( householdDTO );
		}
		catch (Exception e)
		{
			log.debug ( "modify(HouseholdDTO householdDTO): Failed to modify.", e );
			throw new SystemException ( e );
		}
		log.info ( "modify(): Exit" );
		return successResult;
	}
	
	public FamilyPlanningInfoSuccessResult addFamilyPlanningInfo ( FamilyPlanningInfoDTO familyPlanningInfoDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		FamilyPlanningInfoSuccessResult successResult = new FamilyPlanningInfoSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( familyPlanningInfoDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( familyPlanningInfoDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(FamilyPlanningInfoDTO familyPlanningInfoDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	// modify
	public FamilyPlanningInfoSuccessResult modifyFamilyPlanningInfo ( FamilyPlanningInfoDTO familyPlanningInfoDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		FamilyPlanningInfoSuccessResult successResult = new FamilyPlanningInfoSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( familyPlanningInfoDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( familyPlanningInfoDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(FamilyPlanningInfoDTO familyPlanningInfoDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public HouseholdMemberSuccessResult addHouseholdMember ( HouseholdMemberDTO householdMemberDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		HouseholdMemberSuccessResult successResult = new HouseholdMemberSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( householdMemberDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( householdMemberDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(HouseholdMemberDTO householdMemberDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	// modify
	public HouseholdMemberSuccessResult modifyHouseholdMember ( HouseholdMemberDTO householdMemberDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		HouseholdMemberSuccessResult successResult = new HouseholdMemberSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( householdMemberDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( householdMemberDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(HouseholdMemberDTO householdMemberDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public HouseholdMemberVisitSuccessResult addHouseholdMemberVisit ( HouseholdMemberVisitDTO householdMemberVisitDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		HouseholdMemberVisitSuccessResult successResult = new HouseholdMemberVisitSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( householdMemberVisitDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( householdMemberVisitDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(HouseholdMemberVisitDTO householdMemberVisitDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	// modify
	public HouseholdMemberVisitSuccessResult modifyHouseholdMemberVisit ( HouseholdMemberVisitDTO householdMemberVisitDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		HouseholdMemberVisitSuccessResult successResult = new HouseholdMemberVisitSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( householdMemberVisitDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( householdMemberVisitDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(HouseholdMemberVisitDTO householdMemberVisitDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public HouseholdSuccessResult remove ( HouseholdDTO householdDTO )
			throws SystemException
	{
		log.info ( "remove(): Enter" );
		HouseholdSuccessResult successResult = new HouseholdSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete ( householdDTO );
			successResult.setMessage ( "removed Successfully." );
			successResult.setOperationResult ( householdDTO );
		}
		catch (Exception e)
		{
			log.debug ( "remove(HouseholdDTO householdDTO): Failed to remove." + e );
			throw new SystemException ( e );
		}
		log.info ( "remove(): Exit" );
		return successResult;
	}
	
	private String getSortByStr ( HouseholdSortBy sortBy )
	{
		// default value
		String resultStr = "householdDTO.uniqueCode";
		if ( sortBy == HouseholdSortBy.uniqueCode )
		{
			resultStr = "householdDTO.uniqueCode";
		}
		else if ( sortBy == HouseholdSortBy.firstName )
		{
			resultStr = "householdDTO.firstName";
		}
		else if ( sortBy == HouseholdSortBy.lastname )
		{
			resultStr = "householdDTO.lastName";
		}
		return resultStr;
		
	}
}
