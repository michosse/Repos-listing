package org.demo.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class HttpException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int status;

    public HttpException(int status, String message) {
        super(message);
        this.status = status;
    }

    public HttpException(int status){
        super();
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
