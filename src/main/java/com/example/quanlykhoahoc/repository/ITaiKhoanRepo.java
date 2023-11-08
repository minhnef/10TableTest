package com.example.quanlykhoahoc.repository;

import com.example.quanlykhoahoc.entity.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaiKhoanRepo extends JpaRepository<TaiKhoan, Integer> {
    @Query(value = "select * from taikhoan", nativeQuery = true)
    public Page<TaiKhoan> findAllNews(Pageable pageable);
}
