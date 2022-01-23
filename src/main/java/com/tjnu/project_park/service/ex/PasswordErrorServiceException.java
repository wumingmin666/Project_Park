package com.tjnu.project_park.service.ex;

/**
 * @param
 * @return
 */
public class PasswordErrorServiceException extends ServiceException{
    public PasswordErrorServiceException() {
        super();
    }

    public PasswordErrorServiceException(String message) {
        super(message);
    }

    public PasswordErrorServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordErrorServiceException(Throwable cause) {
        super(cause);
    }

    protected PasswordErrorServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
