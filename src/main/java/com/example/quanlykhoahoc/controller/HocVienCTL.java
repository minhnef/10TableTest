package com.example.quanlykhoahoc.controller;

import com.example.quanlykhoahoc.entity.HocVien;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.service.HocVienSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "apiHocVien")
public class HocVienCTL {
    @Autowired
    HocVienSer hocVienSer;

    @PostMapping(value = "themHocVien")
    public ResponseEntity<?> them(@RequestBody Request<HocVien> hocVienRequest){
        return ResponseEntity.ok(hocVienSer.them(hocVienRequest));
    }

    @PutMapping(value = "suaHocVien")
    public ResponseEntity<?> sua(@RequestBody Request<HocVien> hocVienRequest){
        return ResponseEntity.ok(hocVienSer.sua(hocVienRequest));
    }

    @DeleteMapping(value = "xoaHocVien")
    public ResponseEntity<?> xoa(@RequestParam int id){
        return ResponseEntity.ok(hocVienSer.xoa(id));
    }

    @GetMapping(value = "hienThiHocvien")
    public ResponseEntity<?> hienThi(Integer var, Integer size){
        if(var == null) var = 0;
        if(size == null) size = 10;

        Pageable page = PageRequest.of(var, size);
        return ResponseEntity.ok(hocVienSer.hienThi(page));
    }

    @GetMapping(value = "timKiemTheoTenvaEmail")
    public ResponseEntity<?> timKiem(String hoTen, String email){
        return ResponseEntity.ok(hocVienSer.timKiemTheoTenVaEmail(hoTen,email));
    }
}
