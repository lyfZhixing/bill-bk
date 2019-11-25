package org.bill.common.handler;

import org.bill.common.exception.ParameterException;
import org.bill.common.exception.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.bill.common.model.ResultModel;

/**
 * 公共异常处理
 *
 * @author mingshan
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // #issue 02
    @ResponseBody
    public String handleInvalidRequestError(ParameterException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ResultModel> handleServiceException(ServerException ex) {
        return new ResponseEntity<>(ex.getResult(), ex.getHttpStatus());
    }
}
