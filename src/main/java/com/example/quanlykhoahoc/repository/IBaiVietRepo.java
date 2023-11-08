package com.example.quanlykhoahoc.repository;

import com.example.quanlykhoahoc.entity.BaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBaiVietRepo extends JpaRepository<BaiViet, Integer> {
    @Query(value = "select * from baiviet", nativeQuery = true)
    public Page<BaiViet> findAllNews(Pageable pageable);
}
