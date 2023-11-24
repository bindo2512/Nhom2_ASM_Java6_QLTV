package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "details")
public class details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer detailid;
    
    @ManyToOne
    @JoinColumn(name = "retailid")
    retails retails;

    @ManyToOne
    @JoinColumn(name = "bookid")
    books books;


}
