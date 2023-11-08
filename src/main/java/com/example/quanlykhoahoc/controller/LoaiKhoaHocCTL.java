package com.example.quanlykhoahoc.controller;

import com.example.quanlykhoahoc.entity.KhoaHoc;
import com.example.quanlykhoahoc.entity.LoaiKhoaHoc;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.service.LoaiKhoaHocSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "apiLoaiKhoaHoc")
public class LoaiKhoaHocCTL {
    @Autowired
    private LoaiKhoaHocSer loaiKhoaHocSer;


    @PostMapping(value = "themLoaiKhoaHoc")
    public ResponseEntity<?> them(@RequestBody Request<LoaiKhoaHoc> loaiKhoaHocRequest){
        return ResponseEntity.ok(loaiKhoaHocSer.them(loaiKhoaHocRequest));
    }

    @PutMapping(value = "suaLoaiKhoaHoc")
    public ResponseEntity<?> sua(@RequestBody Request<LoaiKhoaHoc> suaLKH){
        return ResponseEntity.ok(loaiKhoaHocSer.sua(suaLKH));
    }

    @DeleteMapping(value = "xoaLoaiKhoaHoc")
    public ResponseEntity<?> xoa(@RequestParam int id){
        return ResponseEntity.ok(loaiKhoaHocSer.xoa(id));
    }

}
