package com.example.quanlykhoahoc.repository;

import com.example.quanlykhoahoc.entity.DangKyHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDangKyHocRepo extends JpaRepository<DangKyHoc, Integer> {
    @Query(value = "select  * from dangkyhoc", nativeQuery = true)
    public Page<DangKyHoc> findAll(Pageable pageable);


}
