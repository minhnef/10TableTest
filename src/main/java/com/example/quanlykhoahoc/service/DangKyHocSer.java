package com.example.quanlykhoahoc.service;

import com.example.quanlykhoahoc.entity.*;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.entity.respon.Respon;
import com.example.quanlykhoahoc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class DangKyHocSer {
    @Autowired
    IDangKyHocRepo dangKyHocRepo;
    @Autowired
    IKhoaHocRepo khoaHocRepo;
    @Autowired
    ITinhTrangHocRepo tinhTrangHocRepo;
    @Autowired
    IHocVienRepo hocVienRepo;
    @Autowired
    ITaiKhoanRepo taiKhoanRepo;

    /*
    i.	Ngày đăng ký tự động cập nhật,
    ngày bắt đầu được tính từ ngày tình trạng học là đang học chính,
    ngày kết thúc tính bằng ngày bắt đầu + thời gian học trong bảng khóa học
     */


    //ii.	Thêm
    public Respon<DangKyHoc> them(Request<DangKyHoc> request) {
        DangKyHoc dkh = request.getData();

        Optional<KhoaHoc> op = khoaHocRepo.findById(dkh.getKhoaHoc().getIdKH());
        if (op.isEmpty()) {
            return new Respon<>(1, "Khoa hoc khong ton tai.", null);
        }

        Optional<TinhTrangHoc> optth = tinhTrangHocRepo.findById(dkh.getTinhTrangHoc().getIdTTH());
        if (optth.isEmpty()) {
            return new Respon<>(2, "Kiem tra lai tinh trang hoc.", null);
        }

        Optional<HocVien> ophv = hocVienRepo.findById(dkh.getHocVien().getIdHV());
        if (ophv.isEmpty()) {
            return new Respon<>(3, "ID hoc vien khong ton tai.", null);
        }

        Optional<TaiKhoan> optk = taiKhoanRepo.findById(dkh.getTaiKh().getIdTK());
        if (optk.isEmpty()) {
            return new Respon<>(4, "ID tai khoan khong ton tai.", null);
        }

        dkh.setNgDKy(LocalDate.now());
        if (dkh.getTinhTrangHoc().getIdTTH() == 2) {
            dkh.setNgBD(LocalDate.now());
        }
        if(dkh.getNgBD() == null){
            return new Respon<>(5, "Hoc vien chua bat dau khoa hoc.", dkh);
        }
        if (dkh.getTinhTrangHoc().getIdTTH() == 3) {
            long ngay = dkh.getNgBD().toEpochDay() + khoaHocRepo.findById(dkh.getKhoaHoc().getIdKH()).get().getThoiGianHoc();
            LocalDate ngayKT = LocalDate.ofEpochDay(ngay);
            dkh.setNgKT(ngayKT);
        }
        op.get().setSoHV(op.get().getSoHV()+1);
        khoaHocRepo.save(op.get());
        dangKyHocRepo.save(dkh);
        return new Respon<>(0, "Them dang ky hoc thanh cong", request.getData());
    }

    // sửa
    public Respon<DangKyHoc> sua(Request<DangKyHoc> request) {
        DangKyHoc dkh = request.getData();

        Optional<DangKyHoc> opdkh = dangKyHocRepo.findById(dkh.getId());
        if (opdkh.isEmpty()) {
            return new Respon<>(1, "ID dang ky hoc khong ton tai.", null);
        }

        Optional<KhoaHoc> op = khoaHocRepo.findById(dkh.getKhoaHoc().getIdKH());
        if (op.isEmpty()) {
            return new Respon<>(2, "Khoa hoc khong ton tai.", null);
        }

        Optional<TinhTrangHoc> optth = tinhTrangHocRepo.findById(dkh.getTinhTrangHoc().getIdTTH());
        if (optth.isEmpty()) {
            return new Respon<>(3, "Kiem tra lai tinh trang hoc.", null);
        }

        Optional<HocVien> ophv = hocVienRepo.findById(dkh.getHocVien().getIdHV());
        if (ophv.isEmpty()) {
            return new Respon<>(4, "ID hoc vien khong ton tai.", null);
        }

        Optional<TaiKhoan> optk = taiKhoanRepo.findById(dkh.getTaiKh().getIdTK());
        if (optk.isEmpty()) {
            return new Respon<>(5, "ID tai khoan khong ton tai.", null);
        }

        if(dkh.getNgDKy()==null)
        dkh.setNgDKy(LocalDate.now());
        if (dkh.getTinhTrangHoc().getIdTTH() == 2) {
            opdkh.get().setNgBD(LocalDate.now());
        }

        if (dkh.getTinhTrangHoc().getIdTTH() == 3) {
            long ngay = opdkh.get().getNgBD().toEpochDay() + khoaHocRepo.findById(dkh.getIdKH()).get().getThoiGianHoc();
            LocalDate ngayKT = LocalDate.ofEpochDay(ngay);
            opdkh.get().setNgKT(ngayKT);
        }
        dangKyHocRepo.save(opdkh.get());
        return new Respon<>(0, "Sua dang ky hoc thanh cong.", opdkh.get());
    }

    //Xoá
    public Respon<DangKyHoc> xoa(int id){
        Optional<DangKyHoc> op = dangKyHocRepo.findById(id);
        if(op.isEmpty()){
            return new Respon<>(1, "ID dang ky khong ton tai.", null);
        }
        dangKyHocRepo.delete(op.get());


        return new Respon<>(0,"Xoa dang ky hoc thanh cong.", null);
    }

    //Hiển thị + phân trang
    public Page<DangKyHoc> hienThi(Pageable pageable){
        return dangKyHocRepo.findAll(pageable);
    }



}
