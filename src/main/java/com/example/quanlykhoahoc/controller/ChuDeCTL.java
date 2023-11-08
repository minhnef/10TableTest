package com.example.quanlykhoahoc.controller;

import com.example.quanlykhoahoc.entity.ChuDe;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.service.ChuDeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "apiChuDe")
public class ChuDeCTL {
    @Autowired
    ChuDeSer chuDeSer;

    @PostMapping(value = "themChuDe")
    public ResponseEntity<?> them(@RequestBody Request<ChuDe> request){
        return ResponseEntity.ok(chuDeSer.them(request));
    }

    @PutMapping(value = "suaChuDe")
    public ResponseEntity<?> sua(@RequestBody Request<ChuDe> request){
        return ResponseEntity.ok(chuDeSer.sua(request));
    }

    @DeleteMapping(value = "xoaChuDe")
    public ResponseEntity<?> xoa(@RequestParam int id){
        return ResponseEntity.ok(chuDeSer.xoa(id));
    }

    @GetMapping(value = "hienThiChuDe")
    public ResponseEntity<?> hienThi(Integer var, Integer size){
        if(var == null) var = 0;
        if(size == null) size = 10;
        Pageable pageable = PageRequest.of(var, size);
        return ResponseEntity.ok(chuDeSer.hienThi(pageable));
    }
}
