package com.example.quanlykhoahoc.service;

import com.example.quanlykhoahoc.entity.HocVien;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.entity.respon.Respon;
import com.example.quanlykhoahoc.repository.IDangKyHocRepo;
import com.example.quanlykhoahoc.repository.IHocVienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class HocVienSer {
    @Autowired
    IHocVienRepo hocVienRepo;
    @Autowired
    IDangKyHocRepo dangKyHocRepo;

    private HocVien chuanHoa(HocVien hocVien) {
        String str = hocVien.getHoTen();
        str = str.trim().toLowerCase();
        str = str.replaceAll("//s", " ");
        String[] hoTen = str.split(" ");
        String hoten = "";
        for (String s :
                hoTen) {
            s = s.substring(0, 1).toUpperCase() + s.substring(1);
            hoten = hoten+" "+ s;
        }
        System.out.println(hoten);
        hocVien.setHoTen(hoten);
        return hocVien;
    }

    //i.	Thêm
    public Respon<HocVien> them(Request<HocVien> hocVienRequest) {
        if (hocVienRequest.getData().getSdt().isEmpty() || hocVienRequest.getData().getEmail().isEmpty())
            return new Respon<>(3, "So dien thoai va email khong duoc de trong", hocVienRequest.getData());
        for (HocVien hv :
                hocVienRepo.findAll()) {
            if (hv.getEmail().equals(hocVienRequest.getData().getEmail()))
                return new Respon<>(1, "Email da ton tai.", null);
            if (hv.getSdt().equals(hocVienRequest.getData().getSdt()))
                return new Respon<>(2, "So dien thoai da ton tai", null);
        }
        chuanHoa(hocVienRequest.getData());
        hocVienRepo.save(hocVienRequest.getData());
        return new Respon<>(0, "Them hoc vien thanh cong", hocVienRequest.getData());
    }

    //sửa
    public Respon<HocVien> sua(Request<HocVien> suaHV) {
        Optional<HocVien> opHV = hocVienRepo.findById(suaHV.getData().getIdHV());
        if (opHV.isEmpty())
            return new Respon<>(4, "ID hoc vien khong ton tai.", suaHV.getData());

        if (suaHV.getData().getSdt().isEmpty() || suaHV.getData().getEmail().isEmpty())
            return new Respon<>(3, "So dien thoai va email khong duoc de trong", suaHV.getData());

        for (HocVien hv :
                hocVienRepo.findAll()) {
            if (hv.getEmail().equals(suaHV.getData().getEmail())) return new Respon<>(1, "Email da ton tai.", null);
            if (hv.getSdt().equals(suaHV.getData().getSdt())) return new Respon<>(2, "So dien thoai da ton tai", null);
        }
        HocVien hocVien = opHV.get();

        hocVien.setDangKyHocs(suaHV.getData().getDangKyHocs());
        hocVien.setEmail(suaHV.getData().getEmail());
        hocVien.setHoTen(suaHV.getData().getHoTen());
        hocVien.setNgSinh(suaHV.getData().getNgSinh());
        hocVien.setPhuongXa(suaHV.getData().getPhuongXa());
        hocVien.setQuanHuyen(suaHV.getData().getQuanHuyen());
        hocVien.setSdt(suaHV.getData().getSdt());
        hocVien.setTinhThanh(suaHV.getData().getTinhThanh());
        chuanHoa(hocVien);
        hocVienRepo.save(hocVien);
        dangKyHocRepo.saveAll(hocVien.getDangKyHocs());

        return new Respon<>(0, "Sua hoc vien thanh cong", suaHV.getData());
    }

    //xóa
    public Respon<HocVien> xoa(int id) {
        Optional<HocVien> op = hocVienRepo.findById(id);
        if (op.isEmpty()) {
            return new Respon<>(1, "ID hoc vien khong ton tai.", null);
        }
        dangKyHocRepo.deleteAll(op.get().getDangKyHocs());
        hocVienRepo.delete(op.get());
        return new Respon<>(0, "Xoa hoc vien thanh cong.", null);
    }

    //hiển thị danh sách học viên
    public Page<HocVien> hienThi(Pageable pageable) {
        return hocVienRepo.findAllNews(pageable);
    }

    public HocVien timKiemTheoTenVaEmail(String hoTen, String email) {
        return hocVienRepo.findAllByHoTenAndEmailEquals(hoTen, email);
    }

}
