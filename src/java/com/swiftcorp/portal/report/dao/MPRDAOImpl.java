package com.swiftcorp.portal.report.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.report.dto.BeneficiaryDeathRecord;

//import com.vonair.marteleron.gateway.dto.GatewayDTO;

/**
 * This is the main implementor class of <code>IGatewayDAO</code> interface 
 * @author Zahangir Alam
 * @since Jan 4, 2009
 */
public class MPRDAOImpl extends HibernateDaoSupport implements IReportDAO 
{
	private static final Log log = LogFactory.getLog(MPRDAOImpl.class);
	
	private DataSource dataSource;

    public void setDataSource(DataSource dataSource) 
    {
        this.dataSource = dataSource;
    }
    
   
    public DataSource getDataSource() {
		return dataSource;
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
