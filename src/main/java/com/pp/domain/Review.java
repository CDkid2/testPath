package com.pp.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name="review")
public class Review {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rv_code")
    private String rvCode;

    @ManyToOne
    @JoinColumn(name="des_code", referencedColumnName = "des_code")
    private Des desCode;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "user_email")
    private User userEmail; // 외래키로 참조할 엔티티 타입으로 변경

    @Column(name="rv_cont")
    private String rvCont;

    @Column(name="rv_date")
    private Date rvDate;

    @Column(name="rv_like")
    private Integer rvLike;

    @Column(name="rv_mdate")
    private Date rvMdate;

    @Column(name="rv_title")
    private String rvTitle;

    @OneToMany(mappedBy = "rvCode")
    private List<Image> images;
}
