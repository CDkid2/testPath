package com.pp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "bookmark")
public class Bookmark {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "bm_code")
    private Integer bmCode;

    @Column(name = "bm_url")
    private String bmUrl;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "user_email")
    private User userEmail;

    @ManyToOne
    @JoinColumn(name = "des_code", referencedColumnName = "des_code")
    private Des desCode;

    @Column(name = "bm_cls")
    private String bmCls;

    @Column(name = "bm_nn")
    private String bmNn;

    private Integer id;

}