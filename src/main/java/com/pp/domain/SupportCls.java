package com.pp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "support_cls")
public class SupportCls {
    @Id
    @Column(name = "sc_code")
    private String scCode;

    @Column(name = "sc_name")
    private String scName;

    // 생성자, getter, setter 등이 있어야 합니다.
}