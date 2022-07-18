package com.example.authentication.auth;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private double balance;

    private Date lastAccess;

}
