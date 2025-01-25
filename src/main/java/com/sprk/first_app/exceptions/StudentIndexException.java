package com.sprk.first_app.exceptions;

import lombok.Data;


public class StudentIndexException extends StudentException {

    public StudentIndexException(int status, String message) {
        super(status, message);

    }
}
