package com.example.quanlykhoahoc.controller;

import com.example.quanlykhoahoc.entity.LoaiBaiViet;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.service.LoaiBaiVietSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "apiLoaiBaiViet")
public class LoaiBaiVietCTL {
    @Autowired
    LoaiBaiVietSer loaiBaiVietSer;

    @PostMapping(value = "themLoaiBaiViet")
    public ResponseEntity<?> them(@RequestBody Request<LoaiBaiViet> request){
        return ResponseEntity.ok(loaiBaiVietSer.them(request));
    }

    @PutMapping(value = "suaLoaiBaiViet")
    public ResponseEntity<?> sua(@RequestBody Request<LoaiBaiViet> request){
        return ResponseEntity.ok(loaiBaiVietSer.sua(request));
    }

    @DeleteMapping(value = "xoaLoaiBaiViet")
    public ResponseEntity<?> xoa(@RequestParam int id){
        return ResponseEntity.ok(loaiBaiVietSer.xoa(id));
    }

    @GetMapping(value = "hienThiLoaiBaiViet")
    public ResponseEntity<?> hienThi(Integer var, Integer size){
        if(var == null) var = 0;
        if(size == null) size = 10;
        Pageable pageable = PageRequest.of(var, size);
        return ResponseEntity.ok(loaiBaiVietSer.hienThi(pageable));
    }
}
