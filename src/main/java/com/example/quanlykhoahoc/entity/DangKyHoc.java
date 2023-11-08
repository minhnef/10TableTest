package com.example.quanlykhoahoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "dangkyhoc")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DangKyHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "khoahocid", insertable = false, updatable = false)
    private int idKH;
    @Column(name = "hocvienid", insertable = false, updatable = false)
    private int idHV;
    @Column(name = "ngaydangky")
    private LocalDate ngDKy;
    @Column(name = "ngaybatdau")
    private LocalDate ngBD;
    @Column(name = "ngayketthuc")
    private LocalDate ngKT;

    @ManyToOne
    @JsonBackReference(value = "dkh-tth")
    @JoinColumn(name = "tinhtranghocid", foreignKey = @ForeignKey(name = "fk_dkh_tth"))
    @NotNull
    private TinhTrangHoc tinhTrangHoc;

    @ManyToOne
    @JsonBackReference(value = "hv-dkh")
    @JoinColumn(name = "hocvienid", foreignKey = @ForeignKey(name = "fk_dkh_hv"))
    private HocVien hocVien;

    @ManyToOne
    @JsonBackReference(value = "kh-dkh")
    @JoinColumn(name = "khoahocid", foreignKey = @ForeignKey(name = "fk_dkh_kh"))
    private KhoaHoc khoaHoc;

    @ManyToOne
    @JsonBackReference(value = "tk-dkh")
    @JoinColumn(name = "taikhoanid", foreignKey = @ForeignKey(name = "fk_dkh_tk"))
    @NotNull
    private TaiKhoan taiKh;

}
