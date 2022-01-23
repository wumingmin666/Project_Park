package com.tjnu.project_park.service.ex;

/**
 * @param
 * @return
 */
public class UsernameDuplicatedServiceException extends ServiceException{
    public UsernameDuplicatedServiceException() {
        super();
    }

    public UsernameDuplicatedServiceException(String message) {
        super(message);
    }

    public UsernameDuplicatedServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicatedServiceException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicatedServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
