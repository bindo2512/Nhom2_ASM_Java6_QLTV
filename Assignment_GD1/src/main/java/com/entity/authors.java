package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer authorid;
    @NotNull(message = "Bạn chưa nhập tên tác giả")
    String authorname;
    String authorimage;
    String authordescription;
    @NotNull(message = "Bạn chưa nhập năm sinh của tác giả")
    Integer authorbirth;
    @JsonIgnore
    @OneToMany(mappedBy = "authors")
    List<books> books;
}
