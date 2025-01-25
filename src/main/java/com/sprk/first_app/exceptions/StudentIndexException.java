package com.sprk.first_app.exceptions;

import lombok.Data;

@Data
public class StudentIndexException extends RuntimeException{
    private int status;
    public StudentIndexException(int status, String message) {
        super(message);

        this.status = status;
    }
}
