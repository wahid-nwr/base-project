package com.swiftcorp.portal.algorithm.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.algorithm.AlgQuestionSuccessResult;
import com.swiftcorp.portal.algorithm.AlgorithmSuccessResult;
import com.swiftcorp.portal.algorithm.dto.AlgQuestionDTO;
import com.swiftcorp.portal.algorithm.dto.AlgorithmDTO;
import com.swiftcorp.portal.common.dto.DTOConstants;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.question.dto.QDTO;

public class AlgorithmHibernateDAOImpl extends HibernateDaoSupport implements IAlgorithmDAO
{
	protected static final Log log = LogFactory.getLog ( AlgorithmHibernateDAOImpl.class );
	
	public AlgorithmDTO get ( Long componentId ) throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		AlgorithmDTO algorithmDTO = null;
		try
		{
			algorithmDTO = (AlgorithmDTO) getHibernateTemplate ().get ( AlgorithmDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return algorithmDTO;
	}
	
	public AlgQuestionDTO getAlgQuestionByQQId ( QDTO qdto ) throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): qDTOId = " + qdto );
		AlgQuestionDTO algQuestionDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from AlgQuestionDTO algQuestionDTO where algQuestionDTO.qdto=?", qdto );
			if ( list.size () > 0 )
			{
				algQuestionDTO = (AlgQuestionDTO) list.get ( 0 );
			}			
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return algQuestionDTO;
	}
	
	public AlgorithmDTO get ( String algId ) throws SystemException
	{
		log.info ( "get(code): Enter" );
		log.info ( "get(code): algId = " + algId );
		AlgorithmDTO algorithmDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from AlgorithmDTO algorithmDTO where algorithmDTO.algId=?", algId );
			if ( list.size () > 0 )
			{
				algorithmDTO = (AlgorithmDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.error ( "get(String algId): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(algId): Exit" );
		return algorithmDTO;
	}
	
	public ArrayList<AlgorithmDTO> getList ( ) throws SystemException
	{
		return getList ( null, AlgorithmSortBy.algId );
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<AlgorithmDTO> getList ( Long groupId, AlgorithmSortBy sortby )
			throws SystemException
	{
		log.info ( "getList: Enter" );
		log.info ( "getList: sortby = " + sortby );
		
		ArrayList<AlgorithmDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT algorithmDTO FROM AlgorithmDTO algorithmDTO WHERE algorithmDTO.algStatus != "+DTOConstants.INACTIVE_ALGORITHM_STATUS );
		if ( groupId != null )
		{
			queryStr.append ( " AND algorithmDTO.groupId=" + groupId );
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
	
	public AlgorithmSuccessResult add ( AlgorithmDTO algorithmDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		AlgorithmSuccessResult successResult = new AlgorithmSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( algorithmDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( algorithmDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(AlgorithmDTO algorithmDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public AlgQuestionSuccessResult addAlgQuestion ( AlgQuestionDTO algQuestionDTO )
	throws SystemException
	{
		log.info ( "addAlgQuestion(): Enter" );
		
		AlgQuestionSuccessResult successResult = new AlgQuestionSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( algQuestionDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( algQuestionDTO );
		}
		catch (Exception e)
		{
			log.debug ( "addAlgQuestion(AlgQuestionDTO algQuestionDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "addAlgQuestion(): Exit" );
		return successResult;
	}
	
	public AlgQuestionSuccessResult modifyAlgQuestion ( AlgQuestionDTO algQuestionDTO )
	throws SystemException
	{
		log.info ( "modifyAlgQuestion(): Enter" );
		
		AlgQuestionSuccessResult successResult = new AlgQuestionSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update( algQuestionDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( algQuestionDTO );
		}
		catch (Exception e)
		{
			log.debug ( "modifyAlgQuestion(AlgQuestionDTO algQuestionDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "modifyAlgQuestion(): Exit" );
		return successResult;
	}
	public AlgQuestionSuccessResult removeAlgQuestion ( AlgQuestionDTO algQuestionDTO )
	throws SystemException
	{
		log.info ( "removeAlgQuestion(): Enter" );
		
		AlgQuestionSuccessResult successResult = new AlgQuestionSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete( algQuestionDTO );
			successResult.setMessage ( "Deleted Successfully." );
			successResult.setOperationResult ( algQuestionDTO );
		}
		catch (Exception e)
		{
			log.debug ( "removeAlgQuestion(AlgQuestionDTO algQuestionDTO): Failed to delete." + e );
			throw new SystemException ( e );
		}
		log.info ( "removeAlgQuestion(): Exit" );
		return successResult;
	}
	
	public AlgorithmSuccessResult saveAlgQuestion ( AlgQuestionDTO algQuestionDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		AlgorithmSuccessResult successResult = new AlgorithmSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( algQuestionDTO );
			successResult.setMessage ( "Added Successfully." );
			// successResult.setOperationResult( algQuestionDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(AlgorithmDTO algorithmDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public AlgorithmSuccessResult modify ( AlgorithmDTO algorithmDTO )
			throws SystemException
	{
		log.info ( "modify(): Enter" );
		AlgorithmSuccessResult successResult = new AlgorithmSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( algorithmDTO );
			successResult.setMessage ( "Modified Successfully." );
			successResult.setOperationResult ( algorithmDTO );
		}
		catch (Exception e)
		{
			log.debug ( "modify(AlgorithmDTO algorithmDTO): Failed to modify.", e );
			throw new SystemException ( e );
		}
		log.info ( "modify(): Exit" );
		return successResult;
	}
	
	public AlgorithmSuccessResult remove ( AlgorithmDTO algorithmDTO )
			throws SystemException
	{
		log.info ( "remove(): Enter" );
		AlgorithmSuccessResult successResult = new AlgorithmSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete ( algorithmDTO );
			successResult.setMessage ( "removed Successfully." );
			successResult.setOperationResult ( algorithmDTO );
		}
		catch (Exception e)
		{
			log.debug ( "remove(AlgorithmDTO algorithmDTO): Failed to remove." + e );
			throw new SystemException ( e );
		}
		log.info ( "remove(): Exit" );
		return successResult;
	}
	
	private String getSortByStr ( AlgorithmSortBy sortBy )
	{
		// default value
		String resultStr = "algorithmDTO.aldId";
		if ( sortBy == AlgorithmSortBy.algId )
		{
			resultStr = "algorithmDTO.algId";
		}
		else if ( sortBy == AlgorithmSortBy.firstName )
		{
			resultStr = "algorithmDTO.firstName";
		}
		else if ( sortBy == AlgorithmSortBy.lastname )
		{
			resultStr = "algorithmDTO.lastName";
		}
		return resultStr;
		
	}
}
