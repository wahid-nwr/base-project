package com.swiftcorp.portal.report.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.report.dto.BeneficiaryDeathRecord;

public class ReportSQLDAOImpl implements IReportDAO
{
	protected static final Log log = LogFactory.getLog ( ReportSQLDAOImpl.class );
	private DriverManagerDataSource sqlDataSource = null;
	private DataSource dataSource;

    public void setDataSource(DataSource dataSource) 
    {
        this.dataSource = dataSource;
    }
    
   
    public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setSqlDataSource ( DriverManagerDataSource dataSource )
	{
		this.sqlDataSource = dataSource;
	}
	
	public Connection getConnection ( ) throws SystemException
	{
		Connection connection = null;
		try
		{
			
			connection = sqlDataSource.getConnection ();
			
		}
		catch (SQLException e)
		{
			e.printStackTrace ();
		}
		return connection;
	}
	
	public List<BeneficiaryDeathRecord> getBeneficiaryDeathDTOList(Date fromDate, Date toDate) 
	{
		
		log.info("getGatewayDTOList(carrierId):Enter");
		
		List<BeneficiaryDeathRecord> beneficiaryDeathRecords = null;
		try
		{
			System.out.println(" getBeneficiaryDeathDTOList ");
			MPRStoredProcedure proc = new MPRStoredProcedure(getDataSource());	
			System.out.println("Print here-2 ");
			proc.execute(fromDate,toDate);		
			beneficiaryDeathRecords = proc.getBeneficiaryDeathRecords();			
		}
		catch(Throwable t)
		{
			log.error("getGatewayDTOList(carrierId): Exit with FAILED!",t);
			return null;
		}
		
		log.info("getGatewayDTOList(carrierId): Exit with gatewayList = " + beneficiaryDeathRecords);
		return beneficiaryDeathRecords;
	}
	
}
