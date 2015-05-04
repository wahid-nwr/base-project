package com.swiftcorp.portal.question.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class ValidationDTO extends PersistentCapableDTO
{
	private String qId;
	private int validationOrder;
	private String ansType;
	private long ansTypeCode;
	private int validationType;
	private String validationName;
	private String validationValue;
	private int secondValidationType;
	private String secondValidationName;
	private String secondValidationValue;
	
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	public int getValidationOrder() {
		return validationOrder;
	}
	public void setValidationOrder(int validationOrder) {
		this.validationOrder = validationOrder;
	}
	public String getAnsType() {
		return ansType;
	}
	public void setAnsType(String ansType) {
		this.ansType = ansType;
	}
	public long getAnsTypeCode() {
		return ansTypeCode;
	}
	public void setAnsTypeCode(long ansTypeCode) {
		this.ansTypeCode = ansTypeCode;
	}
	public int getValidationType() {
		return validationType;
	}
	public void setValidationType(int validationType) {
		this.validationType = validationType;
	}
	public String getValidationName() {
		return validationName;
	}
	public void setValidationName(String validationName) {
		this.validationName = validationName;
	}
	public String getValidationValue() {
		return validationValue;
	}
	public void setValidationValue(String validationValue) {
		this.validationValue = validationValue;
	}
	public int getSecondValidationType() {
		return secondValidationType;
	}
	public void setSecondValidationType(int secondValidationType) {
		this.secondValidationType = secondValidationType;
	}
	public String getSecondValidationName() {
		return secondValidationName;
	}
	public void setSecondValidationName(String secondValidationName) {
		this.secondValidationName = secondValidationName;
	}
	public String getSecondValidationValue() {
		return secondValidationValue;
	}
	public void setSecondValidationValue(String secondValidationValue) {
		this.secondValidationValue = secondValidationValue;
	}
	
}
