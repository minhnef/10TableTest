package com.example.quanlykhoahoc.service;

import com.example.quanlykhoahoc.entity.QuyenHan;
import com.example.quanlykhoahoc.entity.TaiKhoan;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.entity.respon.Respon;
import com.example.quanlykhoahoc.repository.IDangKyHocRepo;
import com.example.quanlykhoahoc.repository.IQuyenHanRepo;
import com.example.quanlykhoahoc.repository.ITaiKhoanRepo;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Set;

@Service
public class QuyenHanSer {
    @Autowired
    IQuyenHanRepo quyenHanRepo;
    @Autowired
    ITaiKhoanRepo taiKhoanRepo;
    @Autowired
    IDangKyHocRepo dangKyHocRepo;


    //    i.	Thêm,
    public Respon<?> them(Request<QuyenHan> request) {
        try {
            for (QuyenHan qh :
                    quyenHanRepo.findAll()) {
                if (qh.getTenQH().equals(request.getData().getTenQH())) {
                    return new Respon<>(1, "Ten quyen han da ton tai.", null);
                }
            }
            quyenHanRepo.save(request.getData());
            taiKhoanRepo.saveAll(request.getData().getTaiKhoans());
        } catch (Exception e) {
            e.printStackTrace();
            return new Respon<>(2, e.getMessage(), request.getData());
        }
        return new Respon<>(0, "Them quyen han thanh cong.", request.getData());
    }

    // sửa,
    public Respon<?> sua(Request<QuyenHan> request) {
        Optional<QuyenHan> op = quyenHanRepo.findById(request.getData().getIdQH());
        QuyenHan quyenHan = null;
        try {
            if (op.isEmpty()) {
                return new Respon<>(1, "ID quyen han khong ton tai.", null);
            }
            for (QuyenHan qh :
                    quyenHanRepo.findAll()) {
                if (qh.getTenQH().equals(request.getData().getTenQH())) {
                    return new Respon<>(1, "Ten quyen han da ton tai.", request.getData());
                }
            }

            quyenHan = op.get();
            quyenHan.setTenQH(request.getData().getTenQH());
            quyenHan.setTaiKhoans(request.getData().getTaiKhoans());
            quyenHanRepo.save(quyenHan);
            taiKhoanRepo.saveAll(quyenHan.getTaiKhoans());
        } catch (Exception e) {
            e.printStackTrace();
            return new Respon<>(2, e.getMessage(), request.getData());
        }
        return new Respon<>(0, "Sua quyen han thanh cong.", quyenHan);
    }

    //xóa,
    public Respon<QuyenHan> xoa(int id) {
        Optional<QuyenHan> op = quyenHanRepo.findById(id);
        if (op.isEmpty()) {
            return new Respon<>(1, "ID quyen han khong ton tai.", null);
        }
        for (TaiKhoan taiKhoan :
                op.get().getTaiKhoans()) {
            taiKhoan.setQuyenHan(null);
            taiKhoan.setAccount(null);
            taiKhoan.setBaiViets(null);
            taiKhoan.setPassword(null);
            taiKhoan.setDangKyHocs(null);
            taiKhoanRepo.save(taiKhoan);
            dangKyHocRepo.saveAll(taiKhoan.getDangKyHocs());

        }
        quyenHanRepo.delete(op.get());
        return new Respon<>(0, "Xoa quyen han thanh cong.", null);
    }

    //hiển thị các quyền hạn + quyền hạn
    public Page<QuyenHan> hienThi(Pageable pageable) {
        return quyenHanRepo.findAllNews(pageable);
    }


}
