package com.example.quanlykhoahoc.controller;

import com.example.quanlykhoahoc.entity.BaiViet;
import com.example.quanlykhoahoc.entity.request.Request;
import com.example.quanlykhoahoc.service.BaiVietSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping(value = "apiBaiViet")
public class BaiVietCTL {
    @Autowired
    BaiVietSer baiVietSer;

    @PostMapping(value = "themBaiViet")
    public ResponseEntity<?> them(@RequestBody Request<BaiViet> request){
        return ResponseEntity.ok(baiVietSer.them(request));
    }

    @PutMapping(value = "suaBaiViet")
    public ResponseEntity<?> sua(@RequestBody Request<BaiViet> request){
        return ResponseEntity.ok(baiVietSer.sua(request));
    }

    @DeleteMapping(value = "xoaBaiViet")
    public ResponseEntity<?> xoa(@RequestParam int id){
        return ResponseEntity.ok(baiVietSer.xoa(id));
    }

    @GetMapping(value = "hienThiBaiViet")
    public ResponseEntity<?> hienThi(Integer var, Integer size){
        if(var == null) var = 0;
        if(size == null) size = 10;
        PageRequest pageable =  PageRequest.of(var, size);
        return  ResponseEntity.ok(baiVietSer.hienThi(pageable));
    }


}
