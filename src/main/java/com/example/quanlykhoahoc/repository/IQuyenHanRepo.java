package com.example.quanlykhoahoc.repository;

import com.example.quanlykhoahoc.entity.QuyenHan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuyenHanRepo extends JpaRepository<QuyenHan, Integer> {

    @Query(value = "select * from quyenhan", nativeQuery = true)
    public Page<QuyenHan> findAllNews(Pageable pageable);
}
