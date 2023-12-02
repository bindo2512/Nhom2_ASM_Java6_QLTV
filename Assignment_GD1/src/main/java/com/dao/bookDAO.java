package com.dao;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import com.entity.books;

public interface bookDAO extends JpaRepository<books, Integer>, JpaSpecificationExecutor<books>{
    List<books> findByBname(String bname);


    @Modifying
    @Query("select count(*) as number_of_added, MONTH(b.createdate) from books b group by MONTH(b.createdate)")
    List<Object[]> countCreatedBookByMonth();

    @Modifying
    @Query("select count(*) as number_of_book, b.hardcover from books b group by b.hardcover")
    List<Object[]> countBookByCover();

    @Modifying
    @Query("select count(*) as number_of_book, a.authorname from books b join b.authors a group by a.authorname")
    List<Object[]> countBookByAuthorname();
}
