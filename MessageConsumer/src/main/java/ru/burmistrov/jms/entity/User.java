package ru.burmistrov.jms.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class User {

    private String id = UUID.randomUUID().toString();

    private String username;

    private String password;
}
