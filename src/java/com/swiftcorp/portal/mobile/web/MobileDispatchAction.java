package com.swiftcorp.portal.mobile.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.mailinfo.web.MailinfoDispatchAction;

public class MobileDispatchAction extends DispatchAction{
	protected static final Log log = LogFactory.getLog(MobileDispatchAction.class);
	
	public ActionForward promptSuccessAddMailinfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||() :");
		System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}

}
