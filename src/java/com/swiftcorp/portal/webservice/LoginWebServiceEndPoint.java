/**
 * 
 */
package com.swiftcorp.portal.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.swiftcorp.portal.common.ErrorConstants;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.LoginSuccessResult;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.login.service.LoginServiceImpl;
import com.swiftcorp.portal.common.login.service.SessionIdGenerator;
import com.swiftcorp.portal.user.exception.InvalidPasswordException;
import com.swiftcorp.portal.user.exception.UserNotFoundException;

/**
 * @author asraful.haque
 * 
 */
@WebService(serviceName = "LoginService")
public class LoginWebServiceEndPoint extends SpringBeanAutowiringSupport
{
	@Autowired
	private LoginServiceImpl loginServiceImpl;
	
	// session id generator
	private SessionIdGenerator sessionIdGenerator;
	
	public LoginWebServiceEndPoint ( )
	{
		
	}
	
	@WebMethod
	public String login ( String userId, String password )
	{
		// make the logindto
		LoginDTO loginDTO = new LoginDTO ();
		loginDTO.setUserId ( userId );
		loginDTO.setPassword ( password );
		
		// now authenticate through the service
		try
		{
			LoginSuccessResult loginSuccessResult = this.loginServiceImpl.authenticate ( loginDTO );
			
			String userToken = sessionIdGenerator.generateSessionId ( userId, password );
			
			// loginSuccessResult.
			return userToken;
		}
		catch (UserNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		catch (InvalidPasswordException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		catch (SystemException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		
		return ErrorConstants.NOT_AUTHENTICATED;
	}
	
	public LoginServiceImpl getLoginServiceImpl ( )
	{
		return loginServiceImpl;
	}
	
	public void setLoginServiceImpl ( LoginServiceImpl loginServiceImpl )
	{
		this.loginServiceImpl = loginServiceImpl;
	}
	
	public SessionIdGenerator getSessionIdGenerator ( )
	{
		return sessionIdGenerator;
	}
	
	public void setSessionIdGenerator ( SessionIdGenerator sessionIdGenerator )
	{
		this.sessionIdGenerator = sessionIdGenerator;
	}
}
