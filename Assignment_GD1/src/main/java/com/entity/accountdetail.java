package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class accountdetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer accountdetailid;
    @JsonIgnore
	@OneToMany(mappedBy = "accountdetail")
	List<accounts> accounts;
    String fullname;
    String phonenum;
    String address;
    String email;
    Boolean gender;
    String image;
}
