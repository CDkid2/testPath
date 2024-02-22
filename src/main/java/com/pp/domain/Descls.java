package com.pp.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="des_cls")
public class Descls {

    @Id
    @Column(name="d_cls_code")
    private String dClsCode;

    @Column(name="d_cls_addr")
    private String desAddr;

    @Column(name="d_cls_lat")
    private Float desLat;

    @Column(name="d_cls_lng")
    private Float desLng;

    @OneToMany(mappedBy="desClsCode")
    private List<Des> des;
}
