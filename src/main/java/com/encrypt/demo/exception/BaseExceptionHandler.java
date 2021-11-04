package com.encrypt.demo.exception;


import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

  @Value("${server.servlet.context-path:}")
  private String context_path; // NOSONAR

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleException(
      Exception e, HttpServletRequest httpServletRequest) {

    log.error("=======handleException=======");

    try {

      log.debug("ERROR Method=[{}]", httpServletRequest.getMethod());
      log.debug("ERROR URL=[{}]", httpServletRequest.getRequestURI());

      log.error(e.getClass().getName(), e);

      var errorCode = ReturnCodeEnum.ERROR_9999;
      String errorMessage = ReturnCodeEnum.ERROR_9999.getMessage();
      if (e.getClass().isAssignableFrom(BaseException.class)) {
        errorCode = ((BaseException) e).getErrorCode();
        errorMessage = ((BaseException) e).getMessage();
      }

      var errorResponse = new ErrorResponse();
      errorResponse.setReturncode(errorCode.getCode());
      errorResponse.setReturndesc(errorMessage);
      return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);

    } catch (Exception ex) {
      log.error("", ex);

      log.debug("ERROR Method=[{}]", httpServletRequest.getMethod());
      log.debug("ERROR URL=[{}]", httpServletRequest.getRequestURI());

      log.error(e.getClass().getName(), e);

      var errorResponse = new ErrorResponse();
      errorResponse.setReturncode(ReturnCodeEnum.ERROR_9999.getCode());
      errorResponse.setReturndesc(ReturnCodeEnum.ERROR_9999.getMessage());
      return ResponseEntity.status(ReturnCodeEnum.ERROR_9999.getHttpStatus()).body(errorResponse);
    }
  }
}
