/**
 * 
 */
package com.swiftcorp.portal.common.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Zahangir
 * 
 */
public class SessionListener implements HttpSessionListener
{
	protected static final Log log = LogFactory.getLog ( SessionListener.class );
	private static long activeSessions = 0;
	
	public void sessionCreated ( HttpSessionEvent sessionEvent )
	{
		HttpSession session = sessionEvent.getSession ();
		log.info ( "sessionCreated(): Before Set, MaxInactiveInterval = " + session.getMaxInactiveInterval () );
		
		session.setMaxInactiveInterval ( 30 * 60 );
		log.info ( "sessionCreated(): After Set, MaxInactiveInterval = " + session.getMaxInactiveInterval () );
		
		activeSessions++;
		
		log.info ( "sessionCreated(): Session: " + (new Date ()).toString () + " ID:" + session.getId () + " - Active sessions: " + activeSessions );
	}
	
	public void sessionDestroyed ( HttpSessionEvent sessionEvent )
	{
		HttpSession session = sessionEvent.getSession ();
		
		if ( activeSessions > 0 )
			activeSessions--;
		
		log.info ( "sessionCreated():Session Closed: " + (new Date ()).toString () + "ID:" + session.getId () + " - Active sessions: " + activeSessions );
		
		session.invalidate ();
	}
	
	public static long getActiveSessions ( )
	{
		return activeSessions;
	}
	
}
