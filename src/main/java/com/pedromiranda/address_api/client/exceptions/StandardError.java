package com.pedromiranda.address_api.client.exceptions;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class StandardError implements Serializable {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError(LocalDateTime timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
