package com.example.quanlykhoahoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Table(name = "taikhoan", uniqueConstraints = @UniqueConstraint(columnNames = {"tennguoidung", "taikhoan", "matkhau"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taikhoanid")
    private int idTK;
    @Column(name = "tennguoidung")
    @NotNull
    private String userName;
    @Column(name = "taikhoan")
    @Size(min = 8)
    @NotBlank
    @NotNull
    private String account;
    @Column(name = "matkhau")
    @Size(min = 8)
    @NotBlank
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}$")
    private String password;

    @ManyToOne
    @JsonBackReference(value = "tk-qh")
    @JoinColumn(name = "quyenhanid", foreignKey = @ForeignKey(name = "fk_tk_qh"))
    private QuyenHan quyenHan;

    @OneToMany(mappedBy = "taiKh", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "tk-dkh")
    private List<DangKyHoc> dangKyHocs;

    @OneToMany(mappedBy = "taiKhoan", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "bv-tk")
    private List<BaiViet> baiViets;

}
