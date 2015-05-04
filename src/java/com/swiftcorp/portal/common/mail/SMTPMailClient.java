package com.swiftcorp.portal.common.mail;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.mail.dto.MailMessage;

public class SMTPMailClient
{
	private static final Log theLogger = LogFactory.getLog ( SMTPMailClient.class );
	private String serverHost;
	private String serverPort;
	private String mailSender;
	private String mailSenderPassword;
	
	public void sendMail ( MailMessage mailDTO )
			throws MessagingException, BusinessRuleViolationException
	{
		
		theLogger.info ( "SMTPMailClient->SendMail():: Enter" );
		boolean debug = false;
		Properties props = new Properties ();
		props.put ( "mail.smtp.host", serverHost );
		props.put ( "mail.smtp.port", serverPort );
		
		Session session = Session.getDefaultInstance ( props, null );
		session.setDebug ( debug );
		
		try
		{
			MimeMessage msg = new MimeMessage ( session );
			String from = mailDTO.getFrom ();
			msg.setFrom ( new InternetAddress ( from ) );
			
			// set to address of the mail
			String recipients[] = new String[mailDTO.getTo ().size ()];
			
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for ( int i = 0; i < recipients.length; i++ )
			{
				addressTo[i] = new InternetAddress ( mailDTO.getTo ().get ( i ) );
				
			}
			msg.setRecipients ( Message.RecipientType.TO, addressTo );
			
			// set to cc of the mail
			String ccRecipients[] = new String[mailDTO.getCc ().size ()];
			
			InternetAddress[] addressCC = new InternetAddress[ccRecipients.length];
			for ( int j = 0; j < ccRecipients.length; j++ )
			{
				addressCC[j] = new InternetAddress ( mailDTO.getCc ().get ( j ) );
				
			}
			
			msg.setRecipients ( Message.RecipientType.CC, addressCC );
			
			// Set the email message text.
			String message = mailDTO.getMailBody ();
			MimeBodyPart messagePart = new MimeBodyPart ();
			messagePart.setText ( message );
			
			// Set the email attachment file
			/*
			 * MimeBodyPart attachmentPart = new MimeBodyPart();
			 * 
			 * FileDataSource fileDataSource = new FileDataSource(fileName) {
			 * 
			 * @Override public String getContentType() { return
			 * "application/pdf"; } }; attachmentPart.setDataHandler(new
			 * DataHandler(fileDataSource));
			 * attachmentPart.setFileName(fileName.replace(fileName, fileName));
			 * attachmentPart.setDisposition(Part.ATTACHMENT);
			 * multipart.addBodyPart(attachmentPart);
			 */
			Multipart multipart = new MimeMultipart ();
			multipart.addBodyPart ( messagePart );
			
			msg.setContent ( multipart );
			msg.setSentDate ( new Date () );
			msg.setSubject ( mailDTO.getMailSubject () );
			
			Transport.send ( msg );
		}
		catch (MessagingException e)
		{
			theLogger.error ( "send mail error", e );
		}
		catch (Exception e)
		{
			theLogger.error ( "send mail error", e );
		}
		theLogger.info ( "SMTPMailClient->SendMail():: Exit" );
	}
	
	public MailMessage prepareMailReceiver ( )
			throws SystemException, BusinessRuleViolationException,
			URISyntaxException, IOException
	{
		theLogger.info ( "SMTPMailClient->prepareMailReceiver():: Enter" );
		MailMessage mailDTO = new MailMessage ();
		try
		{
			List<String> toList = new ArrayList<String> ();
			List<String> ccList = new ArrayList<String> ();
			
			// populate email account list
			mailDTO.setFrom ( mailSender );
			mailDTO.setTo ( toList );
			mailDTO.setCc ( ccList );
			
			VelocityContext context = new VelocityContext ();
			StringWriter writer = new StringWriter ();
			
			URL url = this.getClass ().getResource ( "/Email.vm" );
			
			if ( url != null )
			{
				File file = new File ( url.toURI () );
				String filePath = file.getPath ();
				Reader reader = new FileReader ( filePath );
				Velocity.evaluate ( context, writer, "", reader );
			}
			mailDTO.setMailBody ( writer.toString () );
			mailDTO.setMailSubject ( "Room Sweep Report" );
		}
		catch (Exception e)
		{
			throw new BusinessRuleViolationException ( "Notification email can not be sent" );
		}
		theLogger.info ( "SMTPMailClient->prepareMailReceiver():: Exit" );
		return mailDTO;
	}
	
	public String getMailSender ( )
	{
		return mailSender;
	}
	
	public void setMailSender ( String mailSender )
	{
		this.mailSender = mailSender;
	}
	
	public String getServerHost ( )
	{
		return serverHost;
	}
	
	public void setServerHost ( String serverHost )
	{
		this.serverHost = serverHost;
	}
	
	public String getServerPort ( )
	{
		return serverPort;
	}
	
	public void setServerPort ( String serverPort )
	{
		this.serverPort = serverPort;
	}
	
	public String getMailSenderPassword ( )
	{
		return mailSenderPassword;
	}
	
	public void setMailSenderPassword ( String mailSenderPassword )
	{
		this.mailSenderPassword = mailSenderPassword;
	}
}
