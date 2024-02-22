package com.pp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "support")
public class AboutBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sup_code")
    private int supCode;


    @Column(name = "sup_title")
    private String supTitle;

    @Column(name = "sup_cont")
    private String supCont;

    @Column(name = "sup_ref")
    private String supRef;

    @Column(name = "sup_date")
    @Temporal(TemporalType.DATE)
    private Date supDate;


    @ManyToOne
    @JoinColumn(name = "sc_code", referencedColumnName = "sc_code")
    private SupportCls supportCls;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "user_email")
    private User user;

}