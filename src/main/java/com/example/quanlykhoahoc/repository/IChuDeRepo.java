package com.example.quanlykhoahoc.repository;

import com.example.quanlykhoahoc.entity.ChuDe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IChuDeRepo extends JpaRepository<ChuDe, Integer> {
    @Query(value = "select * from chude", nativeQuery = true)
    public Page<ChuDe> findAllNews(Pageable pageable);
}
