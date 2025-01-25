package com.sprk.first_app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private int rollNo;

    private String firstName;

    private String lastName;

    private String gender;

    private Address address;
}
