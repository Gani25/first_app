package com.sprk.first_app.exceptions;

import lombok.Data;

@Data
public class StudentException extends RuntimeException {
    private int status;

    public StudentException(int status, String message) {
        super(message);
        this.status = status;
    }
}
