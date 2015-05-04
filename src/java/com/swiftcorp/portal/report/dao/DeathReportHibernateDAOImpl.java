/**
 * 
 */
package com.swiftcorp.portal.report.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.report.dto.DeathReportDTOConstants;

/**
 * @author asraful.haque
 * 
 */

public class DeathReportHibernateDAOImpl extends HibernateDaoSupport implements IDeathReportDAO
{
	private static final Log log = LogFactory.getLog ( DeathReportHibernateDAOImpl.class );
	
	public ArrayList getDeathTimeRecord ( int beneficiaryType, int year, int timeLowerLimit, int timeUpperLimit )
			throws SystemException
	{
		log.info ( "getList: Enter" );
		
		Object[] params =
		{
				beneficiaryType, year, timeLowerLimit, timeUpperLimit
		};
		
		ArrayList result = new ArrayList ();
		
		// queryStr.append (
		// "SELECT  count(deathRecord.componentId), deathRecord.monthOfDeath  FROM DeathRecordDTO deathRecord group by deathRecord.monthOfDeath"
		// );
		String deathTimeQuery = "SELECT  count(deathRecord.componentId),  deathRecord.monthOfDeath  FROM DeathRecordDTO deathRecord where deathRecord.beneficiaryType=? and YEAR(deathRecord.timeOfDeath) = ? and (hour(deathRecord.timeOfDeath)*60+minute(deathRecord.timeOfDeath))>=? and (hour(deathRecord.timeOfDeath)*60+minute(deathRecord.timeOfDeath))<? group by deathRecord.monthOfDeath ";
		
		try
		{
			// result = (ArrayList) getHibernateTemplate ().find (
			// queryStr.toString () );
			result = (ArrayList) getHibernateTemplate ().find ( deathTimeQuery, params );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		return result;
	}
	
	public ArrayList getDeathRecordByPregStage ( int year, String pregStage )
			throws SystemException
	{
		log.info ( "getDeathRecordByPregStage: Enter" );
		
		ArrayList result = new ArrayList ();
		// AS it is for pregnancy stage, it will be for mother
		int motherBeneficiaryType = DeathReportDTOConstants.MOTHER_BENEFICIARY_TYPE;
		
		Object[] params =
		{
				motherBeneficiaryType, year, pregStage
		};
		
		String deathTimeQuery = "SELECT  count(deathRecord.componentId),  deathRecord.monthOfDeath  FROM DeathRecordDTO deathRecord where deathRecord.beneficiaryType=? and YEAR(deathRecord.timeOfDeath) = ? and deathRecord.pregnancyStage=? group by deathRecord.monthOfDeath ";
		
		try
		{
			// result = (ArrayList) getHibernateTemplate ().find (
			// queryStr.toString () );
			result = (ArrayList) getHibernateTemplate ().find ( deathTimeQuery, params );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		return result;
	}
	
	public ArrayList getDeathRecordByDeathPlace ( int beneficiaryType, int year, String deathPlace )
			throws SystemException
	{
		log.info ( "getDeathRecordByPregStage: Enter" );
		
		ArrayList result = new ArrayList ();
		
		Object[] params =
		{
				beneficiaryType, year, deathPlace
		};
		
		String deathTimeQuery = "SELECT  count(deathRecord.componentId),  deathRecord.monthOfDeath  FROM DeathRecordDTO deathRecord where deathRecord.beneficiaryType=? and YEAR(deathRecord.timeOfDeath) = ? and deathRecord.placeOfDeath=? group by deathRecord.monthOfDeath ";
		
		try
		{
			// result = (ArrayList) getHibernateTemplate ().find (
			// queryStr.toString () );
			result = (ArrayList) getHibernateTemplate ().find ( deathTimeQuery, params );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		return result;
	}
	
	public ArrayList getDeathRecordByDeathCause ( int beneficiaryType, int year, String deathCause )
			throws SystemException
	{
		log.info ( "getDeathRecordByPregStage: Enter" );
		
		ArrayList result = new ArrayList ();
		
		Object[] params =
		{
				beneficiaryType, year, deathCause
		};
		
		String deathTimeQuery = "SELECT  count(deathRecord.componentId),  deathRecord.monthOfDeath  FROM DeathRecordDTO deathRecord where deathRecord.beneficiaryType=? and YEAR(deathRecord.timeOfDeath) = ? and deathRecord.causeOfDeath=? group by deathRecord.monthOfDeath ";
		
		try
		{
			// result = (ArrayList) getHibernateTemplate ().find (
			// queryStr.toString () );
			result = (ArrayList) getHibernateTemplate ().find ( deathTimeQuery, params );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		return result;
	}
	
	public ArrayList getDeathRecordByMotherGravid ( int beneficiaryType, int year, int motherGravid )
			throws SystemException
	{
		log.info ( "getDeathRecordByPregStage: Enter" );
		
		ArrayList result = new ArrayList ();
		
		Object[] params =
		{
				beneficiaryType, year, motherGravid
		};
		
		String deathTimeQuery = "SELECT  count(deathRecord.componentId),  deathRecord.monthOfDeath  FROM DeathRecordDTO deathRecord where deathRecord.beneficiaryType=? and YEAR(deathRecord.timeOfDeath) = ? and deathRecord.gravidOfMother=? group by deathRecord.monthOfDeath ";
		
		try
		{
			// result = (ArrayList) getHibernateTemplate ().find (
			// queryStr.toString () );
			result = (ArrayList) getHibernateTemplate ().find ( deathTimeQuery, params );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		return result;
	}
	
	public ArrayList getDeathRecordForUpperGravid ( int beneficiaryType, int year, int motherGravidUpperLimit )
			throws SystemException
	{
		log.info ( "getDeathRecordByPregStage: Enter" );
		
		ArrayList result = new ArrayList ();
		
		Object[] params =
		{
				beneficiaryType, year, motherGravidUpperLimit
		};
		
		String deathTimeQuery = "SELECT  count(deathRecord.componentId),  deathRecord.monthOfDeath  FROM DeathRecordDTO deathRecord where deathRecord.beneficiaryType=? and YEAR(deathRecord.timeOfDeath) = ? and deathRecord.gravidOfMother >=? group by deathRecord.monthOfDeath ";
		
		try
		{
			// result = (ArrayList) getHibernateTemplate ().find (
			// queryStr.toString () );
			result = (ArrayList) getHibernateTemplate ().find ( deathTimeQuery, params );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		return result;
	}
	
	public ArrayList getDeathRecordByMotherAge ( int beneficiaryType, int year, int motherAgeUpperLimit, int motherAgeLowerLimit )
			throws SystemException
	{
		log.info ( "getDeathRecordByPregStage: Enter" );
		
		ArrayList result = new ArrayList ();
		
		Object[] params =
		{
				beneficiaryType, year, motherAgeLowerLimit, motherAgeUpperLimit
		};
		
		String deathTimeQuery = "SELECT  count(deathRecord.componentId),  deathRecord.monthOfDeath  FROM DeathRecordDTO deathRecord where deathRecord.beneficiaryType=? and YEAR(deathRecord.timeOfDeath) = ? and deathRecord.ageOfMother >=? and deathRecord.ageOfMother <? group by deathRecord.monthOfDeath ";
		
		try
		{
			// result = (ArrayList) getHibernateTemplate ().find (
			// queryStr.toString () );
			result = (ArrayList) getHibernateTemplate ().find ( deathTimeQuery, params );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		return result;
	}
	
	public ArrayList getDeathRecordByEconomicCondition ( int beneficiaryType, int year, String economicCondition )
			throws SystemException
	{
		log.info ( "getDeathRecordByPregStage: Enter" );
		
		ArrayList result = new ArrayList ();
		
		Object[] params =
		{
				beneficiaryType, year, economicCondition
		};
		
		String deathTimeQuery = "SELECT  count(deathRecord.componentId),  deathRecord.monthOfDeath  FROM DeathRecordDTO deathRecord where deathRecord.beneficiaryType=? and YEAR(deathRecord.timeOfDeath) = ? and deathRecord.economicCondition=? group by deathRecord.monthOfDeath ";
		
		try
		{
			// result = (ArrayList) getHibernateTemplate ().find (
			// queryStr.toString () );
			result = (ArrayList) getHibernateTemplate ().find ( deathTimeQuery, params );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		return result;
	}
	
}
