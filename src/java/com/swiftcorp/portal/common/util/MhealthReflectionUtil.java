/**
 * 
 */
package com.swiftcorp.portal.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.property.BasicPropertyAccessor;
import org.hibernate.property.Getter;
import org.hibernate.property.Setter;
import org.hibernate.util.ReflectHelper;

import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.parser.MhealthDTODataProcessor;
import com.swiftcorp.portal.common.service.DTOPostProcessor;
import com.swiftcorp.portal.common.service.MhealthDTOAccesor;
import com.swiftcorp.portal.common.service.PropertyAccessor;
import com.swiftcorp.portal.common.service.QuestionPropertyAccessorMap;
import com.swiftcorp.portal.dto.request.QuestionAnswerMap;

/**
 * @author asraful.haque
 * 
 */
public class MhealthReflectionUtil
{
	// get the logger
	private static final Log logger = LogFactory.getLog ( MhealthReflectionUtil.class );
	
	public static void setValueToProperty ( Object entityObject, String propertyName, Object propertyValue )
			throws Exception
	{
		// setter for the reflection
		Setter setterMethod = null;
		// Object entityObject = mhealthDTOAccesor.getEntityClass ().newInstance
		// ();
		
		// get the setter method
		setterMethod = new BasicPropertyAccessor ().getSetter ( entityObject.getClass (), propertyName );
		
		// now set the value to the entity object
		ReflectHelper.getMethod ( entityObject.getClass (), setterMethod.getMethod () ).invoke ( entityObject, propertyValue );
		
	}
	
	public static Object getValueOfProperty ( Object entityObject, String propertyName ) 	throws Exception
	{
		Object propertyValue = null;
		
		// setter for the reflection
		Getter getterMethod = null;
		
		
		// get the setter method
		getterMethod = new BasicPropertyAccessor ().getGetter (  entityObject.getClass (), propertyName );
		
		propertyValue = getterMethod.getMethod ().invoke ( entityObject );
		
		// return the value
		return propertyValue;
		
	}
	
	/*
	 * 
	 */
	  public static boolean doesValueExistForQuestion ( Map< String, PropertyAccessor >qidPropertyAccessorMap ,Map<String, Object>  questionAnswerMap) 
	  { 
		  boolean doesExist = false;
		  Set<String> questionIdSet = qidPropertyAccessorMap.keySet (); 
		  for ( String questionId : questionIdSet ) 
		  { 
			  if(questionAnswerMap.get ( questionId )!=null) 
			  { 
				  doesExist = true; 
				  break;
			  } 
		  } 
		  return doesExist;
	  }
	  
	  public static boolean doesValueExistForQuestionnaire ( Map<String, QuestionAnswerMap> questionnaireIdQuestionAnswerMap,Map<String,
	  QuestionPropertyAccessorMap> questionnairePropertyAccessorMap ) 
	  { 
		  boolean
		  doesExist = false;
		  Set<String> questionnaireIdSet =	  questionnaireIdQuestionAnswerMap.keySet ();
		  for ( String questionnaireId : questionnaireIdSet ) { 
			  Map< String, PropertyAccessor > qidPropertyAccessorMap = questionnairePropertyAccessorMap.get ( questionnaireId ).getQidPropertyAccessorMap ();
			  //if(doesValueExistForQuestion(qidPropertyAccessorMap)) 
			  //{ 
				//  doesExist = true;
			  //}
		  } 
		  return doesExist; 
	  }
	 
	public static GenericDTO getDTOFromAccessorAndAnswer ( MhealthDTOAccesor dtoAccessor, Map<String, QuestionAnswerMap> questionnaireIdQuestionAnswerMap, Map<String, Object> questionAnswerMap )
	{
		// genericdto to return
		GenericDTO genericDTO = null;
		
		//
		try
		{
			// instantiate the object to return
			
			// get the question property accessor map
			QuestionPropertyAccessorMap questionPropertyAccessorMap = dtoAccessor.getQuestionPropertyAccessorMap ();
			
			// get the questionnaire property accessor map
			Map<String, QuestionPropertyAccessorMap> questionnairePropertyAccessorMap = dtoAccessor.getQuestionnairePropertyAccessorMap ();
			
			// if questionPropertyAccessorMap is not null, there is some
			// question
			if ( questionPropertyAccessorMap != null )
			{
				
				// get qid property accessor map
				Map<String, PropertyAccessor> qidPropertyAccessorMap = questionPropertyAccessorMap.getQidPropertyAccessorMap ();
				// now for this populate the dto
				//System.out.println("doesvalueexists::::"+doesValueExistForQuestion(qidPropertyAccessorMap,questionAnswerMap));
				if(doesValueExistForQuestion(qidPropertyAccessorMap,questionAnswerMap))
				{
					if ( genericDTO == null )
					{
						genericDTO = dtoAccessor.getEntityClass ().newInstance ();
					}
					
					populateDTO ( genericDTO, qidPropertyAccessorMap, questionAnswerMap );
				}
				
			}
			
			// if questionnaire Property accessor is not null
			if ( questionnairePropertyAccessorMap != null )
			{
				// get the key set
				Set<String> questionnaireIdSet = questionnairePropertyAccessorMap.keySet ();
				
				if ( questionnaireIdSet != null )
				{
					for ( String questionnaireId : questionnaireIdSet )
					{
						// question id property accessor map
						Map<String, PropertyAccessor> qidPropertyAccessorMap = questionnairePropertyAccessorMap.get ( questionnaireId ).getQidPropertyAccessorMap ();
						
						if ( qidPropertyAccessorMap != null )
						{
							// now get the answer map for this questionnaire
							QuestionAnswerMap questionAnswerMapObject = questionnaireIdQuestionAnswerMap.get ( questionnaireId );
							
							// if the questionAnswerMapObject is not null
							if ( questionAnswerMapObject != null )
							{
								// if(doesValueExistForQuestionnaire
								// (questionnaireIdQuestionAnswerMap,questionnairePropertyAccessorMap))
								
								Map<String, Object> questionnaireQuestionAnswerMap = questionAnswerMapObject.getQuestionAnswerMap ();
								
								// now populate the dto
								if ( questionnaireQuestionAnswerMap != null )
								{
									if ( genericDTO == null )
									{
										genericDTO = dtoAccessor.getEntityClass ().newInstance ();
									}
									populateDTO ( genericDTO, qidPropertyAccessorMap, questionnaireQuestionAnswerMap );
								}
								
							}
						}
						
					}
				}
			}
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		// check the post processor
		DTOPostProcessor dtoPostProcessor = dtoAccessor.getDtoPostProcessor ();
		
		// if this postProcessor is not null process it
		if ( dtoPostProcessor != null )
		{
			genericDTO = dtoPostProcessor.process ( genericDTO );
		}
		
		// return the dto
		return genericDTO;
	}
	
	private static void populateDTO ( GenericDTO genericDTO, Map<String, PropertyAccessor> qidPropertyAccessorMap, Map<String, Object> questionAnswerMap )
	{
		// if property accessor map or answer map is null return
		if ( (qidPropertyAccessorMap == null) || (questionAnswerMap == null) )
		{
			return;
		}
		// get the questionId iterator
		Iterator<String> questionIdIterator = questionAnswerMap.keySet ().iterator ();
		
		// iterate the quesiton answer map
		while ( questionIdIterator.hasNext () )
		{
			// get the question id
			String questionId = questionIdIterator.next ();
			logger.info ( "Now parsing data for question id " + questionId );
			// get the ans for this question
			Object answer = questionAnswerMap.get ( questionId );
			if ( answer != null )
			{
				// get the propery accessore for this question
				PropertyAccessor propertyAccessor = qidPropertyAccessorMap.get ( questionId );
				if ( propertyAccessor != null )
				{
					// get the the property name
					String propertyName = propertyAccessor.getPropertyName ();
					
					// get the data processor
					MhealthDTODataProcessor mhealthDTODataProcessor = propertyAccessor.getMhealthDTODataProcessor ();
					
					if ( answer != null )
					{
						// now set the answer
						try
						{
							// check whether the processor is null, it will be
							// null when no need to process the value eg- in
							// case of string
							if ( mhealthDTODataProcessor != null )
							{
								// process the answer to convert data
								answer = mhealthDTODataProcessor.parse ( answer );
							}
							
							// again check where the answer is null
							if ( answer != null )
							{
								logger.debug ( "Now settig the property " + propertyName + " with value " + answer );
								System.out.println ( "Now settig the property " + propertyName + " with value " + answer );
								
								MhealthReflectionUtil.setValueToProperty ( genericDTO, propertyName, answer );
							}
							
						}
						catch (Exception e)
						{
							logger.error ( "Error occured while setting the property " + propertyName );
						}
					}
				}
			}
			
		}
	}
}
