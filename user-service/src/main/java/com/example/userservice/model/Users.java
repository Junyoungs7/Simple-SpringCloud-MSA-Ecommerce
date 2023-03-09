package com.example.userservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String encryptedPwd;

    public void setEncryptedPwd(String encryptedPwd) {
        this.encryptedPwd = encryptedPwd;
    }
}
