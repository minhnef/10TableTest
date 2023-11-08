package com.example.quanlykhoahoc.repository;

import com.example.quanlykhoahoc.entity.LoaiKhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoaiKhoaHocRepo extends JpaRepository<LoaiKhoaHoc, Integer> {
}
