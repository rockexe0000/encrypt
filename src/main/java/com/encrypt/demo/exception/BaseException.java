package com.encrypt.demo.exception;


import lombok.Data;

@Data
public class BaseException extends RuntimeException {

  private ReturnCodeEnum errorCode; // NOSONAR
  private String message; // NOSONAR

  private static final String ERRORCODESTRING = "errorCode";
  private static final String ERRORMESSAGE = "errorMessage";

  public BaseException() {
    super();
    this.errorCode = ReturnCodeEnum.ERROR_9999;
    this.message = errorCode.getMessage();
  }

  public BaseException(ReturnCodeEnum errorCode) {
    super(
        ERRORCODESTRING
            + "=["
            + errorCode.getCode()
            + "]"
            + ", "
            + ERRORMESSAGE
            + "=["
            + errorCode.getMessage()
            + "]");
    this.errorCode = errorCode;
    this.message = errorCode.getMessage();
  }

  public BaseException(ReturnCodeEnum errorCode, Throwable cause) {
    super(
        ERRORCODESTRING
            + "=["
            + errorCode.getCode()
            + "]"
            + ", "
            + ERRORMESSAGE
            + "=["
            + errorCode.getMessage()
            + "]",
        cause);
    this.errorCode = errorCode;
    this.message = errorCode.getMessage();
  }

  public BaseException(String message) {
    super(message);
    this.errorCode = ReturnCodeEnum.ERROR_9999;
    this.message = message;
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
    this.errorCode = ReturnCodeEnum.ERROR_9999;
    this.message = message;
  }

  public BaseException(ReturnCodeEnum errorCode, String message) {
    super(
        ERRORCODESTRING
            + "=["
            + errorCode.getCode()
            + "]"
            + ", "
            + ERRORMESSAGE
            + "=["
            + errorCode.getMessage()
            + "], "
            + message);
    this.errorCode = errorCode;
    this.message = message;
  }

  public BaseException(ReturnCodeEnum errorCode, String message, Throwable cause) {
    super(
        ERRORCODESTRING
            + "=["
            + errorCode.getCode()
            + "]"
            + ", "
            + ERRORMESSAGE
            + "=["
            + errorCode.getMessage()
            + "], "
            + message,
        cause);
    this.errorCode = errorCode;
    this.message = message;
  }
}
