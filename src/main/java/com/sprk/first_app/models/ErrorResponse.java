package com.sprk.first_app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class ErrorResponse {

    private int status;

    private String message;

    private Date timestamp;
}
