package com.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class history {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY.IDENTITY)
    Integer historyid;
    @ManyToOne
    @JoinColumn(name = "bookid")
    books books;
    @ManyToOne
    @JoinColumn(name = "username")
    accounts accounts;
    Date historydate;
}
