package com.example.quanlykhoahoc.service;

import com.example.quanlykhoahoc.entity.DangKyHoc;
import com.example.quanlykhoahoc.entity.KhoaHoc;
import com.example.quanlykhoahoc.entity.LoaiKhoaHoc;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.entity.respon.Respon;
import com.example.quanlykhoahoc.repository.IDangKyHocRepo;
import com.example.quanlykhoahoc.repository.IKhoaHocRepo;
import com.example.quanlykhoahoc.repository.ILoaiKhoaHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KhoaHocSer {
    @Autowired
    ILoaiKhoaHocRepo loaiKhoaHocRepo;
    @Autowired
    IKhoaHocRepo khoaHocRepo;
    @Autowired
    IDangKyHocRepo dangKyHocRepo;

    //	Thêm
    public Respon<KhoaHoc> them(Request<KhoaHoc> themKH) {
        KhoaHoc kh = themKH.getData();
        List<DangKyHoc> list = new ArrayList<>();
        Optional<LoaiKhoaHoc> op = loaiKhoaHocRepo.findById(kh.getLkh().getIdLKH());
        if (op.isEmpty()) {
            return new Respon<>(1, "Loai khoa hoc khong ton tai", null);
        }

        int sohv = kh.getSoHV();
        for (DangKyHoc dangKyHoc :
                kh.getDangKyHocs()) {
            if (dangKyHoc.getTinhTrangHoc().getIdTTH() == 2) {
                if (dangKyHoc.getNgDKy() == null) dangKyHoc.setNgDKy(LocalDate.now());
                if (dangKyHoc.getNgBD() == null) dangKyHoc.setNgBD(LocalDate.now());
                sohv++;
            }
            if (dangKyHoc.getTinhTrangHoc().getIdTTH() == 3) {
                if(dangKyHoc.getNgBD()==null) return new Respon<>(2, "Hoc vien chua bat dau khoa hoc.", null);
                if(dangKyHoc.getNgKT()==null) {
                    long ngay = dangKyHoc.getNgBD().toEpochDay() + khoaHocRepo.findById(dangKyHoc.getIdKH()).get().getThoiGianHoc();
                    LocalDate ngayKT = LocalDate.ofEpochDay(ngay);
                    dangKyHoc.setNgKT(ngayKT);
                }
                sohv--;
            }
            list.add(dangKyHoc);
//            dangKyHocRepo.save(dangKyHoc);
        }
        kh.setDangKyHocs(list);
        kh.setSoHV(sohv);
        khoaHocRepo.save(kh);
        dangKyHocRepo.saveAll(list);
        return new Respon<>(0, "Them khoa hoc thanh cong.", kh);
    }

    //sửa
    public Respon<?> sua(Request<KhoaHoc> suaKH) {
        Optional<LoaiKhoaHoc> opLKH = loaiKhoaHocRepo.findById(suaKH.getData().getLkh().getIdLKH());
        List<DangKyHoc> list = new ArrayList<>();
        if (opLKH.isEmpty()) {
            return new Respon<>(1, "Loai khoa hoc khong ton tai.", null);
        }

        Optional<KhoaHoc> opKH = khoaHocRepo.findById(suaKH.getData().getIdKH());
        if (opKH.isEmpty()) {
            return new Respon<>(2, "ID khoa hoc khong ton tai.", null);
        }

        opKH.get().setSoLuongMon(suaKH.getData().getSoLuongMon());
        opKH.get().setGioiThieu(suaKH.getData().getGioiThieu());
        opKH.get().setLkh(suaKH.getData().getLkh());
        opKH.get().setHocPhi(suaKH.getData().getHocPhi());
        opKH.get().setNoiDung(suaKH.getData().getNoiDung());
        opKH.get().setThoiGianHoc(suaKH.getData().getThoiGianHoc());
        opKH.get().setTenKH(suaKH.getData().getTenKH());
        opKH.get().setDangKyHocs(suaKH.getData().getDangKyHocs());

        int sohv = opKH.get().getSoHV();
        for (DangKyHoc dangKyHoc :
                opKH.get().getDangKyHocs()) {
            if (dangKyHoc.getTinhTrangHoc().getIdTTH() == 2) {
                if (dangKyHoc.getNgDKy() == null) dangKyHoc.setNgDKy(LocalDate.now());
                if (dangKyHoc.getNgBD() == null) dangKyHoc.setNgBD(LocalDate.now());
                sohv++;
            }
            if (dangKyHoc.getTinhTrangHoc().getIdTTH() == 3) {
                if(dangKyHoc.getNgBD()==null) return new Respon<>(3, "Hoc vien chua bat dau khoa hoc.", dangKyHoc);
                if(dangKyHoc.getNgKT()==null) {
                    long ngay = dangKyHoc.getNgBD().toEpochDay() + khoaHocRepo.findById(dangKyHoc.getIdKH()).get().getThoiGianHoc();
                    LocalDate ngayKT = LocalDate.ofEpochDay(ngay);
                    dangKyHoc.setNgKT(ngayKT);
                }
                sohv--;
            }
            list.add(dangKyHoc);
        }
        opKH.get().setSoHV(sohv);
        opKH.get().setDangKyHocs(list);
        dangKyHocRepo.saveAll(list);
        khoaHocRepo.save(opKH.get());

        return new Respon<>(0, "Sua khoa hoc thanh cong.", suaKH.getData());
    }

    //xóa
    public Respon<KhoaHoc> xoa(int id) {
        Optional<KhoaHoc> op = khoaHocRepo.findById(id);
        if (op.isEmpty()) {
            return new Respon<>(1, "Khong tim thay ID khoa hoc.", null);
        }
        khoaHocRepo.delete(op.get());
        return new Respon<>(0, "xoa khoa hoc thanh cong.", null);
    }

    //hiển thị danh sách khóa học
    public Page<KhoaHoc> hienThi(Pageable page) {
        return khoaHocRepo.findAllNews(page);
    }

    //ii.	Tìm kiếm khóa học theo tên
    public Page<KhoaHoc> timKiemTheoTen(Pageable page, String tenKH) {
        return khoaHocRepo.findAllByTenKH(page, tenKH);
    }
}
