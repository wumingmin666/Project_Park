package com.tjnu.project_park.service.ex;

/**
 * @param
 * @return
 */
public class UsernameNotFoundServiceException extends ServiceException{
    public UsernameNotFoundServiceException() {
        super();
    }

    public UsernameNotFoundServiceException(String message) {
        super(message);
    }

    public UsernameNotFoundServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundServiceException(Throwable cause) {
        super(cause);
    }

    protected UsernameNotFoundServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
