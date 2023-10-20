package com.entity;

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
public class accountdetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer accountdetailid;
    @ManyToOne
    @JoinColumn(name = "username")
    accounts accounts;
    String fullname;
    String phonenum;
    String address;
    String email;
    Boolean gender;
    String image;
}
