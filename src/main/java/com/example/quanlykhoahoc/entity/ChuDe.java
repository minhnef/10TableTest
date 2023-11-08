package com.example.quanlykhoahoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "chude")
@Getter
@Setter
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chudeid")
    private int idCD;
    @Column(name = "tenchude")
    private String tenCD;
    @Column(name = "noidung")
    private String noiDung;

    @ManyToOne
    @JsonBackReference(value = "cd-lcd")
    @JoinColumn(name = "loaibaivietid", foreignKey = @ForeignKey(name = "loaibaivietid"))
    private LoaiBaiViet loaiBaiViet;

    @OneToMany(mappedBy = "chuDe", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "cd-bv")
    private List<BaiViet> baiViets;
}
