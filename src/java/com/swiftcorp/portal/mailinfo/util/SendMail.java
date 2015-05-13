package com.swiftcorp.portal.mailinfo.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.*;
import com.sun.mail.smtp.*;
import javax.activation.*;


/**
 * <p>Title: Send Mail for speed Dial</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright Maestro(c) 2006</p>
 * <p>Company: Maestro (Pvt.) Ltd.</p>
 * @author Hasib
 * @version 1.0
 */

public class SendMail
{

  public boolean sendMail(String mess, String to, String from, String subject,String cc,String filename)
  {
    String status="";
    boolean sendStatus = false;
    SMTPTransport t = null;
    String smtphost = "smtp.bizmail.yahoo.com";
    String smtppass = "CtrlAltDel";
    String smtpuser = "info@1pinless.com";
    String smtpport = "587";

    try
    {
        // Create the JavaMail session
        java.util.Properties properties = System.getProperties();


        properties.put("mail.smtp.host", smtphost);
        properties.put("mail.smtp.port", smtpport);
        properties.put("mail.smtp.auth", "true");

        Session esession = Session.getInstance(properties, null);
        esession.setDebug(false);

        // Construct the message
        MimeMessage message = new MimeMessage(esession);

        // Set the from address
        Address fromAddress = new InternetAddress(from);
        message.setFrom(fromAddress);

        // Parse and set the recipient addresses
        Address[] toAddresses = InternetAddress.parse(to);
        message.setRecipients(Message.RecipientType.TO,toAddresses);

        if(!cc.equals(""))
        {
          Address[] ccAddresses = InternetAddress.parse(cc);
          message.setRecipients(Message.RecipientType.CC,ccAddresses);
        }
        // Set the subject and text
        message.setSubject(subject);

        message.setText(mess);


        // send the message
        /////Transport.send(message);

        t = (SMTPTransport)esession.getTransport("smtp");
        t.connect(smtphost, smtpuser, smtppass);
        
        if(!filename.equals(""))
        {
        	   BodyPart messageBodyPart = new MimeBodyPart();
        	   messageBodyPart.setText(mess);
        	   Multipart multipart = new MimeMultipart();
        	   multipart.addBodyPart(messageBodyPart);
        	   messageBodyPart = new MimeBodyPart();
        	   DataSource source = new javax.activation.FileDataSource(filename);
        	   messageBodyPart.setDataHandler(new DataHandler(source));
				filename=filename.substring((filename.lastIndexOf("\\")+1),filename.length());
        	   messageBodyPart.setFileName(filename);
        	   multipart.addBodyPart(messageBodyPart);
        	   message.setContent(multipart);

        }

        t.sendMessage(message, message.getAllRecipients());

        sendStatus = true;
      }
      catch (AddressException e)
      {
        sendStatus = false;
        status = "<h1>Sorry,</h1><h2>There was an error.  </h2>" + e;
        status = "<br>Error cause: " + e;
        e.printStackTrace();
      }
      catch (SendFailedException e)
      {
        sendStatus = false;
        status = "<h1>Sorry,</h1><h2>There was an error.  </h2>" + e;
        status = "<br>Error cause: " + e;
        e.printStackTrace();
      }

      catch(Exception e)
      {
        sendStatus = false;
        status = "<h1>Sorry,</h1><h2>There was an error.  </h2>" + e;
        status = "<br>Error cause: " + e;
        e.printStackTrace();
      }
      finally{
        System.out.println("SEND MAIL : "+status);
        //System.out.println("Response: " + t.getLastServerResponse());
        try{
          t.close();
        }catch(Exception e33){}		}
      return sendStatus;
    }

    ////////////////////////////////////////////////////////


      }
