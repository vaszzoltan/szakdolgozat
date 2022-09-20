package com.vaszily.WorkoutPlanner.exception;

public class ReferencedEntityException extends RuntimeException{
    public ReferencedEntityException(String msg){
        super(msg);
    }
}
