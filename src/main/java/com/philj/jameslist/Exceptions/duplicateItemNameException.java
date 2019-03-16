package com.philj.jameslist.Exceptions;

/**
 * Created by Philip on 2019-02-28.
 */
public class duplicateItemNameException extends RuntimeException {
    public String errorMessage;
    public duplicateItemNameException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
