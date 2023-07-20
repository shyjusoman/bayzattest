package com.mycom.platform.exceptions;

import com.mycom.platform.hr.type.ErrorCode;

public class EntityNotFoundException
  extends RuntimeException
{
  private static final long serialVersionUID = 1L;
      
  private ErrorCode errorCode;
   
  private Object[] messageParameters = new Object[0];

  public EntityNotFoundException() {}
  

  public EntityNotFoundException(ErrorCode errorCode, Object[] messageParameters)
  {
    super(errorCode.getErrorText());
    this.errorCode = errorCode;
    this.messageParameters = messageParameters;
  }
  
  public String getMessage()
  {
    String message = super.getMessage();
    
    if ((messageParameters == null) || (messageParameters.length == 0)) {
      return message;
    }
    
    int messageLength = message.length();
    StringBuilder sb = new StringBuilder(messageLength * 2);
    sb.append(message);
    for (Object obj : messageParameters)
    {
      int indexOfParamPlaceholder = sb.indexOf("{}");
      if (indexOfParamPlaceholder != -1) {
        if (obj == null) {
          sb.replace(indexOfParamPlaceholder, indexOfParamPlaceholder + 2, "{null}");
        } else {
          sb.replace(indexOfParamPlaceholder, indexOfParamPlaceholder + 2, obj.toString());
        }
      }
    }

    return sb.toString();
  }


public ErrorCode getErrorCode() {
	return errorCode;
}


public void setErrorCode(ErrorCode errorCode) {
	this.errorCode = errorCode;
}


public Object[] getMessageParameters() {
	return messageParameters;
}


public void setMessageParameters(Object[] messageParameters) {
	this.messageParameters = messageParameters;
} 

 }
