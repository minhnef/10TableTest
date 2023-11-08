package com.example.quanlykhoahoc.controller;

import com.example.quanlykhoahoc.entity.QuyenHan;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.service.QuyenHanSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "apiQuyenHan")
public class QuyenHanCTL {
    @Autowired
    QuyenHanSer quyenHanSer;

    @PostMapping(value = "themQuyenHan")
    public ResponseEntity<?> them(@RequestBody Request<QuyenHan> request) {
        return ResponseEntity.ok(quyenHanSer.them(request));
    }

    @PutMapping(value = "suaQuyenHan")
    public ResponseEntity<?> sua(@RequestBody Request<QuyenHan> request) {
        return ResponseEntity.ok(quyenHanSer.sua(request));
    }

    @DeleteMapping(value = "xoaQuyenHan")
    public ResponseEntity<?> xoa(@RequestParam int id) {
        return ResponseEntity.ok(quyenHanSer.xoa(id));
    }

    @GetMapping(value = "hienThiQuyenHan")
    public ResponseEntity<Page<QuyenHan>> hienThi(Integer var, Integer size) {
        if (var == null) var = 0;
        if (size == null) size = 10;
        Pageable pageable = PageRequest.of(var, size);
        return ResponseEntity.ok(quyenHanSer.hienThi(pageable));
    }

}
