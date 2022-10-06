package com.vaszily.WorkoutPlanner.exception;

public class MissingAuthorityException extends RuntimeException{
    public MissingAuthorityException(String msg){
        super(msg);
    }
}
