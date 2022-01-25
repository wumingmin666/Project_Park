package com.tjnu.project_park.service.ex;

/**
 * @param
 * @return
 */
public class OrderNotFoundServiceException extends ServiceException{
    public OrderNotFoundServiceException() {
    }

    public OrderNotFoundServiceException(String message) {
        super(message);
    }

    public OrderNotFoundServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundServiceException(Throwable cause) {
        super(cause);
    }

    public OrderNotFoundServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
