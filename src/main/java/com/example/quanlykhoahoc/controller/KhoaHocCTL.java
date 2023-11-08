package com.example.quanlykhoahoc.controller;

import com.example.quanlykhoahoc.entity.KhoaHoc;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.service.KhoaHocSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "apiKhoaHoc")
public class KhoaHocCTL {
    @Autowired
    KhoaHocSer khoaHocSer;

    @PostMapping(value = "themKhoaHoc")
    public ResponseEntity<?> them(@RequestBody Request<KhoaHoc> themKH) {
        return ResponseEntity.ok(khoaHocSer.them(themKH));
    }

    @PutMapping(value = "suaKhoaHoc")
    public ResponseEntity<?> sua(@RequestBody Request<KhoaHoc> suaKH) {
        return ResponseEntity.ok(khoaHocSer.sua(suaKH));
    }

    @DeleteMapping(value = "xoaKhoaHoc")
    public ResponseEntity<?> xoa(@RequestParam int id) {
        return ResponseEntity.ok(khoaHocSer.xoa(id));
    }

    @GetMapping(value = "hienThiKhoaHoc")
    public ResponseEntity<?> hienThi(Integer var, Integer size) {
        if (var == null)
            var = 1;
        if (size == null)
            size = 25;

        Pageable page = PageRequest.of(var, size);
        return ResponseEntity.ok(khoaHocSer.hienThi(page));
    }

    @GetMapping(value = "timKiemTheoTen")
    public ResponseEntity<?> timKiemTheoTen(Integer var, Integer size, String tenKH) {
        if (var == null)
            var = 1;
        if (size == null)
            size = 25;

        Pageable page = PageRequest.of(var, size);
        return ResponseEntity.ok(khoaHocSer.timKiemTheoTen(page, tenKH));
    }
}
