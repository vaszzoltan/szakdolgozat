package com.vaszily.WorkoutPlanner.exception;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RestApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private Date timestamp = new Date();
    private Integer statuscode;

    public RestApiError(HttpStatus status, String message, List<String> errors){
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.statuscode = status.value();
    }

    public RestApiError(HttpStatus status, String message, String error){
        this(status, message, Arrays.asList(error));
    }

    public RestApiError(HttpStatus status, String message){
        this(status, message, message);
    }

    public RestApiError(ConstraintViolationException ex){
        super();
        this.status = HttpStatus.BAD_REQUEST;
        this.message = ex.getLocalizedMessage();
        this.statuscode = status.value();
        this.errors = ex.getConstraintViolations()
                .stream()
                .map(err -> err.getPropertyPath().toString() +": " + err.getMessage())
                .collect(Collectors.toList());
    }
}
