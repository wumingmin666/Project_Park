package com.tjnu.project_park.service.ex;

/**
 * @param
 * @return
 */
public class ParkNotFoundByPidServiceException extends ServiceException{
    public ParkNotFoundByPidServiceException() {
    }

    public ParkNotFoundByPidServiceException(String message) {
        super(message);
    }

    public ParkNotFoundByPidServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParkNotFoundByPidServiceException(Throwable cause) {
        super(cause);
    }

    public ParkNotFoundByPidServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
