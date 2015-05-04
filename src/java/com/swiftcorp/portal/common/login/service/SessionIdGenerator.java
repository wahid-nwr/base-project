/**
 * 
 */
package com.swiftcorp.portal.common.login.service;

/**
 * @author click 1
 * 
 */
public class SessionIdGenerator
{
	public String generateSessionId ( String userId, String password )
	{
		// get the system time in milis
		long systemTime = System.currentTimeMillis ();
		
		// TODO : for now just get the session id by concatening userid and time
		String sessionId = userId + systemTime;
		
		// return id
		return sessionId;
	}
}
