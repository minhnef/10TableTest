package com.example.quanlykhoahoc.service;

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
public class LoaiBaiVietSer {
    @Autowired
    ILoaiBaiVietRepo loaiBaiVietRepo;
    @Autowired
    IChuDeRepo chuDeRepo;
    @Autowired
    IBaiVietRepo baiVietRepo;

    //i.	Thêm,
    public Respon<LoaiBaiViet> them(Request<LoaiBaiViet> request){
        LoaiBaiViet loaiBaiViet = request.getData();
        loaiBaiVietRepo.save(loaiBaiViet);
        chuDeRepo.saveAll(loaiBaiViet.getChuDes());
        return new Respon<>(0, "Them loai bai viet thanh cong.", loaiBaiViet);
    }

    //sửa,
    public Respon<LoaiBaiViet> sua(Request<LoaiBaiViet> requestSua){
        LoaiBaiViet loaiBaiViet = requestSua.getData();
        Optional<LoaiBaiViet> op = loaiBaiVietRepo.findById(loaiBaiViet.getIdLBV());
        if(op.isEmpty()){
            return new Respon<>(1,"ID khong ton tai.", null);
        }

        loaiBaiVietRepo.save(loaiBaiViet);
        for (ChuDe chuDe :
                loaiBaiViet.getChuDes()) {
            Optional<ChuDe> optional = chuDeRepo.findById(chuDe.getIdCD());
            if(optional.isEmpty()){
                chuDeRepo.save(chuDe);
            }
            ChuDe chuDe1 = optional.get();;
            chuDe1.setLoaiBaiViet(chuDe.getLoaiBaiViet());
            chuDe1.setBaiViets(chuDe.getBaiViets());
            chuDe1.setTenCD(chuDe.getTenCD());
            chuDe1.setNoiDung(chuDe.getNoiDung());
            baiVietRepo.saveAll(chuDe1.getBaiViets());
            chuDeRepo.save(chuDe1);

        }
        return new Respon<>(0,"Sua loai bai viet thanh cong.", loaiBaiViet);
    }

    //xóa,
    public Respon<LoaiBaiViet> xoa(int id){
        Optional<LoaiBaiViet> op = loaiBaiVietRepo.findById(id);
        if(op.isEmpty()){
            return new Respon<>(1,"ID loai bai viet khong ton tai.",null);
        }
        for (ChuDe chuDe :
                op.get().getChuDes()) {
            chuDe.setBaiViets(null);
            chuDe.setLoaiBaiViet(null);
            chuDe.setNoiDung(null);
            chuDe.setTenCD(null);
            chuDeRepo.save(chuDe);
        }
        LoaiBaiViet loaiBaiViet = op.get();
        loaiBaiViet.setChuDes(null);
        loaiBaiViet.setTenloai(null);
        loaiBaiVietRepo.save(loaiBaiViet);
        return new Respon<>(0, "Xoa thanh cong.", null);
    }

    //hiển thị danh sách bài viết
    public Page<LoaiBaiViet> hienThi(Pageable pageable){
        return loaiBaiVietRepo.findAllNews(pageable);
    }

}
