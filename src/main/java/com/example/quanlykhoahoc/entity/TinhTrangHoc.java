package com.example.quanlykhoahoc.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tinhtranghoc")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TinhTrangHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tinhtranghocid")
    private int idTTH;
    @Column(name = "tentinhtrang")
    private String tenTT;

    @OneToMany(mappedBy = "tinhTrangHoc", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "dkh-tth")
    private List<DangKyHoc> dangKyHocs;
}
