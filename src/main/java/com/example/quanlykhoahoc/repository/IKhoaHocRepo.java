package com.example.quanlykhoahoc.repository;

import com.example.quanlykhoahoc.entity.KhoaHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IKhoaHocRepo extends JpaRepository<KhoaHoc, Integer> {
    @Query(value = "select * from khoahoc", nativeQuery = true)
    public Page<KhoaHoc> findAllNews(Pageable page);

    public Page<KhoaHoc> findAllByTenKH(Pageable page, String tenKH);
}
