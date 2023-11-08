package com.example.quanlykhoahoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "khoahoc")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "khoahocid")
    private int idKH;
    @Column(name = "tenkhoahoc")
    private String tenKH;
    @Column(name = "thoigianhoc")
    @Min(value = 0, message = "Thoi gian hoc >=0")
    private int thoiGianHoc;
    @Column(name = "gioithieu")
    private String gioiThieu;
    @Column(name = "noidung")
    private String noiDung;
    @Column(name = "hocphi")
    @Min(value = 1, message = "Hoc phi > 0")
    private int hocPhi;
    @Column(name = "sohocvien")
    private int soHV;
    @Column(name = "soluongmon")
    private int soLuongMon;

    @ManyToOne
    @JsonBackReference(value = "lkh-kh")
    @JoinColumn(name = "loaikhoahocid", foreignKey = @ForeignKey(name = "fk_kh-lkh"))
    private LoaiKhoaHoc lkh;

    @OneToMany(mappedBy = "khoaHoc", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "kh-dkh")
    private List<DangKyHoc>dangKyHocs;

}
