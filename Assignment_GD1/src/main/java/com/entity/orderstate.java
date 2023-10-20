package com.entity;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class orderstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderstateid;
    String orderstatename;
    @JsonIgnore
    @OneToMany
    List<retails> retails;
}
