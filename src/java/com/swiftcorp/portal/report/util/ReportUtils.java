package com.swiftcorp.portal.report.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import com.swiftcorp.portal.common.ViewValueDTO;

public class ReportUtils
{
	
	public static List<ViewValueDTO> getMonths ( )
	{
		List<ViewValueDTO> list = new ArrayList<ViewValueDTO> ();
		
		ViewValueDTO month = new ViewValueDTO ( "January", 1 );
		list.add ( month );
		month = new ViewValueDTO ( "February", 2 );
		list.add ( month );
		month = new ViewValueDTO ( "March", 3 );
		list.add ( month );
		month = new ViewValueDTO ( "April", 4 );
		list.add ( month );
		month = new ViewValueDTO ( "May", 5 );
		list.add ( month );
		month = new ViewValueDTO ( "June", 6 );
		list.add ( month );
		month = new ViewValueDTO ( "July", 7 );
		list.add ( month );
		month = new ViewValueDTO ( "August", 8 );
		list.add ( month );
		month = new ViewValueDTO ( "September", 9 );
		list.add ( month );
		month = new ViewValueDTO ( "October", 10 );
		list.add ( month );
		month = new ViewValueDTO ( "November", 11 );
		list.add ( month );
		month = new ViewValueDTO ( "December", 12 );
		list.add ( month );
		
		return list;
	}
	
	public static List<ViewValueDTO> getYears ( )
	{
		List<ViewValueDTO> list = new ArrayList<ViewValueDTO> ();
		
		ViewValueDTO month = new ViewValueDTO ( "2008", 2008 );
		list.add ( month );
		month = new ViewValueDTO ( "2009", 2009 );
		list.add ( month );
		month = new ViewValueDTO ( "2010", 2010 );
		list.add ( month );
		month = new ViewValueDTO ( "2011", 2011 );
		list.add ( month );
		month = new ViewValueDTO ( "2012", 2012 );
		list.add ( month );
		month = new ViewValueDTO ( "2013", 2013 );
		list.add ( month );
		month = new ViewValueDTO ( "2014", 2014 );
		list.add ( month );
		month = new ViewValueDTO ( "2015", 2015 );
		list.add ( month );
		month = new ViewValueDTO ( "2016", 2016 );
		list.add ( month );
		month = new ViewValueDTO ( "2017", 2017 );
		list.add ( month );
		month = new ViewValueDTO ( "2018", 2018 );
		list.add ( month );
		month = new ViewValueDTO ( "2019", 2019 );
		list.add ( month );
		month = new ViewValueDTO ( "2020", 2020 );
		list.add ( month );
		return list;
	}
	public int listType(String property)
	{
		int type=0;
		if(property.equals("Household:")||property.equals("Population:")||property.equals("ELCO Visited")||property.equals("Pill")||property.equals("Condom")||property.equals("Injection")||property.equals("IUD")||property.equals("Norplant")||property.equals("Vasectomy")||property.equals("Tubectomy"))
			type=1;
		return type;
	}
	
	public static void generateReport ( String templateFilePath, Map beans, String destFileName )
	{
		try
		{
			XLSTransformer transformer = new XLSTransformer ();
			transformer.transformXLS ( templateFilePath, beans, destFileName );
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
	}
	
	public static String getDeathReportFileName ( String reportDir, String fileName, int year )
	{
		String fullFilePath = reportDir + File.separatorChar + fileName + "-" + year + ".xls";
		
		return fullFilePath;
	}
}
