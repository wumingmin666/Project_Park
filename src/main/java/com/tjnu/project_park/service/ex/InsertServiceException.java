package com.tjnu.project_park.service.ex;

/**
 * @param
 * @return
 */
public class InsertServiceException extends ServiceException{
    public InsertServiceException() {
        super();
    }

    public InsertServiceException(String message) {
        super(message);
    }

    public InsertServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertServiceException(Throwable cause) {
        super(cause);
    }

    protected InsertServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
