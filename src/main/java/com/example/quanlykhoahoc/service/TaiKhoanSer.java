package com.example.quanlykhoahoc.service;

import com.example.quanlykhoahoc.entity.DangKyHoc;
import com.example.quanlykhoahoc.entity.QuyenHan;
import com.example.quanlykhoahoc.entity.TaiKhoan;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.entity.respon.Respon;
import com.example.quanlykhoahoc.repository.IQuyenHanRepo;
import com.example.quanlykhoahoc.repository.ITaiKhoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaiKhoanSer {
    @Autowired
    ITaiKhoanRepo taiKhoanRepo;
    @Autowired
    IQuyenHanRepo quyenHanRepo;
    @Autowired
    DangKyHocSer dangKyHocSer;


    //i.	Thêm,
    public Respon<TaiKhoan> them(Request<TaiKhoan> request) {
        TaiKhoan taiKhoan = request.getData();
        try {
            Optional<QuyenHan> op = quyenHanRepo.findById(taiKhoan.getQuyenHan().getIdQH());
            if (op.isEmpty()) {
                return new Respon<>(1, "ID quyen han khong ton tai.", null);
            }

            taiKhoanRepo.save(taiKhoan);
            for (DangKyHoc dangKyHoc :
                    taiKhoan.getDangKyHocs()) {
                Request<DangKyHoc> dkh = new Request<>(0, "Cap nhat dang ky hoc theo tai khoan.", dangKyHoc);
                dangKyHocSer.them(dkh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Respon<>(2, e.getMessage(), taiKhoan);
        }
        return new Respon<>(0, "Them tai khoan thanh cong.", taiKhoan);
    }

    // sửa, xóa, hiển thị các tài khoản
    public Respon<TaiKhoan> sua(Request<TaiKhoan> request) {
        try {
            TaiKhoan taiKhoan = request.getData();
            Optional<QuyenHan> op = quyenHanRepo.findById(taiKhoan.getQuyenHan().getIdQH());
            if (op.isEmpty()) {
                return new Respon<>(1, "Quyen han khong ton tai.", null);
            }

            Optional<TaiKhoan> optk = taiKhoanRepo.findById(taiKhoan.getIdTK());
            if (optk.isEmpty()) return new Respon<>(2, "ID tai khoan khong ton tai.", null);

            optk.get().setDangKyHocs(taiKhoan.getDangKyHocs());
            optk.get().setAccount(taiKhoan.getAccount());
            optk.get().setPassword(taiKhoan.getPassword());
            optk.get().setBaiViets(taiKhoan.getBaiViets());
            optk.get().setQuyenHan(taiKhoan.getQuyenHan());
            optk.get().setUserName(taiKhoan.getUserName());

            taiKhoanRepo.save(optk.get());
        } catch (Exception e) {
            e.printStackTrace();
            return new Respon<>(2, e.getMessage(), request.getData());
        }

        return new Respon<>(0, "Sua tai khoan thanh cong.", request.getData());
    }

    public Respon<TaiKhoan> xoa(int id) {
        Optional<TaiKhoan> op = taiKhoanRepo.findById(id);
        if (op.isEmpty()) return new Respon<>(1, "ID tai khoan khong ton tai.", null);

        op.get().setUserName(null);
        op.get().setDangKyHocs(null);
        op.get().setAccount(null);
        op.get().setPassword(null);
        op.get().setQuyenHan(null);
        op.get().setBaiViets(null);

        taiKhoanRepo.save(op.get());
        return new Respon<>(0, "Xoa tai khoan thanh cong.", op.get());
    }

    public Page<TaiKhoan> hienThi(Pageable pageable){
        return taiKhoanRepo.findAllNews(pageable);
    }


}
