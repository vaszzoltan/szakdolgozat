package com.vaszily.WorkoutPlanner.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request){
        String error = ex.getParameterName();
        RestApiError restApiError = new RestApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(restApiError, new HttpHeaders(), restApiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ex.getParameter() + "parameter is missing!";
        RestApiError restApiError = new RestApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(restApiError, new HttpHeaders(), restApiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder msg = new StringBuilder();
        msg.append(ex.getMethod());
        msg.append(" method is not supported for this endpoint. Use ");
        ex.getSupportedHttpMethods().forEach(t-> msg.append(t+""));
        RestApiError restApiError = new RestApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), msg.toString());
        return new ResponseEntity<>(restApiError, new HttpHeaders(), restApiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors())
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        RestApiError restApiError = new RestApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, restApiError, headers, restApiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       String error = ex.getLocalizedMessage();
       RestApiError restApiError = new RestApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
       return new ResponseEntity<>(restApiError, new HttpHeaders(), restApiError.getStatus());
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<RestApiError> handleEntityException(EntityExistsException ex){
        return new ResponseEntity<>(new RestApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getLocalizedMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataUploadException.class)
    public ResponseEntity<RestApiError> handleDataUploadException(DataUploadException ex){
        return new ResponseEntity<>(new RestApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getLocalizedMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MissingAuthorityException.class)
    public ResponseEntity<RestApiError> handleMissingAuthorityException(MissingAuthorityException ex){
        return new ResponseEntity<>(new RestApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getLocalizedMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ReferencedEntityException.class)
    public ResponseEntity<RestApiError> handleReferencedEntityException(ReferencedEntityException ex){
        return new ResponseEntity<>(new RestApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getLocalizedMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


}
