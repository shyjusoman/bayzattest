package com.mycom.platform.hr.type;
/**
 * The Enum ErrorCode.
 */
public enum ErrorCode {
    
    COMPANY_NOT_FOUND_EXCEPTION("COMPANY_NOT_FOUND_EXCEPTION","Company ,{} Not Found"),
	EMPLOYEE_NOT_FOUND_EXCEPTION("EMPLOYEE_NOT_FOUND_EXCEPTION","Employee ,{} Not Found in the Company , {}"),
	DEPENDANT_NOT_FOUND_EXCEPTION("DEPENDANT_NOT_FOUND_EXCEPTION","Dependant ,{} Not Found for the Employee , {} in the company , {}"),;
		
    /** The error text. */
	private final String errorText;
	
	/** The error code. */
	private final String errorCode;
		
	/**
	 * Instantiates a new error code.
	 *
	 * @param errorText the error text
	 * @param errorCode the error code
	 */
	private ErrorCode(String errorCode,String errorText) {
		this.errorText = errorText;
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error text.
	 *
	 * @return the error text
	 */
	public String getErrorText() {
		return errorText;
	}
	
	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}
	
	
}
