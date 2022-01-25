package com.tjnu.project_park.service.ex;

/**
 * @param
 * @return
 */
public class UpdateStatueServiceException extends ServiceException{
    public UpdateStatueServiceException() {
    }

    public UpdateStatueServiceException(String message) {
        super(message);
    }

    public UpdateStatueServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateStatueServiceException(Throwable cause) {
        super(cause);
    }

    public UpdateStatueServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
