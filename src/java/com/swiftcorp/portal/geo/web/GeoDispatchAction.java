/*
 * @ (#) GeoDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.geo.web;

import java.util.List;

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
import com.swiftcorp.portal.geo.GeoSuccessResult;
import com.swiftcorp.portal.geo.dto.GeoDTO;
import com.swiftcorp.portal.geo.service.IGeoService;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.user.dto.UserDTO;

/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class GeoDispatchAction extends DispatchAction {
	protected static final Log log = LogFactory.getLog(GeoDispatchAction.class);
	@SuppressWarnings("unused")
	private IGeoService geoService;
	@SuppressWarnings("unused")
	private IGroupService groupService;

	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

	public void setGeoService(IGeoService geoService) {
		this.geoService = geoService;
	}

	public ActionForward promptGeoSearchSystemLevel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("promptGeoSearchSystemLevel() : enter");
		try {
			GeoSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = GeoSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);
			SearchOperationResult searchOperationResult = geoService
					.search(searchSqlQuery);
			log.info("searchGeoFromSystemLevel():: searchOperationResult> size = "
					+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.GEO_SEARCH_RESULT,
					searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			GeoSearchUtils.prepareSearchPage(request);
		} catch (Exception e) {
			log.info("promptGeoSearchSystemLevel() :", e);
			throw e;
		}
		// show the geo search page
		return mapping.findForward(ForwardNames.GEO_SEARCH_SYSTEM_LEVEL);
	}

	public ActionForward promptGeoSearchGroupLevel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("promptGeoSearchGroupLevel() : enter");
		try {
			// here we want to load the geo
			String searchSqlQuery = GeoSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);

			SearchOperationResult searchOperationResult = geoService
					.search(searchSqlQuery);
			log.info("searchGeoFromSystemLevel():: searchOperationResult> size = "
					+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.GEO_SEARCH_RESULT,
					searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			GeoSearchUtils.prepareSearchPage(request);
		} catch (Exception e) {
			log.info("promptGeoSearchGroupLevel() :", e);
			throw e;
		}
		// show the geo search page
		return mapping.findForward(ForwardNames.GEO_SEARCH_GROUP_LEVEL);
	}

	public ActionForward promptAddGeo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("promptAddGeoHome() : enter");
		DynaValidatorActionForm geoForm = (DynaValidatorActionForm) form;
		geoForm.set("geo", new GeoDTO());
		List<GeoDTO> geoDTOList = geoService.getList();
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,
				GlobalConstants.ADD_OPERATION);
		request.getSession().setAttribute(SESSION_KEYS.GEO_PARENT_LIST,
				geoDTOList);
		return mapping.findForward(ForwardNames.PROMPT_ADD_GEO);
	}

	public ActionForward addGeo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SystemException, BusinessRuleViolationException, Exception {
		log.info("addGeo() : Enter");
		HttpSession session = request.getSession();
		DynaValidatorActionForm geoForm = (DynaValidatorActionForm) form;
		GeoDTO geoDTO = (GeoDTO) geoForm.get("geo");
		GeoDTO parentAreaDTO = (GeoDTO)geoService.get(geoDTO.getParentAreaId());
		geoDTO.setParentArea(parentAreaDTO);
		String[][] messageArgValues = { { geoDTO.getCode() } };
		GeoSuccessResult result = (GeoSuccessResult) geoService.add(geoDTO);
		WebUtils.setSuccessMessages(request,
				MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addGeo() : Exit");
		return promptGeoSearchSystemLevel(mapping, form, request, response);
	}

	public ActionForward searchGeoFromSystemLevel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SystemException,
			BusinessRuleViolationException {
		log.info("searchGeoFromSystemLevel():: Enter");
		String searchSqlQuery = GeoSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);

		SearchOperationResult searchOperationResult = geoService
				.search(searchSqlQuery);
		log.info("searchGeoFromSystemLevel():: searchOperationResult> size = "
				+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.GEO_SEARCH_RESULT,
				searchOperationResult);
		GeoSearchUtils.prepareSearchPage(request);
		log.info("searchGeoFromSystemLevel()::Exit");
		return mapping.findForward(ForwardNames.GEO_SEARCH_SYSTEM_LEVEL);
	}

	public ActionForward searchGeoFromGroupLevel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SystemException,
			BusinessRuleViolationException {
		log.info("searchGeoFromGroupLevel():: Enter");
		String searchSqlQuery = GeoSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);

		SearchOperationResult searchOperationResult = geoService
				.search(searchSqlQuery);
		log.info("searchGeoFromGroupLevel():: searchOperationResult> size = "
				+ searchOperationResult.getTotalRowCount());
		GeoSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.GEO_SEARCH_RESULT,
				searchOperationResult);
		log.info("searchGeoFromGroupLevel()::Exit");
		return mapping.findForward(ForwardNames.GEO_SEARCH_GROUP_LEVEL);
	}

	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyGeo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RuntimeException, Exception {
		log.info("promptModifyGeo() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyGeo() : componentId = " + componentId);
		GeoDTO geoDTO = (GeoDTO) geoService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		dynaValidatorActionForm.set("geo", geoDTO);
		List<GeoDTO> geoDTOList = geoService.getList();
		// List<GeoType> geoTypeList = geoService.getGeoTypeList();
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,
				GlobalConstants.MODIFY_OPERATION);
		request.getSession().setAttribute(SESSION_KEYS.GEO_PARENT_LIST,
				geoDTOList);
		request.getSession().setAttribute(SESSION_KEYS.GEO, geoDTO);
		// request.getSession().setAttribute(SESSION_KEYS.GEO_TYPE_LIST,geoTypeList);
		log.info("promptModifyGeo() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_GEO);
	}

	public ActionForward modifyGeo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SystemException, BusinessRuleViolationException, Exception {
		log.info("modifyGeo() : Enter");
		DynaValidatorActionForm geoForm = (DynaValidatorActionForm) form;
		GeoDTO geoDTO = (GeoDTO) geoForm.get("geo");
		GeoDTO parentAreaDTO = (GeoDTO)geoService.get(geoDTO.getParentAreaId());
		geoDTO.setParentArea(parentAreaDTO);
		String[][] messageArgValues = { { geoDTO.getCode() } };
		geoService.modify(geoDTO);
		WebUtils.setSuccessMessages(request,
				MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyGeo() : Exit");
		return promptGeoSearchSystemLevel(mapping, form, request, response);
	}

	public ActionForward cancelGeoOperation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("cancelGeoOperation() :");
		UserDTO usrDTO = (UserDTO) request.getSession().getAttribute(
				SESSION_KEYS.USER);
		/*
		 * int accessLevel = usrDTO.getRole().getAccessLevel();
		 * 
		 * if(accessLevel == GlobalConstants.SYSTEM_LEVEL) { return
		 * promptGeoSearchSystemLevel(mapping,form,request,response); } else
		 * if(accessLevel == GlobalConstants.GROUP_LEVEL) { return
		 * promptGeoSearchGroupLevel(mapping,form,request,response); }
		 */
		return promptGeoSearchSystemLevel(mapping, form, request, response);
	}

	public ActionForward cancelSearchGeo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SystemException,
			BusinessRuleViolationException, Exception {
		log.info("cancelSearchGeo() :");
		return ForwardUtil.getInstance().promtHomePage(mapping, form, request,
				response);
	}

	public ActionForward promptImportGeo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SystemException,
			BusinessRuleViolationException, Exception {
		log.info("cancelSearchGeo() :");
		return mapping.findForward(ForwardNames.PROMPT_IMPORT_GEO);
	}

	
}
