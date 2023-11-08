package com.example.quanlykhoahoc.controller;

import com.example.quanlykhoahoc.entity.TinhTrangHoc;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.service.TinhTrangHocSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "apiTinhTrangHoc")
public class TinhTrangHocCTL {
    @Autowired
    TinhTrangHocSer tinhTrangHocSer;

    @PostMapping(value = "themTinhTrangHoc")
    public ResponseEntity<?> them(@RequestBody Request<TinhTrangHoc> request){
        return ResponseEntity.ok(tinhTrangHocSer.them(request));
    }

    @PutMapping(value = "suaTinhTrangHoc")
    public ResponseEntity<?> sua(@RequestBody Request<TinhTrangHoc> request){
        return ResponseEntity.ok(tinhTrangHocSer.sua(request));
    }

    @DeleteMapping(value = "xoaTinhTrangHoc")
    public ResponseEntity<?> xoa(@RequestParam int id){
        return ResponseEntity.ok(tinhTrangHocSer.xoa(id));
    }

    @GetMapping(value = "hienThiTinhTrangHoc")
    public ResponseEntity<?> hienThi(Integer var, Integer size){
        if(var == null) var = 0;
        if(size == null) size =5;
        Pageable pageable = PageRequest.of(var, size);
        return ResponseEntity.ok(tinhTrangHocSer.hienThi(pageable));
    }
}
