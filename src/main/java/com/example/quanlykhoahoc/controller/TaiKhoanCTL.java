package com.example.quanlykhoahoc.controller;

import com.example.quanlykhoahoc.entity.TaiKhoan;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.service.TaiKhoanSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "apiTaiKhoan")
public class TaiKhoanCTL {
    @Autowired
    TaiKhoanSer taiKhoanSer;

    @PostMapping(value = "themTaiKhoan")
    public ResponseEntity<?> them(@RequestBody Request<TaiKhoan> request){
        return ResponseEntity.ok(taiKhoanSer.them(request));
    }

    @PutMapping(value = "suaTaiKhoan")
    public ResponseEntity<?> sua(@RequestBody Request<TaiKhoan> request){
        return ResponseEntity.ok(taiKhoanSer.sua( request));
    }

    @DeleteMapping(value = "xoaTaiKhoan")
    public ResponseEntity<?> xoa(@RequestParam int id){
        return ResponseEntity.ok(taiKhoanSer.xoa(id));
    }

    @GetMapping(value = "hienThiTaiKhoan")
    public ResponseEntity<?> hienThi(Integer var, Integer size){
        if(var == null) var = 0;
        if(size == null) size = 10;
        Pageable pageable = PageRequest.of(var, size);
        return ResponseEntity.ok(taiKhoanSer.hienThi(pageable));
    }
}
