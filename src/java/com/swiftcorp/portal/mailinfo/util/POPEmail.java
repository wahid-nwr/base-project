package com.swiftcorp.portal.mailinfo.util;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class POPEmail {
	private String host;
	private String port = "110";
	private String user;
	private String password;
	private boolean ssl = false;
	
	private String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private Session session = null;
	private Store store = null;
	Folder folder = null;
	
	/**
	 * 
	 * @param host POP Hostname
	 * @param port POP port
	 * @param user email address
	 * @param password email password
	 * @param ssl Is host SSL or not
	 */
	public POPEmail(String host, String port, String user, String password, boolean ssl){
		this.host = host;
		if(port!=null)
			this.port = port;
		this.user = user;
		this.password = password;
		this.ssl = ssl;
	}
	/**
	 * Establish Connection to POP server to get messages.
	 * @throws Exception 
	 */
	public void connect() throws Exception{
		Properties properties = System.getProperties();
        if(this.ssl){
        	properties.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        	properties.setProperty("mail.pop3.socketFactory.fallback", "false");
        	properties.setProperty("mail.pop3.socketFactory.port", this.port);
        }
        properties.setProperty("mail.pop3.port",  this.port);
        this.session = Session.getDefaultInstance(properties);
        this.store = session.getStore("pop3");
        store.connect(this.host, this.user, this.password);
        this.folder = store.getFolder("inbox");

        if (!folder.exists()) {
            throw new Exception("No Inbox...");
        }
        folder.open(Folder.READ_ONLY);	
	}
	/**
	 * Closes connection with POP server
	 */
	public void disconnect() {
		try {
			folder.close(true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		try {
			store.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Fetches messages from INBOX of email
	 * 
	 * @return Messages
	 * @throws Exception
	 */
	public Message[] getMessages() throws Exception{
		if(folder==null)
			throw new Exception("[EMAIL EXCEPTION] Not connected, Connect first");
		return folder.getMessages();
	}
}
