package com.ewing.core.voinfo.exception;

public class AuditParseEntityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuditParseEntityException() {
        super();
    }

    public AuditParseEntityException(String message) {
        super(message);
    }

    public AuditParseEntityException(String message, Throwable cause) {
        super(message, cause);
    } 

    public AuditParseEntityException(Throwable cause) {
        super(cause);
    }
}
