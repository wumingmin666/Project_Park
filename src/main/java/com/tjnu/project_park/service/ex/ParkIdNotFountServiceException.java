package com.tjnu.project_park.service.ex;

/**
 * @param
 * @return
 */
public class ParkIdNotFountServiceException extends ServiceException{
    public ParkIdNotFountServiceException() {
    }

    public ParkIdNotFountServiceException(String message) {
        super(message);
    }

    public ParkIdNotFountServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParkIdNotFountServiceException(Throwable cause) {
        super(cause);
    }

    public ParkIdNotFountServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
