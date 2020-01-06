/**
 * 
 */
package com.sensei.app.service.dto;

/**
 * @author HP
 *
 */



public class ApplicationStatusDTO<T> {
	private T dtoObject;
	
	public ErrorCode errorCode;
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public T getDtoObject() {
		return dtoObject;
	}

	public void setDtoObject(T dtoObject) {
		this.dtoObject = dtoObject;
	}
	
	public ApplicationStatusDTO(T dtoObject) {
		this.dtoObject = dtoObject;
	}

	public ApplicationStatusDTO() {
		
	}
}
