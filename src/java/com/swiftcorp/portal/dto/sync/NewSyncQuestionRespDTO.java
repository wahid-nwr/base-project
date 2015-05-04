/**
 * 
 */
package com.swiftcorp.portal.dto.sync;

import java.util.ArrayList;

import com.swiftcorp.portal.dto.ResponseDTO;

/**
 * @author asraful.haque
 * 
 */
public class NewSyncQuestionRespDTO extends ResponseDTO
{
	// algorithm dto list
	private ArrayList<CASAlgorithmDTO> casAlgorithmDTOList;
	
	// Questionnaire dto list
	private ArrayList<CASQuestionnaireDTO> casQuestionnaireDTOList;
	
	public ArrayList<CASAlgorithmDTO> getCasAlgorithmDTOList ( )
	{
		return casAlgorithmDTOList;
	}
	
	public void setCasAlgorithmDTOList ( ArrayList<CASAlgorithmDTO> casAlgorithmDTOList )
	{
		this.casAlgorithmDTOList = casAlgorithmDTOList;
	}
	
	public ArrayList<CASQuestionnaireDTO> getCasQuestionnaireDTOList ( )
	{
		return casQuestionnaireDTOList;
	}
	
	public void setCasQuestionnaireDTOList ( ArrayList<CASQuestionnaireDTO> casQuestionnaireDTOList )
	{
		this.casQuestionnaireDTOList = casQuestionnaireDTOList;
	}
	
}
