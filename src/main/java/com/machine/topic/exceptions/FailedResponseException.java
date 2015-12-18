package com.machine.topic.exceptions;

/**
 * Created by rishabh.tripathi on 12/3/15.
 */
public class FailedResponseException extends Exception {
    String errors;
    public FailedResponseException(String errors) {
        super(errors);
        this.errors = errors;
    }
    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
