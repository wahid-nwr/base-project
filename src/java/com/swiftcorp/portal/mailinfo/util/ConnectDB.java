package com.swiftcorp.portal.mailinfo.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.*;
import java.util.*;
//import com.maestro.crb.orig_billing.info.Inf;

public class ConnectDB
{

	private java.sql.Connection con = null;
	private java.sql.Statement stmt = null;
	private String msg = "";

	public Statement connect(boolean isAutoCommit)
	{
		try
		{
			Properties prop = new Properties();
			String host = "";
			String port = "";
			String databaseName = "";
			String databaseUserId = "";
			String databaseUserPass = "";
			try {
	               //load a properties file
				prop.load(this.getClass().getClassLoader().getResourceAsStream("Settings.properties"));

				//prop.load(new FileInputStream("/WEB-INF/classes/Settings.properties"));
	 
	               //get the property value and print it out
	            System.out.println(prop.getProperty("configuration.database.host"));
	            host = prop.getProperty("configuration.database.host");
	    		System.out.println(prop.getProperty("configuration.database.port"));
	    		port = prop.getProperty("configuration.database.port");
	    		System.out.println(prop.getProperty("configuration.database.name"));
	    		databaseName = prop.getProperty("configuration.database.name");
	    		System.out.println(prop.getProperty("configuration.database.userid"));
	    		databaseUserId = prop.getProperty("configuration.database.userid");
	    		System.out.println(prop.getProperty("configuration.database.password"));
	    		databaseUserPass = prop.getProperty("configuration.database.password");
	 
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }


			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+databaseName;
			System.out.println(url);
			con = DriverManager.getConnection(url,databaseUserId,databaseUserPass);
			con.setAutoCommit(isAutoCommit);
			stmt = con.createStatement();
			System.out.println("Connected :-)");


		}
		catch(Exception eee)
		{
			System.out.println("Connect failed :-(");
			System.out.println("CreateUserTable :: Exception in Connect in "+eee.getMessage());
			msg = "<p><br><center><font color=red> ConnectDB :: Exception in Connect in "+eee.getMessage();
			eee.printStackTrace();
		}

		return stmt;
	}

	public Statement connect()
	{
		return connect(true);
	}

	public void close(){
		try{
			stmt.close();
		}catch(Exception e){}
		try{
			con.close();
			System.out.println("Closed :-)");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}



    protected void finalize(){
		close();
	}

	public String getMessage(){
		return msg;
	}

	public static void main(String arg[]){
		ConnectDB dd = new ConnectDB();
		dd.connect();
		dd.close();
	}

}

