/*
 * @ (#) ItemDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.item.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorActionForm;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.SearchUtil;
import com.swiftcorp.portal.common.util.WebUtils;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.ForwardUtil;
import com.swiftcorp.portal.common.web.MessageKeys;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.user.dto.UserDTO;
import com.swiftcorp.portal.item.dto.ItemDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.item.ItemSuccessResult;
import com.swiftcorp.portal.item.service.IItemService;
import com.swiftcorp.portal.item.web.ItemSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class ItemDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(ItemDispatchAction.class);
	@SuppressWarnings("unused")
	private IItemService itemService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setItemService(IItemService itemService) 
	{
		this.itemService = itemService;
	}
	
	public ActionForward promptItemSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptItemSearchSystemLevel() : enter");	
		try 
		{
			ItemSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = ItemSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = itemService.search(searchSqlQuery);
			log.info("searchItemFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.ITEM_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			ItemSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptItemSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the item search page
		return mapping.findForward(ForwardNames.ITEM_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtItemSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptItemSearchSystemLevel() : enter");	
		try 
		{
			ItemSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = ItemSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = itemService.search(searchSqlQuery);
			log.info("searchItemFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.ITEM_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			ItemSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptItemSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the item search page
		return mapping.findForward(ForwardNames.EXT_ITEM_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptItemSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptItemSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the item
			String searchSqlQuery = ItemSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = itemService.search(searchSqlQuery);
			log.info("searchItemFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.ITEM_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			ItemSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptItemSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the item search page
		return mapping.findForward(ForwardNames.ITEM_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddItem(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddItemHome() : enter");		
		DynaValidatorActionForm itemForm = (DynaValidatorActionForm) form;
		itemForm.set("item",new ItemDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_ITEM);
	}
	public ActionForward addItem(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addItem() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm itemForm = (DynaValidatorActionForm) form;
		ItemDTO itemDTO = (ItemDTO)itemForm.get("item");
		itemDTO = new ItemDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, itemDTO);
		String[][] messageArgValues = {{itemDTO.getUniqueCode()}};
		ItemSuccessResult result = (ItemSuccessResult)itemService.add(itemDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addItem() : Exit");
		return promptSuccessAddItem( mapping,form, request, response);
	}
	
	public ActionForward searchItemFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchItemFromSystemLevel():: Enter");	
		String searchSqlQuery = ItemSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = itemService.search(searchSqlQuery);
		log.info("searchItemFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.ITEM_SEARCH_RESULT, searchOperationResult);
		ItemSearchUtils.prepareSearchPage(request);
		log.info("searchItemFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.ITEM_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchItemFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchItemFromGroupLevel():: Enter");	
		String searchSqlQuery = ItemSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = itemService.search(searchSqlQuery);
		log.info("searchItemFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		ItemSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.ITEM_SEARCH_RESULT, searchOperationResult);	
		log.info("searchItemFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.ITEM_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyItem(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyItem() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyItem() : componentId = "+ componentId);
		ItemDTO itemDTO = (ItemDTO)itemService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("item",itemDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyItem() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_ITEM);
	}
	public ActionForward modifyItem(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyItem() : Enter");
		DynaValidatorActionForm itemForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyItem() : componentId = "+ componentId);
		ItemDTO itemDTO = (ItemDTO)itemService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, itemDTO);
		
		String[][] messageArgValues = {{itemDTO.getUniqueCode()}};
		itemService.modify(itemDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyItem() : Exit");
		return promptSuccessAddItem( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitItem() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelItemOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelItemOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptItemSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptItemSearchGroupLevel(mapping,form,request,response);
		}
		return promptItemSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchItem() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
