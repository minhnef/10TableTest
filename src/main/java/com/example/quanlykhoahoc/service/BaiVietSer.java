package com.example.quanlykhoahoc.service;

import com.example.quanlykhoahoc.entity.BaiViet;
import com.example.quanlykhoahoc.entity.ChuDe;
import com.example.quanlykhoahoc.entity.LoaiBaiViet;
import com.example.quanlykhoahoc.entity.TaiKhoan;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.entity.respon.Respon;
import com.example.quanlykhoahoc.repository.IBaiVietRepo;
import com.example.quanlykhoahoc.repository.IChuDeRepo;
import com.example.quanlykhoahoc.repository.ILoaiBaiVietRepo;
import com.example.quanlykhoahoc.repository.ITaiKhoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BaiVietSer {
    @Autowired
    IBaiVietRepo baiVietRepo;
    @Autowired
    IChuDeRepo chuDeRepo;
    @Autowired
    ITaiKhoanRepo taiKhoanRepo;


    //i.	Thêm,
    public Respon<?> them(Request<BaiViet> request){
        BaiViet baiViet = request.getData();

        Optional<ChuDe> opcd = chuDeRepo.findById(baiViet.getChuDe().getIdCD());
        if(opcd.isEmpty()){
            return new Respon<>(1, "Chu de bai viet khong ton tai.", baiViet.getChuDe());
        }

        Optional<TaiKhoan> optk = taiKhoanRepo.findById(baiViet.getTaiKhoan().getIdTK());
        if(optk.isEmpty()){
            return new Respon<>(2, "Tai khoan khong ton tai.", baiViet.getTaiKhoan());
        }

        baiViet.setThoiGianTao(LocalDate.now());
        baiVietRepo.save(baiViet);
        return new Respon<>(0, "Them bai viet thanh cong.", baiViet);
    }

    //sửa,
    public Respon<?> sua(Request<BaiViet> request){
        BaiViet baiViet = request.getData();
        baiViet.setThoiGianTao(LocalDate.now());

        Optional<ChuDe> opcd = chuDeRepo.findById(baiViet.getChuDe().getIdCD());
        if(opcd.isEmpty()){
            return new Respon<>(1, "Chu de bai viet khong ton tai.", baiViet.getChuDe());
        }

        Optional<TaiKhoan> optk = taiKhoanRepo.findById(baiViet.getTaiKhoan().getIdTK());
        if(optk.isEmpty()){
            return new Respon<>(2, "Tai khoan khong ton tai.", baiViet.getTaiKhoan());
        }

        baiVietRepo.save(baiViet);
        return new Respon<>(0, "Sua bai viet thanh cong.", baiViet);
    }

    //xóa,
    public Respon<?> xoa(int id){
        Optional<BaiViet> op = baiVietRepo.findById(id);
        if(op.isEmpty()){
            return new Respon<>(1, "Bai viet khong ton tai." , null);
        }
        baiVietRepo.delete(op.get());
        return new Respon<>(0, "Xoa bai viet thanh cong.", null);
    }

    //hiển thị danh sách bài viết + phan trang
    public Page<BaiViet> hienThi(Pageable pageable){
        return baiVietRepo.findAllNews(pageable);
    }


}
