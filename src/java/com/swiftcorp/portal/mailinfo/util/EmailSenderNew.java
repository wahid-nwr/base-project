package com.swiftcorp.portal.mailinfo.util;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import java.util.StringTokenizer;
import java.io.*;
import java.util.*;
import com.sun.mail.smtp.*;
import javax.activation.*;


/**
 * Smile Email sender
 * @author Sharif Uddin
 *
 */
public class EmailSenderNew {

   /**
    *
    */
	public EmailSenderNew() {

	}

	private String from="";
    private String replyto="";
    private String to="";
    private String cc="";
    private String bcc="";
    private String msg="";
    private String HTMLMsg = "";
    private Vector attachments = new Vector();
    private String subject="";
    private Vector headers = new Vector();

    /**
     *
     * @throws Exception
     */
    public void send(String from, String smtp_host, String smtp_port, String pass, String isssl) throws Exception{

		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		String SMTP_HOST_NAME = smtp_host;
    	int SMTP_HOST_PORT = Integer.parseInt(smtp_port);
    	String SMTP_AUTH_USER = from;
    	String SMTP_AUTH_PWD  = pass;

    	System.out.println("from::::"+from);
		Properties props = System.getProperties();
		props.put("mail.host", SMTP_HOST_NAME);
		props.put("mail.transport.protocol.", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.", "true");
		props.put("mail.smtp.port", ""+SMTP_HOST_PORT);
		if(isssl!=null && !isssl.equals("null") && isssl.equals("Y"))
		{
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		}
		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(false);

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(subject);
	    message.setContent(msg, "text/html");
        message.setFrom(new InternetAddress(from));

        if(getTo().length()>1){
		        StringTokenizer st_to = new StringTokenizer(getTo(), ",");
		        int tokenCount_to = st_to.countTokens();
		        InternetAddress[] recipientList_to = new InternetAddress[tokenCount_to];
		        for (int i = 0; st_to.hasMoreTokens(); i++) {
		            //Get the next token
		            String msgTo_to = st_to.nextToken();
					msgTo_to = msgTo_to.trim();
		            //Ensure the token received is a valid address
					System.out.println("TO:"+msgTo_to);
		            if (msgTo_to != null && msgTo_to.trim().length() > 0)
		                   recipientList_to[i] = new InternetAddress(msgTo_to);
					}
		       message.setRecipients(Message.RecipientType.TO, recipientList_to);
        }
		/*
		// Set TO
            if( to != null && ( to.length > 0 ) ) {
                InternetAddress[] address = new InternetAddress[to.length ];

                for( int i = 0; i < to.length; i++ ) {
                    address[ i ] = new InternetAddress( to[ i ] ) ;
                }

                message.setRecipients( Message.RecipientType.TO, address ) ;
            }
		*/

		if(getCc().length()>1){
		        StringTokenizer st = new StringTokenizer(getCc(), ",");
		        int tokenCount = st.countTokens();
		        InternetAddress[] recipientList = new InternetAddress[tokenCount];
		        for (int i = 0; st.hasMoreTokens(); i++) {
		            //Get the next token
		            String msgTo = st.nextToken();
					msgTo = msgTo.trim();
					//System.out.println("CC:"+msgTo);

		            //Ensure the token received is a valid address
		            if (msgTo != null && msgTo.trim().length() > 0)
		                    recipientList[i] = new InternetAddress(msgTo);
        		}
        	message.setRecipients(Message.RecipientType.CC, recipientList);
        }

        message = attachment(message);
		/*
        if(to.length()<1)
        	throw new Exception("Destination address not specified");
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
        if(cc.length()>0)
        	message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
		*/
        if(bcc.length()>0)
        	message.addRecipient(Message.RecipientType.BCC,new InternetAddress(bcc));
        if(replyto!=null && replyto.length()>0)
        	message.setReplyTo(new Address[]{new InternetAddress(replyto)});

		Transport transport = mailSession.getTransport("smtp");
		transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
		try {
				transport.sendMessage(message,message.getAllRecipients());
				System.out.println("Mail Sent");
				//WasEmailSent = true; // assume it was sent
			}
			catch (Exception err) {
				System.out.println("ERROR:"+err);
				//WasEmailSent = false; // assume it's a fail
				}
		transport.close();
    	}

    /**
     *
     * @param msg
     * @param text
     * @param file
     * @return
     * @throws MessagingException
     */
    private MimeMessage attachment(MimeMessage msg) throws MessagingException{
    	String attachment = null;
    	MimeBodyPart mbp1 = new MimeBodyPart();
    	if(this.msg!=null && this.msg.length()>0)
    		mbp1.setText(this.msg);
    	else if(HTMLMsg!=null && HTMLMsg.length()>0)
    		mbp1.setContent(this.HTMLMsg, "text/html");
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp1);
        for(int i=0;i<attachments.size();i++)
        {
        	attachment = (String)attachments.get(i);
        	// create the second message part
        	MimeBodyPart mbp2 = new MimeBodyPart();
            // attach the file to the message
        	FileDataSource fds = new FileDataSource(attachment);
        	mbp2.setDataHandler(new DataHandler(fds));
        	mbp2.setFileName(fds.getName());
        	//create the Multipart and add its parts to it
        	mp.addBodyPart(mbp2);
        }
        // add the Multipart to the message
        msg.setContent(mp);
        for(int i=0;i<headers.size();i++){
        	Header header = (Header)headers.get(i);
        	msg.addHeader(header.getName(),header.getValue());
        }
        // set the Date: headerg
        msg.setSentDate(new Date());
        return msg;
    }

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}
	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the attachment
	 */
	public Vector getAttachment() {
		return attachments;
	}
	/**
	 * @param attachment the attachment to set
	 */
	public void addAttachment(String attachment) {
		this.attachments.add(attachment);
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the replyto
	 */
	public String getReplyto() {
		return replyto;
	}
	/**
	 * @param replyto the replyto to set
	 */
	public void setReplyto(String replyto) {
		this.replyto = replyto;
	}
	/**
	 * @return the hTMLMsg
	 */
	public String getHTMLMsg() {
		return HTMLMsg;
	}
	/**
	 * @param msg the hTMLMsg to set
	 */
	public void setHTMLMsg(String msg) {
		HTMLMsg = msg;
	}
	public void addHeader(String name, String value){
		Header header = new Header(name, value);
		headers.add(header);
	}
}
