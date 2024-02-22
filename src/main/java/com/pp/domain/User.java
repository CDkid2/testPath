package com.pp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Configuration
@Table(name="user")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_email")
    private String userEmail;

    private String id;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(name = "name")
    private String name;

    @Column(name = "nn")
    private String nn;

    private String role;
    private String birth;
    private String gen;
    private Date crdate;
    private String pwd;

    @Column(name="url")
    private String url;

    @OneToMany(mappedBy = "userEmail")
    private List<Review> reviews;

    @OneToMany(mappedBy = "userEmail")
    private List<Bookmark> bookmarks;
}