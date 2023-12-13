package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.bname;
import com.entity.books;

import java.util.List;


public interface bnameDAO extends JpaRepository<bname, Integer> {
    @Query("select b from books b join b.bname bn where bn.bookname like %?1%")
    List<books> findByBookname(String bookname);
}
