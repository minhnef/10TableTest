package com.example.quanlykhoahoc.repository;

import com.example.quanlykhoahoc.entity.TinhTrangHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Repository
public interface ITinhTrangHocRepo extends JpaRepository<TinhTrangHoc, Integer> {
    @Query(value = "select * from tinhtranghoc", nativeQuery = true)
    public Page<TinhTrangHoc> findAll(Pageable pageable);
}
