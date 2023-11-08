package com.example.quanlykhoahoc.service;

import com.example.quanlykhoahoc.entity.BaiViet;
import com.example.quanlykhoahoc.entity.ChuDe;
import com.example.quanlykhoahoc.entity.LoaiBaiViet;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.entity.respon.Respon;
import com.example.quanlykhoahoc.repository.IBaiVietRepo;
import com.example.quanlykhoahoc.repository.IChuDeRepo;
import com.example.quanlykhoahoc.repository.ILoaiBaiVietRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChuDeSer {
    @Autowired
    IChuDeRepo chuDeRepo;
    @Autowired
    ILoaiBaiVietRepo loaiBaiVietRepo;
    @Autowired
    IBaiVietRepo baiVietRepo;

    //i.	Thêm,
    public Respon<ChuDe> them(Request<ChuDe> request) {
        ChuDe chuDe = request.getData();
        Optional<LoaiBaiViet> op = loaiBaiVietRepo.findById(chuDe.getLoaiBaiViet().getIdLBV());
        if (op.isEmpty()) {
            return new Respon<>(1, "ID loai bai viet khong ton tai.", null);
        }

        chuDeRepo.save(chuDe);
        baiVietRepo.saveAll(chuDe.getBaiViets());

        return new Respon<>(0, "Them chu de thanh cong.", chuDe);

    }

    //    , sửa,
    public Respon<ChuDe> sua(Request<ChuDe> request) {
        ChuDe chuDe = request.getData();
        Optional<LoaiBaiViet> op = loaiBaiVietRepo.findById(chuDe.getLoaiBaiViet().getIdLBV());
        if (op.isEmpty()) {
            return new Respon<>(1, "ID loai bai viet khong ton tai.", null);
        }
        baiVietRepo.saveAll(chuDe.getBaiViets());
        chuDeRepo.save(chuDe);

        return new Respon<>(0, "Sua chu de thanh cong.", chuDe);
    }

    //xóa,
    public Respon<ChuDe> xoa(int id){
        Optional<ChuDe> op = chuDeRepo.findById(id);
        if(op.isEmpty()){
            return new Respon<>(1, "ID chu de khong ton tai.", null);
        }

        chuDeRepo.delete(op.get());
        return new Respon<>(0,"Xoa chu de thanh cong.", op.get());
    }

    //hiển thị các chủ đề + phan trang
    public Page<ChuDe> hienThi(Pageable pageable){
        return chuDeRepo.findAllNews(pageable);
    }

}
