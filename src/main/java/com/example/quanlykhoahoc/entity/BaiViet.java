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
@Table(name = "baiviet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baivietid")
    private int idBV;
    @Column(name = "tenbaiviet")
    @NotNull
    private String tenBV;
    @Column(name = "thoigiantao")
    @NotNull
    private LocalDate thoiGianTao;
    @Column(name = "tentacgia")
    @NotNull
    private String tenTG;
    @Column(name = "noidung")
    @NotNull
    private String noiDung;
    @Column(name = "noidungngan")
    @NotNull
    private String noiDungNgan;

    @ManyToOne
    @JsonBackReference(value = "cd-bv")
    @JoinColumn(name = "chudeid", foreignKey = @ForeignKey(name = "fk_bv_cd"))
    private ChuDe chuDe;

    @ManyToOne
    @JsonBackReference(value = "bv-tk")
    @JoinColumn(name = "taikhoanid", foreignKey = @ForeignKey(name = "fk_bv_tk"))
    private TaiKhoan taiKhoan;

}
