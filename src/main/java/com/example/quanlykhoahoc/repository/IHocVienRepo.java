package com.example.quanlykhoahoc.repository;

import com.example.quanlykhoahoc.entity.HocVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IHocVienRepo extends JpaRepository<HocVien, Integer> {
    @Query(value = "select * from hocvien", nativeQuery = true)
    public Page<HocVien> findAllNews(Pageable pageable);

    public HocVien findAllByHoTenAndEmailEquals(String hoTen, String email);
}
