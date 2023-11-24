package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class filterCriteria {
    private Integer authorid;
    private Integer publishersid;
    private Integer categoriesid;
    private String booknamekeyword;

    public void clear() {
        this.authorid = null;
        this.publishersid = null;
        this.categoriesid = null;
        this.booknamekeyword = null;
    }
}
