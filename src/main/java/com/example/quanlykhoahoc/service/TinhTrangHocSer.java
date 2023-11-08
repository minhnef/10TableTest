package com.example.quanlykhoahoc.service;

import com.example.quanlykhoahoc.entity.TinhTrangHoc;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.entity.respon.Respon;
import com.example.quanlykhoahoc.repository.IDangKyHocRepo;
import com.example.quanlykhoahoc.repository.ITinhTrangHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class TinhTrangHocSer {
    @Autowired
    ITinhTrangHocRepo tinhTrangHocRepo;
    @Autowired
    IDangKyHocRepo dangKyHocRepo;

    //i.	Thêm
    public Respon<TinhTrangHoc> them(Request<TinhTrangHoc> tinhTrangHocRequest){
        TinhTrangHoc tth = tinhTrangHocRequest.getData();
        String str = tth.getTenTT();
        str = str.trim().toLowerCase(Locale.ROOT);
        str = str.replaceAll("//s", " ");
        tth.setTenTT(str);
        tinhTrangHocRepo.save(tth);
        return new Respon<>(0, "Them tinh trang hoc thanh cong.", tth);
    }

    //sửa
    public Respon<TinhTrangHoc> sua(Request<TinhTrangHoc> tinhTrangHocRequest){
        Optional<TinhTrangHoc> op = tinhTrangHocRepo.findById(tinhTrangHocRequest.getData().getIdTTH());
        if(op.isEmpty()){
            return new Respon<>(1, "ID tinh trang hoc khong ton tai.", null);
        }
        TinhTrangHoc tth = op.get();
        tth.setTenTT(tinhTrangHocRequest.getData().getTenTT());
        tth.setDangKyHocs(tinhTrangHocRequest.getData().getDangKyHocs());
        tinhTrangHocRepo.save(tth);
        dangKyHocRepo.saveAll(tth.getDangKyHocs());
        return new Respon<>(0, "Sua tinh trang hoc thanh cong.", tth);
    }

    //xóa
    public Respon<TinhTrangHoc> xoa(int id){
        Optional<TinhTrangHoc> op = tinhTrangHocRepo.findById(id);
        if(op.isEmpty()){
            return new Respon<>(1, "ID tinh trang hoc khong ton tai", null);
        }
        tinhTrangHocRepo.delete(op.get());
        return new Respon<>(0, "Da xoa tinh trang hoc.", null);
    }

    //hiển thị các tình trạng học
    public Page<TinhTrangHoc> hienThi(Pageable pageable){
        return tinhTrangHocRepo.findAll(pageable);
    }
}
