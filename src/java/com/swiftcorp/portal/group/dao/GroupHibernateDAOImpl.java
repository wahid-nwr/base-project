/*
 * @ (#) GroupHibernateDAOImpl.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.group.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.exception.UniqueCodeRequiredException;
import com.swiftcorp.portal.common.util.ValidationUtil;
import com.swiftcorp.portal.group.GroupSuccessResult;
import com.swiftcorp.portal.group.dto.GroupDTO;
import com.swiftcorp.portal.group.exception.GroupCreationException;
import com.swiftcorp.portal.group.exception.GroupForThisCodeNotFoundException;
import com.swiftcorp.portal.group.exception.GroupWithSameCodeAlreadyExistsException;
import com.swiftcorp.portal.group.exception.InvalidParentException;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class GroupHibernateDAOImpl extends HibernateDaoSupport implements IGroupDAO
{
	
	private static final Log theLogger = LogFactory.getLog ( GroupHibernateDAOImpl.class );
	
	public GroupDTO get ( Long componentId )
			throws BusinessRuleViolationException
	{
		if ( componentId == null )
		{
			throw new RuntimeException ( "componentId IS NULL...." );
		}
		
		theLogger.info ( "get(Long componentId):: componentId=" + componentId );
		HibernateTemplate hibernateTemplate = getHibernateTemplate ();
		GroupDTO groupDTO = (GroupDTO) hibernateTemplate.get ( GroupDTO.class, componentId );
		if ( groupDTO == null )
		{
			throw new BusinessRuleViolationException ();
		}
		
		theLogger.debug ( "get(Long componentId):: groupDTO=\n" + groupDTO );
		
		return groupDTO;
	}
	
	public GroupDTO get ( String unicodeCode )
			throws GroupForThisCodeNotFoundException
	{
		if ( ValidationUtil.isEmpty ( unicodeCode ) )
		{
			throw new RuntimeException ( "unicodeCode IS NULL or EMPTY...." );
		}
		
		theLogger.info ( "get(Long unicodeCode):: unicodeCode=" + unicodeCode );
		ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from GroupDTO groupDTO where groupDTO.uniqueCode=?", unicodeCode );
		
		GroupDTO groupDTO = null;
		if ( list != null )
		{
			groupDTO = (GroupDTO) list.get ( 0 );
		}
		
		if ( groupDTO == null )
		{
			throw new GroupForThisCodeNotFoundException ();
		}
		
		theLogger.debug ( "get(Long componentId):: groupDTO=\n" + groupDTO );
		
		return groupDTO;
	}
	
	public ArrayList<GroupDTO> getList ( ) throws SystemException
	{
		theLogger.info ( "getList: Enter" );
		
		ArrayList<GroupDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT groupDTO FROM GroupDTO groupDTO" );
		try
		{
			result = (ArrayList) getHibernateTemplate ().find ( queryStr.toString () );
			theLogger.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		theLogger.info ( "getList: Exit" );
		return result;
	}
	
	public ArrayList<GroupDTO> getList ( String groupId )
			throws SystemException
	{
		theLogger.info ( "getList: Enter" );
		
		ArrayList<GroupDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT groupDTO FROM GroupDTO groupDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE groupDTO.componentId=" + groupId );
		}
		
		try
		{
			result = (ArrayList) getHibernateTemplate ().find ( queryStr.toString () );
			theLogger.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		theLogger.info ( "getList: Exit" );
		return result;
	}
	
	public GroupSuccessResult add ( GroupDTO groupDTO )
			throws GroupWithSameCodeAlreadyExistsException,
			GroupCreationException, SystemException, InvalidDateException,
			UniqueCodeRequiredException, InvalidParentException
	{
		theLogger.info ( "add(GroupDTO groupDTO)::" );
		HibernateTemplate hibernateTemplate = getHibernateTemplate ();
		GroupSuccessResult groupSuccessResult = new GroupSuccessResult ();
		hibernateTemplate.save ( groupDTO );
		groupSuccessResult.setMessage ( "ADDED SUCCESSFULLY." );
		groupSuccessResult.setOperationResult ( groupDTO );
		
		return groupSuccessResult;
	}
	
	public GroupSuccessResult modify ( GroupDTO groupDTO )
			throws BusinessRuleViolationException
	{
		if ( groupDTO == null )
		{
			throw new RuntimeException ( "groupDTO IS NULL...." );
		}
		
		theLogger.info ( "modify(GroupDTO groupDTO)::" );
		GroupSuccessResult groupSuccessResult = new GroupSuccessResult ();
		HibernateTemplate hibernateTemplate = getHibernateTemplate ();
		hibernateTemplate.update ( groupDTO );
		groupSuccessResult.setMessage ( "MODIFIED SUCCESSFULLY." );
		groupSuccessResult.setOperationResult ( groupDTO );
		
		return groupSuccessResult;
	}
	
	public GroupSuccessResult remove ( GroupDTO groupDTO )
			throws BusinessRuleViolationException
	{
		if ( groupDTO == null )
		{
			throw new RuntimeException ( "groupDTO IS NULL...." );
		}
		theLogger.info ( "remove(GroupDTO groupDTO)::" );
		GroupSuccessResult groupSuccessResult = new GroupSuccessResult ();
		HibernateTemplate hibernateTemplate = getHibernateTemplate ();
		hibernateTemplate.delete ( groupDTO );
		groupSuccessResult.setMessage ( "REMOVED SUCCESSFULLY." );
		groupSuccessResult.setOperationResult ( groupDTO );
		
		return groupSuccessResult;
	}
	
	public ArrayList search ( String query )
			throws GroupForThisCodeNotFoundException
	{
		HibernateTemplate hibernateTemplate = getHibernateTemplate ();
		ArrayList list = (ArrayList) hibernateTemplate.find ( "select group.componentId, group.uniqueCode from GroupDTO group" );
		return (ArrayList) list;
	}
	
}
