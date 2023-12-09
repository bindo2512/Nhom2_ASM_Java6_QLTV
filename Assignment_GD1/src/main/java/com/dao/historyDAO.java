package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.history;

public interface historyDAO extends JpaRepository<history, Integer> {
    @Modifying
    @Query("select count(*) as number_of_read, bn.bookname from history h join h.books b join b.bname bn group by bn.bookname")
    List<Object[]> countNumberOfReadByBook();
}
