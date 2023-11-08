package com.example.quanlykhoahoc.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "quyenhan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuyenHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quyenhanid")
    private int idQH;
    @Column(name = "tenquyenhan")
    private String tenQH;

    @OneToMany(mappedBy = "quyenHan", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "tk-qh")
    private List<TaiKhoan> taiKhoans;
}
