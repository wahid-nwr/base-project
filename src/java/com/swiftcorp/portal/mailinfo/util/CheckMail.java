package com.swiftcorp.portal.mailinfo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class CheckMail {

	 /**
	 * @param args
	 * @throws Exception 
	 */
	
	public void popEmail(Statement stmt, String host,String port, String emailId,String emailPasswd, boolean ssl, String path) throws Exception {
		POPEmail popEmail = new POPEmail(host, port, emailId, emailPasswd, ssl);
		popEmail.connect();
		Message[] messages = popEmail.getMessages();
		String from;
		String to;
		String replyTo;
		String subject;
		Date sendDate;
		String contentType;
		String content;
		String sql = "";
		String cc;
		String allRecipients;
		Date lattestSendDate = null;
		int isAfter = 100;
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		String email_id = "";
		PreparedStatement pstmt = null;
		
		/*
		TO BE REMOVED
		*/
				
		Date checkDate = new Date();
		Date dt = new Date();
		try{
			ResultSet rs = null;
			
			sql = "SELECT NVL(MAX(SEND_DATE),TO_DATE(TO_CHAR(SYSDATE,'dd-mm-rrrr')||' 00:00:00', 'dd-mm-rrrr hh24:mi:ss' ))  FROM EMAIL WHERE RECIPIENT LIKE '%" + emailId + "%'";
			System.out.println("Date SQL>"+sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				lattestSendDate = rs.getTimestamp(1);
			}
			checkDate.setTime(lattestSendDate.getTime()-1000*60*60*24*3);
			dt.setTime(lattestSendDate.getTime());
			System.out.println("Last Send Date: "+lattestSendDate+"			"+dt);
			
			sql = "select ";
			
			
			for(int i=messages.length-1;i>=0;i--){			
				System.out.println("look@me.... "+i+" ");
				from = InternetAddress.toString(messages[i].getFrom());
				replyTo = InternetAddress.toString(messages[i].getReplyTo());
				to = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.TO));
				cc = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.CC));
				System.out.println("look@me.... "+i+" 1");				
				java.util.Enumeration headers =messages[i].getAllHeaders();
		        javax.mail.Header head = null;
		        String priority="";
		        while ( headers.hasMoreElements( ) ){
		             head = (javax.mail.Header) headers.nextElement( );
		             if(head.getName().equals("Email-Priority"))		             	
		            	 priority= head.getValue( );
		        }
		        System.out.println("look@me.... "+i+" 2");
				allRecipients = InternetAddress.toString(messages[i].getAllRecipients());
				sendDate = messages[i].getSentDate();
				System.out.println("look@me.... "+i+" 3");
				System.out.println("S	"+sendDate+"	L	"+lattestSendDate+"		P	"+checkDate +"   : "+sendDate.compareTo(lattestSendDate)+"		: 	"+sendDate.compareTo(checkDate));
				/*
				if (lattestSendDate != null)
				{
					isAfter = sendDate.compareTo(lattestSendDate);
				}
				
				if (sendDate.compareTo(checkDate) <=0)
					break;
				if (isAfter<=0)
					continue;
				*/	
				System.out.println("look@me.... "+i+"4 ");
				subject = messages[i].getSubject();
				System.out.println("From: "+from.trim());
				System.out.println("Reply-To: "+replyTo.trim());
				System.out.println("To: "+emailId);
				System.out.println("Cc: "+cc);
				System.out.println("Date: "+sendDate);
				System.out.println("Subject: "+subject);
				System.out.println();
				contentType = messages[i].getContentType();
				//insert into CR_2.EMAIL --- start
				t1.setTime(sendDate.getTime());
				
				sql=	" Select count(*) from admin where " +
						" case " + 
						"      when instr(EMAIL ,'>') > 0 then substr(substr(EMAIL,instr(EMAIL,'<')+1),0,length(substr(EMAIL,instr(EMAIL,'<')+1))-1)  " +    
						"      when instr(EMAIL ,'>') =0 then EMAIL " +
						" end like " +
						" case " +
						"    when instr('"+from+"' ,'>') > 0 then substr(substr('"+from+"',instr('"+from+"','<')+1),0,length(substr('"+from+"',instr('"+from+"','<')+1))-1) " +    
						"    when instr('"+from+"' ,'>') = 0 then '"+from+"' " +
						" end " ;
				System.out.println("Look @ Here .... ::::: \n"+ sql+"\n ::: Finish ");	
				rs = stmt.executeQuery(sql);
				int IsinternalUser=0;
				if (rs.next())
					IsinternalUser=rs.getInt(1);
				
				sql = "SELECT EMAIL_SQ.nextval AS EMAIL_ID FROM DUAL";
				rs = stmt.executeQuery(sql);
				if (rs.next())
					email_id = rs.getString("EMAIL_ID");
				
				if(IsinternalUser!=0)
					sql = " INSERT INTO EMAIL(ID,SENDER,RECIPIENT,CC,SEND_DATE,SUBJECT,ALL_RECIPIENTS,E_CHECKED,PRIORITY)" +
					  " VALUES("+email_id+",?,?,?,?,?,?,'I','"+priority+"')";
				else
					sql = " INSERT INTO EMAIL(ID,SENDER,RECIPIENT,CC,SEND_DATE,SUBJECT,ALL_RECIPIENTS,E_CHECKED,PRIORITY)" +
					  " VALUES("+email_id+",?,?,?,?,?,?,'U','"+priority+"')";
				
				System.out.println("insert_email::"+sql);
				pstmt = stmt.getConnection().prepareStatement(sql);
				/*
				CLOB ccClob = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
				ccClob.putString(1,cc);
				CLOB allResp = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
				ccClob.putString(1,allRecipients);
				*/
				pstmt.setString(1, from);
				pstmt.setString(2, to);
				//pstmt.setClob(3, ccClob);
				pstmt.setTimestamp(4, t1);
				pstmt.setString(5, subject);
				//pstmt.setClob(6,allResp);
				pstmt.execute();
				
				if(contentType.indexOf("text/")!=-1 || contentType.indexOf("multipart/")==-1){
					
					System.out.println("Content Type: "+contentType.trim());
					content = (String)messages[i].getContent();
					
					sql = "INSERT INTO EMAIL_DTL(ID,EMAIL_ID,CONTENT_TYPE,CONTENTS)" +
	    				" VALUES(EMAIL_SQ.nextval," + email_id + ",'" + contentType + "',?)";
	    				System.out.println("MM SQL>"+sql);
	    			pstmt = stmt.getConnection().prepareStatement(sql);
	    			/*
	    			CLOB newClob = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
            		newClob.putString(1,content);        
            		pstmt.setClob(1, newClob);
            		*/
	    			pstmt.execute();
					
				}else{
		
					Multipart multipart = (Multipart) messages[i].getContent();
		            
		            for (int x = 0; x < multipart.getCount(); x++) {
		                BodyPart bodyPart = multipart.getBodyPart(x);
		                contentType = bodyPart.getContentType();
		                
		                if(contentType.indexOf("text/")!=-1)
		                {
		                	content = (String)bodyPart.getContent();
		                	content = content.replaceAll("'", "''");
		                	System.out.println("Content Type: "+contentType.trim());
		                	
		                	sql = "INSERT INTO EMAIL_DTL(ID,EMAIL_ID,CONTENT_TYPE,CONTENTS)" +
	 	    				" VALUES(EMAIL_SQ.nextval," + email_id + ",'" + contentType + "',?)";
	 	    				System.out.println("MM SQL>"+sql);
	 	    				pstmt = stmt.getConnection().prepareStatement(sql);
	 	    				/*
	 	    				CLOB newClob = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
	                		newClob.putString(1,content);        
	                		pstmt.setClob(1, newClob);
	 	    				*/
	                		pstmt.execute();
							
							
		    				
		    			//insert into CR_2.EMAIL_DTL --- end
		                }else if(contentType.indexOf("message/")!=-1) {
		                	MimeMessage mime = (MimeMessage)bodyPart.getContent();
		                	 contentType = mime.getContentType();
		                	 if(contentType.indexOf("text/")!=-1)
		 	                {
		                		content = (String)mime.getContent();
		                		sql = "INSERT INTO EMAIL_DTL(ID,EMAIL_ID,CONTENT_TYPE,CONTENTS)" +
		 	    				" VALUES(EMAIL_SQ.nextval," + email_id + ",'" + contentType + "',?)";
		 	    				System.out.println("MM SQL>"+sql);
		 	    				pstmt = stmt.getConnection().prepareStatement(sql);
		 	    				/*
		 	    				CLOB newClob = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
		                		newClob.putString(1,content);        
		                		pstmt.setClob(1, newClob);
		 	    				*/
		                		pstmt.execute();
		 	                }else{
		 	                	System.out.println("TODO : With email type......"+contentType);
		 	                }
		                	
		                }else{
		                	contentType = "ATTACHMENT";
		                	DataHandler handler = bodyPart.getDataHandler();
		                    //FileOutputStream fout = new FileOutputStream(new File(handler.getName()));
		                    //handler.writeTo(fout);
		                	System.out.println("Content Type: "+contentType.trim());
		                	//content = (String)bodyPart.getContent();
		                	if(handler.getName()!=null){
		                		System.out.println("File Name: "+handler.getName());
		                		content = email_id + "_" + handler.getName();
		                		FileOutputStream fout = new FileOutputStream(new File(path+File.separator+content));
		                        handler.writeTo(fout);
		                        //Insert into CR_2.EMAIL_DTL --- start
			    				sql = "INSERT INTO EMAIL_DTL(ID,EMAIL_ID,CONTENT_TYPE,CONTENTS)" +
			    				" VALUES(EMAIL_SQ.nextval," + email_id + ",'" + contentType + "',?)";
			    				pstmt = stmt.getConnection().prepareStatement(sql);
			    				pstmt.setString(1, content);
			    				pstmt.execute();
			    				//insert into CR_2.EMAIL_DTL --- end
		                    	   	}else{
		                		System.out.println("bla-bla-bla-bla");
		                	}
		                		
		                }
		                stmt.getConnection().commit();	
						popEmail.disconnect();
		            }
				}
				System.out.println();
			}
		}
		catch(Exception exp) 
		{
			//exp.printStackTrace();
			throw new Exception("Exception in fetch email:"+exp.getMessage());
		}
		finally{
			try{
				pstmt.close();
			}catch(Exception e){}
		}
	}
	public void receiveEmails(Statement stmt, String path) throws Exception{
		ResultSet rs = stmt.executeQuery("SELECT company_email_address, pop_host, pop_port, password, is_ssl FROM email_recv");
		Vector emails = new Vector();
		while(rs.next()){
			emails.add(new String[]{rs.getString("company_email_address"),rs.getString("pop_host"),rs.getString("pop_port"),rs.getString("password"),rs.getString("is_ssl")});
		}
		
		for(int i=0;i<emails.size();i++){
			String email[] = (String[])emails.get(i);
			System.out.println("EMAIL: "+email[0]);
			System.out.println("HOST: "+email[1]);
			System.out.println("PORT: "+email[2]);
			System.out.println("PASS: "+email[3]);
			System.out.println("SSL: "+email[4]);
			SimpleCipher cipher = new SimpleCipher(Info.CYPHER_KEY);
			email[3] = cipher.decrypt(email[3]);
			try{
				popEmail(stmt, email[1],  email[2],  email[0], email[3], email[4].equals("Y")?true:false,path);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
