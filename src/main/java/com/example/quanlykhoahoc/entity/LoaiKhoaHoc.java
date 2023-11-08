package com.example.quanlykhoahoc.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "loaikhoahoc")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoaiKhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idloaikhoahoc")
    private int idLKH;
    @Column(name = "tenloai")
    private String tenloai;

    @OneToMany(mappedBy = "lkh", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "lkh-kh")
    private List<KhoaHoc> khoaHocs;
}
