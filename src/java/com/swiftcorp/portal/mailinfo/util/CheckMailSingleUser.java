package com.swiftcorp.portal.mailinfo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import com.swiftcorp.portal.mailinfo.util.MailSqlGenerator;
public class CheckMailSingleUser {
	 /**
	 *
	 * @param args
	 * @param host POP Hostname
	 * @param port POP port
	 * @param user email address
	 * @param password email password
	 * @param ssl Is host SSL or not
	 */
	public void popEmail(Statement stmt, String host,String port, String emailId,String emailPasswd, boolean ssl, String path, String recv_id, String user_id) throws Exception {
		
		POPEmail popEmail = new POPEmail(host, port, emailId, emailPasswd, ssl);
		System.out.println("host::"+host+"\nport::"+port+"\nemailId::"+emailId+"\nemailPasswd::"+emailPasswd+"\nssl::"+ssl);
		popEmail.connect();
		System.out.println("POP Mail....Connected ");
		Message[] messages = popEmail.getMessages();
		String from;
		String to;
		String replyTo;
		String subject;
		Date sendDate;
		Date rec_date;
		String contentType;
		String content;
		String sql = "";
		String flag = "";
		String cc;
		String allRecipients;
		Date lattestSendDate = null;
		int isAfter = 100;
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		String email_id = "";
		PreparedStatement pstmt = null;
		
		try{
			ResultSet rs = null;
			
			//sql = "SELECT NVL(MAX(SEND_DATE),TO_DATE(TO_CHAR(SYSDATE,'dd-mm-rrrr')||' 00:00:00', 'dd-mm-rrrr hh24:mi:ss' ))  FROM EMAIL WHERE RECIPIENT LIKE '%" + emailId + "%'";
			//sql = 	"SELECT MAX(SEND_DATE) FROM RECIPIENTS WHERE EMAIL_RECV_ID = (SELECT ID FROM EMAIL_RECV WHERE ";
			//sql +=  " TAGGED_USER_ID ="+user_id+" AND COMPANY_EMAIL_ADDRESS LIKE '%"+emailId+"%')";
			sql = MailSqlGenerator.getMaxSendDate(user_id, emailId);
			//System.out.println("Date SQL>"+sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
				lattestSendDate = rs.getTimestamp(1);
			int abc=0;
			
			
			for(int i=messages.length-1;i>=0;i--){						
				from = InternetAddress.toString(messages[i].getFrom());
				replyTo = InternetAddress.toString(messages[i].getReplyTo());
				to = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.TO));
				cc = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.CC));
				if(cc==null || cc.equals("null"))
				{
					cc = "";
				}
				System.out.println("from::"+from);
				System.out.println("replyTo::"+replyTo);
				System.out.println("to::"+to);
				System.out.println("cc::"+cc);
				java.util.Enumeration headers =messages[i].getAllHeaders();
		        javax.mail.Header head = null;
		        String priority="";
		        while ( headers.hasMoreElements( ) ){
		             head = (javax.mail.Header) headers.nextElement( );
		             if(head.getName().equals("Email-Priority"))		             	
		            	 priority= head.getValue( );
		        }
				allRecipients = InternetAddress.toString(messages[i].getAllRecipients());				
				sendDate = messages[i].getSentDate();				
				System.out.println("S:"+sendDate+" || L:"+lattestSendDate);									
				
				subject = messages[i].getSubject();
				contentType = messages[i].getContentType();
				//System.out.println("From: "+from.trim());
				//System.out.println("Reply-To: "+replyTo.trim());
				//System.out.println("To: "+emailId);
				//System.out.println("Cc: "+cc);
				//System.out.println("Date: "+sendDate);
				//System.out.println("Subject: "+subject);
				//t1.setTime(sendDate.getTime());
				abc++;
				
				if(lattestSendDate==null){
					System.out.println("NEW");
				}
				else
				{
					if (sendDate.compareTo(lattestSendDate)<0)
					{
						break;
					}
				}
				//sql = "SELECT EMAIL_SQ.nextval AS EMAIL_ID FROM DUAL";
				sql = MailSqlGenerator.getMaxEmailRecipientsComponentId(user_id, emailId);
				rs = stmt.executeQuery(sql);
				email_id = "1";
				if (rs.next())
					email_id = rs.getString("EMAIL_ID");
				if(email_id!=null && !email_id.equals("null") && email_id.length()>0)
				{
					
				}
				else
				{
					email_id = "1";
				}
					
				System.out.println("email_id::"+email_id);
				String email_recv_id = "";	
				SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
				String qry = "";
				//qry = "SELECT ID FROM EMAIL_RECV WHERE COMPANY_EMAIL_ADDRESS LIKE '%"+emailId+"%' ";
				qry = MailSqlGenerator.getEmailRecvComponentId(emailId);
				//System.out.println("\n");
				//System.out.println("SQL>"+qry);
				rs = stmt.executeQuery(qry);
				Vector rec_v = new Vector();
				while(rs.next())
				{
					System.out.println("email recv id::"+rs.getString("componentId"));
					rec_v.add(new String[]{rs.getString("componentId")});
				}
				for(int j=0;j<rec_v.size();j++)
				{
					String str_recv_id[] = (String[])rec_v.get(j);
					email_recv_id = str_recv_id[0];
					
					//System.out.println("email_recv_id: "+email_recv_id);	
					//sql = "INSERT INTO RECIPIENTS(ID,MESSAGE_ID,CONTACT_ID,STATUS,EMAIL_RECV_ID,MESSAGE_TYPE,SEND_DATE	) VALUES(ID.NEXTVAL, ";
					//sql += ""+email_id+",'"+user_id+"','U','"+email_recv_id+"','EMAIL',TO_DATE('"+sd.format(sendDate)+"','dd-mm-rr hh24-mi-ss') )";
					sql = MailSqlGenerator.getEmailRecipientsMaxComponentId();
					rs = stmt.executeQuery(sql);
					String emailrecipientId = "1";
					if(rs.next())
					{
						emailrecipientId = rs.getString(1);
						if(emailrecipientId!=null && !emailrecipientId.equals("null") && emailrecipientId.length()>0)
						{
							
						}
						else
						{
							emailrecipientId = "1";
						}
					}
					System.out.println("emailrecipientId::"+emailrecipientId);
					sql = MailSqlGenerator.getInsertEmailRecipients(emailrecipientId,user_id, email_id,"U", email_recv_id,"MAIL", sendDate);
					System.out.println("email recipient::"+sql);
					stmt.executeUpdate(sql);
					//System.out.println("SQL> RECIPIENTS"+sql);
				}
						
				String senderEmail = "";
				String sender = from; 
				String email_group_id = "";
		
				sender = sender.replaceAll("\"", "");
				int start = sender.indexOf("<");
				if(start > 0 )
				{
					int end = sender.indexOf(">");			
					if(start > 0 && end > 0)
					{
						senderEmail = sender.substring(start+1, end); 
						senderEmail = senderEmail.trim();
					}					
				}
				else
				{
					senderEmail = sender;
				}
				//sql = "SELECT EMAIL_GROUP_ID FROM CONTACT WHERE EMAIL LIKE'%"+senderEmail+"%'";
				sql = MailSqlGenerator.getMaxEmailRecvComponentId(user_id, emailId);
				rs = stmt.executeQuery(sql);
				email_group_id = "1";
				if(rs.next())
				{
					email_group_id = rs.getString("componentId");
					if(email_group_id!=null && !email_group_id.equals("null") && email_group_id.length()>0)
					{
							
					}
					else
					{
						email_group_id = "1";
					}
				}
				else
				{
					//sql = "SELECT * FROM USERS WHERE EMAIL LIKE'%"+senderEmail+"%'";
					sql = MailSqlGenerator.getUsers(senderEmail);
					rs = stmt.executeQuery(sql);
					if(rs.next())
						email_group_id = "4";
					}
		
					//sql = " INSERT INTO EMAIL(ID,SENDER,RECIPIENT,CC,SEND_DATE,SUBJECT,ALL_RECIPIENTS,E_CHECKED,PRIORITY,EMAIL_GROUP_ID)" +
					//	  " VALUES("+email_id+",?,?,?,?,?,?,'U','"+priority+"','"+email_group_id+"')";
					sql = MailSqlGenerator.getInsertIntoEmail(email_id, priority, email_group_id);
					System.out.println("email insert::"+sql);
					pstmt = stmt.getConnection().prepareStatement(sql);					
					pstmt.setString(1, from);
					pstmt.setString(2, to);
					/*
					CLOB ccClob = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
					ccClob.putString(1,cc);
					pstmt.setClob(3,ccClob);
					*/
					StringReader reader = new StringReader(cc);
					
					
					/*
					Clob ccClob = (Clob) pstmt.getConnection().createClob();
					ccClob.setString(1,cc);
					pstmt.setClob(3,ccClob);*/
					pstmt.setCharacterStream(3, reader, cc.length());
					//pstmt.setString(3, "");
					if(sendDate==null)
					{
						sendDate = new Date();
					}
					pstmt.setTimestamp(4, new Timestamp(sendDate.getTime()));
					pstmt.setString(5, subject);				
					/*
					CLOB allResp = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
					allResp.putString(1,allRecipients);
					pstmt.setClob(6,allResp);
					*/
					/*Clob allResp = (Clob) pstmt.getConnection().createClob();
					allResp.setString(1,allRecipients);
					pstmt.setClob(6,allResp);*/
					reader = new StringReader(allRecipients);
					pstmt.setCharacterStream(6, reader, allRecipients.length());
					//pstmt.setString(6, "");
					pstmt.execute();
					
					if(contentType.indexOf("text/")!=-1 || contentType.indexOf("multipart/")==-1)
					{
						content = (String)messages[i].getContent();
						//sql = "INSERT INTO EMAIL_DTL(ID,EMAIL_ID,CONTENT_TYPE,CONTENTS)" +
						//	" VALUES(EMAIL_SQ.nextval," + email_id + ",'" + contentType + "',?)";
						sql = MailSqlGenerator.getMaxEmailDtlComponentId();
						rs = stmt.executeQuery(sql);
						String email_dtl_id = "1";
						if (rs.next())
						{
							email_dtl_id = rs.getString("EMAIL_DTL_ID");
						}
						if(email_dtl_id!=null && !email_dtl_id.equals("null") && email_dtl_id.length()>0)
						{
							
						}
						else
						{
							email_dtl_id = "1";
						}
						sql = MailSqlGenerator.getInsertIntoEmailDtl(email_id, contentType, email_dtl_id);
						System.out.println("MM SQL>"+sql);
						pstmt = stmt.getConnection().prepareStatement(sql);
						/*
						CLOB newClob = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
						newClob.putString(1,content);        
						pstmt.setClob(1, newClob);
						*/
						/*Clob newClob = (Clob) pstmt.getConnection().createClob();
						newClob.setString(1,cc);
						pstmt.setClob(1,newClob);*/
						reader = new StringReader(content);
						pstmt.setCharacterStream(1, reader, content.length());
						//pstmt.setString(1, "");
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
								String input = content;
	
								if(input.indexOf("<div>Ref No.Maes:")>0){
									input = input.substring(input.indexOf("<div>Ref No"));
									input = input.substring(input.indexOf("<div>Ref No"),input.indexOf("</div>"));
									if(input.indexOf("Ref No.Maes")>0){
										String mail_com_id = input.substring((input.indexOf(":")+1),input.indexOf("-"));
										String mail_group_id = input.substring((input.indexOf("-")+1),input.indexOf("/"));
										String ref_group_id = input.substring(input.indexOf("/")+1);	
									//sql = "UPDATE EMAIL SET COM_ID='"+mail_com_id+"', REF_ID='"+ref_group_id+"',";
									//sql += " EMAIL_GROUP_ID='"+mail_group_id+"' WHERE ID = "+email_id+"";
									sql = MailSqlGenerator.getUpdateEmail(email_id, mail_com_id, ref_group_id, mail_group_id);
									rs = stmt.executeQuery(sql);
		
										}			
									}
								else
									{
									}
	
								//sql = "INSERT INTO EMAIL_DTL(ID,EMAIL_ID,CONTENT_TYPE,CONTENTS)" +
								//" VALUES(EMAIL_SQ.nextval," + email_id + ",'" + contentType + "',?)";
								sql = MailSqlGenerator.getMaxEmailDtlComponentId();
								rs = stmt.executeQuery(sql);
								String email_dtl_id = "1";
								if (rs.next())
									email_dtl_id = rs.getString("EMAIL_DTL_ID");
								if(email_dtl_id!=null && !email_dtl_id.equals("null") && email_dtl_id.length()>0)
								{
									
								}
								else
								{
									email_dtl_id = "1";
								}
								sql = MailSqlGenerator.getInsertIntoEmailDtl(email_id, contentType, email_dtl_id);
								//System.out.println("MM SQL>"+sql);
								pstmt = stmt.getConnection().prepareStatement(sql);
								/*
								CLOB newClob = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
								newClob.putString(1,content);        
								pstmt.setClob(1, newClob);
								*/
								reader = new StringReader(content);
								pstmt.setCharacterStream(1, reader, content.length());
								pstmt.execute();
								
							//insert into CR_2.EMAIL_DTL --- end
							}else if(contentType.indexOf("message/")!=-1) {
								MimeMessage mime = (MimeMessage)bodyPart.getContent();
								 contentType = mime.getContentType();
								 //System.out.println("look@me 2:");
								 if(contentType.indexOf("text/")!=-1)
								{
									content = (String)mime.getContent();
									//sql = "INSERT INTO EMAIL_DTL(ID,EMAIL_ID,CONTENT_TYPE,CONTENTS)" +
									//" VALUES(EMAIL_SQ.nextval," + email_id + ",'" + contentType + "',?)";
									sql = MailSqlGenerator.getMaxEmailDtlComponentId();
									rs = stmt.executeQuery(sql);
									String email_dtl_id = "1";
									if (rs.next())
										email_dtl_id = rs.getString("EMAIL_DTL_ID");
									if(email_dtl_id!=null && !email_dtl_id.equals("null") && email_dtl_id.length()>0)
									{
										
									}
									else
									{
										email_dtl_id = "1";
									}
									sql = MailSqlGenerator.getInsertIntoEmailDtl(email_id, contentType, email_dtl_id);
									//System.out.println("MM SQL>"+sql);
									pstmt = stmt.getConnection().prepareStatement(sql);
									/*
									CLOB newClob = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
									newClob.putString(1,content);        
									pstmt.setClob(1, newClob);
									*/
									reader = new StringReader(content);
									pstmt.setCharacterStream(1, reader, content.length());
									pstmt.execute();
								}else{
								}
								
							}else{							
								contentType = "ATTACHMENT";
								DataHandler handler = bodyPart.getDataHandler();
								String fileName = handler.getName();
								System.out.println("fileName::"+fileName);
								if(fileName!=null){
									fileName = fileName.trim();
									System.out.println("fileName::"+fileName);
									fileName = fileName.replace(" ", "");
									System.out.println("fileName::"+fileName);
									content = email_id + "_" + fileName;
									FileOutputStream fout = new FileOutputStream(new File(path+File.separator+content));
									handler.writeTo(fout);
									//Insert into CR_2.EMAIL_DTL --- start
									//sql = "INSERT INTO EMAIL_DTL(ID,EMAIL_ID,CONTENT_TYPE,CONTENTS)" +
									//" VALUES(EMAIL_SQ.nextval," + email_id + ",'" + contentType + "',?)";
									sql = MailSqlGenerator.getMaxEmailDtlComponentId();
									rs = stmt.executeQuery(sql);
									String email_dtl_id = "1";
									if (rs.next())
										email_dtl_id = rs.getString("EMAIL_DTL_ID");
									if(email_dtl_id!=null && !email_dtl_id.equals("null") && email_dtl_id.length()>0)
									{
										
									}
									else
									{
										email_dtl_id = "1";
									}
									sql = MailSqlGenerator.getInsertIntoEmailDtl(email_id, contentType, email_dtl_id);
									pstmt = stmt.getConnection().prepareStatement(sql);
									pstmt.setString(1, content);
									pstmt.execute();
										}else{
									Multipart attachment_multipart = (Multipart) handler.getContent();		            
									for (int y = 0; y < attachment_multipart.getCount(); y++) {
										BodyPart attachment_bodyPart = attachment_multipart.getBodyPart(y);
										contentType = attachment_bodyPart.getContentType();
										if(contentType.indexOf("text/")!=-1)
											{
												content = (String)attachment_bodyPart.getContent();						
												content = content.replaceAll("'", "''");
												String input = content;
												if(input.indexOf("<div>Ref No.Maes:")>0){
													input = input.substring(input.indexOf("<div>Ref No"));
													input = input.substring(input.indexOf("<div>Ref No"),input.indexOf("</div>"));
													if(input.indexOf("Ref No.Maes")>0){
														String mail_com_id = input.substring((input.indexOf(":")+1),input.indexOf("-"));
														String mail_group_id = input.substring((input.indexOf("-")+1),input.indexOf("/"));
														String ref_group_id = input.substring(input.indexOf("/")+1);	
													//sql = "UPDATE EMAIL SET COM_ID='"+mail_com_id+"', REF_ID='"+ref_group_id+"',";
													//sql += " EMAIL_GROUP_ID='"+mail_group_id+"' WHERE ID = "+email_id+"";
													sql = MailSqlGenerator.getUpdateEmail(email_id, mail_com_id, ref_group_id, mail_group_id);
													 stmt.executeUpdate(sql);
						
														}			
													}
												else
													{
														 //System.out.println("NO REF FOUND .... \n");
													}
												//sql = "INSERT INTO EMAIL_DTL(ID,EMAIL_ID,CONTENT_TYPE,CONTENTS)" +
												//" VALUES(EMAIL_SQ.nextval," + email_id + ",'" + contentType + "',?)";
												sql = MailSqlGenerator.getMaxEmailDtlComponentId();
												rs = stmt.executeQuery(sql);
												String email_dtl_id = "1";
												if (rs.next())
													email_dtl_id = rs.getString("EMAIL_DTL_ID");
												if(email_dtl_id!=null && !email_dtl_id.equals("null") && email_dtl_id.length()>0)
												{
													
												}
												else
												{
													email_dtl_id = "1";
												}
												sql = MailSqlGenerator.getInsertIntoEmailDtl(email_id, contentType, email_dtl_id);
												pstmt = stmt.getConnection().prepareStatement(sql);
												/*
												CLOB newClob = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
												newClob.putString(1,content);        
												pstmt.setClob(1, newClob);
												*/
												reader = new StringReader(content);
												pstmt.setCharacterStream(1, reader, content.length());
												pstmt.execute();
											}	
	
										}
								}
							}						
							//stmt.getConnection().commit();	
						}
						
					} /// ---------------					
			}
			//System.out.println("TOTAL READ FROM ("+ emailId +"):"+abc);
			popEmail.disconnect();
			//System.out.println("popEmail Disconnected ");
		}
		catch(Exception exp) 
		{
			//exp.printStackTrace();
			popEmail.disconnect();
			//System.out.println("popEmail Disconnected ");
			throw new Exception("Exception in fetch email:"+exp.getMessage());
		}
		finally{
			try{
				pstmt.close();
				popEmail.disconnect();
				//System.out.println("popEmail Disconnected ");
			}catch(Exception e){}
		}
		
	}
	public void receiveEmails(Statement stmt, String path, String user_id) throws Exception{
	String qry = "SELECT company_email_address, pop_host, pop_port, password, is_ssl,id FROM email_recv where tagged_user_id = '"+user_id+"' ";
	qry = MailSqlGenerator.getRecieveEmail(user_id);
	ResultSet rs = stmt.executeQuery(qry);
	Vector emails = new Vector();
	while(rs.next()){
			emails.add(new String[]{rs.getString("companyemailaddress"),rs.getString("pophost"),rs.getString("popport"),
					rs.getString("password"),rs.getString("isssl"),rs.getString("componentId")});
		}
		for(int i=0;i<emails.size();i++){
			String email[] = (String[])emails.get(i);
			SimpleCipher cipher = new SimpleCipher(Info.CYPHER_KEY);
			String password = cipher.decrypt(email[3]);
			password = email[3];
			boolean ssl= email[4].equals("Y")?true:false;			
			try{
				popEmail(stmt, email[1],  email[2],  email[0], password, ssl,path,email[5],user_id);
				//Statement stmt, String host,String port, String emailId,String emailPasswd, boolean ssl, String path, String recv_id, String user_id
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
