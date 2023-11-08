package com.example.quanlykhoahoc.repository;

import com.example.quanlykhoahoc.entity.LoaiBaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoaiBaiVietRepo extends JpaRepository<LoaiBaiViet, Integer> {
    @Query(value = "select * from loaibaiviet", nativeQuery = true)
    public Page<LoaiBaiViet> findAllNews(Pageable pageable);
}
