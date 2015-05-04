/**
 * 
 */
package com.swiftcorp.portal.algorithm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.swiftcorp.portal.algorithm.dto.AlgAnswerDTO;
import com.swiftcorp.portal.algorithm.dto.AlgQuestionBranchDTO;
import com.swiftcorp.portal.algorithm.dto.AlgQuestionDTO;
import com.swiftcorp.portal.algorithm.dto.AlgorithmDTO;
import com.swiftcorp.portal.algorithm.dto.SubAlgorithmDTO;
import com.swiftcorp.portal.common.dto.DTOConstants;
import com.swiftcorp.portal.dto.sync.CASAlgAnswerDTO;
import com.swiftcorp.portal.dto.sync.CASAlgQuestionBranchDTO;
import com.swiftcorp.portal.dto.sync.CASAlgQuestionDTO;
import com.swiftcorp.portal.dto.sync.CASAlgorithmDTO;
import com.swiftcorp.portal.dto.sync.CASQDTO;
import com.swiftcorp.portal.dto.sync.CASQuestionDTO;
import com.swiftcorp.portal.dto.sync.CASQuestionnaireDTO;
import com.swiftcorp.portal.dto.sync.CASSubAlgorithmDTO;
import com.swiftcorp.portal.dto.sync.NewSyncQuestionRespDTO;
import com.swiftcorp.portal.question.dto.MCQOptionDTO;
import com.swiftcorp.portal.question.dto.QDTO;
import com.swiftcorp.portal.question.dto.QuestionDTO;
import com.swiftcorp.portal.question.dto.QuestionnaireDTO;
import com.swiftcorp.portal.question.dto.ValidationDTO;

/**
 * @author asraful.haque
 * 
 */
public class SyncQuestionUtil
{
	public NewSyncQuestionRespDTO getSyncQuestionResponseFromAlgorithmQuestions ( List<AlgorithmDTO> algorithmList, List<QuestionnaireDTO> questionnaireList )
	{
		// make the dto to return
		NewSyncQuestionRespDTO newSyncQuestionRespDTO = new NewSyncQuestionRespDTO ();
		
		// at first process the algorithm
		if ( algorithmList != null && algorithmList.size () > 0 )
		{
			ArrayList<CASAlgorithmDTO> casAlgorithmDTOList = new ArrayList<CASAlgorithmDTO> ();
			for ( AlgorithmDTO algorithmDTO : algorithmList )
			{
				// make a new cas algorithm
				CASAlgorithmDTO casAlgorithmDTO = this.getCasAlgorithmWithBasicAttr ( algorithmDTO );
				this.populateCasAlgorithmForList ( casAlgorithmDTO, algorithmDTO );
				
				// get the sub Algorithm list
				Set<SubAlgorithmDTO> subAlgorithmSet = algorithmDTO.getSubAlgorithmSet ();
				
				// if it has some sub algorithms
				if ( subAlgorithmSet.size () != 0 )
				{
					for ( SubAlgorithmDTO subAlgorithmDTO : subAlgorithmSet )
					{
						// get the sub algorithm
						CASSubAlgorithmDTO casSubAlgorithmDTO = getCasSubAlgorithmWithBasicAttr ( subAlgorithmDTO );
						this.populateCasSubAlgorithmForList ( casSubAlgorithmDTO, subAlgorithmDTO );
						
						// add it to list
						casAlgorithmDTO.getCasSubAlgorithmList ().add ( casSubAlgorithmDTO );
					}
				}
								
				casAlgorithmDTOList.add ( casAlgorithmDTO );				
				
			}
			newSyncQuestionRespDTO.setCasAlgorithmDTOList ( casAlgorithmDTOList );
		}
		
		// now process the questionnaire
		if ( questionnaireList != null && questionnaireList.size () > 0 )
		{
			for ( QuestionnaireDTO questionnaireDTO : questionnaireList )
			{
				
			}
		}
		// return null for now
		return newSyncQuestionRespDTO;
	}
	
	private CASAlgorithmDTO getCasAlgorithmWithBasicAttr ( AlgorithmDTO algorithmDTO )
	{
		// dto to return
		CASAlgorithmDTO casAlgorithmDTO = new CASAlgorithmDTO ();
		
		// now get the name , algid, first alg question id
		String name = algorithmDTO.getName ();
		String algId = algorithmDTO.getAlgId ();
		String version = algorithmDTO.getVersion ();
		String category = algorithmDTO.getCategory ();
		AlgQuestionDTO algQuestionDTO = algorithmDTO.getFirstAlgQuestion ();
		if ( algQuestionDTO != null )
		{
			String firstAlgQuestionId = "" + algQuestionDTO.getComponentId ();
			casAlgorithmDTO.setFirstAlgQuestionId ( firstAlgQuestionId );
		}
		
		// now set the attribute
		casAlgorithmDTO.setName ( name );
		casAlgorithmDTO.setAlgId ( algId );
		casAlgorithmDTO.setVersion ( version );
		casAlgorithmDTO.setCategory ( category );
		
		// return
		return casAlgorithmDTO;
	}
	
	private CASSubAlgorithmDTO getCasSubAlgorithmWithBasicAttr ( SubAlgorithmDTO  subAlgorithmDTO )
	{
		// dto to return
		CASSubAlgorithmDTO casSubAlgorithmDTO = new CASSubAlgorithmDTO ();
		
		// now get the name , algid, first alg question id
		String name = subAlgorithmDTO.getName ();
		String algId = subAlgorithmDTO.getAlgId ();
		String version = subAlgorithmDTO.getVersion ();
		String category = subAlgorithmDTO.getCategory ();
		AlgQuestionDTO algQuestionDTO = subAlgorithmDTO.getFirstAlgQuestion ();
		AlgQuestionDTO prevAlgQuestionDTO = subAlgorithmDTO.getPrevAlgQuestion ();
		AlgQuestionDTO nextAlgQuestionDTO = subAlgorithmDTO.getNextAlgQuestion ();
		
		if ( algQuestionDTO != null )
		{
			String firstAlgQuestionId = "" + algQuestionDTO.getComponentId ();
			casSubAlgorithmDTO.setFirstAlgQuestionId ( firstAlgQuestionId );
		}
		if ( prevAlgQuestionDTO != null )
		{
			String prevAlgQuestionId = "" + prevAlgQuestionDTO.getComponentId ();
			casSubAlgorithmDTO.setPrevAlgQuestionId ( prevAlgQuestionId );
		}
		if ( nextAlgQuestionDTO != null )
		{
			String nextAlgQuestionId = "" + nextAlgQuestionDTO.getComponentId ();
			casSubAlgorithmDTO.setNextAlgQuestionId ( nextAlgQuestionId );
		}
		// now set the attribute
		casSubAlgorithmDTO.setName ( name );
		casSubAlgorithmDTO.setAlgId ( algId );
		casSubAlgorithmDTO.setVersion ( version );
		casSubAlgorithmDTO.setCategory ( category );
		
		// return
		return casSubAlgorithmDTO;
	}
	
	private void populateCasAlgorithmForList ( CASAlgorithmDTO casAlgorithmDTO, AlgorithmDTO algorithmDTO )
	{
		
		AlgQuestionDTO algQuestionDTO = algorithmDTO.getFirstAlgQuestion ();
		if ( algQuestionDTO != null )
		{
			this.populateCASAlgorithmForAlgQuestion ( casAlgorithmDTO, algQuestionDTO );
		}
	}
	
	private void populateCasSubAlgorithmForList ( CASSubAlgorithmDTO casSubAlgorithmDTO, SubAlgorithmDTO algorithmDTO )
	{
		
		AlgQuestionDTO algQuestionDTO = algorithmDTO.getFirstAlgQuestion ();
		if ( algQuestionDTO != null )
		{
			this.populateCASSubAlgorithmForAlgQuestion ( casSubAlgorithmDTO, algQuestionDTO );
		}
	}
	
	private void populateCASAlgorithmForAlgQuestion ( CASAlgorithmDTO casAlgorithmDTO, AlgQuestionDTO algQuestionDTO )
	{
		
		CASAlgQuestionDTO casAlgQuestionDTO = this.getCASAlgQuestionDTOFromAlgQuestionDTo ( algQuestionDTO );
		ArrayList<CASAlgQuestionDTO> casAlgQuestionDTOList = casAlgorithmDTO.getCasAlgQuestionDTOList ();
		casAlgQuestionDTOList.add ( casAlgQuestionDTO );
		//System.out.println ("branch alu::"+algQuestionDTO.getBranchAlu ());
		//System.out.println ("branch alu::"+casAlgQuestionDTO.getBranchAlu ());
		// get the question or questionnaire
		QDTO qdto = algQuestionDTO.getQdto ();
		int qqType = qdto.getQqType ();
		
		if ( qqType == DTOConstants.QUESTION_TYPE )
		{
			QuestionDTO questionDTO = (QuestionDTO) qdto;
			CASQuestionDTO casQuestionDTO = this.getCASQuestionDTOFromQuestionDTo ( questionDTO );
			// algorithm question list
			ArrayList<CASQuestionDTO> casQuestionDTOList = casAlgorithmDTO.getCasQuestionDTOList ();
			casQuestionDTOList.add ( casQuestionDTO );
		}
		else if ( qqType == DTOConstants.QUESTIONNAIRE_TYPE )
		{
			QuestionnaireDTO questionnaireDTO = (QuestionnaireDTO) qdto;
			CASQuestionnaireDTO casQuestionnaireDTO = this.getCASQuestionnaireDTOFromQuestionnaireDTO ( questionnaireDTO );
			// algoirthm questionnaire list
			ArrayList<CASQuestionnaireDTO> casQuestionnaireDTOList = casAlgorithmDTO.getCasQuestionnaireDTOList ();
			casQuestionnaireDTOList.add ( casQuestionnaireDTO );
			
			// get the questions from here and add it to the list
			List<QuestionDTO> questionDTOList = questionnaireDTO.getQuestionList ();
			for ( QuestionDTO questionDTO : questionDTOList )
			{
				CASQuestionDTO casQuestionDTO = this.getCASQuestionDTOFromQuestionDTo ( questionDTO );
				// algorithm question list
				ArrayList<CASQuestionDTO> casQuestionDTOList = casAlgorithmDTO.getCasQuestionDTOList ();
				casQuestionDTOList.add ( casQuestionDTO );
			}
		}
		
		// now get the branch list
		List<AlgQuestionBranchDTO> algQuestionBranchDTOList = algQuestionDTO.getAlgQuestionBranchList ();
		
		if ( algQuestionBranchDTOList != null && algQuestionBranchDTOList.size () != 0 )
		{
			for ( AlgQuestionBranchDTO algQuestionBranchDTO : algQuestionBranchDTOList )
			{
				this.populateCASAlgorithmForAlgQuestionBranch ( casAlgorithmDTO, algQuestionBranchDTO );
			}
		}
		
	}
	
	private void populateCASSubAlgorithmForAlgQuestion ( CASSubAlgorithmDTO casAlgorithmDTO, AlgQuestionDTO algQuestionDTO )
	{
		
		CASAlgQuestionDTO casAlgQuestionDTO = this.getCASAlgQuestionDTOFromAlgQuestionDTo ( algQuestionDTO );
		ArrayList<CASAlgQuestionDTO> casAlgQuestionDTOList = casAlgorithmDTO.getCasAlgQuestionDTOList ();
		casAlgQuestionDTOList.add ( casAlgQuestionDTO );
		System.out.println ("branch alu::"+algQuestionDTO.getBranchAlu ());
		System.out.println ("branch alu::"+casAlgQuestionDTO.getBranchAlu ());
		// get the question or questionnaire
		QDTO qdto = algQuestionDTO.getQdto ();
		int qqType = qdto.getQqType ();
		
		if ( qqType == DTOConstants.QUESTION_TYPE )
		{
			QuestionDTO questionDTO = (QuestionDTO) qdto;
			CASQuestionDTO casQuestionDTO = this.getCASQuestionDTOFromQuestionDTo ( questionDTO );
			// algorithm question list
			ArrayList<CASQuestionDTO> casQuestionDTOList = casAlgorithmDTO.getCasQuestionDTOList ();
			casQuestionDTOList.add ( casQuestionDTO );
		}
		else if ( qqType == DTOConstants.QUESTIONNAIRE_TYPE )
		{
			QuestionnaireDTO questionnaireDTO = (QuestionnaireDTO) qdto;
			CASQuestionnaireDTO casQuestionnaireDTO = this.getCASQuestionnaireDTOFromQuestionnaireDTO ( questionnaireDTO );
			// algoirthm questionnaire list
			ArrayList<CASQuestionnaireDTO> casQuestionnaireDTOList = casAlgorithmDTO.getCasQuestionnaireDTOList ();
			casQuestionnaireDTOList.add ( casQuestionnaireDTO );
			
			// get the questions from here and add it to the list
			List<QuestionDTO> questionDTOList = questionnaireDTO.getQuestionList ();
			for ( QuestionDTO questionDTO : questionDTOList )
			{
				CASQuestionDTO casQuestionDTO = this.getCASQuestionDTOFromQuestionDTo ( questionDTO );
				// algorithm question list
				ArrayList<CASQuestionDTO> casQuestionDTOList = casAlgorithmDTO.getCasQuestionDTOList ();
				casQuestionDTOList.add ( casQuestionDTO );
			}
		}
		
		// now get the branch list
		List<AlgQuestionBranchDTO> algQuestionBranchDTOList = algQuestionDTO.getAlgQuestionBranchList ();
		
		if ( algQuestionBranchDTOList != null && algQuestionBranchDTOList.size () != 0 )
		{
			for ( AlgQuestionBranchDTO algQuestionBranchDTO : algQuestionBranchDTOList )
			{
				this.populateCASSubAlgorithmForAlgQuestionBranch ( casAlgorithmDTO, algQuestionBranchDTO );
			}
		}
		
	}
	
	private void populateCASAlgorithmForAlgQuestionBranch ( CASAlgorithmDTO casAlgorithmDTO, AlgQuestionBranchDTO algQuestionBranchDTO )
	{
		if ( algQuestionBranchDTO == null )
		{
			return;
		}
		CASAlgQuestionBranchDTO casAlgQuestionBranchDTO = getCASAlgQuestionBranchDTOFromAlgQuestionBranchDTO ( algQuestionBranchDTO );
		ArrayList<CASAlgQuestionBranchDTO> casAlgQuestionBranchDTOList = casAlgorithmDTO.getCasAlgQuestionBranchDTOList ();
		casAlgQuestionBranchDTOList.add ( casAlgQuestionBranchDTO );
		
		// get the question list
		List<AlgAnswerDTO> algAnswerDTOList = algQuestionBranchDTO.getAlgAnswerList ();
		if ( algAnswerDTOList != null && algAnswerDTOList.size () != 0 )
		{
			for ( AlgAnswerDTO algAnswerDTO : algAnswerDTOList )
			{
				if ( algAnswerDTO != null )
				{
					CASAlgAnswerDTO casAlgAnswerDTO = this.getCASAlgAnswerDTOFromAlgAnswerDTO ( algAnswerDTO );
					
					// now get the list and add it
					ArrayList<CASAlgAnswerDTO> casAlgAnswerDTOList = casAlgorithmDTO.getCasAlgAnswerDTOList ();
					casAlgAnswerDTOList.add ( casAlgAnswerDTO );
				}
				
			}
		}
		AlgQuestionDTO algQuestionDTO = algQuestionBranchDTO.getNextQuestion ();
		if ( algQuestionDTO != null )
		{
			this.populateCASAlgorithmForAlgQuestion ( casAlgorithmDTO, algQuestionDTO );
		}
		
	}
	
	private void populateCASSubAlgorithmForAlgQuestionBranch ( CASSubAlgorithmDTO casAlgorithmDTO, AlgQuestionBranchDTO algQuestionBranchDTO )
	{
		CASAlgQuestionBranchDTO casAlgQuestionBranchDTO = getCASAlgQuestionBranchDTOFromAlgQuestionBranchDTO ( algQuestionBranchDTO );
		ArrayList<CASAlgQuestionBranchDTO> casAlgQuestionBranchDTOList = casAlgorithmDTO.getCasAlgQuestionBranchDTOList ();
		casAlgQuestionBranchDTOList.add ( casAlgQuestionBranchDTO );
		
		// get the question list
		List<AlgAnswerDTO> algAnswerDTOList = algQuestionBranchDTO.getAlgAnswerList ();
		if ( algAnswerDTOList != null && algAnswerDTOList.size () != 0 )
		{
			for ( AlgAnswerDTO algAnswerDTO : algAnswerDTOList )
			{
				if ( algAnswerDTO != null )
				{
					CASAlgAnswerDTO casAlgAnswerDTO = this.getCASAlgAnswerDTOFromAlgAnswerDTO ( algAnswerDTO );
					
					// now get the list and add it
					ArrayList<CASAlgAnswerDTO> casAlgAnswerDTOList = casAlgorithmDTO.getCasAlgAnswerDTOList ();
					casAlgAnswerDTOList.add ( casAlgAnswerDTO );
				}
				
			}
		}
		AlgQuestionDTO algQuestionDTO = algQuestionBranchDTO.getNextQuestion ();
		if ( algQuestionDTO != null )
		{
			this.populateCASSubAlgorithmForAlgQuestion ( casAlgorithmDTO, algQuestionDTO );
		}
		
	}
	
	private CASAlgQuestionDTO getCASAlgQuestionDTOFromAlgQuestionDTo ( AlgQuestionDTO algQuestionDTO )
	{
		CASAlgQuestionDTO casAlgQuestionDTO = new CASAlgQuestionDTO ();
		
		casAlgQuestionDTO.setAlgQuestionId ( algQuestionDTO.getComponentId () + "" );
		// list of Question branch
		ArrayList<String> algQuestionBranchIdList = new ArrayList<String> ();
		// last question flag
		int lastQuestionFlag = algQuestionDTO.getLastQuestionFlag ();
		
		String branchAlu = algQuestionDTO.getBranchAlu ();
		// name - optional here
		String name = algQuestionDTO.getName ();
		
		QDTO qdto = algQuestionDTO.getQdto ();
		
		int qqType = qdto.getQqType ();
		
		// get the attribute
		CASQDTO casqdto = new CASQDTO ();
		casqdto.setQqType ( qqType );
		if ( qqType == DTOConstants.QUESTION_TYPE )
		{
			QuestionDTO questionDTO = (QuestionDTO) qdto;
			casqdto.setQuestionQuestionnaireId ( questionDTO.getQuestionId () );
		}
		else if ( qqType == DTOConstants.QUESTIONNAIRE_TYPE )
		{
			QuestionnaireDTO questionnaireDTO = (QuestionnaireDTO) qdto;
			casqdto.setQuestionQuestionnaireId ( questionnaireDTO.getQuestionnaireId () );
		}
		
		List<AlgQuestionBranchDTO> algQuestionBranchDTOList = algQuestionDTO.getAlgQuestionBranchList ();
		if ( algQuestionBranchDTOList != null )
		{
			for ( AlgQuestionBranchDTO algQuestionBranchDTO : algQuestionBranchDTOList )
			{
				if ( algQuestionBranchDTO != null )
				{
					String algQuestionBranchId = "" + algQuestionBranchDTO.getComponentId ();
					algQuestionBranchIdList.add ( algQuestionBranchId );
				}
				
			}
		}
		
		// set the attribute
		casAlgQuestionDTO.setAlgQuestionBranchIdList ( algQuestionBranchIdList );
		casAlgQuestionDTO.setName ( name );
		casAlgQuestionDTO.setLastQuestionFlag ( lastQuestionFlag );
		if(branchAlu!=null && !branchAlu.equals ( "null" ) && branchAlu.length ()>0)
		{
			System.out.println ("Setting branch alu "+branchAlu+ "for "+ name);
			casAlgQuestionDTO.setBranchAlu ( branchAlu );
		}
		casAlgQuestionDTO.setCasqdto ( casqdto );
		
		return casAlgQuestionDTO;
	}
	
	private CASAlgQuestionBranchDTO getCASAlgQuestionBranchDTOFromAlgQuestionBranchDTO ( AlgQuestionBranchDTO algQuestionBranchDTO )
	{
		CASAlgQuestionBranchDTO casAlgQuestionBranchDTO = new CASAlgQuestionBranchDTO ();
		
		AlgQuestionDTO algQuestionDTO = algQuestionBranchDTO.getNextQuestion ();
		
		Long algBranchId = algQuestionBranchDTO.getComponentId ();
		
		casAlgQuestionBranchDTO.setComponentId ( algBranchId );
		// next question to go
		if ( algQuestionDTO != null )
		{
			String nextAlgQuestionId = "" + algQuestionDTO.getComponentId ();
			casAlgQuestionBranchDTO.setNextAlgQuestionId ( nextAlgQuestionId );
		}
		
		List<AlgAnswerDTO> answerDTOList = algQuestionBranchDTO.getAlgAnswerList ();
		
		if ( answerDTOList != null && answerDTOList.size () != 0 )
		{
			// get the attribute
			ArrayList<String> algAnswerIdList = new ArrayList<String> ();
			for ( AlgAnswerDTO algAnswerDTO : answerDTOList )
			{
				if ( algAnswerDTO != null )
				{
					String answerId = "" + algAnswerDTO.getComponentId ();
					algAnswerIdList.add ( answerId );
				}
			}
			casAlgQuestionBranchDTO.setAlgAnswerIdList ( algAnswerIdList );
		}
		
		// set the attribute
		return casAlgQuestionBranchDTO;
	}
	
	private CASQuestionDTO getCASQuestionDTOFromQuestionDTo ( QuestionDTO questionDTO )
	{
		CASQuestionDTO casQuestionDTO = new CASQuestionDTO ();
		// get the attribute
		String questionName = questionDTO.getQuestionName ();
		String answerType = questionDTO.getAnswerType ().getAnswerTypeName ();
		String categoryName = questionDTO.getCategoryType ().getCategoryName ();
		String questionId = questionDTO.getQuestionId ();
		List<MCQOptionDTO> mcqOptionDTOList = questionDTO.getMcqOptionList ();
		List<ValidationDTO> validationDTOList = questionDTO.getValidationDTOList();
				
		// set the attribute
		if(validationDTOList!=null && validationDTOList.size()>0)
		{
			casQuestionDTO.setValidationDTOList(validationDTOList);
		}
		casQuestionDTO.setMcqOptionDTOList ( mcqOptionDTOList );
		casQuestionDTO.setQuestionId ( questionId );
		casQuestionDTO.setQuestionName ( questionName );
		casQuestionDTO.setCategoryName ( categoryName );
		casQuestionDTO.setAnswerType ( answerType );
		
		return casQuestionDTO;
	}
	
	private CASQuestionnaireDTO getCASQuestionnaireDTOFromQuestionnaireDTO ( QuestionnaireDTO questionnaireDTO )
	{
		CASQuestionnaireDTO casQuestionnaireDTO = new CASQuestionnaireDTO ();
		
		// get the attribute
		String questionnaireName = questionnaireDTO.getQuestionnaireName ();
		String questionnaireVersion = questionnaireDTO.getQuestionnaireVersion ();
		String questionnaireStatus = questionnaireDTO.getQuestionnaireStatus ().getStatusName ();
		String questionnaireId = questionnaireDTO.getQuestionnaireId ();
		String questionnaireTimestamp = questionnaireDTO.getQuestionnaireTimestamp ();
		String questionnaireDescription = questionnaireDTO.getDescription ();
		int numberOfQuestion = questionnaireDTO.getNumberOfQuestion ();
		
		ArrayList<String> questionIdList = new ArrayList<String> ();
		
		// set the attribute
		casQuestionnaireDTO.setQuestionnaireName ( questionnaireName );
		casQuestionnaireDTO.setQuestionnaireVersion ( questionnaireVersion );
		casQuestionnaireDTO.setQuestionnaireStatus ( questionnaireStatus );
		casQuestionnaireDTO.setQuestionnaireId ( questionnaireId );
		casQuestionnaireDTO.setQuestionnaireTimestamp ( questionnaireTimestamp );
		casQuestionnaireDTO.setDescription ( questionnaireDescription );
		casQuestionnaireDTO.setNumberOfQuestion ( numberOfQuestion );
		
		// get the questiondto list
		List<QuestionDTO> questionDTOList = questionnaireDTO.getQuestionList ();
		if ( questionDTOList != null )
		{
			for ( QuestionDTO questionDTO : questionDTOList )
			{
				questionIdList.add ( questionDTO.getQuestionId () );
			}
			
			casQuestionnaireDTO.setQuestionIdList ( questionIdList );
		}
		
		return casQuestionnaireDTO;
	}
	
	private CASAlgAnswerDTO getCASAlgAnswerDTOFromAlgAnswerDTO ( AlgAnswerDTO algAnswerDTO )
	{
		CASAlgAnswerDTO casAlgAnswerDTO = new CASAlgAnswerDTO ();
		
		long componentId = algAnswerDTO.getComponentId ();
		casAlgAnswerDTO.setComponentId ( componentId );
		// get the attribute
		String answerTypeName = algAnswerDTO.getAnswerTypeDTO ().getAnswerTypeName ();
		String questionId = algAnswerDTO.getQuestionDTO ().getQuestionId ();
		String answer1 = algAnswerDTO.getAnswer1 ();
		String answer2 = algAnswerDTO.getAnswer2 ();
		
		// set the attribute
		casAlgAnswerDTO.setAnswerType ( answerTypeName );
		casAlgAnswerDTO.setQuestionId ( questionId );
		casAlgAnswerDTO.setAnswer1 ( answer1 );
		casAlgAnswerDTO.setAnswer2 ( answer2 );
		
		return casAlgAnswerDTO;
	}
	
}
