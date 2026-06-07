package com.WAP.auth_Service.model;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "users")
@Data
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private  String name;


    @Column(nullable = false, unique = true)
    private  String email;

    @Column(nullable = false, unique = true)
    private  String password;



}
