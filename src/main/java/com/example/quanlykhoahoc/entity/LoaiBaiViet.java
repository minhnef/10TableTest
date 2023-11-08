package com.example.quanlykhoahoc.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Table(name = "loaibaiviet")
@Getter
@Setter
public class LoaiBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loaibaivvietid")
    private int idLBV;
    @Column(name = "tenloai")
    private String tenloai;

    @OneToMany(mappedBy = "loaiBaiViet", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "cd-lcd")
    private List<ChuDe> chuDes;

}
