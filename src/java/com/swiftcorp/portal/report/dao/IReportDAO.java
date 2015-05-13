package com.swiftcorp.portal.report.dao;

import java.util.Date;
import java.util.List;

import com.swiftcorp.portal.report.dto.BeneficiaryDeathRecord;

public interface IReportDAO
{
	public List<BeneficiaryDeathRecord> getBeneficiaryDeathDTOList(Date fromDate, Date toDate) ;
}
