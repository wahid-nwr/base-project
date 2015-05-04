/*
 * Copyright (c) 2005 Vonair Inc. All  Rights Reserved.
 * This software is the confidential and proprietary information
 * of Vonair ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Vonair.
 */

package com.swiftcorp.portal.report.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

import com.swiftcorp.portal.report.dto.BeneficiaryDeathRecord;
import com.swiftcorp.portal.report.dto.MPRIndexDTO;
import com.swiftcorp.portal.report.dto.MonthwiseCountRecord;

//import com.vonair.marteleron.gateway.dto.GatewayDTO;

/**
 * This class is designed to call stored procedure of DATABASE for saving gateway information
 * @author Zahangir Alam
 * @since Jan 3, 2009
 */
public class MPRStoredProcedure extends StoredProcedure 
{
	private static final Log log = LogFactory.getLog(MPRStoredProcedure.class);
	
    private static final String STORED_PROC_NAME = "report";
    private List<BeneficiaryDeathRecord> beneficiaryDeathRecords = new ArrayList<BeneficiaryDeathRecord>();
    private List<BeneficiaryDeathRecord> maternalDeathRecords = new ArrayList<BeneficiaryDeathRecord>();
    private MPRIndexDTO  mprIndexDTO = new MPRIndexDTO();
    
    public MPRStoredProcedure(DataSource ds) {
        super(ds, STORED_PROC_NAME);

        //Return Resultset parameters
        declareParameter(new SqlReturnResultSet("rs", new RowCallbackHandlerImpl()));

        //input parameters
        declareParameter(new SqlParameter("FROM_DATE", Types.DATE));
        declareParameter(new SqlParameter("TO_DATE", Types.DATE));
        System.out.println(" MPRStoredProcedure ");
        
        try
        {
            compile();
        }
        catch (Throwable t)
        {
        	t.printStackTrace();
        }
        
        System.out.println(" MPRStoredProcedure compile ");
    }

	public void execute(Date fromDate, Date toDate) 
    {
		beneficiaryDeathRecords.clear();
		maternalDeathRecords.clear();
        Map<String, Object> inParams = new HashMap<String, Object>();        
        inParams.put("FROM_DATE", fromDate);
        inParams.put("TO_DATE", toDate);
                
        execute(inParams);
    }
		
    private class RowCallbackHandlerImpl implements RowCallbackHandler {
    	public void processRow(ResultSet rs) throws SQLException 
    	{
    		try
    		{
    			String propertyName = rs.getString(1);
    			
    			BeneficiaryDeathRecord beneficiaryDeathRecord = new BeneficiaryDeathRecord(propertyName);
    			MonthwiseCountRecord monthwiseCountRecord = new MonthwiseCountRecord();
    			monthwiseCountRecord.setJanCount(rs.getLong(2));
    			monthwiseCountRecord.setFebCount(Integer.parseInt(rs.getString(3)));
    			monthwiseCountRecord.setMarCount(Integer.parseInt(rs.getString(4)));
    			//monthwiseCountRecord.setMarCount(rs.getString(1));
    			monthwiseCountRecord.setAprCount(rs.getLong(5));
    			monthwiseCountRecord.setMayCount(rs.getLong(6));
    			monthwiseCountRecord.setJunCount(rs.getLong(7));
    			monthwiseCountRecord.setJulCount(rs.getLong(8));
    			monthwiseCountRecord.setAugCount(rs.getLong(9));
    			monthwiseCountRecord.setSepCount(rs.getLong(10));
    			monthwiseCountRecord.setOctCount(rs.getLong(11));
    			monthwiseCountRecord.setNovCount(rs.getLong(12));
    			monthwiseCountRecord.setDecCount(rs.getLong(13));
    			//System.out.println("RowCallbackHandlerImpl");
    			beneficiaryDeathRecord.setMonthwiseCountRecord(monthwiseCountRecord);
    			
    			beneficiaryDeathRecords.add(beneficiaryDeathRecord);
    			/*
    			ReportUtils reportUtils = new ReportUtils();
    			int type = reportUtils.listType(propertyName);
    			if(type==1)
    			{
    				beneficiaryDeathRecords.add(beneficiaryDeathRecord);
    			}
    			else
    			{
    				maternalDeathRecords.add(beneficiaryDeathRecord);
    				
    			}
    			*/
    			if(propertyName.equals("Total Population:"))
    			{
    				propertyName="Total household visited";
    				beneficiaryDeathRecord = new BeneficiaryDeathRecord(propertyName);
        			monthwiseCountRecord.setJanCount(rs.getLong(2));
        			monthwiseCountRecord.setFebCount(Integer.parseInt(rs.getString(3)));
        			monthwiseCountRecord.setMarCount(Integer.parseInt(rs.getString(4)));
        			monthwiseCountRecord.setAprCount(rs.getLong(5));
        			monthwiseCountRecord.setMayCount(rs.getLong(6));
        			monthwiseCountRecord.setJunCount(rs.getLong(7));
        			monthwiseCountRecord.setJulCount(rs.getLong(8));
        			monthwiseCountRecord.setAugCount(rs.getLong(9));
        			monthwiseCountRecord.setSepCount(rs.getLong(10));
        			monthwiseCountRecord.setOctCount(rs.getLong(11));
        			monthwiseCountRecord.setNovCount(rs.getLong(12));
        			monthwiseCountRecord.setDecCount(rs.getLong(13));
        			//System.out.println("RowCallbackHandlerImpl");
        			beneficiaryDeathRecord.setMonthwiseCountRecord(monthwiseCountRecord);
        			beneficiaryDeathRecords.add(beneficiaryDeathRecord);
    			}
    			else if(propertyName.equals("    FP Method Acceptor-Tubectomy"))
    			{
    				propertyName="Total";
    				beneficiaryDeathRecord = new BeneficiaryDeathRecord(propertyName);
    				long jantotal=0;
    				long febtotal=0;
    				long martotal=0;
    				long aprtotal=0;
    				long maytotal=0;
    				long juntotal=0;
    				long jultotal=0;
    				long augtotal=0;
    				long septotal=0;
    				long octtotal=0;
    				long novtotal=0;
    				long dectotal=0;
    				for(int i=5;i< beneficiaryDeathRecords.size();i++)
    				{
    					jantotal =jantotal + beneficiaryDeathRecords.get( i ).getMonthwiseCountRecord().getJanCount();
    					febtotal = febtotal + beneficiaryDeathRecords.get( i ).getMonthwiseCountRecord().getFebCount();
    					martotal = martotal + beneficiaryDeathRecords.get( i ).getMonthwiseCountRecord().getMarCount();
    					aprtotal = aprtotal + beneficiaryDeathRecords.get( i ).getMonthwiseCountRecord().getAprCount();
    					maytotal = maytotal + beneficiaryDeathRecords.get( i ).getMonthwiseCountRecord().getMayCount();
    					juntotal = juntotal + beneficiaryDeathRecords.get( i ).getMonthwiseCountRecord().getJunCount();
    					jultotal = jultotal + beneficiaryDeathRecords.get( i ).getMonthwiseCountRecord().getJulCount();
    					augtotal = augtotal + beneficiaryDeathRecords.get( i ).getMonthwiseCountRecord().getAugCount();
    					septotal = septotal + beneficiaryDeathRecords.get( i ).getMonthwiseCountRecord().getSepCount();
    					octtotal = octtotal + beneficiaryDeathRecords.get( i ).getMonthwiseCountRecord().getOctCount();
    					novtotal = novtotal + beneficiaryDeathRecords.get( i ).getMonthwiseCountRecord().getNovCount();	
    					dectotal = dectotal + beneficiaryDeathRecords.get( i ).getMonthwiseCountRecord().getDecCount();
    					
    				}	
    				monthwiseCountRecord.setJanCount(jantotal);
        			monthwiseCountRecord.setFebCount(febtotal);
        			monthwiseCountRecord.setMarCount(martotal);
        			monthwiseCountRecord.setAprCount(aprtotal);
        			monthwiseCountRecord.setMayCount(maytotal);
        			monthwiseCountRecord.setJunCount(juntotal);
        			monthwiseCountRecord.setJulCount(jultotal);
        			monthwiseCountRecord.setAugCount(augtotal);
        			monthwiseCountRecord.setSepCount(septotal);
        			monthwiseCountRecord.setOctCount(octtotal);
        			monthwiseCountRecord.setNovCount(novtotal);
        			monthwiseCountRecord.setDecCount(dectotal);
        			beneficiaryDeathRecord.setMonthwiseCountRecord(monthwiseCountRecord);
        			beneficiaryDeathRecords.add(beneficiaryDeathRecord);
    				
    			}
    			if(propertyName.equals("Total"))
    			{
    				propertyName="Contraceptive acceptance rate (CAR)";
    				beneficiaryDeathRecord = new BeneficiaryDeathRecord(propertyName);
    				long jantotal=beneficiaryDeathRecords.get( 4 ).getMonthwiseCountRecord().getJanCount();
    				long febtotal=beneficiaryDeathRecords.get( 4 ).getMonthwiseCountRecord().getFebCount();
    				long martotal=beneficiaryDeathRecords.get( 4 ).getMonthwiseCountRecord().getMarCount();
    				long aprtotal=beneficiaryDeathRecords.get( 4 ).getMonthwiseCountRecord().getAprCount();
    				long maytotal=beneficiaryDeathRecords.get( 4 ).getMonthwiseCountRecord().getMayCount();
    				long juntotal=beneficiaryDeathRecords.get( 4 ).getMonthwiseCountRecord().getJunCount();
    				long jultotal=beneficiaryDeathRecords.get( 4 ).getMonthwiseCountRecord().getJulCount();
    				long augtotal=beneficiaryDeathRecords.get( 4 ).getMonthwiseCountRecord().getAugCount();
    				long septotal=beneficiaryDeathRecords.get( 4 ).getMonthwiseCountRecord().getSepCount();
    				long octtotal=beneficiaryDeathRecords.get( 4 ).getMonthwiseCountRecord().getOctCount();
    				long novtotal=beneficiaryDeathRecords.get( 4 ).getMonthwiseCountRecord().getNovCount();
    				long dectotal=beneficiaryDeathRecords.get( 4 ).getMonthwiseCountRecord().getDecCount();
    				
    				if(jantotal!=0)
    				{
    					jantotal =( ( beneficiaryDeathRecords.get( 12 ).getMonthwiseCountRecord().getJanCount()) /( jantotal ) )*100;
    				}
    				if(febtotal!=0)
    				{
    					febtotal = (  ( beneficiaryDeathRecords.get( 12 ).getMonthwiseCountRecord().getFebCount() ) /  ( febtotal ))*100;
    				}
    				if(martotal!=0)
    				{
    					martotal = (  ( beneficiaryDeathRecords.get( 12 ).getMonthwiseCountRecord().getMarCount() ) /  ( martotal ))*100;
    				}
    				if(aprtotal!=0)
    				{
    				
    					aprtotal = (  ( beneficiaryDeathRecords.get( 12 ).getMonthwiseCountRecord().getAprCount() )/  ( aprtotal  ))*100;
    				}
    				if(maytotal!=0)
    				{
    				
    					maytotal = (  (  beneficiaryDeathRecords.get( 12 ).getMonthwiseCountRecord().getMayCount() )/  ( maytotal ))*100;
    				}
    				if(juntotal!=0)
    				{
    				
    					juntotal = (  ( beneficiaryDeathRecords.get( 12 ).getMonthwiseCountRecord().getJunCount() ) / ( juntotal ))*100;
    				}
    				if(jultotal!=0)
    				{
    				
    					jultotal = (  ( beneficiaryDeathRecords.get( 12 ).getMonthwiseCountRecord().getJulCount() )/  ( jultotal ))*100;
    				}
    				if(augtotal!=0)
    				{
    					augtotal = (  ( beneficiaryDeathRecords.get( 12 ).getMonthwiseCountRecord().getAugCount() )/  ( augtotal ))*100;
    				}
    				if(septotal!=0)
    				{
    				
    					septotal = (  ( beneficiaryDeathRecords.get( 12 ).getMonthwiseCountRecord().getSepCount() )/  ( septotal ))*100;
    				}
    				if(octtotal!=0)
    				{
    				
    					octtotal = (  (  beneficiaryDeathRecords.get( 12 ).getMonthwiseCountRecord().getOctCount() ) / (  octtotal ))*100;
    				}
    				if(novtotal!=0)
    				{
    				
    					novtotal = ( ( beneficiaryDeathRecords.get( 12 ).getMonthwiseCountRecord().getNovCount() )/   ( novtotal ))*100;	
    				}
    				if(dectotal!=0)
    				{
    				
    					dectotal = (( beneficiaryDeathRecords.get( 12 ).getMonthwiseCountRecord().getDecCount() )/   ( dectotal ))*100;
    				}		
    
    				monthwiseCountRecord.setJanCount(jantotal);
        			monthwiseCountRecord.setFebCount(febtotal);
        			monthwiseCountRecord.setMarCount(martotal);
        			monthwiseCountRecord.setAprCount(aprtotal);
        			monthwiseCountRecord.setMayCount(maytotal);
        			monthwiseCountRecord.setJunCount(juntotal);
        			monthwiseCountRecord.setJulCount(jultotal);
        			monthwiseCountRecord.setAugCount(augtotal);
        			monthwiseCountRecord.setSepCount(septotal);
        			monthwiseCountRecord.setOctCount(octtotal);
        			monthwiseCountRecord.setNovCount(novtotal);
        			monthwiseCountRecord.setDecCount(dectotal);
        			beneficiaryDeathRecord.setMonthwiseCountRecord(monthwiseCountRecord);
        			beneficiaryDeathRecords.add(beneficiaryDeathRecord);
    			}
    			
    			
    		}
    		catch(RuntimeException e)
    		{
    			log.error("RowCallbackHandlerImpl. processRow(ResultSet):",e);
    		}
    	}
    }
    
    public MPRIndexDTO getMprIndexDTO() {
		return mprIndexDTO;
	}

	public List<BeneficiaryDeathRecord> getBeneficiaryDeathRecords()
    {
    	return beneficiaryDeathRecords;
    }

	public List<BeneficiaryDeathRecord> getMaternalDeathRecords() {
		return maternalDeathRecords;
	}
	
	
}
