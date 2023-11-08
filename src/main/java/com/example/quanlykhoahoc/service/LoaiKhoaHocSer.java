package com.example.quanlykhoahoc.service;

import com.example.quanlykhoahoc.entity.KhoaHoc;
import com.example.quanlykhoahoc.entity.LoaiKhoaHoc;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.entity.respon.Respon;
import com.example.quanlykhoahoc.repository.IKhoaHocRepo;
import com.example.quanlykhoahoc.repository.ILoaiKhoaHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoaiKhoaHocSer {
    @Autowired
    private ILoaiKhoaHocRepo loaiKhoaHocRepo;
    @Autowired
    private IKhoaHocRepo khoaHocRepo;

    public Respon<LoaiKhoaHoc> them(Request<LoaiKhoaHoc> themLKH) {
        LoaiKhoaHoc loaiKH = themLKH.getData();

        Optional<LoaiKhoaHoc> op = loaiKhoaHocRepo.findById(loaiKH.getIdLKH());
        for (LoaiKhoaHoc loaiKhoaHoc :
                loaiKhoaHocRepo.findAll()) {
            if (loaiKhoaHoc.getTenloai().equals(loaiKH.getTenloai()))
                return new Respon<LoaiKhoaHoc>(0, "Loai khoa hoc da ton tai.", null);
        }
        loaiKhoaHocRepo.save(loaiKH);
        khoaHocRepo.saveAll(loaiKH.getKhoaHocs());
        return new Respon<>(1, "Them loai khoa hoc thanh cong.", loaiKH);
    }


    public Respon<LoaiKhoaHoc> sua(Request<LoaiKhoaHoc> suaLKH) {
        LoaiKhoaHoc loaiKH = suaLKH.getData();

        Optional<LoaiKhoaHoc> opLKH = loaiKhoaHocRepo.findById(loaiKH.getIdLKH());
        if (opLKH.isEmpty()) {
            return new Respon<>(0, "Loai khoa hoc khong ton tai.", null);
        }
        LoaiKhoaHoc loaiKhoaHoc = opLKH.get();

        loaiKhoaHoc.setTenloai(loaiKH.getTenloai());
        loaiKhoaHoc.setKhoaHocs(loaiKH.getKhoaHocs());

        for (KhoaHoc kh :
                suaLKH.getData().getKhoaHocs()) {
            Optional<KhoaHoc> opkh = khoaHocRepo.findById(kh.getIdKH());
            if (opkh.isEmpty()) {
                khoaHocRepo.save(opkh.get());
            } else {
                opkh.get().setTenKH(kh.getTenKH());
                opkh.get().setHocPhi(kh.getHocPhi());
                opkh.get().setDangKyHocs(kh.getDangKyHocs());
                opkh.get().setGioiThieu(kh.getGioiThieu());
                opkh.get().setLkh(kh.getLkh());
                opkh.get().setThoiGianHoc(kh.getThoiGianHoc());
                opkh.get().setGioiThieu(kh.getGioiThieu());
                opkh.get().setNoiDung(kh.getNoiDung());
                opkh.get().setSoHV(kh.getSoHV());
                opkh.get().setSoLuongMon(kh.getSoLuongMon());

                khoaHocRepo.save(opkh.get());
            }
        }
        loaiKhoaHocRepo.save(loaiKhoaHoc);

        return new Respon<>(1, "Sua loai khoa hoc thanh cong", loaiKhoaHoc);
    }

    public Respon<LoaiKhoaHoc> xoa(int id) {
        Optional<LoaiKhoaHoc> op = loaiKhoaHocRepo.findById(id);
        if (op.isEmpty()) {
            return new Respon<>(0, "Loai khoa hoc khong ton tai.", null);
        }

        for (KhoaHoc khoaHoc :
                op.get().getKhoaHocs()) {
            khoaHocRepo.delete(khoaHoc);
        }
        loaiKhoaHocRepo.delete(op.get());
        return new Respon<>(1, "Xoa loai khoa hoc thanh cong.", op.get());
    }


}
