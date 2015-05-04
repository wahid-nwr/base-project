/**
 * 
 */
package com.swiftcorp.portal.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import com.swiftcorp.portal.algorithm.dto.AlgorithmDTO;
import com.swiftcorp.portal.algorithm.service.AlgorithmServiceImpl;
import com.swiftcorp.portal.algorithm.service.SyncQuestionUtil;
import com.swiftcorp.portal.dto.request.SyncQuestionRequestDTO;
import com.swiftcorp.portal.dto.sync.NewSyncQuestionRespDTO;
import com.swiftcorp.portal.question.dto.QuestionnaireDTO;
import com.swiftcorp.portal.question.service.QuestionServiceImpl;

/**
 * @author asraful.haque
 * 
 */
@WebService(serviceName = "syncQuestionsService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class SyncQuestionsWSEndPoint
{
	//
	private AlgorithmServiceImpl algorithmServiceImpl;
	private QuestionServiceImpl questionServiceImpl;
	private SyncQuestionUtil syncQuestionUtil;
	
	public SyncQuestionsWSEndPoint ( )
	{
		
	}
	
	@WebMethod
	@SOAPBinding(parameterStyle = ParameterStyle.WRAPPED)
	public NewSyncQuestionRespDTO syncQuestions ( SyncQuestionRequestDTO syncQuestionRequestDTO )
	{
		System.out.println ( "Now trying to sync the question" );
		// get the algorithm list from this dto
		List<AlgorithmDTO> algorithmList = syncQuestionRequestDTO.getAlgorithmList ();
		
		// get the questionnaire List
		List<QuestionnaireDTO> questionnaireList = syncQuestionRequestDTO.getQuestionnaireList ();
		
		// get the list from the algorithm service
		algorithmList = this.algorithmServiceImpl.getSyncAlgorithmList ( algorithmList );
		
		// get the questionnaire list
		questionnaireList = this.questionServiceImpl.getSyncQuestionnaireList ( questionnaireList );
		
		// get cas algorithm list from the sdp
		// now convert it to cas algorithm
		
		NewSyncQuestionRespDTO newSyncQuestionRespDTO = syncQuestionUtil.getSyncQuestionResponseFromAlgorithmQuestions ( algorithmList, questionnaireList );
		// SyncQuestionResponseDTO syncQuestionResponseDTO = new
		// SyncQuestionResponseDTO ();
		// syncQuestionResponseDTO.setAlgorithmList ( algorithmList );
		// syncQuestionResponseDTO.setQuestionnaireList ( questionnaireList );
		
		return newSyncQuestionRespDTO;
	}
	
	// Getters and setters
	public AlgorithmServiceImpl getAlgorithmServiceImpl ( )
	{
		return algorithmServiceImpl;
	}
	
	public void setAlgorithmServiceImpl ( AlgorithmServiceImpl algorithmServiceImpl )
	{
		this.algorithmServiceImpl = algorithmServiceImpl;
	}
	
	public QuestionServiceImpl getQuestionServiceImpl ( )
	{
		return questionServiceImpl;
	}
	
	public void setQuestionServiceImpl ( QuestionServiceImpl questionServiceImpl )
	{
		this.questionServiceImpl = questionServiceImpl;
	}
	
	public SyncQuestionUtil getSyncQuestionUtil ( )
	{
		return syncQuestionUtil;
	}
	
	public void setSyncQuestionUtil ( SyncQuestionUtil syncQuestionUtil )
	{
		this.syncQuestionUtil = syncQuestionUtil;
	}
	
}
