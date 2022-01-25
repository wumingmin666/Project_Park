package com.tjnu.project_park.huawei.ex;

/**
 * @param
 * @return
 */
public class GetTokenException extends RuntimeException{
    public GetTokenException() {
    }

    public GetTokenException(String message) {
        super(message);
    }

    public GetTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetTokenException(Throwable cause) {
        super(cause);
    }

    public GetTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
