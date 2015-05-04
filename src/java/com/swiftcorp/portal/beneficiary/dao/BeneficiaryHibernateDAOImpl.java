package com.swiftcorp.portal.beneficiary.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.beneficiary.BeneficiarySuccessResult;
import com.swiftcorp.portal.beneficiary.dao.IBeneficiaryDAO.BeneficiarySortBy;
import com.swiftcorp.portal.beneficiary.dto.BeneficiaryDTO;
import com.swiftcorp.portal.common.exception.SystemException;

public class BeneficiaryHibernateDAOImpl extends HibernateDaoSupport implements IBeneficiaryDAO
{
	protected static final Log log = LogFactory.getLog ( BeneficiaryHibernateDAOImpl.class );
	
	public BeneficiaryDTO get ( Long componentId ) throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		BeneficiaryDTO beneficiaryDTO = null;
		try
		{
			beneficiaryDTO = (BeneficiaryDTO) getHibernateTemplate ().get ( BeneficiaryDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return beneficiaryDTO;
	}
	
	public BeneficiaryDTO getBeneficiaryByBenId ( String beneficiaryId )
			throws SystemException
	{
		log.info ( "getBeneficiaryByBenId(code): Enter" );
		
		BeneficiaryDTO beneficiaryDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from BeneficiaryDTO beneficiaryDTO where beneficiaryDTO.beneficiaryId=?", beneficiaryId );
			if ( list.size () > 0 )
			{
				beneficiaryDTO = (BeneficiaryDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.error ( "get(String beneficiaryId): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(code): Exit" );
		return beneficiaryDTO;
	}
	
	public ArrayList<BeneficiaryDTO> getList ( ) throws SystemException
	{
		return getList ( null, BeneficiarySortBy.beneficiaryId );
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<BeneficiaryDTO> getList ( Long groupId, BeneficiarySortBy sortby )
			throws SystemException
	{
		log.info ( "getList: Enter" );
		log.info ( "getList: sortby = " + sortby );
		
		ArrayList<BeneficiaryDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT beneficiaryDTO FROM BeneficiaryDTO beneficiaryDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE beneficiaryDTO.groupId=" + groupId );
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
	
	public BeneficiarySuccessResult modifyBeneficiary ( BeneficiaryDTO beneficiaryDTO )
			throws SystemException
	{
		log.info ( "update(): Enter" );
		
		BeneficiarySuccessResult successResult = new BeneficiarySuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( beneficiaryDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( beneficiaryDTO );
		}
		catch (Exception e)
		{
			log.debug ( "update(ChildBeneficiaryDTO childBeneficiaryDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "update(): Exit" );
		return successResult;
	}
	
	public BeneficiarySuccessResult addBeneficiary ( BeneficiaryDTO beneficiaryDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		BeneficiarySuccessResult successResult = new BeneficiarySuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( beneficiaryDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( beneficiaryDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(MotherBeneficiaryDTO motherBeneficiaryDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	private String getSortByStr ( BeneficiarySortBy sortBy )
	{
		// default value
		String resultStr = "beneficiaryDTO.beneficiaryId";
		if ( sortBy == BeneficiarySortBy.beneficiaryId )
		{
			resultStr = "beneficiaryDTO.beneficiaryId";
		}
		
		return resultStr;
		
	}
	
}
