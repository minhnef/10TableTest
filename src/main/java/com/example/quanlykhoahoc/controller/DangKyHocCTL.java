package com.example.quanlykhoahoc.controller;

import com.example.quanlykhoahoc.entity.DangKyHoc;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.service.DangKyHocSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "apiDangKyHoc")
public class DangKyHocCTL {
    @Autowired
    DangKyHocSer dangKyHocSer;

    @PostMapping(value = "themDangKyHoc")
    public ResponseEntity<?> them(@RequestBody Request<DangKyHoc> request) {
        return ResponseEntity.ok(dangKyHocSer.them(request));
    }

    @PutMapping(value = "suaDangKyHoc")
    public ResponseEntity<?> sua(@RequestBody Request<DangKyHoc> request) {
        return ResponseEntity.ok(dangKyHocSer.sua(request));
    }

    @DeleteMapping(value = "xoaDangKyHoc")
    public ResponseEntity<?> xoa(@RequestParam int id) {
        return ResponseEntity.ok(dangKyHocSer.xoa(id));
    }

    @GetMapping(value = "hienThi")
    public ResponseEntity<?> hienThi(Integer var, Integer size) {
        if (var == null) var = 0;
        if (size == null) size = 10;
        Pageable pageable = PageRequest.of(var, size);
        return ResponseEntity.ok(dangKyHocSer.hienThi(pageable));
    }


}
