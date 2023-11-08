package com.example.quanlykhoahoc.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "hocvien")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HocVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hocvienid")
    private int idHV;
    @Column(name = "hoten")
    private String hoTen;
    @Column(name = "ngaysinh")
    private LocalDate ngSinh;
    @Column(name = "sdt")
    private String sdt;
    @Column(name = "email")
    private String email;
    @Column(name = "tinhthanh")
    private String tinhThanh;
    @Column(name = "quanhuyen")
    private String quanHuyen;
    @Column(name = "phuongxa")
    private String phuongXa;
    @Column(name = "sonha")
    private String soNha;

    @OneToMany(mappedBy = "hocVien", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "hv-dkh")
    private List<DangKyHoc>dangKyHocs;

}
