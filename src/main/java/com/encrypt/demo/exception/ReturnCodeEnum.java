package com.encrypt.demo.exception;

public enum ReturnCodeEnum {
  /** 操作成功 */
  SUCCESS_200("200", 200, "操作成功"),
  /** 其他未定義錯誤，請聯絡API負責人員 */
  ERROR_9999("9999", 400, "其他未定義錯誤，請聯絡API負責人員");

  private final String code;
  private final int httpStatus;
  private final String message;

  private ReturnCodeEnum(String code, int httpStatus, String message) {
    this.code = code;
    this.httpStatus = httpStatus;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return code + ": " + httpStatus + ": " + message;
  }
}
