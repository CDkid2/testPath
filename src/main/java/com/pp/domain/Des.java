package com.pp.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="destination")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "desCode")
public class Des {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="des_code")
    private String desCode;

    @ManyToOne
    @JoinColumn(name="d_cls_code", referencedColumnName="d_cls_code")
    @JsonIgnore
    private Descls desClsCode;

    private String desName;
    private String desAddr;
    private Double desLat;
    private Double desLng;
    private String desCont;
    private Integer desLike;

    @OneToMany(mappedBy="desCode")
    private List<Image> images;

    @OneToMany(mappedBy="desCode")
    @JsonManagedReference
    private List<Review> reviews;
}
