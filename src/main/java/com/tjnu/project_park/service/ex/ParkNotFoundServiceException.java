package com.tjnu.project_park.service.ex;

/**
 * @param
 * @return
 */
public class ParkNotFoundServiceException extends ServiceException{
    public ParkNotFoundServiceException() {
    }

    public ParkNotFoundServiceException(String message) {
        super(message);
    }

    public ParkNotFoundServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParkNotFoundServiceException(Throwable cause) {
        super(cause);
    }

    public ParkNotFoundServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
